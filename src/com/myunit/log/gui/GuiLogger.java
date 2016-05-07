package com.myunit.log.gui;

import com.myunit.log.Logger;
import com.myunit.log.MultiLogger;
import com.myunit.test.TestRunner;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Displays the test results in a JavaFX GUI.
 *
 * This object is a Singleton, reset() has to be called in order
 * for the object to be reused. No guarantees concerning
 * thread-safety are given.
 */
public class GuiLogger extends Application implements Logger {
    private static MultiLogger externalLogger = null;
    private static Class[] testClasses = new Class[]{};
    private static volatile boolean initialized = false;
    /**
     * Signal whether the test execution has been interrupted,
     * e.g. by closing the test window.
     * Methods acting on the window have to check this flag
     * before doing so.
     */
    private static volatile boolean interrupted;

    /**
     * Total number of test methods
     */
    private int numTests;
    /**
     * Tests contained in current class
     */
    private int expectedClassTests;
    /**
     * Processed tests, not considering current class's tests
     */
    private int currentTestCount;
    /**
     * Processed tests, only considers current class's tests
     */
    private int currentClassTestCount;

    private ObservableList<TableRowData> tableRows;
    private TableView<TableRowData> resultTable;
    private TextArea textArea;
    private ProgressBar progressBar;
    private Transition progressBarTransition;
    private Thread testThread;

    public GuiLogger() {
        this(null, (Class[])null);
    }

    /**
     * @param testClasses The classes with the @Test-annotated methods to be tested
     */
    public GuiLogger(
            Class... testClasses
    ) {
        this(null,testClasses);
    }

    /**
     * @param loggers Loggers receiving the task of logging the test
     *                suite execution
     * @param testClasses The classes with the @Test-annotated methods to be tested
     */
    private GuiLogger(
            List<Logger> loggers,
            Class... testClasses
    ) {
        if (!initialized) {
            this.externalLogger = loggers != null ?
                    new MultiLogger(loggers) :
                    new MultiLogger();
            this.testClasses = testClasses != null ? testClasses : new Class[]{};
            initialized = true;
            interrupted = false;
            numTests = 0;
            expectedClassTests = 0;
            currentTestCount = 0;
            currentClassTestCount = 0;
        }
    }

    /**
     * Reset the Singleton GuiLogger, making it available
     * for reinitialization
     */
    public void reset() {
        externalLogger = null;
        testClasses = null;
        initialized = false;
        interrupted = false;
        try {
            testThread.join();
        } catch (InterruptedException e) {}
        testThread = null;
    }

    /**
     * Sets the external loggers
     *
     * @param loggers Loggers receiving the task of logging the test
     *                suite execution
     * @return the caller
     */
    public GuiLogger setLoggers(Logger... loggers) {
        if (initialized) {
            try {
                for (Logger l : loggers) {
                    Objects.requireNonNull(l);
                }
                externalLogger = new MultiLogger(loggers);
                return this;
            } catch (NullPointerException e) {
                throw new NullPointerException("Null logger set to a GuiLogger");
            }
        } else {
            throw new IllegalStateException("GuiLogger not initialized");
        }
    }

    /**
     * Starts the GUI logger and runs the tests
     *
     * @note Calling this method modifies Logging.setControlsLogger()'s
     *       log level from INFO to WARNING to avoid unnecessary INFO level
     *       logging (refer bug RT-40654)
     *
     * @param args Command-line application arguments
     */
    public void run(String[] args) {
        if (initialized)
            launch(args);
        else
            throw new IllegalStateException("GuiLogger not initialized");
    }

    /**
     * Starts the GuiLogger and test execution. Not to be explicitly
     * called - run has to be used instead
     *
     * @param primaryStage The main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Todo: check initialized and called from run

        makeOutputTable();

        TabPane classTabs = new TabPane();

        textArea = new TextArea();
        textArea.setEditable(false);

        progressBar = new ProgressBar();
        progressBar.setMinHeight(20);
        progressBar.setPrefWidth(800);

        TestRunner testRunner = startTestThread();

        VBox mainLayout = new VBox(resultTable, textArea, progressBar);
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            testThread.interrupt();
            testRunner.interrupt();
            interrupted = true;
        });
        primaryStage.show();
    }

    /**
     * Sets up the output TableView
     */
    private void makeOutputTable() {
        tableRows = FXCollections.observableArrayList();
        TableColumn<TableRowData, String> methodsColumn = new TableColumn<>("Test method");
        methodsColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getMethodName());
            } else {
                return new SimpleStringProperty("<no name>");
            }
        });
        methodsColumn.setPrefWidth(350);
        TableColumn<TableRowData, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getTestResult());
            } else {
                return new SimpleStringProperty("<no result>");
            }
        });
        resultColumn.setCellFactory(
                col -> new TableCell<TableRowData, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !interrupted) {
                            setText(item);
                            switch (item) {
                                case "Success":
                                    setStyle("-fx-background-color: \"0x00FF00\"");
                                    break;
                                case "Fail":
                                    setStyle("-fx-background-color: \"0xFF0000\"");
                                    break;
                                default:
                                    setStyle(""); // Needed for empty lines
                                    break;
                            }
                        }
                    }
                }
        );
        TableColumn<TableRowData, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setCellValueFactory(p -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getNotes());
            } else {
                return new SimpleStringProperty("");
            }
        });
        notesColumn.setPrefWidth(350);
        resultTable = new TableView<>(tableRows);
        resultTable.getColumns().setAll(methodsColumn, resultColumn, notesColumn);
        resultTable.getItems().addListener((ListChangeListener<TableRowData>) (c -> {
            c.next();
            scrollTableToBottom();
        }));
    }

    /**
     * Launches a test suite in another thread. The TestRunner is
     * returned as soon as it is started
     *
     * @return The TestRunner executing the tests
     */
    private TestRunner startTestThread() {
        TestRunner testRunner = new TestRunner(this) {
            @Override
            protected void testMethod(Object test, Method method) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {}
                super.testMethod(test, method);
            }
        };
        testThread = new Thread(() -> {
            testRunner.run(testClasses);
        });
        testThread.start();
        return testRunner;
    }

    /**
     * @throws IllegalStateException when called by an uninitialized GuiLogger
     */
    private void scrollTableToBottom() {
        Platform.runLater(()-> {
            if (isRunning()) {
                final int size = resultTable.getItems().size();
                if (size > 1) {
                    resultTable.scrollTo(size - 1);
                }
            } else {
                throw new IllegalStateException("Cannot scroll in an unitialized GuiLogger GUI");
            }
        });
    }

    private void scrollOutputLogToBottom() {
        Platform.runLater(() -> {
            if (isRunning()) {
                textArea.setScrollTop(Double.MAX_VALUE);
            }
        });
    }

    @Override
    public void log(String message) {
        Platform.runLater(() -> {
            if (isRunning()) {
                String logText = textArea.getText();
                textArea.appendText((logText.equals("") ? "" : "\n") + message);
                scrollOutputLogToBottom();
                externalLogger.log(message);
            }
        });
    }

    @Override
    public void logClassNumTests(int numTests) {
        this.expectedClassTests = numTests;
    }

    @Override
    public void logTotalNumTests(int numTests) {
        this.numTests = numTests;
    }

    @Override
    public void logExceptionRaised(Class testClass, Method method, Throwable errorCause) {

    }

    String currentMethodName;
    @Override
    public void logExecutingMethod(Method method) {
        currentMethodName = method.getName();
        log("Executing " + currentMethodName);
    }

    @Override
    public void logTestCaseSuccess() {
        putNewResultRow("Success", "");
    }

    @Override
    public void logTestCaseFail(Throwable error) {
        putNewResultRow(
                "Fail",
                error.getClass().getSimpleName() + " - " + error.getMessage()
        );
    }

    private void putNewResultRow(String result, String notes) {
        if (isRunning()) {
            Platform.runLater(() -> {
                tableRows.add(new TableRowData("\t" + currentMethodName, result, notes));
                currentClassTestCount++;
                setProgress(currentTestCount + currentClassTestCount, numTests);
            });
        }
    }

    @Override
    public void logSkipWholeTest(Class testClass, Throwable throwable) {
        if (isRunning()) {
            currentTestCount += expectedClassTests;
            expectedClassTests = 0;
            currentClassTestCount = 0;
            setProgress(currentTestCount, numTests);
        }
    }

    /**
     * Fills the bar up to (current/total) percent
     *
     * @param current Processed values
     * @param total Total values
     */
    private void setProgress(int current, int total) {
        Platform.runLater(() -> progressBar.setProgress(((double) current) / total));
    }

    @Override
    public void logTestBegin(Class testClass) {
        if (isRunning()) {
            Platform.runLater(() -> {
                tableRows.add(new TableRowData(testClass.getName(), "", ""));
            });
        }
    }

    @Override
    public void logTestEnd() {
    }

    @Override
    public void logSuiteResults(int passedTests, int failedTests) {
        log("\nTest suite finished."+
                "\nPassed: " + passedTests +
                "\nFailed: " + failedTests +
                "\nTotal: " + (passedTests+failedTests));
    }

    @Override
    public void endLog(boolean interrupted) {
        if (isRunning()) {
            scrollOutputLogToBottom();
        }
    }

    private boolean isRunning() {
        return initialized && !interrupted;
    }
}
