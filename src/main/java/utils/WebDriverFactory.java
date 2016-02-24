package utils;

import entities.Browser;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    // Variables

    private static WebDriver webDriver = null;
    // Browsers constants
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String SAFARI = "safari";
    public static final String INTERNET_EXPLORER = "ie";
    // Platform constants
    public static final String MAC = "mac";
    public static final String LINUX = "linux";
    public static final String WINDOWS = "windows";
    public static final String ANDROID = "android";

    // Methods

    /*
     * Factory method to return a RemoteWebDriver instance given the url of the
     * Grid hub and a Browser instance.
     *
     * @param gridHubUrl : grid hub URI
     *
     * @param browser : Browser object containing info around the browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return RemoteWebDriver
     */
    public static WebDriver getInstance(String gridHubUrl, Browser browser, String username, String password) {

        String browserName = browser.getName();
        String browserVersion = browser.getVersion();
        String browserPlatform = browser.getPlatform();

        WebDriver webDriver = null;
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);

        // In case there is no Hub
        if (gridHubUrl == null || gridHubUrl.length() == 0) {
            return getInstance(browserName, username, password);
        }

        if (CHROME.equals(browserName)) {
            capability = DesiredCapabilities.chrome();

        } else if (SAFARI.equals(browserName)) {
            capability = DesiredCapabilities.safari();

        } else if (INTERNET_EXPLORER.equals(browserName)) {
            capability = DesiredCapabilities.internetExplorer();
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        } else if (FIREFOX.equals(browserName)) {
            capability = DesiredCapabilities.firefox();
            FirefoxProfile ffProfile = new FirefoxProfile();
            // Authenication Hack for Firefox
            if (username != null && password != null) {
                ffProfile.setPreference("network.http.phishy-userpass-length", 255);
                capability.setCapability(FirefoxDriver.PROFILE, ffProfile);
            }
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        }

        capability = setVersionAndPlatform(capability, browserVersion, browserPlatform);

        // Create Remote WebDriver
        try {
            webDriver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return webDriver;
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : String representing the local browser to hit
     *
     * @param username : username for BASIC authentication on the page to test
     *
     * @param password : password for BASIC authentication on the page to test
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(String browser, String username, String password) {

        if (webDriver != null) {
            return webDriver;
        }

        if (CHROME.equals(browser)) {
            webDriver = new ChromeDriver();

        } else if (SAFARI.equals(browser)) {
            webDriver = new SafariDriver();

        } else if (INTERNET_EXPLORER.equals(browser)) {
            webDriver = new InternetExplorerDriver();

        } else if (FIREFOX.equals(browser)) {
            FirefoxProfile ffProfile = new FirefoxProfile();
            //Для обхода прокси

            // Authentication Hack for Firefox
            if (username != null && password != null) {
                ffProfile.setPreference("network.http.phishy-userpass-length", 255);
            }
            webDriver = new FirefoxDriver(ffProfile);

        }

        return webDriver;
    }


    /*
     * Helper method to set version and platform for a specific browser
     *
     * @param capability : DesiredCapabilities object coming from the selected
     * browser
     *
     * @param version : browser version
     *
     * @param platform : browser platform
     *
     * @return DesiredCapabilities
     */
    private static DesiredCapabilities setVersionAndPlatform(DesiredCapabilities capability, String version, String platform) {
        if (MAC.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.MAC);
        } else if (WINDOWS.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.WINDOWS);
        } else if (LINUX.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.LINUX);
        } else if (ANDROID.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.ANDROID);
        } else {
            capability.setPlatform(Platform.ANY);
        }

        if (version != null) {
            capability.setVersion(version);
        }

        return capability;
    }
}