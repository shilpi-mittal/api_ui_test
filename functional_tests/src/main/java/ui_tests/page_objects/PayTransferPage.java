package ui_tests.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui_tests.utils.TestWebDriver;

import java.util.List;

import static org.openqa.selenium.support.How.*;

public class PayTransferPage extends Page {
    @FindBy(how = CSS, using = "[data-testid=\"from-account-chooser\"]")
    private static WebElement fromAccountChooser = null;

    @FindBy(how = CSS, using = "[data-testid=\"to-account-chooser\"]")
    private static WebElement toAccountChooser = null;

    private static String searchInputSelector = "[data-monitoring-label=\"Transfer Form Search\"]";

    @FindBy(how = CSS, using = "[data-monitoring-label=\"Transfer Form Search\"]")
    private static WebElement searchInput = null;

    @FindBy(how = CSS, using = "[data-testid=\"PaymentModal\"] ul li button[data-monitoring-label=\"Transfer Form Account Card\"]")
    private static List<WebElement> searchResults = null;

//    private static String resultItemButton = "";

    @FindBy(how = CSS, using = "[data-monitoring-label=\"Transfer Form Amount\"]")
    private static WebElement amountInput = null;

    @FindBy(how = CSS, using = "[data-monitoring-label=\"Transfer Form Submit\"]")
    private static WebElement submitButton = null;

    private static final Logger LOG = LoggerFactory.getLogger(PayTransferPage.class);

    public PayTransferPage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public void waitForPageToLoad() {
        testWebDriver.waitForElementToAppear(fromAccountChooser);
        LOG.info("Payees Page is loaded");
    }

    public boolean isPageDisplayed() {
        waitForPageToLoad();
        return testWebDriver.isDisplayed(fromAccountChooser);
    }

    public void selectAccount(String sender_name) {
        testWebDriver.waitForElementToAppear(searchInput);
        LOG.debug("Transfer Form is loaded");
        testWebDriver.enterInput(searchInput, sender_name);
        testWebDriver.clickOnElement(searchResults.get(0));
        testWebDriver.waitForElementToDisappear(By.cssSelector(searchInputSelector));
    }

    public void selectSenderAs(String sender_name) {
        testWebDriver.clickOnElement(fromAccountChooser);
        selectAccount(sender_name);
    }

    public void selectReceiverAs(String receiver_name) {
        testWebDriver.clickOnElement(toAccountChooser);
        selectAccount(receiver_name);
    }

    public void enterAmount(String amount) {
        testWebDriver.enterInput(amountInput, amount);
    }

    public void submitTransferDetails() {
        testWebDriver.clickOnElement(submitButton);
        LOG.debug("Transfer details submitted");
    }
}
