package pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class MainPage extends BasePage {

    // Variables

    @FindBy(css = ".ng-scope>p")
    private WebElement services;            // name of services on the Main page

    @FindBy(xpath = "//input[@ng-change='search()']")
    private WebElement searchField;         // search field

    @FindBy(xpath = "//a[contains(text(), 'Всі послуги')] ")
    private WebElement expandMoreServices; // to display all found services when more than 4 services are found


    // Webelements: Service page elements

    @FindBy(xpath = "//button[contains(.,'Змінити')]")
    public WebElement changeRegionButton;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement inputEmailAbsentMessageField;

    @FindBy(xpath = "//button[contains(.,'Зберегти')]")
    public WebElement sendAbsentMessageButton;


    // Methods

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    // Method for service selection
    public void clickService(String service) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Проверка на наличие апострофа в названии услуги (если есть апостроф ломается XPATH,
        // так как он в виде одинарной кавычки)
        if (service.contains("'")) {
            String resultStr = service.substring(0, service.indexOf("'"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(.,'" + resultStr + "')]")))
                    .click();
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(.,'" + service + "')]")))
                    .click();
        }
    }

    // Method for searching the service
    public void typeInSearchField(String service) {
        searchField.clear();
        searchField.sendKeys(service);
    }

    // Method for searching the service
    public void search(String service) {
        searchField.clear();
        searchField.sendKeys(service);
        app.pause(5000);
    }

    // Method for switching to test service
    public void goToTestServices(String server, String service) {
        driver.get(server + service);
    }

    // Method to expand the list of services
    public void clickExpandAllFoundServices() {
        expandMoreServices.click();
    }

    public boolean isSearchResultContains(String service) {
        return services.getText().contentEquals(service);
    }

    // Interaction with service page elements methods

    public void typeInEmailField(String email) {
        inputEmailAbsentMessageField.clear();
        inputEmailAbsentMessageField.sendKeys(email);
    }

    public void clickSendAbsentMessageButton() {
        sendAbsentMessageButton.click();
    }

}