package test.portal.services.police.traffic;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestBase;

public class CriminalRecord extends TestBase {


    @Test
    public void DnipropetrovskCriminalRecord() {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Settings.ServiceMVD.CRIMINAL_RECORD;
        String region = Constants.Settings.Region.DNIPROPETROVSKA;
        String city = Constants.Settings.City.DNIPROPETROVSK;
        String birthDate = Constants.Settings.Data.BIRTH_DAY;
        String birthLoc = "Украина";
        String country = "Україна";
        String goal = "Оформлення візи для виїзду за кордон.";
        String phone = "931234567";
        String resType = "Прошу надати довідку в паперовому вигляді";
        String email = "test@gmail.com";
        String status = "Заявка подана";
        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        app.pause(5000);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        selectAreaPage.selectCity(city);
        authorizationPage.privatBankAuthorization();
        Assert.assertEquals(criminalRecordPage.getServiceName(), service);

        criminalRecordPage.typeInBirthDateField(birthDate)
                .typeInBirthLocField(birthLoc)
                .selectСountry(country)
                .selectGoal(goal)
                .typeInPhoneField(phone)
                .selectResType(resType)
                .typeInEmailField(email)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();
        mainPage.goToStatus();
        statusPage.inputReferenceNumberForCriminalRecord()
                .clickViewStatusButton()
                .verifyStatus(status);


    }
}


