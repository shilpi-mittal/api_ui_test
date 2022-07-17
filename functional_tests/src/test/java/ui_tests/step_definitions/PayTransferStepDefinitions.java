package ui_tests.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ui_tests.page_objects.HomePage;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.page_objects.PayTransferPage;
import ui_tests.utils.TestCaseHelper;

public class PayTransferStepDefinitions extends TestCaseHelper {
    HomePage homePage;
    PayTransferPage payTransferPage;
    String senderName;
    String receiverName;
    double senderInitialBalance;
    double receiverInitialBalance;
    double amountTransferred;

    @When("I navigate to Payments page")
    public void navigateToPaymentsPage() {
        payTransferPage = homePage.goToPayTransfers();
    }

    @And("I transfer {string}")
    public void transfer(String amount) {
        payTransferPage.selectSenderAs(senderName);
        payTransferPage.selectReceiverAs(receiverName);
        payTransferPage.enterAmount(amount);
        amountTransferred = Double.parseDouble(amount.replace(",", ""));
        payTransferPage.submitTransferDetails();
    }

    @Then("I should see {string} message as notification on home page")
    public void validateNotificationMessageOnHomePage(String message) {
        assertTrue("\n Notification is not displayed,\n", homePage.isNotificationDisplayed());
        assertEquals("\n Notification message is not as expected,\n", homePage.getNotificationMessage(), message);
    }

    @And("balances should be updated for both accounts")
    public void validateBalancesAreUpdatedForBothAccounts() {
        double senderFinalBalance = Double.parseDouble(homePage.getAccountBalance(senderName).replace(",", ""));
        double receiverFinalBalance = Double.parseDouble(homePage.getAccountBalance(receiverName).replace(",", ""));
        assertEquals("\nSender balance is not as expected,\n",
                (senderInitialBalance - amountTransferred), senderFinalBalance, 0.00);
        assertEquals("\nReceiver balance is not as expected,\n",
                (receiverInitialBalance + amountTransferred), receiverFinalBalance, 0.00);
    }

    @And("I check balances for")
    public void getBalancesFor(DataTable table) {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        List<String> data = table.asLists(String.class).get(1);
        senderName = data.get(0);
        receiverName = data.get(1) + " ";
        senderInitialBalance = Double.parseDouble(homePage.getAccountBalance(senderName).replace(",", ""));
        receiverInitialBalance = Double.parseDouble(homePage.getAccountBalance(receiverName).replace(",", ""));
    }
}
