package pages;


import appLogic.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends ApplicationManager {


    @FindBy(xpath = "//h4[contains(.,'Послуги')]")
    public WebElement servicesLink;
    @FindBy(xpath = "//h4[contains(.,'Документи')]")
    public WebElement documentsLink;

    //----------------  Верхние табы ------------------//
    @FindBy(xpath = "//h4[contains(.,'Статуси')]")
    public WebElement statusLink;
    @FindBy(xpath = "//h4[contains(.,'Мій журнал')]")
    public WebElement myLogLink;
    @FindBy(xpath = "//h4[contains(.,'Про портал')]")
    public WebElement aboutPortalLink;
    @FindBy(xpath = "//a[contains(@href,'https://igov.org.ua/ecp')]")
    public WebElement checkElectronDigitalSignatureLink;
    @FindBy(xpath = "//footer/div/div/div[3]")
    public WebElement portalsNewsOnFacebookLink;


    //----------------  Футер ------------------//
    @FindBy(xpath = "//a[contains(@href, 'https://docs.google.com/forms/d/1ueU6PQa-OSA2Tsisxx2RbRWRJ9rLsFlPBlHsr7W-4gE/viewform')]")
    public WebElement errorOrABugOnThePortalLink;
    @FindBy(xpath = "//a[contains(@href, 'https://github.com/e-government-ua/i/wiki/%D0%AF%D0%BA-%D0%BF%D0%BE%D1%87%D0%B0%D1%82%D0%B8-%D1%80%D0%BE%D0%B1%D0%BE%D1%82%D1%83')]")
    public WebElement joinOnGitHubLink;
    @FindBy(xpath = "//a[contains(@href,'https://docs.google.com/forms/d/1w-BR01CSicvhWSXb36CiRjHCwvp-vyPuTHBaWw1iW4U/viewform')]")
    public WebElement volunteerIGov;
    @FindBy(css = ".ng-scope>p")
    public WebElement services;         // название сервисов на главной странице
    @FindBy(xpath = "//input[@ng-change='search()']")
    public WebElement searchField;            // поле поиска

    //---------------- Выбор сервиса по региону  ------------------//
    private WebDriver driver;


    //---------------- Элементы поиска  ------------------//

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //    ------------------- Метод выбора услуги  ------------------------------//
    public void clickService(String service) {
        driver.findElement(By.xpath("//a[contains(.,'" + service + "')]")).click();

    }

    //    ------------------- Метод поиска услуги  ------------------------------//

    public void typeInSearchField(String service) {
        searchField.clear();
        searchField.sendKeys(service);
    }

    //    ------------------- Метод поиска услуги  ------------------------------//
    public void goToServices() {
        servicesLink.click();
    }

    //    ------------------- Метод перехода в меню документов  ------------------------------//
    public void goToDocuments() {
        documentsLink.click();
    }

    //    ------------------- Метод перехода в меню статусы  ------------------------------//
    public void goToStatus() {
        statusLink.click();
    }

    //    ------------------- Метод перехода в меню жкрнал  ------------------------------//
    public void goToMyLog() {
        myLogLink.click();
    }

    //    ------------------- Метод перехода в меню про портал  ------------------------------//
    public void goToAboutPortal() {
        aboutPortalLink.click();
    }

    //    ------------------- Метод перехода в тестовой услуге  ------------------------------//
    public void goToTestServices(String server, String service) {
        driver.get(server + service);
    }

    public void goToService() {
        driver.get("https://test-version.igov.org.ua/service/176/general"); //временно из за бага поиска
    }

}






