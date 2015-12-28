package test.portal.services.police.traffic;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.police.traffic.CriminalRecordPage;
import test.TestBase;

public class CriminalRecord extends TestBase {

    @Test (priority = 10)
    public void DnipropetrovskCriminalRecord() {
        // ------------------- Тестовые данные -------------------//
        String service = Constants.Services.MVD.CRIMINAL_RECORD;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String city = Constants.Areas.City.DNIPROPETROVSK;
        String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
        String birthLoc = "Украина";
        String country = "Україна";
        String goal = "Оформлення візи для виїзду за кордон.";
        String phone = "931234567";
        String resType = "Прошу надати довідку в паперовому вигляді";
        String email = "test@gmail.com";

        // --------------------- Тест-кейс----------------------//
        mainPage.typeInSearchField(service);
        mainPage.clickService(service);
        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);
        //selectAreaPage.selectCity(city);
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
        statusPage.enterReferenceNumber(CriminalRecordPage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);


    }
}