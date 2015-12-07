package driverLogic;


import ServicePages.CriminalRecordPage;
import ServicePages.InternationalPassportPage;
import ServicePages.SubsidyPage;
import ServicePages.UnregisterFromLocationPage;
import TestServicePages.TestDependenceFormPage;
import TestServicePages.TestFieldsBankidPage;
import TestServicePages.TestLiqpayPage;
import appLogic.ApplicationManager;
import appLogic.Constants;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.io.File;
import java.util.Calendar;

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


    @BeforeClass()
    public static void setUp() {
        driver = ApplicationManager.startTestsIn(Constants.Settings.BROWSER);
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
        driver.get(Constants.Server.VersionSERVER);
    }

    @AfterClass()
    public static void tearsDown() {
        driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) throws Exception
    {
        //Для того чтобы передавать html теги и спец-символы в reporter.log
        //Или можно передать параметр в командную строку при выполнении TestNG:
        //-Dorg.uncommons.reportng.escape-output=false
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Reporter.setCurrentTestResult(result);

        //Create a directory; all non-existent ancestor directories are
        //automatically created
        boolean success = (new File("TestReport/html/Screens/")).mkdirs();
        if (!success) {
            //Directory creation failed
            //System.out.println("Directory creation failed. Папка уже создана?");
        }

        Calendar calendar = new ApplicationManager().getCurrentCalendar();
        String SuccsessLogMessage =
                "The test - \"" +
                        result.getMethod().getMethodName().toString() +
                        "\" was successfully ended" +
                        "(" +
                        calendar.get(Calendar.DATE) +
                        "." +
                        (calendar.get(Calendar.MONTH) + 1) +
                        "." +
                        calendar.get(Calendar.YEAR) +
                        " " +
                        calendar.get(Calendar.HOUR_OF_DAY) +
                        ":" +
                        calendar.get(Calendar.MINUTE) +
                        ":" +
                        calendar.get(Calendar.SECOND) +
                        ")";

        String ErrorLogMessage =
                "The test - \"" +
                        result.getMethod().getMethodName().toString() +
                        "\" was failed!" +
                        "(" +
                        calendar.get(Calendar.DATE) +
                        "." +
                        (calendar.get(Calendar.MONTH) + 1) +
                        "." +
                        calendar.get(Calendar.YEAR) +
                        " " +
                        calendar.get(Calendar.HOUR_OF_DAY) +
                        ":" +
                        calendar.get(Calendar.MINUTE) +
                        ":" +
                        calendar.get(Calendar.SECOND) +
                        ")";

        try {
            if (!result.isSuccess()) {
                File screenshot1 = new File("TestReport/html/Screens/" + result.getMethod().getMethodName() + ".png");
                screenshot1.delete();
                File screenshotTempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(screenshotTempFile, screenshot1);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Reporter.log(
                        "<center>Скриншот снят при падении теста " +
                                screenshot1.getName() +
                                ", URL = " +
                                driver.getCurrentUrl() +
                                "<br><div><a target=\"_blank\" href=\"Screens/" +
                                result.getMethod().getMethodName() +
                                ".png\"><img  style=\"height:400px; width: 600px;\"  src=\"" + "Screens/" +
                                result.getMethod().getMethodName() +
                                ".png" +
                                "\"></a></div><center><br><br>",
                        true);
                System.out.println(ErrorLogMessage);
            } else {
                System.out.println(SuccsessLogMessage);
                Reporter.log(SuccsessLogMessage);
            }
        } catch (Exception e) {
            new ApplicationManager().addErrorToTheReport("Connection with browser was lost.");
        }
    }

}
