package ui_tests.page_objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

public class PageObjectFactory {
    private static Page instanceOfPage;
    private static HomePage instanceOfHomePage;
    private static PayeesPage instanceOfPayeesPage;
    private static PayTransferPage instanceOfPayTransferPage;

    private static final Logger LOG = LoggerFactory.getLogger(PageObjectFactory.class);

    public static Page getPage(TestWebDriver testWebDriver) {
        if (instanceOfPage == null) {
            LOG.debug("New Page instance is created");
            instanceOfPage = new Page(testWebDriver);
        }
        return instanceOfPage;
    }

    public static HomePage getHomePage(TestWebDriver testWebDriver) {
        if (instanceOfHomePage == null) {
            LOG.debug("New Home Page instance is created");
            instanceOfHomePage = new HomePage(testWebDriver);
        }
        return instanceOfHomePage;
    }

    public static PayeesPage getPayeesPage(TestWebDriver testWebDriver) {
        if (instanceOfPayeesPage == null) {
            LOG.debug("New Payees Page instance is created");
            instanceOfPayeesPage = new PayeesPage(testWebDriver);
        }
        return instanceOfPayeesPage;
    }

    public static PayTransferPage getPayTransferPage(TestWebDriver testWebDriver) {
        if (instanceOfPayTransferPage == null) {
            LOG.debug("New Payees Page instance is created");
            instanceOfPayTransferPage = new PayTransferPage(testWebDriver);
        }
        return instanceOfPayTransferPage;
    }

    public static void clearAllPageObjectReferences() {
        instanceOfPage = null;
        instanceOfHomePage = null;
        instanceOfPayeesPage = null;
        instanceOfPayTransferPage = null;
    }
}
