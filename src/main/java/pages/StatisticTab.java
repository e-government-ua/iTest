package pages;

import appLogic.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatisticTab extends ApplicationManager {

    private WebDriver driver;

    public StatisticTab (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    //---------------- Элементы таба статистика ------------------//

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[3]")
    public WebElement timingColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[3]")
    public WebElement timingRow;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//th[2]")
    public WebElement numberOfServicesProvidedColumn;

    @FindBy(xpath = "//*[@class='table table-striped ng-scope']//tr/td[1]")
    public WebElement regionRow;
}
