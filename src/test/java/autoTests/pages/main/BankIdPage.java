package autoTests.pages.main;

import autoTests.ConfigurationVariables;
import autoTests.Constants;
import autoTests.CustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

/**
 * Created by Slame on 24.02.16.
 */
public class BankIdPage extends CustomMethods {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    // Variables

    @FindBy(xpath = "//span[contains(.,'BankID')]")
    public WebElement BankID;          // button for authorization through BankID

    @FindBy(id = "privatBank")
    public WebElement privatBank;      // PrivatBank selection

    @FindBy(id = "loginLikePhone")
    public WebElement phone;           // login field for Privat24

    @FindBy(id = "passwordLikePassword")
    public WebElement password;        // password field for Privat24

    @FindBy(id = "confirmButton")
    public WebElement confirm;         // button to open ОТР step

    @FindBy(id = "first-section")
    public WebElement otpOne;         // the first field after entering ОТР

    @FindBy(id = "second-section")
    public WebElement otpTwo;         // the second field after entering ОТР

    @FindBy(id = "third-section")
    public WebElement otpThree;       // the third field after entering ОТР

    @FindBy(id = "signInButton")
    public WebElement signIn;         //  sign in button after entering ОТР

    @FindBy(xpath = "//span[@class='pull-right ng-binding']")
    public WebElement fio;            //  full name of client after login

    @FindBy(xpath = "//span[contains(.,'Вийти')]")
    public WebElement logOutButton;   //  logout button


    // Methods

    public BankIdPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Method for logging out
    public void logOut() {
        logOutButton.click();
    }

    // Method for FIO verification after login
    public void verifyFIO() throws Exception {
        waitForElementPresent(driver,fio,configVariables.implicitTimeWait,1);
        assertEquals(fio.getText(), Constants.TestData.PersonalInfo.FIO_UA_DUBILET);
    }

    // Method for entering ОТР
    public void typeOTP() {
        otpOne.sendKeys(Constants.TestData.BankIDprivatBank.OTP1);
        otpTwo.sendKeys(Constants.TestData.BankIDprivatBank.OTP2);
        otpThree.sendKeys(Constants.TestData.BankIDprivatBank.OTP3);
        confirm.click();
    }

    // Method for selecting BankID authorization method
    public void clickOnBankIdButton() {
        BankID.click();
        new WebDriverWait(driver, 2).until(visibilityOfElementLocated(By.xpath("//div[@id='IdLogo']/img")));
    }

    // Method for selecting PrivatBank
    public void clickOnPrivatBank() {
        privatBank.click();
        new WebDriverWait(driver, 2).until(visibilityOfElementLocated(By.id("loginLikePhone")));
    }

    // Method for entering login and password
    public void typeLoginAndPassword() {
        phone.clear();
        phone.sendKeys(Constants.TestData.BankIDprivatBank.LOGIN);
        password.sendKeys(Constants.TestData.BankIDprivatBank.PASSWORD);
        signIn.click();
        new WebDriverWait(driver, 6).until(visibilityOfElementLocated(By.id("first-section")));
    }

    // Method for authorization through PrivatBank
    public void loginByPrivatBankBankID() throws Exception {
        clickOnBankIdButton();
        clickOnPrivatBank();
        typeLoginAndPassword();
        typeOTP();
        verifyFIO();
    }
}
