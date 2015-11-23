package pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.AssertJUnit.assertEquals;

import appLogic.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MyJournalPage extends ApplicationManager {

    private WebDriver driver;


    public MyJournalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //  =================  кноки входа в ид банк===============//
	@FindBy(linkText = "увійдіть через BankID")
    public WebElement bankIdAuthorizationButton;
	
	@FindBy(linkText = "електронно-цифровий підпис")
    public WebElement electronicDigitalSignatureButton;

    //  =================  форма входа в журнал ===============//
    @FindBy(xpath = "//h4[@class='form-signin-bankid-heading_']")
    public WebElement formSignInBankId;

    //  ================= ссылка на заявки  ===============//
    @FindBy(xpath = "//a[@ng-click='limit = limit +10']")
    public WebElement nextLink;

    //  ================= ссылка на заявки  ===============//
    @FindBy(xpath = "//h1[contains(.,'Мій журнал')]")
    public WebElement myLog;

    }

