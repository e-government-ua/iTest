package autoTests.pages.main;


import autoTests.ConfigurationVariables;
import autoTests.CustomMethods;
import autoTests.pages.service.BaseServicePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TasksPage extends BaseServicePage {
    WebDriver driver;
    ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
    CustomMethods customMethods = new CustomMethods();

    // Variables
    @FindBy(xpath = ".//ul[@class='nav navbar-nav']/li/a")
    public List<WebElement> listTabs;


    //---------------- Методы ------------------//
    public TasksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void verifyListTabs(){
        int i = 0;
        for (WebElement element : listTabs) {
            try {
                i+=1;
                customMethods.CheckElementPresent(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(i,5);


    }
}

