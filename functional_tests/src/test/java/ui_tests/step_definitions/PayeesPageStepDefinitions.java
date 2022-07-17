package ui_tests.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import ui_tests.page_objects.HomePage;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.page_objects.PayeesPage;
import ui_tests.utils.TestCaseHelper;

public class PayeesPageStepDefinitions extends TestCaseHelper {
    HomePage homePage;
    PayeesPage payeesPage;

    @When("I click on Menu")
    public void clickOnMenu() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.clickOnMenu();
    }

    @And("I click on Payees option")
    public void clickOnPayeesOption() {
        payeesPage = homePage.clickOnPayeesOptionInMenu();
    }

    @Then("I should see Payees Page loaded")
    public void validatePayeesPageIsLoaded() {
        assertTrue("\n Payee Page is not as displayed,\n", payeesPage.isPageDisplayed());
    }

    @When("I navigate to Payees Page")
    public void navigateToPayeesPage() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.clickOnMenu();
        payeesPage = homePage.clickOnPayeesOptionInMenu();
        payeesPage.waitForPageToLoad();
    }

    @And("I click on Add button")
    public void clickOnAddButton() {
        payeesPage.clickOnAddPayeeButton();
    }

    @And("I enter payee name as {string}")
    public void enterPayeeName(String payeeName) {
        payeesPage.enterPayeeName(payeeName);
    }

    @And("I enter payee account number as {string}")
    public void enterPayeeAccountNumberAs(String accountNumber) {
        payeesPage.enterAccountNumber(accountNumber);
    }

    @And("I submit details")
    public void submitPayeeDetails() {
        payeesPage.clickOnSubmitPayeeButton();
    }

    @Then("I should see {string} message as notification")
    public void shouldSeePayeeAddedNotification(String message) {
        assertTrue("\n Notification is not displayed,\n", payeesPage.isNotificationDisplayed());
        assertEquals("\n Notification message is not as expected,\n", payeesPage.getNotificationMessage(), message);
    }

    @And("payee {string} should be added to the list on Payees page")
    public void payeeShouldBeListedOnPayeesPage(String payeeName) {
        assertTrue("\n Payee is not as present in list,\n", payeesPage.payeesListContains(payeeName));
    }

    @Then("I should see error as {string}")
    public void validateErrorMessage(String errorMessage) {
        assertTrue("\n Error Message is not displayed,\n", payeesPage.isErrorMessageDisplayed());
        assertEquals("\n Error message is not as expected,\n", payeesPage.getErrorMessage(), errorMessage);
    }

    @And("I should error icon on Payee name field")
    public void validateErrorIconOnPayeeNameField() {
        assertTrue("\n Error Icon is not displayed on Payee Name Input Field,\n", payeesPage.isErrorIconDisplayed());
    }

    @Then("error message disappears")
    public void validateErrorMessageDisappears() {
        assertTrue("\n Error Icon is not displayed on Payee Name Input Field,\n", payeesPage.hasErrorMessageDisappeared());
    }

    @And("error icon on payee name field disappears")
    public void validateErrorIconOnPayeeNameFieldDisappears() {
        assertTrue("\n Error Icon is not displayed on Payee Name Input Field,\n", payeesPage.hasErrorIconDisappeared());
    }

    @And("the list should be sorted in ascending order")
    public void validateListSortOrderIsaAscending() {
        assertEquals("\n Payees Names list is not ordered in ascending order,\n",
                payeesPage.getSortingOrderOfPayeeNames(), "ascending");
    }

    @Then("the list should be sorted in descending order")
    public void validateListSortOrderIsDescending() {
        assertEquals("\n Payees Names list is not ordered in descending order,\n",
                payeesPage.getSortingOrderOfPayeeNames(), "descending");
    }

    @When("I click on Name column")
    public void clickOnNameColumn() {
        payeesPage.clickOnPayeesNameColumn();
    }
}
