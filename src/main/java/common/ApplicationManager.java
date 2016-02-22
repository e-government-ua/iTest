package common;

import entities.Browser;
import helpers.BaseHelper;
import helpers.NavHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import pages.BasePage;
import pages.main.*;
import pages.service.authorities.interaction.AppealToHeadOfRegion;
import pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import pages.service.authorities.interaction.LandSizeAndExistencePage;
import pages.service.authorities.interaction.SubsidyPage;
import pages.service.identity.citizenship.residense.InternationalPassportPage;
import pages.service.identity.citizenship.residense.UnregisterFromLocationPage;
import pages.service.police.traffic.CriminalRecordPage;
import pages.service.police.traffic.RegisterUsedCarPage;
import pages.service.taxes.CertificateFromUnifiedRegisterPage;
import pages.service.taxes.PensionAmountCertificatePage;
import pages.service.taxes.PersonalIncomeCertificatePage;
import pages.service.test.*;
import utils.PropertyLoader;
import utils.WebDriverFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ApplicationManager {

    // Variables

    protected static final Logger log = LogManager.getLogger(ApplicationManager.class);

    public static WebDriver driver;
    private static WebDriverWait wait;
    private static String baseUrl;
    // Pages
    public MainPage mainPage;
    public BankIdPage bankIdPage;
    public DocumentsPage documentsPage;
    public MyJournalPage journalPage;
    public AboutPortalPage aboutPortalPage;
    public SelectAreaPage selectAreaPage;
    public Header header;
    public Footer footer;
    public StatusPage statusPage;
    public SubsidyPage subsidyPage;
    public CriminalRecordPage criminalRecordPage;
    public InternationalPassportPage internationalPassportPage;
    public TestDependenceFormPage testDependenceFormPage;
    public TestFieldsBankidPage testFieldsBankidPage;
    public TestLiqpayPage testLiqpayPage;
    public TestMailerPage testMailerPage;
    public TestZPCnapMailerPage testZPCnapMailerPage;
    public StatisticTab statisticTab;
    public UnregisterFromLocationPage unregisterFromLocationPage;
    public AssignSocialAssistanceForChildBirthPage assignSocialAssistanceForChildBirthPage;
    public PersonalIncomeCertificatePage personalIncomeCertificatePage;
    public PensionAmountCertificatePage pensionAmountCertificatePage;
    public CertificateFromUnifiedRegisterPage certificateFromUnifiedRegisterPage;
    public RegisterUsedCarPage registerUsedCarPage;
    public LandSizeAndExistencePage landSizeAndExistencePage;
    public ModalDialog modalDialog;
    public AppealToHeadOfRegion appealToHeadOfRegion;
    // Helpers
    public NavHelper navHelper;


    // Methods

    public ApplicationManager() {

        // Get properties (from application.properties file)
        Browser browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");
        String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        baseUrl = PropertyLoader.loadProperty("base.url");

        // Create browser (using given properties) and maximize it
        driver = WebDriverFactory.getInstance(gridHubUrl, browser, username, password);
        driver.manage().window().maximize();

        // Setup implicit and explicit waits
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);

        // Setup links (to ApplicationManager, driver, wait) to BasePage and BaseHelper
        BasePage.setUp(this);
        BaseHelper.setUp(this);

        // Create pages objects
        mainPage = new MainPage();
        bankIdPage = new BankIdPage();
        documentsPage = new DocumentsPage();
        journalPage = new MyJournalPage();
        aboutPortalPage = new AboutPortalPage();
        selectAreaPage = new SelectAreaPage();
        header = new Header();
        footer = new Footer();
        statusPage = new StatusPage();
        subsidyPage = new SubsidyPage();
        criminalRecordPage = new CriminalRecordPage();
        registerUsedCarPage = new RegisterUsedCarPage();
        internationalPassportPage = new InternationalPassportPage();
        testDependenceFormPage = new TestDependenceFormPage();
        testFieldsBankidPage = new TestFieldsBankidPage();
        testLiqpayPage = new TestLiqpayPage();
        testMailerPage = new TestMailerPage();
        testZPCnapMailerPage = new TestZPCnapMailerPage();
        statisticTab = new StatisticTab();
        unregisterFromLocationPage = new UnregisterFromLocationPage();
        assignSocialAssistanceForChildBirthPage = new AssignSocialAssistanceForChildBirthPage();
        personalIncomeCertificatePage = new PersonalIncomeCertificatePage();
        landSizeAndExistencePage = new LandSizeAndExistencePage();
        pensionAmountCertificatePage = new PensionAmountCertificatePage();
        certificateFromUnifiedRegisterPage = new CertificateFromUnifiedRegisterPage();
        modalDialog = new ModalDialog();
        appealToHeadOfRegion = new AppealToHeadOfRegion();

        // Create helpers objects
        navHelper = new NavHelper();
    }

    // Method for getting driver
    public WebDriver getDriver() {
        return driver;
    }

    // Method for getting wait
    public WebDriverWait getWait() {
        return wait;
    }

    // Method for getting BaseURL
    public String getBaseUrl() {
        return baseUrl;
    }

    // Method for adding red test name to report
    public static void addErrorToTheReport(String testName) {
        Reporter.log("<b><font color=\"red\" size=\"3\">" + testName + "</font></b><br>");
    }

    // Method for making a pause
    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method for file attachment
    public ApplicationManager attachDocument(WebElement locator, String document) {
        String script = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(script, locator);

        File file = new File(document);
        locator.sendKeys(file.getAbsolutePath());

        // Wait attach upload
        //TODO: add counter condition to avoid infinite loop
        while (!locator.isEnabled()) {
            try {
                log.info("Wait for file uploaded..");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}