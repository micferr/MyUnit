# MyUnit

Source-only JUnit-like Java testing framework. 

## Usage

Just add the files to the root of your project's source tree. See package `com.myunit.selftest` for a concrete use-case example.

## Continuous Integration

Continuous Integration software can be easily configured to work with MyUnit.

Following are instructions for Jenkins (with Gradle). It is assumed that the project already uses SCM and a Jenkins Project has been created.

1. Go to the Jenkins Project's settings page. The page should be located at `[yourJenkinsProjectPage]/configure`.
2. Under `Source Code Management`, choose your SCM system, add the URL of the repository on which the project is hosted and, if needed, access credentials.
3. Under `Build Triggers`, select `Poll SCM` and, in the `Schedule` text field, write how often the repo must be polled to check for updates (It's a cron expression).
4. Under `Build`, select `Add build step` and then `Execute Gradle script`. In the resulting tab, select `Invoke Gradle` and choose your Gradle version. In `Tasks`, paste the following:
    
    ```
    compileJavaClasses
    runTestSuite
    generateJavadocs
    ```
    
    Finally, in `Build File`, just write `build.gradle`. 
The provided build.gradle will work with minimal changes (you just have to change `runTestSuite`'s `main` attribute).
You can of course use your custom build script should you so prefer.

5. In `Post-build Actions`, select `Publish JUnit test result report`. In the resulting tab, write the location of the test result XML file under `Test report XMLs`, it must match the location specified your code. Then choose a `Health report amplification factor` (1.0 is fine).
Remember that, in order to publish test results, you have to use a `JUnitXMLLogger ` in your test code.
6. In `Post-build Actions`, select `Publish Javadocs`. In the resulting tab, choose the `Javadoc directory`. It must match `generateJavadocs`'s `destinationDir` attribute in the Gradle build script.
7. All done!