package pages.main;

import common.ApplicationManager;
import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;


public class DocumentsPage extends ApplicationManager {

    @FindBy(xpath = "//a[@href='/documents/user']")
    public WebElement myDocumentsTab;                                 // Таб мои документы

    @FindBy(xpath = "//div[@ng-hide='authProcess']")
    public WebElement formSignInBankId;                                 // форма входа в банк ИД

// =================== Элементы таба "Мои документы" ===================//
    @FindBy(xpath = "//p[@align='justify']")
    public WebElement infoBlockDocument;                                 // форма входа в банк ИД

    @FindBy(xpath = "//a[@ng-click='loginWithBankId()']")
    public WebElement bankIdAuthorizationButton;                      // Кнопка ИД Банка

    @FindBy(xpath = "//button[contains(@ng-click,'loginWithEds()')]")
    public WebElement edsAuthorizationButton;                         // Кнопка ЕЦП

    @FindBy(xpath = "//button[contains(.,' Добавить документ ')]")
    private WebElement uploadNewDocumentButton;                        // Кнопка "Добавить документ"

    @FindBy(name = "documentNameForUpload")
    public WebElement nameNewDocumentField;                           // Поле ввода названия нового документа

    @FindBy(name = "documentTypeForUpload")
    public WebElement typeNewDocumentSelector;                        // Селектор выбора типа документа

    @FindBy(xpath = "//button[@ng-click='uploadDocument(documentTypeForUpload, documentNameForUpload)']")
    public WebElement saveNewDocumentButton;                          // Кнопка сохранения нового документа

    @FindBy(xpath = "//tbody[1]/tr[1]/td[5]/a[2]/span")
    private WebElement shareDocumentLink;                              // Иконка шаринга документа

    @FindBy(xpath = "//table/tbody[1]/tr[2]/td/div/center/div//form/div[1]/div/input")
    private WebElement nameOfRecipientField;                           // Поле ввода ФИО для шаринга

    @FindBy(xpath = "//tbody[1]/tr[2]/td/div/center//div/form/div[6]/button")
    private WebElement getCodeButton;                                  // Кнопка получения кода

    @FindBy(id = "link")
    private WebElement codeField;                                       // Поле с отображением кода

    @FindBy(xpath = "//button[contains(@ng-click,'close()')]")
    private WebElement okButton;                                        // Кнопка ОК форме отображения кода

    @FindBy(xpath = "//label[@for='link']")
    public WebElement alertInfoBlock;                                // Инфо на всплывающем окне

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneField;                                // поле ввода телефона

    @FindBy(xpath = "//tbody[1]/tr[2]/td/div/center/div//form/div[5]/div/input")
    private WebElement emailField;                                // поле ввода почты

    @FindBy(xpath = "//tbody[1]/tr[2]/td/div/center/div//form/div[3]/label[2]")
    private WebElement yesRadioButton;                                // поле ввода почты

    @FindBy(xpath = "//a[@href='/documents/search']")
    private WebElement searchWithCodeTab;                             // Таб  поиска документов

    @FindBy(id = "code")
    private WebElement inputCodeField;                                 // Поле ввода  кода доступа

// =================== Элементы таба "Поиск за кодом" ===================//
    @FindBy(id = "typeId")
    public WebElement typeDocumentSelector;                            // Селектор выбора документа

    @FindBy(id = "operatorId")
    public WebElement operatorSelector;                                // Селектор выбора оператора

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement searchDocumentButton;                            // Кнопка поиска документа

    @FindBy(xpath = "//a[contains(.,'Завантажити')]")
    private WebElement downloadLink;                                    // Ссылка загрузки найденного документа

    @FindBy(id = "smsPass")
    private WebElement inputSMSCodeField;                                // Пооле ввода паоля из СМС

    @FindBy(xpath = "//button[@ng-disabled='!smsPass']")
    private WebElement confirmDocumentButton;                            // Ссылка загрузки найденного документа по смс

    @FindBy(xpath = "//label[@for='smsPass']")
    public WebElement infoBlockSMS;                                       // Блок sms инфо

    @FindBy(xpath = " //div[@role='alert']")
    public WebElement errorBlockSMS;                                       // Блок sms erorr

    @FindBy(xpath = "//a[@href='/documents/notary']")
    private WebElement notaryTab;                                       // Таб "Нотариусам"

    @FindBy(xpath = "//div[2]/div[2]/div/div/div/div")
    public WebElement notaryInfoBlock;                                    // Инфо блок

// =================== Элементы таба "Нотариус" ===================//
private String accessCode;

    // =============================================== МЕТОДЫ  =================================================//

    public DocumentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    private void selectionOperator(String goal) {
        new Select(driver.findElement(By.id("operatorId"))).selectByVisibleText(goal);
    }

    private void searchDocumentByCode() {
        searchDocumentButton.click();
    }

    private void inputCode() {
        inputCodeField.sendKeys(accessCode);
    }

    private void clickSearchWithCodeLink() {
        searchWithCodeTab.click();
    }

    private void clickShareDocumentLink() {
        shareDocumentLink.click();
    }

    private void fillNameOfRecipientField(String name) {
        nameOfRecipientField.clear();
        nameOfRecipientField.sendKeys(name);
    }

    private void clickGetCodeButton() {
        getCodeButton.click();
    }

    private void saveCodeToAVariable() {
        setAccessCode(codeField.getAttribute("value"));
    }

    public void clickOkButton() {
        okButton.click();
    }

    private boolean verifyCodePresent() {
        return !getAccessCode().isEmpty();

    }

    private String getAccessCode() {
        return accessCode;
    }

    private void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

// upload Document

    private boolean isElementPresent() {
        downloadLink.isDisplayed();
        return true;
    }

    public void uploadFile(String document) throws AWTException {
        File file = new File(document);
        //
        uploadNewDocumentButton.click();
        //
        setClipboardData(file.getAbsolutePath());
        //
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
    }

    // refresh Page
    public void refreshPage() {
        driver.navigate().refresh();

    }


    public void goToNotaryTab() {
        notaryTab.click();
    }


    //==================================================================
    public void getAccessCode(String name) {
        clickShareDocumentLink();
        fillNameOfRecipientField(name);
        clickGetCodeButton();
        saveCodeToAVariable();
        clickOkButton();
    }


    public void getAccessCodeWithPhoneEmail(String name) {
        clickShareDocumentLink();
        fillNameOfRecipientField(name);
        yesRadioButton.click();
        typePhone();
        emailField.sendKeys(Constants.Settings.Data.E_MAIL);
        clickGetCodeButton();
        saveCodeToAVariable();
        clickOkButton();
    }

    public void isAccessCodeNotNull() {
        verifyCodePresent();
    }

    public void searchDocumentWithCode() {
        clickSearchWithCodeLink();
        selectionOperator("iGov");
        inputCode();
        searchDocumentByCode();
        pause(2000);

    }

    public void isDocumentFound() {
        isElementPresent();
    }

    private void typePhone() {
        phoneField.clear();
        phoneField.sendKeys("+" + Constants.Settings.Data.PHONE);
    }

    public void typeSMSCode() {
        inputSMSCodeField.sendKeys("11111");
        confirmDocumentButton.click();

    }


}


