package ui_tests.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static org.openqa.selenium.support.How.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui_tests.utils.TestWebDriver;

public class PayeesPage extends Page {
    @FindBy(how = CLASS_NAME, using = "Payees")
    private static WebElement PayeesPageDiv = null;

    @FindBy(how = CLASS_NAME, using = "js-add-payee")
    private static WebElement addPayeeButton = null;

    @FindBy(how = CLASS_NAME, using = "js-submit")
    private static WebElement submitButton = null;

    private static String payeeNameInputSelector = ".payee-selector-row .inputs input";

    @FindBy(how = CSS, using = ".payee-selector-row .inputs input")
    private static WebElement payeeNameInput = null;

    @FindBy(how = ID, using = "apm-form")
    private static WebElement payeeInputForm = null;

    private static String errorHeaderSelector = "error-header";

    @FindBy(how = CLASS_NAME, using = "error-header")
    private static WebElement errorHeader = null;

    private static String payeeNameErrorSelector = ".payee-selector-row .error-arrow";

    @FindBy(how = CSS, using = ".payee-selector-row .error-arrow")
    private static WebElement payeeNameError = null;

    @FindBy(how = CLASS_NAME, using = "bank")
    private static WebElement bankInputField = null;//2

    @FindBy(how = CLASS_NAME, using = "branch")
    private static WebElement branchInputField = null;//4

    @FindBy(how = CLASS_NAME, using = "account-number")
    private static WebElement accountNumberInputField = null;//7

    @FindBy(how = CLASS_NAME, using = "suffix")
    private static WebElement suffixInputField = null;//3

    @FindBy(how = CSS, using = "#notification .message")
    private static WebElement notificationMessage = null;

    @FindBy(how = CSS, using = ".List li")
    private static List<WebElement> payees = null;

    private static String payeeNameInListPath = "js-payee-name";

    @FindBy(how = CLASS_NAME, using = "js-payee-name-column")
    private static WebElement nameColumn = null;

    private static final Logger LOG = LoggerFactory.getLogger(PayeesPage.class);

    public PayeesPage(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public void waitForPageToLoad() {
        testWebDriver.waitForElementToAppear(PayeesPageDiv);
        LOG.info("Payees Page is loaded");
    }

    public boolean isPageDisplayed() {
        waitForPageToLoad();
        return testWebDriver.isDisplayed(PayeesPageDiv);
    }

    public void clickOnAddPayeeButton() {
        testWebDriver.clickOnElement(addPayeeButton);
        testWebDriver.waitForElementToAppear(payeeNameInput);
        LOG.debug("Add Payees form is loaded");
    }

    public void clickOnSubmitPayeeButton() {
        testWebDriver.clickOnElement(submitButton);
    }

    public void clickOnPayeesNameColumn() {
        testWebDriver.clickOnElement(nameColumn);
        LOG.debug("Payees list is sorted by Name");
    }

    public void enterPayeeName(String name) {
        testWebDriver.enterInput(payeeNameInput, name);
        testWebDriver.clickOnElement(payeeInputForm);
    }

    public void enterAccountNumber(String accountNumber) {
        testWebDriver.enterInput(bankInputField, accountNumber);
    }

    public boolean isNotificationDisplayed() {
        testWebDriver.waitForElementToAppear(notificationMessage);
        return testWebDriver.isDisplayed(notificationMessage);
    }

    public String getNotificationMessage() {
        testWebDriver.waitForElementToAppear(notificationMessage);
        return testWebDriver.getText(notificationMessage);
    }

    public boolean payeesListContains(String expectedName) {
        for (WebElement payee : payees) {
            WebElement payeeName = payee.findElement(By.className(payeeNameInListPath));
            if (payeeName.getText().equals(expectedName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isErrorMessageDisplayed() {
        testWebDriver.waitForElementToAppear(errorHeader);
        return testWebDriver.isDisplayed(errorHeader);
    }

    public boolean hasErrorMessageDisappeared() {
        testWebDriver.sleep(10);
        testWebDriver.waitForElementToDisappear(By.className(errorHeaderSelector));
        return testWebDriver.findElements(By.className(errorHeaderSelector)).isEmpty();
    }

    public boolean isErrorIconDisplayed() {
        testWebDriver.waitForElementToAppear(payeeNameError);
        return testWebDriver.isDisplayed(payeeNameError);
    }

    public boolean hasErrorIconDisappeared() {
        testWebDriver.waitForElementToDisappear(By.cssSelector(payeeNameErrorSelector));
        return testWebDriver.findElements(By.cssSelector(payeeNameErrorSelector)).isEmpty();
    }

    public String getErrorMessage() {
        testWebDriver.waitForElementToAppear(errorHeader);
        return testWebDriver.getText(errorHeader);
    }

    public String getSortingOrderOfPayeeNames() {
        testWebDriver.waitForElementToAppear(payees.get(0));
        boolean isAscending = false;
        int flagFlipCounter = 0;

        for (int i = 0; i < payees.size() - 1; i++) {
            String payeeName1 = payees.get(i).findElement(By.className(payeeNameInListPath)).getText().toLowerCase();
            String payeeName2 = payees.get(i + 1).findElement(By.className(payeeNameInListPath)).getText().toLowerCase();
            if (payeeName1.compareTo(payeeName2) > 0) {
                // desc
                if (isAscending) {
                    isAscending = false;
                    flagFlipCounter++;
                }
            } else if (payeeName1.compareTo(payeeName2) < 0) {
                //asc
                if (!isAscending) {
                    isAscending = true;
                    flagFlipCounter++;
                }
            }
        }
        if (flagFlipCounter <= 1) {
            if (isAscending) {
                return "ascending";
            } else {
                return "descending";
            }
        }
        return "not in order";
    }
}
