package pages.service.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.BaseServicePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class TestDependenceFormPage extends BaseServicePage {

    public static String referenceNumber;

//---------------- Элементы страницы------------------//   
    @FindBy(name = "client")
    private WebElement clientField; // поле выбора заявителя

    @FindBy(name = "info1")
    private WebElement info1Field; //поле ввода информации

    @FindBy(name = "info2")
    private WebElement info2Field; //поле ввода информации

    @FindBy(xpath = "(//input[@type='file'])[1]")
    public WebElement attachDocumentButton; // поле аттача

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    public TestDependenceFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //---------------- Методы ввода данных в поля------------------//

    private static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public TestDependenceFormPage selectClient(String client) {
        new Select(clientField).selectByVisibleText(client);//выбор заявителя
        return this;
    }

    public TestDependenceFormPage typeInInfo1Field(String info) {
        info1Field.clear();
        info1Field.sendKeys(info); // ввод информации
        return this;
    }

    public TestDependenceFormPage typeInInfo2Field(String info) {
        info2Field.clear();
        info2Field.sendKeys(info); // ввод информации
        return this;
    }

    public TestDependenceFormPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }

    @Override
    public TestDependenceFormPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public TestDependenceFormPage verifyServiceSuccessCreated(String email) {
        Assert.assertEquals(successText.getText(), "Результати будуть спрямовані на Ваш e-mail " + email);// проверка успешного создания заявки
        return this;
    }

    //---------------- Методы по работе с номером заявки ----------------//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}
