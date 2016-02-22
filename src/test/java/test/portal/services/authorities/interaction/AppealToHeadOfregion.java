package test.portal.services.authorities.interaction;

import common.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.service.BaseServicePage;
import test.TestBase;

import static org.testng.Assert.assertEquals;


public class AppealToHeadOfRegion extends TestBase {

    @Test(description = "Звернення до голови ОДА", priority = 10)
    public void successMessageToHeadOfRegion(){
        String service = Constants.Services.InteractionWithPublicAuthorities.APPEAL_TO_HEAD_OF_REGION;
        String region = Constants.Areas.Region.KHERSONSKA;
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String phone = Constants.TestData.PersonalInfo.PHONE;
        String address = "проспект карла маркса 22";
        String randomSubjectDetails = RandomStringUtils.randomAlphabetic(100);
        String randomAppealText = RandomStringUtils.randomAlphanumeric(255);
        String testDocument = "src/test/resources/files/test.jpg";
        String status = "Заявка подана";


        app.mainPage.typeInSearchField(service);
        app.mainPage.clickService(service);
        assertEquals(app.appealToHeadOfRegion.getServiceName(), service);
        app.selectAreaPage.selectRegion(region);
        app.bankIdPage.loginByPrivatBankBankID();
        app.appealToHeadOfRegion
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .typeInRegistrationAddress(address);
        app.appealToHeadOfRegion
                .selectRandomTypeOfAppeal()
                .selectRandomSubjectOfAppeal()
                .typeInSubjectDetails(randomSubjectDetails)
                .typeInTextOfAppeal(randomAppealText)
                .selectRandomAnswerForm();
        app.attachDocument(app.appealToHeadOfRegion.getAttachFileElement(), testDocument);
        app.appealToHeadOfRegion
                .clickConfirmButton()
                .verifyAppealSuccessCreated(email)
                .saveReferenceNumber();
        app.navHelper.openStatusesPage();
        app.statusPage.enterReferenceNumber(app.appealToHeadOfRegion.getReferenceNumber())
                .clickViewStatusButton()
                .verifyStatus(status);
    }


}
