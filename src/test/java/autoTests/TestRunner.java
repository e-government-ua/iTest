package autoTests;

import org.testng.annotations.Test;

public class TestRunner extends SetupAndTeardown
{

	TestSuite testSuite = new TestSuite();

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
	public void A1() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"о портале",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A1_AboutPortal(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
	public void A2() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Футер",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A2_Footer(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 3)
	public void A3() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Хедер",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A3_Header(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 4)
	public void A4() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Видача архівних довідок, копій, витягів",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A4_IssueArcDoc(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 5)
	public void A5() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Надання довідки про притягнення до кримінальної відповідальності",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A5_CriminalRecord(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 6)
	public void A6() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"проверка возможности выдачи загран пасспорта",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A6_AvailabilityOfIssuanceAndReplacementPassportForTravelAbroad(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 7)
	public void A7() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Доступ к документам без смс",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A7_AccessToDocumentWithoutSMS(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 8)
	public void A8() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Доступ к документам по смс",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A8_AccessToDocumentWithSMS(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 9)
	public void A9() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Проверка ноториальных",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A9_СheckNotaryTabTest(driver);
	}

	@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 10)
	public void A10() throws Exception
	{
		CustomMethods.addTestNameToTheReport(
				"Мій журнал",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.A10_MyJornal(driver);
	}

	@Test(enabled = true, groups = {"authInteraction", "AssignSocialAssistanceForChildBirth"}, priority = 1)
	public void B1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.B1_SuccessMessagesServicesByPostOffice(driver);
	}

	@Test(enabled = true, groups = {"authInteraction", "AssignSocialAssistanceForChildBirth"}, priority = 2)
	public void B2() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Призначення соціальної допомоги при народженні дитини на рахунок у банку",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.B2_SuccessMessagesServicesByAccountBank(driver);
	}

	@Test(enabled = true, groups = {"authInteraction", "CertificateOfLandSize"}, priority = 3)
	public void B3() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"certificateOfLandSize",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.B3_CertificateOfLandSizeTest(driver);
	}

	@Test(enabled = true, groups = {"authInteraction", "Subsidy"}, priority = 4)
	public void B4() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Субсидии днепропетровск",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.B4_DnipropetrovskSubsidyTest(driver);
	}

	@Test(enabled = true, groups = {"identity", "InternationalPassport"}, priority = 1)
	public void C1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Загран пасспорт",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.C1_DnipropetrovskInternationalPassportTest(driver);
	}

	@Test(enabled = true, groups = {"identity", "Registration"}, priority = 2)
	public void C2() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Незарегестрирован по текущему адресу",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.C2_UnregisterFromCurrentAddressTest(driver);
	}

	@Test(enabled = true, groups = {"police", "CriminalRecord"}, priority = 1)
	public void D1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Криминальные записи",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.D1_DnipropetrovskCriminalRecord(driver);
	}

	@Test(enabled = true, groups = {"police", "RegisterUsedCar"}, priority = 2)
	public void D2() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Регистрация БУ автомобиля",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.D2_RegisterUsedCarTest(driver);
	}

	@Test(enabled = true, groups = {"taxes", "CertificateFromUnifiedRegister"}, priority = 1)
	public void E1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Сертификат",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E1_CertificateFromURSelf(driver);
	}

	@Test(enabled = true, groups = {"taxes", "CertificateFromUnifiedRegister"}, priority = 2)
	public void E2() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Сертификат",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E2_CertificateFromURByOtherCompany(driver);
	}

	@Test(enabled = true, groups = {"taxes", "PensionAmountCertificate"}, priority = 3)
	public void E3() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Сертификат",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E3_CertificateFromURByReferentSelf(driver);
	}

	@Test(enabled = true, groups = {"taxes", "PensionAmountCertificate"}, priority = 4)
	public void E4() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Сертификат",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E4_CertificateFromURByReferentByOtherCompany(driver);
	}

	@Test(enabled = true, groups = {"taxes", "PensionAmountCertificate"}, priority = 5)
	public void E5() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Видача довідки про доходи (розмір пенсії)",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E5_PersonalIncomeCertificateTest(driver);
	}

	@Test(enabled = true, groups = {"taxes", "PersonalIncomeCertificate"}, priority = 6)
	public void E6() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.E6_PersonalIncomeCertificateTest(driver);
	}

	@Test(enabled = true, groups = {"pregnancy", "Social"}, priority = 1)
	public void F1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Призначення допомоги у зв'язку з вагітністю та пологами",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.F1_Pregnancy(driver);
	}

	@Test(enabled = true, groups = {"dashboard", "admins"}, priority = 1)
	public void H1() throws Exception {
		CustomMethods.addTestNameToTheReport(
				"Вход на страницу Dashboards",
				Thread.currentThread().getStackTrace()[1].toString()
		);
		testSuite.H1_EnterDashboard(driver);
	}
}
