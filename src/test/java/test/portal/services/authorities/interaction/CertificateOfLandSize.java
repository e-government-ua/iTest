package test.portal.services.authorities.interaction;

import common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.service.authorities.interaction.LandSizeAndExistencePage;
import pages.service.police.traffic.RegisterUsedCarPage;
import test.TestBase;

/**
 * Test for services in this class:
 * - " Надання довідки про наявність та розмір земельної частки (паю)"
 *   (https://github.com/e-government-ua/iTest/issues/30)
 */
public class CertificateOfLandSize extends TestBase {

    @Test(priority = 10)
    public void certificateOfLandSizeTest() {

        String service = Constants.Services.InteractionWithPublicAuthorities.CERTIFICATE_OF_LAND_SIZE;
        String region = Constants.Areas.Region.DNIPROPETROVSKA;
        String district = "ЦНАП Вільногірської міськради";
        String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
        String landAddress = "Вільногірськ, вул. Вільногірська, 1";
        String phone = "0931234567";
        String email = Constants.TestData.PersonalInfo.E_MAIL;
        String applicant = "Особа, в інтересах якої встановлене обмеження";
        String filePath = "src/test/resources/test.jpg";
        String landRegisterNumber = "1234567890:45:456:1234";

        mainPage.typeInSearchField(service);
        mainPage.clickService(service);

        Assert.assertEquals(selectAreaPage.serviceName.getText(), service);
        selectAreaPage.selectRegion(region);

        authorizationPage.privatBankAuthorization();

        landSizeAndExistencePage
                .selectDistrict(district)
                .enterAddress(address)
                .typeInPhoneField(phone)
                .typeInEmailField(email)
                .selectApplicant(applicant)
                .typeInLandAddressField(landAddress)
                .typeInLandRegisterNumberField(landRegisterNumber)
                .attachFile(app, filePath)
                .clickConfirmButton()
                .verifyServiceSuccessCreated()
                .saveReferenceNumber();

        mainPage.goToStatus();
        statusPage.enterReferenceNumber(LandSizeAndExistencePage.referenceNumber)
                .clickViewStatusButton()
                .verifyStatus(Constants.Status.SUCCESS_STATUS);
    }
}
