package test.portal.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class AboutPortal extends TestBase {

    @Test (priority = 10)
    public void checkInfoAboutPortal() {
        mainPage.goToAboutPortal();
        Assert.assertEquals(portalPage.infoBlockOne.getText(), "На цьому порталі зібрано послуги, які державні органи України надають громадянам та бізнесу. Частина послуг надається через Інтернет (такі послуги позначені іконкою), інша частина послуг все ще очікує на своє впровадження в електронній формі.");
        Assert.assertEquals(portalPage.infoBlockTwo.getText(), "Цей портал зроблено волонтерською командою iGov в рамках боротьби з корупцією в Україні та вдосконалення бізнес-процесів в наших державних органах.");
        Assert.assertEquals(portalPage.infoBlockThree.getText(), "Велика подяка ІТ-волонтерам, які взяли та беруть участь у розробці Порталу:");
        Assert.assertEquals(portalPage.infoBlockFour.getText(), "Якщо ви маєте досвід у розробці ІТ-продуктів та бажаєте приєднатися до команди ІТ-волонтерів, запрошуємо Вас заповнити цю форму.");
    }
}