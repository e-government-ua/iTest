package test;

import common.ApplicationManager;
import common.Constants;
import listener.ScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
    public ApplicationManager app;
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
    public StatisticTab statisticTab;
    public UnregisterFromLocationPage unregisterFromLocationPage;
    public AssignSocialAssistanceForChildBirthPage assignSocialAssistanceForChildBirthPage;

    @BeforeClass()
    public static void setUp() {
        driver = ApplicationManager.startTestsIn(Constants.Settings.BROWSER);
    }

    @AfterClass()
    public static void tearsDown() {
        driver.quit();
    }

    @BeforeMethod()
    public void set() {
        app = new ApplicationManager(driver);
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
        statisticTab = new StatisticTab(driver);
        unregisterFromLocationPage = new UnregisterFromLocationPage(driver);
        assignSocialAssistanceForChildBirthPage = new AssignSocialAssistanceForChildBirthPage(driver);
        driver.get(Constants.Server.VERSION_SERVER);
    }
}
