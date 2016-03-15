package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import autoTests.Constants;
import autoTests.CustomMethods;
import autoTests.pages.service.BaseServicePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterDashBoardPage extends BaseServicePage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods customMethods = new CustomMethods();
    // Variables

    @FindBy(xpath = "//input[@name='login']")
    public WebElement field_login;

    @FindBy(xpath ="//input[@type='password']")
    public WebElement field_password;

    @FindBy(xpath ="//button[@type='submit']")
    public WebElement button_enter;

    //---------------- Методы ------------------//
    public EnterDashBoardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void getPage() {
        driver.navigate().to(Constants.Dashboard.URL_DASHBOARD);
    }

    public void enterLogin(String login) throws Exception {
        customMethods.waitForElementPresent(driver,field_login,configVariables.implicitTimeWait,500);
        field_login.sendKeys(login);
    }

    public void enterPassword(String password) {
        field_password.sendKeys(password);
     }

    public EnterDashBoardPage clickButtonEnter(){
        button_enter.click();
        return this;
    }

}
