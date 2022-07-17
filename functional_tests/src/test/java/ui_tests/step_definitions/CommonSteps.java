package ui_tests.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import ui_tests.page_objects.HomePage;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.utils.TestCaseHelper;

public class CommonSteps extends TestCaseHelper {

    private static int count = 0;
    private static int count1 = 0;

    @Before
    public void setUp() {
        testWebDriver = super.setup();
    }

    @Given("^I am on Home Page$")
    public void goToHomePage() {
        HomePage homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.openApplication();
    }

    @After
    public void tearDown() {
        // tear down steps will go here
        testWebDriver.quitDriver();
        PageObjectFactory.clearAllPageObjectReferences();
    }
}
