package autoTests;

import autoTests.pages.main.*;
import autoTests.pages.service.authorities.interaction.AssignSocialAssistanceForChildBirthPage;
import autoTests.pages.service.authorities.interaction.LandSizeAndExistencePage;
import autoTests.pages.service.authorities.interaction.SubsidyPage;
import autoTests.pages.service.identity.citizenship.residense.InternationalPassportPage;
import autoTests.pages.service.identity.citizenship.residense.UnregisterFromLocationPage;
import autoTests.pages.service.police.traffic.CriminalRecordPage;
import autoTests.pages.service.police.traffic.RegisterUsedCarPage;
import autoTests.pages.service.social.help.PregnancyPage;
import autoTests.pages.service.taxes.CertificateFromUnifiedRegisterPage;
import autoTests.pages.service.taxes.PensionAmountCertificatePage;
import autoTests.pages.service.taxes.PersonalIncomeCertificatePage;
import autoTests.pages.service.test.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSuite extends CustomMethods {
	ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
	CustomMethods customMethods = new CustomMethods();

	//<editor-fold desc="A1 - о портале.">
	public void A1_AboutPortal(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу о портале");
		AboutPortalPage aboutPortalPage = new AboutPortalPage(driver);
		aboutPortalPage.openAboutPortalPage();

		addStepToTheReport("2. Выполним проверки");
		assertEquals(aboutPortalPage.infoBlockOne.getText(),
				"На цьому порталі зібрано послуги, які державні органи України надають громадянам та бізнесу. " +
						"Частина послуг надається через Інтернет (такі послуги позначені іконкою), " +
						"інша частина послуг все ще очікує на своє впровадження в електронній формі.");
		assertEquals(aboutPortalPage.infoBlockTwo.getText(),
				"Цей портал зроблено волонтерською командою iGov в рамках боротьби з корупцією в Україні " +
						"та вдосконалення бізнес-процесів в наших державних органах.");
		assertEquals(aboutPortalPage.infoBlockThree.getText(),
				"Велика подяка ІТ-волонтерам, які взяли та беруть участь у розробці Порталу:");
		assertEquals(aboutPortalPage.infoBlockFour.getText(),
				"Якщо ви маєте досвід у розробці ІТ-продуктів та бажаєте приєднатися до команди ІТ-волонтерів, " +
						"запрошуємо Вас заповнити цю форму.");

	}

	//</editor-fold>
	//<editor-fold desc="A2 - футер.">
	public void A2_Footer(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		Footer footer = new Footer(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		assertTrue(footer.checkSignatureLink.isDisplayed() &&
				footer.portalsNewsOnFacebookLink.isDisplayed() &&
				footer.errorOrABugOnThePortalLink.isDisplayed() &&
				footer.joinOnGitHubLink.isDisplayed() &&
				footer.volunteerIGov.isDisplayed());

	}

	//</editor-fold>
	//<editor-fold desc="A3 - хедер.">
	public void A3_Header(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		Header header = new Header(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		assertTrue(header.servicesLink.isDisplayed() &&
				header.documentsLink.isDisplayed() &&
				//header.statusesLink.isDisplayed() &&
				header.myJournalLink.isDisplayed() &&
				header.aboutPortalLink.isDisplayed());

	}

	//</editor-fold>
	//<editor-fold desc="A4 - Видача архівних довідок, копій, витягів.">
	public void A4_IssueArcDoc(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		String service = "Видача архівних довідок, копій, витягів";
		mainPage.search(service);
		assertTrue(mainPage.isSearchResultContains(service));

	}

	//</editor-fold>
	//<editor-fold desc="A5 - Надання довідки про притягнення до кримінальної відповідальності.">
	public void A5_CriminalRecord(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		StatisticTab statisticTab = new StatisticTab(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		// Data
		String service = Constants.Services.MVD.CRIMINAL_RECORD;
		// Test
		mainPage.search(service);
		mainPage.clickService(service);
		assertTrue(selectAreaPage.isServiceName(service));
		selectAreaPage.openStatisticTab();
		assertTrue(statisticTab.numberOfProvidedServicesColumn.isDisplayed());
		assertTrue(statisticTab.averageScoreColumn.isDisplayed());
		assertTrue(statisticTab.timingColumn.isDisplayed());

	}

	//</editor-fold>
	//<editor-fold desc="A6 - Проверка возможности выдачи загран пасспорта.">
	public void A6_AvailabilityOfIssuanceAndReplacementPassportForTravelAbroad(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		ModalDialog modalDialog = new ModalDialog(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		// Data
		String service = Constants.Services.Identity.INTERNATIONAL_PASSPORT;
		String regions = Constants.Areas.Region.KYIVSKA;
		String cities = Constants.Areas.City.BUCHA;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String successfulServiceMessagesTitle = "Інформація по операції: 'Відсилка запиту на додання нової послуги'";
		String successfulServiceMessagesBody = Constants.AlertMessages.SuccessfulMessages.INFORMED_SERVICE_AVAILABLE_VIA_INTERNET;

		// Test
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(regions);
		selectAreaPage.selectCity(cities);
		//TODO нет кнопки такой при выполнении теста
		//assertTrue(mainPage.changeRegionButton.isDisplayed());
		assertTrue(mainPage.inputEmailAbsentMessageField.isDisplayed());
		assertTrue(mainPage.sendAbsentMessageButton.isDisplayed());
		mainPage.typeInEmailField(email);
		mainPage.clickSendAbsentMessageButton();
		assertEquals(modalDialog.titlePopUpMessages.getText(), successfulServiceMessagesTitle);
		assertEquals(modalDialog.bodyPopUpMessages.getText(), successfulServiceMessagesBody);

	}

	//</editor-fold>
	//<editor-fold desc="A7 - Доступ к документам без смс.">
	public void A7_AccessToDocumentWithoutSMS(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		DocumentsPage documentsPage = new DocumentsPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		customMethods.openDocumentsPage(driver);
		assertTrue(documentsPage.isLoginFormDisplayed());
		bankIdPage.loginByPrivatBankBankID();
		documentsPage.infoBlockDocument.getText();
		assertEquals(documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше " +
				"завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам " +
				"(в тому числі іншим державним або приватним організаціям).");
		String accessCode = documentsPage.getAccessCode("Test");
		documentsPage.isAccessCodeNotNull(accessCode);
		pause(2000); // временно
		bankIdPage.logOut();
		customMethods.openDocumentsPage(driver);
		assertEquals(documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
				"Сертифікат електронно-\n" +
				"цифрового підпису\n" + "IDcard");
		documentsPage.searchDocumentWithCode(accessCode);
		documentsPage.isDocumentFound();

	}

	//</editor-fold>
	//<editor-fold desc="A8 - Доступ к документам по смс.">
	public void A8_AccessToDocumentWithSMS(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		DocumentsPage documentsPage = new DocumentsPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		customMethods.openDocumentsPage(driver);
		assertEquals(documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
				"Сертифікат електронно-\n" +
				"цифрового підпису");
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(documentsPage.infoBlockDocument.getText(), "Тут знаходяться всі Ваші документи, які були раніше завантажені авторизованими організаціями. Ви можете їх скачати або надати доступ третім особам (в тому числі іншим державним або приватним організаціям).");
		String accessCode = documentsPage.getAccessCodeWithPhoneEmail("Test");
		documentsPage.isAccessCodeNotNull(accessCode);
		assertEquals(documentsPage.alertInfoBlock.getText(), "Посилання, за яким користувач може отримати доступ");
		pause(2000); // временно
		bankIdPage.logOut();
		customMethods.openDocumentsPage(driver);
		assertEquals(documentsPage.loginForm.getText(), "Крок 1. Увійдіть в систему через BankID\n" +
				"Сертифікат електронно-\n" +
				"цифрового підпису");
		documentsPage.searchDocumentWithCode(accessCode);
		assertEquals(documentsPage.infoBlockSMS.getText(), "Введіть отриманий Вами SMS код на телефон +38093*****05");
		documentsPage.typeSMSCode();
		assertEquals(documentsPage.errorBlockSMS.getText(), "Невірний код");
	}

	//</editor-fold>
	//<editor-fold desc="A9 - Проверка ноториальных.">
	public void A9_СheckNotaryTabTest(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		DocumentsPage documentsPage = new DocumentsPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		customMethods.openDocumentsPage(driver);
		documentsPage.goToNotaryTab();
		assertEquals(documentsPage.notaryInfoBlock.getText(), "Тут нотаріуси зможуть завантажувати документи із " +
				"електронно-цифровим підписом. Таким чином, через нотаріусів будь-який документ можна буде " +
				"переводити із паперового вигляду у цифровий.");
	}

	//</editor-fold>
	//<editor-fold desc="A10 - Мій журнал.">
	public void A10_MyJornal(WebDriver driver) throws Exception {
		addStepToTheReport("1. Перейдем на страницу");
		MyJournalPage myJournalPage = new MyJournalPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		driver.get(configVariables.baseUrl);
		customMethods.openMyJournalPage(driver);

		addStepToTheReport("2. Выполним проверки");
		assertEquals(myJournalPage.formSignInBankId.getText(), "... або увійдіть в систему для перегляду подій щодо " +
				"усіх Ваших звернень та документів");
		bankIdPage.loginByPrivatBankBankID();
		//assertEquals(myJournalPage.myLog.getText(), "Мій журнал");
		assertEquals(myJournalPage.nextLink.getText(), "Показати ще");
		bankIdPage.logOut();
		customMethods.openMyJournalPage(driver);
		assertEquals(myJournalPage.formSignInBankId.getText(), "... або увійдіть в систему для перегляду подій щодо " +
				"усіх Ваших звернень та документів");
	}
	//</editor-fold>

	//<editor-fold desc="B1 - Призначення соціальної допомоги при народженні дитини через національного оператора поштового зв'язку.">
	public void B1_SuccessMessagesServicesByPostOffice(WebDriver driver) throws Exception {
		//------------------- Тестовые данные -------------------//
		String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String district = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
		String address1 = "проспект карла маркса 22";
		String address2 = "проспект карла маркса 22";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String innScanDocument = "src/test/resources/files/test.jpg";
		String birthScanDocument = "src/test/resources/files/test.jpg";
		String area = "Самарський, м.Дніпропетровськ";
		String transferType = "через національного оператора поштового зв'язку";
		String numberPostOffice = "12345";
		String status = "Заявка подана";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		AssignSocialAssistanceForChildBirthPage assignSocialAssistanceForChildBirthPage =
				new AssignSocialAssistanceForChildBirthPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
		assignSocialAssistanceForChildBirthPage
				.selectDistrict(district)
				.typeInAdress1Field(address1)
				.typeInAdress2Field(address2)
				.typeInEmailField(email)
				.typeInPhoneField(phone);
		customMethods.attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument,
				innScanDocument, driver);
		customMethods.attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument,
				birthScanDocument, driver);
		assignSocialAssistanceForChildBirthPage
				// .selectArea(area)
				.selectTransferTypeField(transferType)
				.typeInNumberPostOfficeField(numberPostOffice)
				.clickConfirmButton()
				.verifyServiceSuccessCreated(email)
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(status);

	}

	//</editor-fold>
	//<editor-fold desc="B2 - Призначення соціальної допомоги при народженні дитини на рахунок у банку.">
	public void B2_SuccessMessagesServicesByAccountBank(WebDriver driver) throws Exception {
		//------------------- Тестовые данные -------------------//
		String service = Constants.Services.InteractionWithPublicAuthorities.ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String district = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
		String address1 = "проспект карла маркса 22";
		String address2 = "проспект карла маркса 22";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String innScanDocument = "src/test/resources/files/test.jpg";
		String birthScanDocument = "src/test/resources/files/test.jpg";
		String area = "Самарський, м.Дніпропетровськ";
		String transferType = "на рахунок у банку";
		String bankName = Constants.TestData.TestBankDetails.BANK_NAME;
		String bankMFO = Constants.TestData.TestBankDetails.BANK_MFO;
		String bankOKPO = Constants.TestData.TestBankDetails.BANK_OKPO;
		String bankAccount = Constants.TestData.TestBankDetails.BANK_ACCOUNT;
		String bankTicket = "src/test/resources/test.jpg";
		String status = "Заявка подана";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		AssignSocialAssistanceForChildBirthPage assignSocialAssistanceForChildBirthPage =
				new AssignSocialAssistanceForChildBirthPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(assignSocialAssistanceForChildBirthPage.serviceName.getText(), service);
		assignSocialAssistanceForChildBirthPage
				.selectDistrict(district)
				.typeInAdress1Field(address1)
				.typeInAdress2Field(address2)
				.typeInEmailField(email)
				.typeInPhoneField(phone);
		customMethods.attachDocument(assignSocialAssistanceForChildBirthPage.attachInnScanDocument,
				innScanDocument, driver);
		customMethods.attachDocument(assignSocialAssistanceForChildBirthPage.attachBirthScanDocument,
				birthScanDocument, driver);
		assignSocialAssistanceForChildBirthPage
				//.selectArea(area)
				.selectTransferTypeField(transferType)
				.typeInBankNameField(bankName)
				.typeInBankMFOField(bankMFO)
				.typeInBankOKPOField(bankOKPO)
				.typeInBankAccountField(bankAccount);
		customMethods.attachDocument(assignSocialAssistanceForChildBirthPage.attachBankTicket, bankTicket, driver);
		assignSocialAssistanceForChildBirthPage
				.clickConfirmButton()
				.verifyServiceSuccessCreated(email)
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(AssignSocialAssistanceForChildBirthPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(status);
	}

	//</editor-fold>
	//<editor-fold desc="B3 - certificateOfLandSize.">
	public void B3_CertificateOfLandSizeTest(WebDriver driver) throws Exception {
		String service = Constants.Services.InteractionWithPublicAuthorities.CERTIFICATE_OF_LAND_SIZE;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPRODZERGINSK;
		String district = "ЦНАП Вільногірської міськради";
		String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
		String landAddress = "Вільногірськ, вул. Вільногірська, 1";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String applicant = "Особа, в інтересах якої встановлене обмеження";
		String filePath = "src/test/resources/files/test.jpg";
		String landRegisterNumber = "1234567890:45:456:1234";
		String status = "";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		LandSizeAndExistencePage landSizeAndExistencePage = new LandSizeAndExistencePage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);

		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();

		landSizeAndExistencePage
				.selectDistrict(district)
				.enterAddress(address)
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.selectApplicant(applicant)
				.typeInLandAddressField(landAddress)
				.typeInLandRegisterNumberField(landRegisterNumber)
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(LandSizeAndExistencePage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(status);
	}

	//</editor-fold>
	//<editor-fold desc="B4 - Субсидии днепропетровск.">
	public void B4_DnipropetrovskSubsidyTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.InteractionWithPublicAuthorities.SUBSIDY;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String area = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
		String placeOfLiving = "test";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String subsidyType = "Оплата житлово-комунальних послуг";
		String electricity = "Не користуюсь";
		String houseArea = "Не користуюсь";
		String gas = "Не користуюсь";
		String coolWater = "Не користуюсь";
		String hotWater = "Не користуюсь";
		String waterBack = "Не користуюсь";
		String warming = "Не користуюсь";
		String garbage = "Не користуюсь";
		String placeType = "окремий будинок";
		String floors = "2";
		String totalPlace = "35";
		String warmingPlace = "32";
		String income = "test";
		String orgName = "test";
		String otherPeople = "Ні";
		String infoAboutoOverload = "test";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		SubsidyPage subsidyPage = new SubsidyPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(subsidyPage.getServiceName(), service);
		subsidyPage
				.selectArea(area)
				.typeInPlaceOfLivingField(placeOfLiving)
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.selectSubsidyType(subsidyType)
				.selectElectricity(electricity)
				.selectHouseArea(houseArea)
				.selectGas(gas)
				.selectCoolWater(coolWater)
				.selectHotWater(hotWater)
				.selectWaterBack(waterBack)
				.selectWarming(warming)
				.selectGarbage(garbage)
				.selectPlaceType(placeType)
				.typeInFloorsField(floors)
				.typeInTotalPlaceField(totalPlace)
				.typeInWarmingPlaceField(warmingPlace)
				.typeInIncomeField(income)
				.typeInOrgNameField(orgName)
				.selectOtherPeople(otherPeople)
				.typeInInfoAboutoOverloa(infoAboutoOverload)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(SubsidyPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS7);
	}
	//</editor-fold>

	//<editor-fold desc="C1 - Загран пасспорт.">
	public void C1_DnipropetrovskInternationalPassportTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Identity.INTERNATIONAL_PASSPORT;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String havePassport = "ні, буду отримувати перший раз";
		String biometrical = "ні";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String area = "Дніпропетровськ (Центральний), вул. Поля, 1";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		InternationalPassportPage internationalPassportPage = new InternationalPassportPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(internationalPassportPage.getServiceName(), service);
		internationalPassportPage
				.selectHavePassport(havePassport)
				.selectBiometrical(biometrical)
				.selectArea(area)
				.selectDay()
				.selectTime()
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(InternationalPassportPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS6);
	}
	//</editor-fold>
	//<editor-fold desc="C2 - Незарегестрирован по текущему адресу.">
	public void C2_UnregisterFromCurrentAddressTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.InteractionWithPublicAuthorities.REGISTRATION;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
		String birthLocation = "Україна,Дніпропетровська,Дніпропетровськ";
		String nationality = "Україна";
		String district = "Амур-Нижньодніпровський РВ у м.Дніпропетровську";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String area = "Дніпропетровськ (Центральний), вул. Поля, 1";
		String surnameChanged = "Ні";
		String militStatus = "Ні";
		String kids = "Ні";
		String status = "";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		UnregisterFromLocationPage unregisterFromLocationPage = new UnregisterFromLocationPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(unregisterFromLocationPage.getServiceName(), service);

		unregisterFromLocationPage
				.selectDistrict(district)
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.selectSurnameChanged(surnameChanged)
				.typeInBirthDateField(birthDate)
				.typeInBirthLocField(birthLocation)
				.selectNationality(nationality)
				.typeInCurrentAddress(birthLocation)
				.typeInNewAddress(area)
				.selectMilitaryStatus(militStatus)
				.selectIfKidsUnderFourteen(kids)
				.selectDay()
				.selectTime()
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();

		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(UnregisterFromLocationPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(status);
	}
	//</editor-fold>

	//<editor-fold desc="D1 - Криминальные записи.">
	public void D1_DnipropetrovskCriminalRecord(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.MVD.CRIMINAL_RECORD;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String birthDate = Constants.TestData.PersonalInfo.BIRTH_DAY;
		String birthLoc = "Украина";
		String country = "Україна";
		String goal = "Оформлення візи для виїзду за кордон.";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String resType = "Прошу надати довідку в паперовому вигляді";
		String email = Constants.TestData.PersonalInfo.E_MAIL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		CriminalRecordPage criminalRecordPage = new CriminalRecordPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(criminalRecordPage.getServiceName(), service);

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

		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(CriminalRecordPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS5);
	}
	//</editor-fold>
	//<editor-fold desc="D2 - Регистрация БУ автомобиля.">
	public void D2_RegisterUsedCarTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.MVD.REGISTER_USED_CAR;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
		String carVinNumber = "5HGEG644387712845";
		String carBrand = "Honda";
		String model = "Pilot";
		String number = "AH4568EE";
		String transitNumber = "AВ7845EE";
		String invoice = "АБВ123456";
		String invoiceDate = "19/01/2016";
		String mreoAddress = "узвіз Тольятті, 2";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		RegisterUsedCarPage registerUsedCarPage = new RegisterUsedCarPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickExpandAllFoundServices();
		//TODO: remove leading whitespace from clickService(" " + service);
		mainPage.clickService(" " + service);

		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();

		registerUsedCarPage
				.typeInAddressField(address)
				.typeInVinCarVinNumberField(carVinNumber)
				.typeInCarBrand(carBrand)
				.typeInModelField(model)
				.typeInExistingNumberField(number)
				.typeInTransitNumberField(transitNumber)
				.typeInCarRegistrationField(invoice)
				.typeInInvoiceField(invoice)
				.typeInInvoiceDateField(invoiceDate)
				.selectMreo(mreoAddress)
				.selectDay()
				.selectTime()
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();

		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(RegisterUsedCarPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS4);
	}
	//</editor-fold>

	//<editor-fold desc="E1 - Сертификат.">
	public void E1_CertificateFromURSelf(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String filePath = "src/test/resources/files/test.jpg";
		String referenceNumber;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		CertificateFromUnifiedRegisterPage certificateFromUnifiedRegisterPage = new CertificateFromUnifiedRegisterPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		referenceNumber =  certificateFromUnifiedRegisterPage
				.typeInEmailField(email)
				.typeInPhoneField(phone)
				.selectFiscalBranchField(fiscalBranch)
				.selectCertificateSubject("по собі")
				.typeInReasonForCertificateField("test")
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS8);
	}
	//</editor-fold>
	//<editor-fold desc="E2 - Сертификат.">
	public void E2_CertificateFromURByOtherCompany(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String filePath = "src/test/resources/files/test.jpg";
		String referenceNumber;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		CertificateFromUnifiedRegisterPage certificateFromUnifiedRegisterPage = new CertificateFromUnifiedRegisterPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		referenceNumber = certificateFromUnifiedRegisterPage
				.typeInEmailField(email)
				.typeInPhoneField(phone)
				.selectFiscalBranchField(fiscalBranch)
				.selectCertificateSubject("по іншому підприємству")
				.typeInEdrpouORIpnField("1234567890")
				.typeInCompanyNameField("test company")
				.typeInReasonForCertificateField("test")
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS8);
	}
	//</editor-fold>
	//<editor-fold desc="E3 - Сертификат.">
	public void E3_CertificateFromURByReferentSelf(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
		String firstName = "ДМИТРО";
		String lastName = "ДУБІЛЕТ";
		String middleName = "ОЛЕКСАНДРОВИЧ";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String filePath = "src/test/resources/files/test.jpg";
		String referenceNumber;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		CertificateFromUnifiedRegisterPage certificateFromUnifiedRegisterPage = new CertificateFromUnifiedRegisterPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		referenceNumber =  certificateFromUnifiedRegisterPage
				.clickOnFillByReferentField()
				.typeInLastNameField(lastName)
				.typeInFirstNameField(firstName)
				.typeInMiddleNameField(middleName)
				.typeInPassportField("TE000000ST test department from 1970")
				.typeInIPNField("1234567890")
				.typeInEmailField(email)
				.typeInPhoneField(phone)
				.selectFiscalBranchField(fiscalBranch)
				.selectCertificateSubject("по собі")
				.typeInReasonForCertificateField("test")
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS8);
	}
	//</editor-fold>
	//<editor-fold desc="E4 - Сертификат.">
	public void E4_CertificateFromURByReferentByOtherCompany(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.CERTIFICATE_FROM_UNIFIED_REGISTER;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String fiscalBranch = "Дніпропетровськ - Бабушкінський (вул. Героїв Сталінграду, 25)|0462";
		String firstName = "ДМИТРО";
		String lastName = "ДУБІЛЕТ";
		String middleName = "ОЛЕКСАНДРОВИЧ";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String filePath = "src/test/resources/files/test.jpg";
		String referenceNumber;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		CertificateFromUnifiedRegisterPage certificateFromUnifiedRegisterPage = new CertificateFromUnifiedRegisterPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		referenceNumber =  certificateFromUnifiedRegisterPage
				.clickOnFillByReferentField()
				.typeInLastNameField(lastName)
				.typeInFirstNameField(firstName)
				.typeInMiddleNameField(middleName)
				.typeInPassportField("TE000000ST test department from 1970")
				.typeInIPNField("1234567890")
				.typeInEmailField(email)
				.typeInPhoneField(phone)
				.selectFiscalBranchField(fiscalBranch)
				.selectCertificateSubject("по іншому підприємству")
				.typeInEdrpouORIpnField("1234567890")
				.typeInCompanyNameField("test company")
				.typeInReasonForCertificateField("test")
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS8);
	}
	//</editor-fold>
	//<editor-fold desc="E5 - Видача довідки про доходи (розмір пенсії).">
	public void E5_PersonalIncomeCertificateTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.PENSION_AOUNT_CERTIFICATE;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String startOfPeriod = "01/02/2009";
		String endOfPeriod = "01/02/2012";
		String address = "Дніпропетровськ - Соборний (вул. перемоги, 12)";
		String fiscalBranch = "Бабушкінський, вул. Героїв Сталінграда, 116-а";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String filePath = "src/test/resources/files/test.jpg";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		PensionAmountCertificatePage pensionAmountCertificatePage = new PensionAmountCertificatePage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();
		pensionAmountCertificatePage
				.typeInAddressField(address)
				.typeInEmailField(email)
				.typeInPhoneField(phone)
				.selectFiscalBranchField(fiscalBranch)
				.selectStartOfPeriodField(startOfPeriod)
				.selectEndOfPeriodField(endOfPeriod)
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(PensionAmountCertificatePage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS3);
	}
	//</editor-fold>
	//<editor-fold desc="E6 - Видача довідки про доходи (розмір пенсії).">
	public void E6_PersonalIncomeCertificateTest(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Taxes.PERSONAL_INCOME_CERTIFICATE;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String startOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
		String endOfPeriod = ""; //TEMP, until interaction with Calendar dropdown will be implemented
		String fiscalBranch = "Дніпропетровськ - АНД (пров. Універсальний, 12)|0461";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String placeOfLiving = "Дніпропетровськ, вул. Поля, 1";
		String aim = "ТЕСТ для подання за вимогою";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		PersonalIncomeCertificatePage personalIncomeCertificatePage = new PersonalIncomeCertificatePage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService(service);
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		personalIncomeCertificatePage
				.selectFiscalBranchField(fiscalBranch)
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.typeInPlaceLivingField(placeOfLiving)
				.selectStartOfPeriodField(startOfPeriod)
				.selectEndOfPeriodField(endOfPeriod)
				.typeInAimField(aim)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(PersonalIncomeCertificatePage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS3);
	}
	//</editor-fold>

	//<editor-fold desc="F1 - Призначення допомоги у зв'язку з вагітністю та пологами.">
	public void F1_Pregnancy(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String service = Constants.Services.Pregnancy.CERTIFICATE_PREGNANCY;
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String district = "Амур-Нижньодніпровський район, м.Дніпропетровськ";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String address = "Дніпропетровськ (Центральний), вул. Поля, 1";
		String filePath = "src/test/resources/files/test.jpg";
		String answer_yes = "так";
		String answer_no = "ні";
		String exemption = "жінка-військовослужбовець";
		String transferType = "через національного оператора поштового зв";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		PregnancyPage pregnancyPage = new PregnancyPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.typeInSearchField(service);
		mainPage.clickService("Призначення допомоги у зв");
		assertEquals(selectAreaPage.serviceName.getText(), service);
		selectAreaPage.selectRegion(region);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(pregnancyPage.getServiceName(),service);
		pregnancyPage
				.selectTown(district)
				.selectZayavka(answer_yes)
				.typeInRegAddr(address)
				.typeInLivAddr(address)
				.enterPhone(phone)
				.enterEmail(email)
				.selectArea_reestr(answer_yes)
				.selectAdoption(answer_no)
				.selectExemption(exemption)
				.selectTransfer_type(transferType)
				.attachFile(filePath)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
		        .clickLinkResort();
		statusPage.verifyStatus(Constants.Status.SUCCESS_STATUS9);
		bankIdPage.logOut();
	}
	//</editor-fold>

	//<editor-fold desc="G1 - ---.">
	public void G1_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_DEPENDENCE_FORM;
		String serviceName = "_test_dependence_form";
		String client = "отримувач особисто";
		String info = "test";
		String document = "src/test/resources/files/test.jpg";
		String email = Constants.TestData.PersonalInfo.E_MAIL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestDependenceFormPage testDependenceFormPage = new TestDependenceFormPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testDependenceFormPage.getServiceName(), serviceName);
		testDependenceFormPage
				.selectClient(client)
				.typeInInfo1Field(info);
		customMethods.attachDocument(testDependenceFormPage.attachDocumentButton, document, driver);
		testDependenceFormPage
				.typeInEmailField(email)
				.clickConfirmButton()
				.verifyServiceSuccessCreated(email)
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestDependenceFormPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS);
	}
	//</editor-fold>
	//<editor-fold desc="G2 - ---.">
	public void G2_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_DEPENDENCE_FORM;
		String serviceName = "_test_dependence_form";
		String client = "представник отримувача";
		String info = "test";
		String document = "src/test/resources/files/test.jpg";
		String email = Constants.TestData.PersonalInfo.E_MAIL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestDependenceFormPage testDependenceFormPage = new TestDependenceFormPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testDependenceFormPage.getServiceName(), serviceName);
		testDependenceFormPage
				.selectClient(client)
				.typeInInfo2Field(info);
		attachDocument(testDependenceFormPage.attachDocumentButton, document, driver);
		testDependenceFormPage
				.typeInEmailField(email)
				.clickConfirmButton()
				.verifyServiceSuccessCreated(email)
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestDependenceFormPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS);
	}
	//</editor-fold>
	//<editor-fold desc="G3 - ---.">
	public void G3_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_FIELDS_BANKID;
		String serviceName = "_test_fields_bankid";
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String country = "country";
		String address = "address";
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String document = "src/test/resources/files/test.jpg";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestFieldsBankidPage testFieldsBankidPage = new TestFieldsBankidPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		selectAreaPage.selectRegion(region);
		selectAreaPage.selectCity(city);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testFieldsBankidPage.getServiceName(), serviceName);
		testFieldsBankidPage
				.typeInCountryField(country)
				.typeInAddressField(address)
				.typeInEmailField(email);
		customMethods.attachDocument(testFieldsBankidPage.attachDocumentButton, document, driver);
		testFieldsBankidPage
				.clickConfirmButton()
				.verifyServiceSuccessCreated(email)
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestFieldsBankidPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS1);
	}
	//</editor-fold>
	//<editor-fold desc="G4 - ---.">
	public void G4_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_LIQPAY;
		String serviceName = "_test_liqpay";
		String region = Constants.Areas.Region.DNIPROPETROVSKA;
		String city = Constants.Areas.City.DNIPROPETROVSK;
		String bankIdAddressField = Constants.TestData.PersonalInfo.BIRTH_LOCAL;
		String vin = "12345678912345678";
		String brand = "brand";
		String model = "model";
		String number = "number";
		String invoiceNumber = "invoiceNumber";
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String email = Constants.TestData.PersonalInfo.E_MAIL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestLiqpayPage testLiqpayPage = new TestLiqpayPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testLiqpayPage.getServiceName(), serviceName);
		testLiqpayPage
				.typeInBankIdAddressField(bankIdAddressField)
				.typeInVinField(vin)
				.typeInBrandField(brand)
				.typeInModelField(model)
				.typeInNumberField(number)
				.typeInInvoiceNumberField(invoiceNumber)
				.selectDate()
				.typeInPhoneField(phone)
				.typeInEmailField(email)
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestLiqpayPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS);
	}
	//</editor-fold>
	//<editor-fold desc="G5 - ---.">
	public void G5_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_MAILER;
		String serviceName = "_test_mailer";
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String document = "src/test/resources/files/test.jpg";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestMailerPage testMailerPage = new TestMailerPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testMailerPage.getServiceName(), serviceName);
		testMailerPage
				.typeInEmailField(email);
		customMethods.attachDocument(testMailerPage.attachDocumentButton, document, driver);
		testMailerPage
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestMailerPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS);
	}
	//</editor-fold>
	//<editor-fold desc="G6 - Тест пустой.">
	public void G6_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_PRINT_FORM;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
	}
	//</editor-fold>
	//<editor-fold desc="G7 - Тест пустой.">
	public void G7_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_QUEUE_CANCEL;

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
	}
	//</editor-fold>
	//<editor-fold desc="G8 - ---.">
	public void G8_TestService(WebDriver driver) throws Exception {
		// ------------------- Тестовые данные -------------------//
		String server = Constants.Server.VERSION_SERVER;
		String service = Constants.TestService.TEST_ZP_CNAP_MAILER;
		String region = Constants.Areas.Region.ZAPORIZHSKA;
		String serviceName = "_test_ZP_cnap_mailer";
		String email = Constants.TestData.PersonalInfo.E_MAIL;
		String phone = Constants.TestData.PersonalInfo.PHONE;
		String document = "src/test/resources/files/test.jpg";

		addStepToTheReport("1. Перейдем на страницу");
		MainPage mainPage = new MainPage(driver);
		TestZPCnapMailerPage testZPCnapMailerPage = new TestZPCnapMailerPage(driver);
		SelectAreaPage selectAreaPage = new SelectAreaPage(driver);
		BankIdPage bankIdPage = new BankIdPage(driver);
		StatusPage statusPage = new StatusPage(driver);
		driver.get(configVariables.baseUrl);

		addStepToTheReport("2. Выполним проверки");
		mainPage.goToTestServices(server, service);
		selectAreaPage.selectRegion(region);
		pause(6000);
		bankIdPage.loginByPrivatBankBankID();
		assertEquals(testZPCnapMailerPage.getServiceName(), serviceName);
		testZPCnapMailerPage
				.typeInPhoneField(phone)
				.typeInEmailField(email);
		customMethods.attachDocument(testZPCnapMailerPage.attachDocumentButton, document, driver);
		testZPCnapMailerPage
				.clickConfirmButton()
				.verifyServiceSuccessCreated()
				.saveReferenceNumber();
		bankIdPage.logOut();
		customMethods.openStatusesPage(driver);
		statusPage.enterReferenceNumber(TestZPCnapMailerPage.referenceNumber)
				.clickViewStatusButton()
				.verifyStatus(Constants.Status.SUCCESS_STATUS2);
	}
	//</editor-fold>

	//<editor-fold desc="H1 - Вход в дашборд">
	public void H1_EnterDashboard(WebDriver driver) throws Exception {
		driver.get(Constants.Dashboard.URL_DASHBOARD);
		EnterDashBoardPage dashBoardPage = new EnterDashBoardPage(driver);
		TasksPage tasksPage = new TasksPage(driver);

		addStepToTheReport("1. Перейдем на входа в дашборд");
		dashBoardPage.enterLogin(Constants.Dashboard.LOGIN_PASSWORD_DASHBOARD);
		dashBoardPage.enterPassword(Constants.Dashboard.LOGIN_PASSWORD_DASHBOARD);
		dashBoardPage.clickButtonEnter();
		tasksPage.verifyListTabs();

	}
	//</editor-fold>
}
