package common;

import entities.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.internal.Streams;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utils.PropertyLoader;
import utils.WebDriverFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ApplicationManager {

    // Variables

    private static Browser browser;
    private static String username;
    private static String password;
    private static String gridHubUrl;
    private static String baseUrl;
    public static WebDriver driver;
    public static WebDriverWait wait;

    // Methods

    //------------------- Запуск браузеров  -----------------//

    public static WebDriver initDriver() {

        // Get properties from resources/application.properties file
        browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
        username = PropertyLoader.loadProperty("user.username");
        password = PropertyLoader.loadProperty("user.password");
        gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        baseUrl = PropertyLoader.loadProperty("base.url");

        // Create browser (using given properties) & maximize it
        driver = WebDriverFactory.getInstance(gridHubUrl, browser, username, password);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Create wait object
        wait = new WebDriverWait(driver, 20);

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static void addErrorToTheReport(String testName) {
        Reporter.log("<b><font color=\"red\" size=\"3\">" + testName + "</font></b><br>");
    }

    //------------ Методы работы с выпадающими списками ---------------//

    public void selectValue(By locator, String value) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    public void selectVisibleText(By locator, String value) {
        WebElement selectElement = driver.findElement(locator);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }

    //--------------------- Метод ввода данных -----------------------//

    public void typeValue(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    //------------------- Методы работы c датами -------------------//

    public String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(calendar.getTime());
    }

    public String getDateAfterNDays(int days) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return formatter.format(calendar.getTime());
    }

    //--------------------- Методы ожидания -----------------------//

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitInElementTextPresent(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(locator, text));
    }

    public void waitElementInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //----------- Методы проверок элементов/текста на странице -----------//

    public String verifyTextPresent(String text) {
        if (driver.getPageSource().contains(text)) return "";
        else System.out.println("ERROR: NOT FOUND TEXT: \"" + text + "\"");
        return "\n" + "ERROR: NOT FOUND TEXT: \"" + text + "\"";
    }

    public String verifyElementPresent(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (driver.findElement(locator).isDisplayed()) {
            }
            return "";
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: NOT FOUND ELEMENT: \"" + locator + "\"");
            return "\n" + "ERROR: NOT FOUND ELEMENT: \"" + locator + "\"";
        }
    }

    public String verifyWithNBSPTextPresent(By locator, String text) {
        if (driver.findElement(locator).getText().contains(text)) return "";
        else System.out.println("ERROR: NOT FOUND TEXT: \"" + text + "\"");
        return "\n" + "ERROR: NOT FOUND TEXT: \"" + text + "\"";
    }

    public String verifyInElementTextPresent(By locator, String text) {
        String textElem = "";
        try {
            textElem = driver.findElement(locator).getText();
            assertEquals(textElem, text);
            return "";
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: NOT FOUND ELEMENT: \"" + locator + "\" FOR METHOD verifyTextPresentInElement(" + text + ")");
            return "\n" + "ERROR: NOT FOUND ELEMENT: \"" + locator + "\" FOR METHOD verifyTextPresentInElement()";
        } catch (AssertionError e) {
            System.out.println("ERROR: IN ELEMENT \"" + locator + "\" NOT FOUND TEXT: \"" + text + "\". WAS FOUND \"" + textElem + "\"");
            return "\n" + "ERROR: IN ELEMENT \"" + locator + "\" NOT FOUND TEXT: \"" + text + "\". WAS FOUND \"" + textElem + "\"";
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if (driver.findElement(locator).isDisplayed()) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void makeElementVisibleByJavascript(final WebElement element) {
        String script = "var element = arguments[0];" + "element.style.display='block';element.style.opacity = 1;element.style.visibility = 'visible';";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void showErrors(StringBuilder presentErrors) {
        if (presentErrors.length() > 0)
            fail(presentErrors.toString());
    }

    public void failTestWithMessage(String message) {
        fail(message);
    }

    public String verifyTextNotPresent(String text) {
        if (!driver.getPageSource().contains(text)) return "";
        else System.out.println("ERROR: FOUND TEXT: \"" + text + "\"");
        return "\n" + "ERROR: FOUND TEXT: \"" + text + "\"";

    }

    //------------------- Методы для скринов ---------------------//

    public Calendar getCurrentCalendar() {
        // http://docs.oracle.com/javase/6/docs/api/java/util/GregorianCalendar.html
        // get the supported ids for GMT+02:00 ("Среднеевропейское (Центральноевропейское) летнее время")

        String[] ids = TimeZone.getAvailableIDs(+2 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0) System.exit(0);
        // create a (CEST - Central Europe Summer Time Zone) UTC/GMT+2 time zone
        SimpleTimeZone GMT = new SimpleTimeZone(+2 * 60 * 60 * 1000, ids[0]);
        // create a GregorianCalendar with the current date and time
        Calendar calendar = new GregorianCalendar(GMT);
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        //// print out a bunch of interesting things
        // System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        // System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        // System.out.println("DATE: " + calendar.get(Calendar.DATE));
        // System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        // System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
        // System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        // System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        //
        // System.out.println("Current Time, with hour reset to 3");
        // calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
        // calendar.set(Calendar.HOUR, 3);
        return calendar;
    }


    //------------------- Attachment methods -------------------//

    public ApplicationManager attachDocument(WebElement locator, String document) {
        unhide(locator);
        File file = new File(document);
        locator.sendKeys(file.getAbsolutePath());
        waitAttachUpload(locator);
        return this;
    }

    public void unhide(WebElement element) {
        String script = "var element = arguments[0];"
                + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void waitAttachUpload(WebElement element) {
        while (!element.isEnabled()) {
            try {
                System.out.println("Wait, wait, wait..");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //------------------- OLD Attachment methods -------------------//

//        public ApplicationManager attachDocument (WebElement locator, String document) throws AWTException {
//        File file = new File(document);
//        //
//        locator.click();
//        pause(2000); // временно
//        //
//        setClipboardData(file.getAbsolutePath());
//        //
//        pause(2000); // временно
//        Robot robot = new Robot();
//        robot.delay(1000);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.delay(300);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.delay(300);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        robot.delay(300);
//
//        return this;
//    }



}