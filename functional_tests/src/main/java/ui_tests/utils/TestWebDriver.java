package ui_tests.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import constants.EnvConstants;
import common_utils.TestEnvironmentUtils;

public class TestWebDriver {
    private static Duration DEFAULT_WAIT_TIME;
    private WebDriver webDriver;

    public TestWebDriver(WebDriver driver) {
        webDriver = driver;
        TestEnvironmentUtils.loadTestEnvProperties();
        DEFAULT_WAIT_TIME = Duration.ofSeconds(Long.parseLong(EnvConstants.DEFAULT_WAIT));
        maximizeWindows();
    }

    public void goToBaseUrl() {
        webDriver.manage().deleteAllCookies();
        navigateTo(EnvConstants.BASE_URL);
    }

    public void maximizeWindows() {
        webDriver.manage().window().maximize();
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public void waitForElementToAppear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final By locator) {
        new WebDriverWait(webDriver, DEFAULT_WAIT_TIME).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(final WebElement element, int waitTime) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(waitTime))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }

    public void enterInput(final WebElement element, String input) {
        waitForElementToAppear(element);
        element.clear();
        element.sendKeys(input);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    public void quitDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void navigateTo(String url) {
        webDriver.navigate().to(url);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        waitForElementToAppear(element);
        element.click();
    }

    public String getText(WebElement element) {
        waitForElementToAppear(element);
        return element.getText().trim();
    }
}
