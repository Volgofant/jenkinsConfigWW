import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestMainClass {
    public WebDriver driver;
    private MainPage mainPage;
    private AccountPage accountPage;
    private BasketPage basketPage;
    private CollectAnImagePage collectAnImagePage;
    private RegistrationPage registrationPage;
    private DekorPage dekorPage;
    private FurniturePage furniturePage;
    private MainLoginedPage mainLoginedPage;
    private NoveltiesPage noveltiesPage;
    private PasswordConfirmPage passwordConfirmPage;
    private RestoreForgotPasswordPage restoreForgotPasswordPage;
    private SearchPage searchPage;
    private TextilesPage textilesPage;
    private WareAndAccessoriesPage wareAndAccessoriesPage;
    private WishPage wishPage;
    private MailRuRegistration mailRuRegistration;
    private ProductPage productPage;
    private BillPages billPages;


    public void Wait2() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.className("cookiePolicyOverlay qa-cookiePolicyOverlay cookiePolicyOverlay--active")));
    }

    Date dateNow = new Date();
    SimpleDateFormat format = new SimpleDateFormat("hhч mmмин ssсек");
    String fileName = format.format(dateNow) + ".png";

    public TestMainClass screenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File("C:\\Users\\viktor.nenashev\\screenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }




    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://alice-ru.shop-stage.ww-ru.ru/");
        mainPage = new MainPage(driver);
        mainPage.closeFuckingCoockie();
        registrationPage = new RegistrationPage(driver);
        accountPage = new AccountPage(driver);
        basketPage = new BasketPage(driver);
        collectAnImagePage = new CollectAnImagePage(driver);
        dekorPage = new DekorPage(driver);
        furniturePage = new FurniturePage(driver);
        mainLoginedPage = new MainLoginedPage(driver);
        noveltiesPage = new NoveltiesPage(driver);
        passwordConfirmPage = new PasswordConfirmPage(driver);
        restoreForgotPasswordPage = new RestoreForgotPasswordPage(driver);
        searchPage = new SearchPage(driver);
        textilesPage = new TextilesPage(driver);
        wareAndAccessoriesPage = new WareAndAccessoriesPage(driver);
        wishPage = new WishPage(driver);
        mailRuRegistration = new MailRuRegistration(driver);
        productPage = new ProductPage(driver);
        billPages = new BillPages(driver);

    }

    @Test
    public void registrationNullNameTest() {
         mainPage.clickRegistration();
         registrationPage.insertSignInName("");
         registrationPage.insertSignInSurname("Ненашев");
         registrationPage.insertSignInMail("nenashev" + registrationPage.i + "@mail.ru");
         registrationPage.insertSignInPass("1q2w3e4r");
         registrationPage.checkBoxAgreement();
         registrationPage.checkBoxMailSpam();
         registrationPage.clickSignInButton();
         Assert.assertEquals("Обязательное поле", registrationPage.getErrorMessageNullName());

    }
//    @Test
//    public void registrationNullSurNameTest() {
//        mainPage.clickRegistration();
//        registrationPage.insertSignInName("Виктор");
//        registrationPage.insertSignInSurname("");
//        registrationPage.insertSignInMail("nenashev" + registrationPage.i + "@mail.ru");
//        registrationPage.insertSignInPass("1q2w3e4r");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Обязательное поле", registrationPage.getErrorMessageNullSurName());
//    }
//    @Test
//    public void registrationNullMail() {
//        mainPage.clickRegistration();
//        registrationPage.insertSignInName("Виктор");
//        registrationPage.insertSignInSurname("Ненашев");
//        registrationPage.insertSignInMail("");
//        registrationPage.insertSignInPass("1q2w3e4r");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Неверный адрес электронной почты", registrationPage.getErrorMessageNullMail());
//    }
//    @Test
//    public void registrationRepeatMail() {
//        mainPage.clickRegistration();
//        registrationPage.insertSignInName("Виктор");
//        registrationPage.insertSignInSurname("Ненашев");
//        registrationPage.insertSignInMail("borcuha64@mail.ru");
//        registrationPage.insertSignInPass("1q2w3e4r");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Пользователь с таким адресом уже зарегистрирован", registrationPage.getErrorMessageRepeatMail());
//    }
//    @Test
//    public void registrationWrongMail() {
//        mainPage.clickRegistration();
//        registrationPage.insertSignInName("Виктор");
//        registrationPage.insertSignInSurname("Ненашев");
//        registrationPage.insertSignInMail("borcuha64@s");
//        registrationPage.insertSignInPass("1q2w3e4r");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Неверный адрес электронной почты", driver.findElement(By.xpath("//div[@class=\"s-error msg\"]")).getText());
//    }
//    @Test
//    public void registrationNullPassword() {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Пароль поле слишком короткое (не менее 6 знаков)", registrationPage.getNullPassNo());
//    }
//    @Test
//    public void registrationSmallPassword() {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("12345");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Пароль поле слишком короткое (не менее 6 знаков)", registrationPage.getNullPassNo());
//    }
//    @Test
//    public void registrationBigPassword() {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("123451234512345123451234512345123451234512345123451");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Максимум 50 символов", registrationPage.getThisPageWrongPass());
//    }
//    @Test
//    public void registrationOkFemale() {
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        mainPage.clickRegistration();
//        registrationPage.choiseMaleGender();
//        registrationPage.choiseFemaleGender();
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("nenashev" + str);
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("nenashev" + str, mainPage.getNameLogin());
//    }
//    @Test
//    public void registrationOkMale() throws InterruptedException {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        String constant = "nenashev" + str;
//        registrationPage.choiseMaleGender();
//        registrationPage.insertSignInName(constant);
//        registrationPage.insertSignInSurname(constant);
//        registrationPage.insertSignInMail(constant + "@mail.ru");
//        registrationPage.insertSignInPass(constant);
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Assert.assertEquals(constant, mainPage.getNameLogin());
//        driver.get("https://crm-app-stage.ww-ru.ru/ru/messages?created_at=&ecircle_response=&email=&message_type=&origin=&state=");
//        driver.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("viktor.nenashev@westwing.ru");
//        driver.findElement(By.xpath("//input[@id=\"next\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"Passwd\"]")).sendKeys("9112146104");
//        driver.findElement(By.xpath("//input[@id=\"signIn\"]")).click();
//        Assert.assertEquals(constant + "@mail.ru", driver.findElement(By.xpath("//td[text()=" + constant + "@mail.ru" + "]")).getText());
////        Assert.assertEquals("customer_successfulregistration", driver.findElement(By.xpath("//tbody/tr/td[7]")).getText());
////        Thread.sleep(20000);
////        Assert.assertEquals("executed" , driver.findElement(By.xpath("//tbody/tr/td[3]")).getText());
//    }
//    @Test
//    public void registrationNullAll() {
//        mainPage.clickRegistration();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("Обязательное поле", registrationPage.getErrorMessageNullName());
//        Assert.assertEquals("Обязательное поле", registrationPage.getErrorMessageNullSurName());
//        Assert.assertEquals("Неверный адрес электронной почты", registrationPage.getErrorMessageNullMail());
//        Assert.assertEquals("Пароль поле слишком короткое (не менее 6 знаков)", registrationPage.getErrorMessageMailOnPass());
//        Assert.assertEquals("Обязательное поле", registrationPage.getNullArgeement());
//
//    }
//    @Test
//    public void registrationOnFacebook() throws InterruptedException {
//        mainPage.clickRegistration();
//        String mainWindow = driver.getWindowHandle();
//        registrationPage.clickSingInFacebook();
//        registrationPage.inputFacebookInfo();
//        driver.switchTo().window(mainWindow);
//        WebElement dunamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("accountStep1__header__text")));
//        Assert.assertEquals("Из обзора учетной записи пользователя можно просматривать последние операции и редактировать данные учетной записи. Для этого выберите одну из ссылок ниже для просмотра или редактирования.", accountPage.getHelloMessage());
//    }
//    @Test
//    public void logIngOnFacebook() throws InterruptedException {
//        mainPage.clickRegistration();
//        String mainWindow = driver.getWindowHandle();
//        Thread.sleep(500);
//        registrationPage.loginInFacebook();
//        registrationPage.inputFacebookInfo();
//        driver.switchTo().window(mainWindow);
//        Thread.sleep(4000);
//        Assert.assertEquals("Виктор", mainPage.getNameLogin());
//    }
//    @Test
//    public void WishList() throws InterruptedException {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("kpamel@bk.ru");
//        registrationPage.insertLogInPass("1q2w3e4r");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        String product = productPage.getProductName();
//        productPage.clickButtonInWishList();
//        productPage.clickButtonChoiseWishList();
//        Thread.sleep(1000);
//        mainPage.clickWishSpan();
//        Assert.assertEquals(product, wishPage.getNameWishlist());
//        wishPage.clickDeleteProductWishlist();
//    }
//    @Test
//    public void WishListProductDelete() throws InterruptedException {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("kpamel@bk.ru");
//        registrationPage.insertLogInPass("1q2w3e4r");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        String product = productPage.getProductName();
//        productPage.clickButtonInWishList();
//        productPage.clickButtonChoiseWishList();
//        Thread.sleep(1000);
//        mainPage.clickWishSpan();
//        wishPage.clickDeleteProductWishlist();
//        Assert.assertEquals("Продукт был удален из Вашего списка Список желаний", wishPage.getTextDeleteProduct());
//    }
//    @Test
//    public void WishListProductInBasket() throws InterruptedException {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("kpamel@bk.ru");
//        registrationPage.insertLogInPass("1q2w3e4r");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        String product = productPage.getProductName();
//        productPage.clickButtonInWishList();
//        productPage.clickButtonChoiseWishList();
//        Thread.sleep(1000);
//        mainPage.clickWishSpan();
//        wishPage.clickProductBuy();
//        Thread.sleep(500);
//        Assert.assertEquals("Товар успешно добавлен в корзину", wishPage.getTextProductBuyed());
//        mainPage.clickBasketSpan();
//        Assert.assertEquals(product, basketPage.getProductName());
//        basketPage.clickProductDelete();
//        basketPage.clickConfirmProductDelete();
//        mainPage.clickWishSpan();
//        wishPage.clickDeleteProductWishlist();
//    }
//    @Test
//    public void doOrderFirstTime() {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.choiseMaleGender();
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("nenashev" + str);
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        productPage.clickButtonInBasket();
//        mainPage.clickBasketSpan();
//        basketPage.clickPayButton();
//        billPages.inputName();
//        billPages.inputSurname();
//        billPages.inputStreetNumber();
//        billPages.inputMailIndex();
//        billPages.inputCity();
//        billPages.inputPhone();
//        billPages.clickButtonNextStep();
//        try {
//            billPages.choiseCash();
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        } catch (Exception e) {
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        }
//        Assert.assertEquals("Спасибо за покупку!", billPages.getTextCheckreadyOrder());
//    }
//    @Test
//    public void doOrderRepeatedlyTimeCash() {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("adyshatov@bk.ru");
//        registrationPage.insertLogInPass("westwingpas");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        productPage.clickButtonInBasket();
//        mainPage.clickBasketSpan();
//        basketPage.clickPayButton();
//        try {
//            billPages.choiseCash();
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        } catch (Exception e) {
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        }
//        Assert.assertEquals("Спасибо за покупку!", billPages.getTextCheckreadyOrder());
//        driver.get("https://crm-app-stage.ww-ru.ru/ru/messages?created_at=&ecircle_response=&email=&message_type=&origin=&state=");
//        driver.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("viktor.nenashev@westwing.ru");
//        driver.findElement(By.xpath("//input[@id=\"next\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"Passwd\"]")).sendKeys("9112146104");
//        driver.findElement(By.xpath("//input[@id=\"signIn\"]")).click();
//        Assert.assertEquals("adyshatov@bk.ru", driver.findElement(By.xpath("//tbody/tr/td[2]")).getText());
//        Assert.assertEquals("executed", driver.findElement(By.xpath("//tbody/tr/td[3]")).getText());
//        Assert.assertEquals("sales_orderconfirmation", driver.findElement(By.xpath("//tbody/tr/td[7]")).getText());
//    }
//    @Test
//    public void doOrderRepeatedlyTimeCreditCard() {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("adyshatov@bk.ru");
//        registrationPage.insertLogInPass("westwingpas");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        productPage.clickButtonInBasket();
//        mainPage.clickBasketSpan();
//        basketPage.clickPayButton();
//        try {
//            billPages.choiseCreditCard();
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        } catch (Exception e) {
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        }
//        Assert.assertEquals("ООО \"ВЕСТВИНГ РАША\"", billPages.getTextYandexPayService());
//
//    }
//    @Test
//    public void changePass() throws InterruptedException {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.choiseMaleGender();
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("nenashev" + str + "@mail.ru");
//        registrationPage.insertSignInPass("westwingpas");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        mainPage.clickMyAccount();
//        accountPage.clickChangeDate();
//        accountPage.clickButtonChangePass();
//        accountPage.inputActualPass("westwingpas");
//        accountPage.inputNewPass("newwestwingpas");
//        accountPage.inputNewPassConfirm("newwestwingpas");
//        accountPage.clickSaveNewPass();
//        Thread.sleep(3000);
//        String OkPassChange = accountPage.getTextPassChange();
//        Assert.assertEquals("Пароль успешно изменен", OkPassChange);
//    }
//    @Test
//    public void forgetPass() throws InterruptedException {
//        mainPage.clickRegistration();
//        driver.findElement(By.xpath("//a[@class=\"jsCheckout__forms__checkout__link checkout__forms__checkout__link\"]")).click();
//        driver.findElement(By.xpath("//input[@class=\"passwordForgot__email\"]")).sendKeys("ovetstrahaev@yandex.ru");
//        driver.findElement(By.xpath("//button[@class=\"passwordForgot__button btn-primary ga-forgot-password\"]")).click();
//        Assert.assertEquals("Пожалуйста, проверьте свои входящие", driver.findElement(By.xpath("//div[@class=\"passwordConfirm__headline\"]")).getText());
//        driver.get("https://mail.yandex.ru/?addMultiUserFromDropdownButton=true&uid=928663549#inbox");
//        Thread.sleep(500);
//        driver.findElement(By.xpath("//a[@class=\"button2 button2_size_mail-big button2_theme_mail-white button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"passp-field-login\"]")).sendKeys("ovetstrahaev@yandex.ru\n");
//        driver.findElement(By.xpath("//input[@id=\"passp-field-passwd\"]")).sendKeys("westwingpas");
//        driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action button2_width_max button2_type_submit passp-form-button\"]")).click();
////        driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_normal button2_width_max passp-form-button\"]")).click();
//        driver.findElement(By.xpath("//span[text()='Смена вашего пароля на Westwing.ru']")).click();
//        driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[5]/div/div[3]/div[3]/div[2]/div[5]/div[1]/div/div[3]/div/div[1]/table/tbody/tr[1]/td[2]/table[1]/tbody/tr/td/table[2]/tbody/tr/td/table[3]/tbody/tr/td/div/table/tbody/tr/td/a")).click();
//        for (String winHandle : driver.getWindowHandles()) {
//            System.out.println(winHandle);
//            driver.switchTo().window(winHandle);
//        }
//        driver.findElement(By.xpath("//input[@id=\"PasswordForm_password\"]")).sendKeys("westwingpas");
//        driver.findElement(By.xpath("//button[@class=\"btn-primary restorePassword__form__btn ga-reset-password\"]")).click();
//        Assert.assertEquals("Овец Трахаев", driver.findElement(By.xpath("//p[@class=\"accountStep1__row__box__main__text\"]")).getText());
//        Assert.assertEquals("Пароль успешно изменен", driver.findElement(By.xpath("//div[@class=\"message__text\"]")).getText());
//    }
//    @Test
//    public void stockUploadOnLive() throws InterruptedException {
//        driver.get("http://bob-shop.westwing.ru/stock/upload");
//        driver.findElement(By.xpath("//a[@href=\"http://bob-shop.westwing.ru/stock/upload?setWebsite=8\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
//        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
//        driver.findElement(By.xpath("//input[@id=\"login\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\First test upload.csv");
//        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
//        Assert.assertEquals("741", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
//        driver.get("http://bob-shop.westwing.ru/stock/upload");
//        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\secondarily test upload.csv");
//        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
//        Assert.assertEquals("1284", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
//    }
//    @Test
//    public void stockUploadOnStage() {
//        driver.get("https://bob.shop-stage.ww-ru.ru/stock/upload/");
//        driver.findElement(By.xpath("//a[@href=\"http://bob.shop-stage.ww-ru.ru/stock/upload/?setWebsite=8\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
//        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
//        driver.findElement(By.xpath("//input[@id=\"login\"]")).click();
//        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\first.csv");
//        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
//        Assert.assertEquals("421", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
//        driver.get("https://bob.shop-stage.ww-ru.ru/stock/upload/");
//        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\second.csv");
//        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
//        Assert.assertEquals("936", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
//    }
//    @Test
//    public void doCheckSumOrder() {
//        mainPage.clickRegistration();
//        registrationPage.insertLogInMail("adyshatov@bk.ru");
//        registrationPage.insertLogInPass("westwingpas");
//        registrationPage.clickLogInButton();
//        mainPage.clickFurniture();
//        furniturePage.clickProduct();
//        productPage.clickButtonInBasket();
//        mainPage.clickBasketSpan();
//        String sum = billPages.getFinalSum();
//        basketPage.clickPayButton();
//        try {
//            billPages.choiseCreditCard();
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        } catch (Exception e) {
//            billPages.clickButtonNextStep();
//            billPages.clickCheckoutBtn();
//        }
//        Assert.assertEquals(sum, billPages.getYandexSum());
//    }
//    @Rule
//    public TestWatcher watcher = new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            screenshot();
//        }
//    };
//    @Test
//    public void registrationFullMail() {
//        mainPage.clickRegistration();
//        Integer element = registrationPage.i;
//        String str = Integer.toString(element);
//        registrationPage.insertSignInName("nenashev" + str);
//        registrationPage.insertSignInSurname("nenashev" + str);
//        registrationPage.insertSignInMail("kpamel@bk.ru");
//        registrationPage.insertSignInPass("1q2w3e4r");
//        registrationPage.checkBoxAgreement();
//        registrationPage.checkBoxMailSpam();
//        registrationPage.clickSignInButton();
//        Assert.assertEquals("nenashev" +  str, mainPage.getNameLogin());
//    }    // механизм регистрации нового пользователя.
    @After
    public void tierDown() {
//        screenshot();
        driver.quit();
    }
}
