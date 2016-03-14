package autoTests;


public class Constants {
    public static class Areas {

        //------------------- Регионы  -------------------//
        public static class Region {
            public static final String DNIPROPETROVSKA = "Дніпропетровська";
            public static final String POLTAVSKA = "Полтавська";
            public static final String ZAPORIZHSKA = "Запорізька";
            public static final String KYIVSKA = "Київська";
            public static final String KHERSONSKA = "Херсонська";
        }

        //------------------- Города  --------------------//
        public static class City {
            public static final String DNIPROPETROVSK = "Дніпропетровськ";
            public static final String POLTAVA = "Полтава";
            public static final String DNIPRODZERGINSK = "Дніпродзержинськ";
            public static final String BUCHA = "Буча";
        }

    }

    public static class Services {

        // Міліція та ДАІ
        public static class MVD {
            public static final String CRIMINAL_RECORD = "Надання довідки про притягнення до кримінальної відповідальності, відсутність (наявність) судимості або обмежень, передбачених кримінально-процесуальним законодавством України";
            public static final String REGISTER_USED_CAR = "Реєстрація авто з пробігом в МРЕВ";
        }

        // Взаємодія з державними органами
        public static class InteractionWithPublicAuthorities {
            public static final String SUBSIDY = "Отримання субсидії на оплату житлово-комунальних послуг";
            public static final String REGISTRATION = "Зняття з реєстрації місця проживання";
            public static final String ASSIGN_SOCIAL_ASSISTANCE_FOR_CHILD_BIRTH = "Призначення соціальної допомоги при народженні дитини";
            public static final String CERTIFICATE_OF_LAND_SIZE = "Надання довідки про наявність та розмір земельної частки (паю)";
            public static final String APPEAL_TO_HEAD_OF_REGION = "Звернення до голови ОДА";
        }

        // Посвідчення особи, Громадянство, Місце проживання
        public static class Identity {
            public static final String INTERNATIONAL_PASSPORT = "Видача/заміна паспорта громадянина для виїзду за кордон";
        }

        // Податки
        public static class Taxes {
            public static final String PERSONAL_INCOME_CERTIFICATE = "Отримання довідки про доходи фізичних осіб";
            public static final String PENSION_AOUNT_CERTIFICATE = "Видача довідки про доходи (розмір пенсії)";
            public static final String CERTIFICATE_FROM_UNIFIED_REGISTER = "Надання витягу з реєстру платників єдиного податку";
        }

        // Податки
        public static class Pregnancy {
            public static final String CERTIFICATE_PREGNANCY = "Призначення допомоги у зв'язку з вагітністю та пологами";
            public static final String CERTIFICATE_SOCIAL = "Взаємодія з державними органами";
        }
    }

    public static class TestData {

        //------------------- Данные для подачи заявок  --------------//
        public static class PersonalInfo {
            public static final String PHONE = "380932030405";
            public static final String FIO_UA = "Володимир Володимирович Білявцев";
            public static final String FIO_RUS = "Дубилет Дмитрий Александрович";
            public static final String E_MAIL = "vidokgulich@gmail.com";
            public static final String BIRTH_DAY = "01.01.1970";
            public static final String BIRTH_LOCAL = "Україна,Дніпропетровська,Дніпропетровськ";
            public static final String RESIDENT = "Українське";
        }

        //---------- Данные для входа в Банк ИД ПриватБанк  ----------//
        public static class BankIDprivatBank {
            public static final String LOGIN = "+380102030405";   // номер телефона
            public static final String PASSWORD = "value";   // пароль
            public static final String OTP1 = "12";   // Одноразовый пароль
            public static final String OTP2 = "34";   // Одноразовый пароль
            public static final String OTP3 = "56";   // Одноразовый пароль
        }

        public static class TestBankDetails {
            public static final String BANK_NAME = "ПриватБанк";
            public static final String BANK_MFO = "305299";
            public static final String BANK_OKPO = "14360570";
            public static final String BANK_IPN = "143605704021";
            public static final String BANK_ACCOUNT = "1234567890123456";
        }
    }

    //------------------- УРЛ для работы с тестовыми услугами  ------------------//
    public static class TestService {
        public static final String TEST_DEPENDENCE_FORM = "/service/671/general";
        public static final String TEST_FIELDS_BANKID = "/service/720/general";
        public static final String TEST_LIQPAY = "/service/674/general";
        public static final String TEST_MAILER = "/service/755/general";
        public static final String TEST_PRINT_FORM = "/service/673/general";
        public static final String TEST_QUEUE_CANCEL = "/service/756/general";
        public static final String TEST_ZP_CNAP_MAILER = "/service/1426/general";
        public static final String TEST_DASHBOARD_LOGIN_IN = "https://test-version.region.igov.org.ua/";
    }

    //--------------------- Сервера для запуска тестов  ---------------------------//
    public static class Server {
        public static final String VERSION_SERVER = "https://test-version.igov.org.ua";
        public static final String TEST_SERVER = "https://test.igov.org.ua/";
        // https://test-version.igov.org.ua/   - для регреса
        // https://test.igov.org.ua/   - тестовый
    }

    //------------------- Тестовые данные для входа Дашбоард  ------------------//
    public static class Dashboard{
        public static final String URL_DASHBOARD = "https://test-version.region.igov.org.ua/";
        public static final String LOGIN_PASSWORD_DASHBOARD = "kermit";
    }

    public static class Status {

        public static final String SUCCESS_STATUS = "Заявка подана - рассмотрение и загрузка" ;
        public static final String SUCCESS_STATUS1 = "Заявка подана - обробка заявки";
        public static final String SUCCESS_STATUS2 = "Заявка подана - Формування додатків документів для відправлення держ.службовцям на email";
        public static final String SUCCESS_STATUS3 = "Заявка подана - Обробка";
        public static final String SUCCESS_STATUS4 = "Заявка подана - Перевірка наявності обтяжень";
        public static final String SUCCESS_STATUS5 = "Заявка подана - Надання послуги";
        public static final String SUCCESS_STATUS6 = "Заявка подана - обробка дмс";
        public static final String SUCCESS_STATUS7 = "Заявка подана - Взяти в роботу";
        public static final String SUCCESS_STATUS8 = "Заявка подана";
        public static final String SUCCESS_STATUS9 = "Заявка подана - перевірка документів";
    }


    public static class AlertMessages {
        public static class SuccessfulMessages {
            public static final String INFORMED_SERVICE_AVAILABLE_VIA_INTERNET = "Дякуємо! Ви будете проінформовані, коли ця послуга буде доступна через Інтернет.";
        }
    }
}
