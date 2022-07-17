package api_tests.step_definitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import utils.TestEnvironmentUtils;

public class CommonSteps {

    @BeforeAll
    public static void setUp() {
        TestEnvironmentUtils.loadTestEnvProperties();
    }

    @Given("^I have access$")
    public void provideAccess() {
        // access related steps, like login, will go here
    }

    @AfterAll
    public static void tearDown() {
        // tear down steps will go here
    }
}
