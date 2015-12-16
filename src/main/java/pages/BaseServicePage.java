package pages;

import common.ApplicationManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseServicePage extends ApplicationManager {

    @FindBy(xpath = "//div[@class='service-name ng-binding']")
    protected WebElement serviceName; // название услуги

    @FindBy(xpath = "//button[@class='btn btn-info']")
    protected WebElement confirmButton; // кнопка подтверждения создания услуги

    @FindBy(xpath = "//div[@class='text-center ng-binding']")
    protected WebElement referenceNumberField; //поле референс заявки

    public String referenceNumber;

    public BaseServicePage clickConfirmButton() {
        confirmButton.click(); //нажать конпку подтверждения создания услуги
        return this;
    }

    public String getServiceName() {
        return serviceName.getText();
    }

    public String saveReferenceNumber() {
        String refField = referenceNumberField.getText();
        this.referenceNumber = refField.substring(16, 23);
        return referenceNumber;
    }

}
