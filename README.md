## API_UI_TEST
<br/>This repository contains API and UI tests.
<br/>Currently it contains a module, named functional_tests, where all functional tests are kept.
<br/>It further contains a package, named api_tests, where all API tests go.
<br/>And it contains a package, named ui_tests, where all UI tests go.
<br/>
<br/> The tests execute API calls/UI actions and perform assertions on result. Gherkin is used to help provide scenarios in plain English level.
<br/> The scenarios are capable of accepting a table of test data, to avoid duplicate scenarios.
<br/> The tests write logs at different logging levels and generate test failure logs and messaging.
<br/> They also generate HTML report, named index.html.
<br/> Currently we are leveraging Cucumber team's free, cloud-based service for sharing reports throughout the organisation.

<br/>

### Prerequisite:
* Java 17
* IDE
* ChromeDriver
* FirefoxDriver

### Tech Stack
* Java 17
* Gradle
* RestAssured
* Jackson
* Cucumber
* Git
* Logger slf4j
* selenium-java
* webdrivermanager

<br/>

### Commands to execute


###### To build project:
./gradlew build

###### To run all UI acceptance tests:
./gradlew ui_acceptance_tests

###### To run all API acceptance tests only:
./gradlew api_acceptance_tests

###### To run all API acceptance tests in parallel:
./gradlew api_acceptance_tests -Dparallel=true

###### To run API tests by tag
./gradlew tagged_api_tests -Dtags=@acceptance_test
</br>./gradlew tagged_api_tests -Dtags="not @acceptance_test"

###### To run tests in parallel, append
 -Dparallel=true (default is false)

###### To run tests against a specified environment, append
 -Dtest_env=[local | qa] (default is local)

###### To change the log level, append
 -Dtest_log_level=[ERROR | WARN | INFO | DEBUG | TRACE] (default is DEBUG)

###### To change the browser for UI tests, append
 -Dtest_browser=[chrome | firefox] (default is chrome)

<br/>

### Directory Structure
functional_tests/src:
* main/java/constants - Contains common constants
* main/java/common_utils - Contains common common utils
* main/java/api_tests/dto - Contains all DTOs to parse response JSON
* main/java/api_tests/utils - Contains all api tests utils/helpers
* main/java/ui_tests/page_objects - Contains all page objects to interact with web UI
* main/java/ui_tests/utils - Contains all ui tests utils/helpers
* main/resources - contains property files for different environments, and logback.xml
* test/java/api_tests.features - contains api tests feature files
* test/java/api_tests.step_definitions - contains step definitions for features in api_tests.features
* test/java/ui_tests.features - contains ui tests feature files
* test/java/ui_tests.step_definitions - contains step definitions for features in ui_tests.features
* test/resources - contains cucumber properties

#### Next Steps:
* Add more API and UI tests
* Add more test data to tests
* Integrate with CI/CD pipeline