package pages.service.test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BaseServicePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class TestZPCnapMailerPage extends BaseServicePage {
    public TestZPCnapMailerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static String referenceNumber;

    //---------------- Элементы страницы------------------//
    @FindBy(xpath = "//input[@name='bankIdPhone']")
    private WebElement phoneField;

    @FindBy(name = "email")
    private WebElement emailField; //поле эмейла

    @FindBy(xpath = "(//input[@type='file'])[1]")
    public WebElement attachDocumentButton; // поле аттача


    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки



    //---------------- Методы ввода данных в поля------------------//

    private static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    public TestZPCnapMailerPage typeInPhoneField(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone); // ввод телефона
        return this;
    }
    public TestZPCnapMailerPage typeInEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email); // ввод емайла
        return this;
    }
    @Override
    public TestZPCnapMailerPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public TestZPCnapMailerPage verifyServiceSuccessCreated() {
        Assert.assertEquals(successText.getText(), "Результати будуть спрямовані на Ваш e-mail vidokgulich@gmail.com");// проверка успешного создания заявки
        return this;
    }

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}