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

public class TestFieldsBankidPage extends BaseServicePage {

    public static String referenceNumber;

    //---------------- Элементы страницы------------------// 
    @FindBy(name = "bankIdsID_Country")
    private WebElement countryField; // поле ввода гражданства

    @FindBy(name = "bankIdAddressFactual")
    private WebElement addressField; // поле ввода адреса прописки

    @FindBy(css = "button.btn.btn-success")
    private WebElement attachDocumentButton;// поле аттача документа

    @FindBy(xpath = "//div[@class='text-center ng-binding ng-scope']")
    private WebElement successText; //текст удачной создании заявки

    public TestFieldsBankidPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static void setClipboardData(String document) {
        StringSelection stringSelection = new StringSelection(document);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }

    //---------------- Методы ввода данных в поля------------------//
    public TestFieldsBankidPage typeInCountryField(String country) {
        countryField.clear();
        countryField.sendKeys(country); // ввод гражданства
        return this;
    }
    // upload Document

    public TestFieldsBankidPage typeInAddressField(String address) {
        addressField.clear();
        addressField.sendKeys(address); // ввод адреса прописки
        return this;
    }

    public TestFieldsBankidPage attachDocument(String document) throws AWTException {
        File file = new File(document);
        //
        attachDocumentButton.click();
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

        return this;
    }

    @Override
    public TestFieldsBankidPage clickConfirmButton() {
        super.clickConfirmButton();
        return this;
    }

    public TestFieldsBankidPage verifyServiceSuccessCreated() {
        Assert.assertEquals(successText.getText(), "Результати будуть спрямовані на Ваш e-mail");// проверка успешного создания заявки
        return this;
    }
//=================методы по работе с номером заявки=======================//

    @Override
    public String saveReferenceNumber() {
        referenceNumber = super.saveReferenceNumber();
        return referenceNumber;
    }
}
