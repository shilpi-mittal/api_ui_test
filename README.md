## API_UI_TEST
<br/>This repository contains API and UI tests.
<br/>Currently it contains a module, named functional_tests, where all functional tests are kept.
<br/>It further contains a package, named api_tests, where all API tests go.
<br/>
<br/> The tests execute API calls and perform assertions on result. Gherkin is used to help provide scenarios in plain English level.
<br/> The scenarios are capable of accepting a table of test data, to avoid duplicate scenarios.
<br/> The tests write logs at different logging levels and generate test failure logs and messaging.
<br/> They also generate HTML report, named index.html.
<br/> Currently we are leveraging Cucumber team's free, cloud-based service for sharing reports throughout the organisation.

<br/>

### Prerequisite:
* Java 17
* IDE

### Tech Stack
* Java 17
* Gradle
* RestAssured
* Jackson
* Cucumber
* Git
* Logger slf4j

<br/>

### Commands to execute


######To build project:
./gradlew build

######To run all API tests:
./gradlew all_api_tests

######To run all API tests in parallel:
./gradlew all_api_tests -Dparallel=true

######To run API acceptance tests only:
./gradlew api_acceptance_tests

######To run API acceptance tests in parallel:
./gradlew api_acceptance_tests -Dparallel=true

######To run API tests by tag
./gradlew tagged_api_tests -Dtags=@acceptance_test
</br>./gradlew tagged_api_tests -Dtags="not @acceptance_test"

######To run tests in parallel, append
 -Dparallel=true (default is false)

######To run tests against a specified environment, append
 -Dtest_env=[local | qa] (default is local)

######To change the log level, append
 -Dtest_log_level=[ERROR | WARN | INFO | DEBUG | TRACE] (default is DEBUG)

<br/>

### Directory Structure
functional_tests/src:
* main/java/constants - Contains constants
* main/java/dto - Contains all DTOs to parse response JSON
* main/java/utils - Contains all utils/helpers
* main/resources - contains property files for different environments, and logback.xml
* test/java/api_tests.features - contains api tests feature files
* test/java/api_tests.step_definitions - contains step definitions for features in api_tests.features
* test/resources - contains cucumber properties

#### Assumptions made:
* 
<br/>

#### Next Steps:
* Add more API tests
* Add more test data to tests
* Add other types of tests
* Integrate with CI/CD pipeline