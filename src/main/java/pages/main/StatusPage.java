package pages.main;

import common.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatusPage extends ApplicationManager {

    @FindBy(id = "code")
    public WebElement refIdField;          // поле ввода реф

    @FindBy(xpath = "//a[contains(.,'Переглянути')]")
    public WebElement viewStatusButton;    //кнопка просмотра статуса

    //    ------------------- Элементы ID Bank------------------------------//

    public StatusPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // =============================================== МЕТОДЫ  =================================================//

    public StatusPage enterReferenceNumber(String number) {
        refIdField.sendKeys(number);
        return this;
    }

    public StatusPage clickViewStatusButton() {
        viewStatusButton.click();
        return this;
    }

    public void verifyStatus(String status) {
        driver.findElement(By.xpath("//td[contains(.,'" + status + "')]")).isDisplayed();
    }
}


