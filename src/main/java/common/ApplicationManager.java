package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

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

    protected static WebDriver driver;
    public WebDriverWait wait;

    public ApplicationManager(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 40);
    }

    public ApplicationManager() {

    }

    //    ------------------- Запуск браузеров  -----------------//
    public static WebDriver startTestsIn(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("myfirefox")) {
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile("default");
            driver = new FirefoxDriver(profile);
        } else if (browser.equalsIgnoreCase("myfirefox")) {
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile("default");
            driver = new FirefoxDriver(profile);

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        } else if (browser.equalsIgnoreCase("proxy")) {
            Proxy proxy = new Proxy();
            proxy.setHttpProxy("partner.privatbank.ua:3128");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, proxy);
            driver = new FirefoxDriver(capabilities);
        } else {
            System.out.println("Unknown BROWSER for testing!");
        }
        assert driver != null;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void addErrorToTheReport(String testName) {
        Reporter.log("<b><font color=\"red\" size=\"3\">" + testName + "</font></b><br>");

    }

    public static WebDriver getDriver() {
        return driver;
    }

    //    ------------------- Методы работы с выпадающими списками  -----------------//
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

    //    ------------------- Метод ввода данных  -----------------//
    public void typeValue(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    //    ------------------- Методы работы c датами  -----------------//
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

    //    ------------------- Методы ожидания   -----------------//
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

    //    ------------------- Методы проверок элементов/текста на странице  -----------------//
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

    //    ------------------- Методы для скринов -----------------//
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
}


