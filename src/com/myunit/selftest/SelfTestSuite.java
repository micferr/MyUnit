package com.myunit.selftest;

import com.myunit.log.JUnitXMLLogger;
import com.myunit.log.gui.GuiLogger;
import com.myunit.test.*;

/**
 * The class in charge of the framework self-testing. Executing its main method starts the testing.
 * When the testing is finished, a report is saved in JUnit XML format, such as to be compatible
 * with test reporting frameworks such as Jenkins
 *
 * @see TestRunner
 * @see com.myunit.log.Logger
 */
public class SelfTestSuite {
    /**
     * Specifies SelfTestSuite's running mode
     */
    enum TestMode {
        STANDARD,
        GUI
    }

    /**
     * The main method to execute to start testing
     *
     * @param args      testing params, currently unused
     */
    public static void main(String[] args){
        Class[] testClasses = new Class[] {
                TestAssertMiscellaneous.class,
                TestAssertTrueFalse.class,
                TestAssertEquals.class,
                TestAssertNotEquals.class,
                TestAssertGreaterThan.class,
                TestAssertLessThan.class,
                TestAssertGreaterThanOrEquals.class,
                TestAssertLessThanOrEquals.class,
                TestNullNotNull.class
        };
        TestMode testMode = TestMode.STANDARD;
        switch (testMode) {
            case STANDARD:
                new TestRunner(
                        new JUnitXMLLogger("log.xml").openLogAfterTests(false)
                ).run(testClasses);
                break;
            case GUI:
                new GuiLogger(testClasses).run(args);
                break;
            default: break;
        }
    }
}
