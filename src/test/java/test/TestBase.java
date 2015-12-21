package test;

import common.ApplicationManager;
import pages.service.taxes.PersonalIncomeCertificatePage;
import pages.service.test.TestMailerPage;
import test.portal.services.taxes.PersonalIncomeCertificate;
import utils.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.main.AboutPortalPage;
import pages.main.BankIdAuthorizationPage;
import pages.main.DocumentsPage;
import pages.main.MainPage;
import pages.main.MyJournalPage;
import pages.main.SelectAreaPage;
import pages.main.StatisticTab;
import pages.main.StatusPage;
import pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import pages.service.police.traffic.CriminalRecordPage;
import pages.service.identity.citizenship.residense.InternationalPassportPage;
import pages.service.authorities.interaction.SubsidyPage;
import pages.service.identity.citizenship.residense.UnregisterFromLocationPage;
import pages.service.test.TestDependenceFormPage;
import pages.service.test.TestFieldsBankidPage;
import pages.service.test.TestLiqpayPage;

@Listeners({ScreenshotListener.class})
public class TestBase {

    private static WebDriver driver;
    public static ApplicationManager app;
    public MainPage mainPage;
    public BankIdAuthorizationPage authorizationPage;
    public DocumentsPage documentsPage;
    public MyJournalPage journalPage;
    public AboutPortalPage portalPage;
    public SelectAreaPage selectAreaPage;
    public StatusPage statusPage;
    public SubsidyPage subsidyPage;
    public CriminalRecordPage criminalRecordPage;
    public InternationalPassportPage internationalPassportPage;
    public TestDependenceFormPage testDependenceFormPage;
    public TestFieldsBankidPage testFieldsBankidPage;
    public TestLiqpayPage testLiqpayPage;
    public TestMailerPage testMailerPage;
    public StatisticTab statisticTab;
    public UnregisterFromLocationPage unregisterFromLocationPage;
    public AssignSocialAssistanceForChildBirthPage assignSocialAssistanceForChildBirthPage;
    public PersonalIncomeCertificatePage personalIncomeCertificatePage;

    @BeforeSuite
    public void setUp() {
        driver = ApplicationManager.initDriver();
    }

    @BeforeMethod
    public void set() {
        app = new ApplicationManager();
        mainPage = new MainPage(driver);
        authorizationPage = new BankIdAuthorizationPage(driver);
        documentsPage = new DocumentsPage(driver);
        journalPage = new MyJournalPage(driver);
        portalPage = new AboutPortalPage(driver);
        selectAreaPage = new SelectAreaPage(driver);
        statusPage = new StatusPage(driver);
        subsidyPage = new SubsidyPage(driver);
        criminalRecordPage = new CriminalRecordPage(driver);
        internationalPassportPage = new InternationalPassportPage(driver);
        testDependenceFormPage = new TestDependenceFormPage(driver);
        testFieldsBankidPage = new TestFieldsBankidPage(driver);
        testLiqpayPage = new TestLiqpayPage(driver);
        testMailerPage = new TestMailerPage(driver);
        statisticTab = new StatisticTab(driver);
        unregisterFromLocationPage = new UnregisterFromLocationPage(driver);
        assignSocialAssistanceForChildBirthPage = new AssignSocialAssistanceForChildBirthPage(driver);
        personalIncomeCertificatePage = new PersonalIncomeCertificatePage(driver);
        driver.get(app.getBaseUrl());
    }

    @AfterClass
    public void clean() {
        driver.manage().deleteAllCookies();
    }

    @AfterSuite()
    public void tearsDown() {
        driver.quit();
    }
}