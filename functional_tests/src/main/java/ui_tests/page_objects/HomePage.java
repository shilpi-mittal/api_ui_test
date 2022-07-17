package ui_tests.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

import static org.openqa.selenium.support.How.*;

public class HomePage extends Page {
    @FindBy(how = ID, using = "YouMoney")
    private static WebElement homePageDiv = null;

    @FindBy(how = CLASS_NAME, using = "MenuButton")
    private static WebElement menuButton = null;

    @FindBy(how = CSS, using = ".js-main-menu-payees a")
    private static WebElement payeesOption = null;

    @FindBy(how = CSS, using = ".js-main-menu-paytransfer button")
    private static WebElement payTransferButton = null;

    @FindBy(how = CSS, using = "#notification .message")
    private static WebElement notificationMessage = null;

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

    public HomePage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public void openApplication() {
        testWebDriver.goToBaseUrl();
        testWebDriver.waitForElementToAppear(homePageDiv);
        testWebDriver.waitForElementToAppear(menuButton);
        LOG.info("Application is open");
    }

    public void clickOnMenu() {
        testWebDriver.clickOnElement(menuButton);
    }

    public PayeesPage clickOnPayeesOptionInMenu() {
        testWebDriver.clickOnElement(payeesOption);
        LOG.info("Payees Page is loading");
        return PageObjectFactory.getPayeesPage(testWebDriver);
    }

    public PayTransferPage goToPayTransfers() {
        testWebDriver.clickOnElement(menuButton);
        testWebDriver.clickOnElement(payTransferButton);
        LOG.info("Pay Transfer Page is loading");
        return PageObjectFactory.getPayTransferPage(testWebDriver);
    }

    public boolean isNotificationDisplayed() {
        testWebDriver.waitForElementToAppear(notificationMessage);
        return testWebDriver.isDisplayed(notificationMessage);
    }

    public String getNotificationMessage() {
        testWebDriver.waitForElementToAppear(notificationMessage);
        return testWebDriver.getText(notificationMessage);
    }

    public String getAccountBalance(String accountName) {
        WebElement element = testWebDriver.findElement(By.xpath("//h3[@title=\"" + accountName +
                "\"]/../..//span[@class=\"account-balance\"]"));
        testWebDriver.waitForElementToAppear(element);
        return element.getText();
    }
}
