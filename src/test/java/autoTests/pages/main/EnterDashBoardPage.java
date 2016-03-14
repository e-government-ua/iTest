package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import autoTests.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterDashBoardPage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    // Variables

    @FindBy(xpath = "//input[@name='login']")
    public WebElement field_login;

    @FindBy(name ="//input[@name='password']")
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

    public EnterDashBoardPage enterLogin(String login) {
        field_login.sendKeys(login);
        field_login.sendKeys(Keys.TAB);
        return this;
    }

    public EnterDashBoardPage enterPassword(String password) {
        field_password.sendKeys(password);
        return this;
    }

    public EnterDashBoardPage clickButtonEnter(){
        button_enter.click();
        return this;
    }

}
