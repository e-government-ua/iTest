package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 * Created by Privat24 on 09.09.2016.-	ПБ24 прошло, локатор не находит
 */
public class dnepr_dms_212s extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void default_test() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "dnepr_dms-212s";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/185/general");

        o.selectRegion("Дніпропетровська");
        o.selectCity("Дніпро (Дніпропетровськ)");

        o.testPrivat24Authorization();

        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldValue("sDate_of_birth", "Дата народження");
        setFieldValue("sAreabirth", "Місце народження");
        setFieldValue("sNationality", "Україна");
        setFieldValue("lastName_UA2", "Прізвище");
        setFieldValue("firstName_UA2", "Ім'я");
        setFieldValue("middleName_UA2", "По батькові");
        setFieldValue("sChildBirth", "Дата народження дитини");
        setFieldValue("sChildPOB", "Місце народження дитини");
        setFieldValue("sChildDoc", "Свідоцтво про народження дитини");
        setFieldValue("sChildCitizenship", "Україна");
        setFieldAutocomplete("sID_Public_SubjectOrganJoin", "Соборний (Жовтневий) район");
        setFieldValue("sRegistrationAddress", "Поточна адреса реєстрації дитини");
        setFieldValue("sNewAddress", "Нова адреса реєстрації");
        setFieldSelectSlotDate();
        setFieldSelectSlotTime();
        setFieldFile(sBP, "nSvidChild", "src/test/resources/files/test.jpg");
        setFieldValue("sMailClerk", email);

        click(o.buttonSendingForm);

        o.checkMessageSuccess("Шановний(-а) MockUser MockUser!\n"
                + "Ваше звернення х-хххххххх успішно зареєстровано\n"
                + "(номер також відправлено Вам електронною поштою на Ваш e-mail " + email + ") Результати будуть спрямовані також на email.\n"
                + "Звертаємо увагу, що Іноді листи потрапляють у спам або у розділ \"Реклама\" (для Gmail).");

        click(o.buttonLogOut);

        openURLdashboard(getRegionUrl());

        AuthorizationBySetLoginPassword("tester", "tester");
        clickButton("Увійти");

        // Опрацювання в табі "В необроблені"    
        setRegionTask();
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        // Опрацювання [Етап I] 
        SetRegionFieldInputTypeEnum("Призначити зустріч у відділенні");
        SetRegionFieldInputTypeTextArea("sDecisionComment", "Коментар до рішення");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Ok");

        // Опрацювання [Етап II]
        setRegionFindOrder(sBP);
        clickButton("Взяти в роботу");
        clickButton("Почати опрацювання задачі");

        SetRegionFieldInputTypeEnum("Дитину знято з реєстрації");
        SetRegionFieldInputTypeString("sWhenCome", "Вкажіть дату зняття з реєстрації");

        clickButton("Опрацювати");
        clickButton("Підтвердити");
        clickButton("Підтвердити");
        clickButton("Ok");

    }
    //</editor-fold>
}
