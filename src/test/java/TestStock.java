import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.xpath;

public class TestStock {public WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setUp() {
 //       driver = new ChromeDriver();
 //       driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  //      driver.get("https://alice-ru.shop-stage.ww-ru.ru/");
        mainPage = new MainPage(driver);
        mainPage.closeFuckingCoockie();
    }
    @Test
    public void stockUploadOnLive() throws InterruptedException {
        open("http://bob-shop.westwing.ru/stock/upload");
//        driver.get("http://bob-shop.westwing.ru/stock/upload");
        $(xpath("//a[@href=\"http://bob-shop.westwing.ru/stock/upload?setWebsite=8\"]")).click();
        $(xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
        $(xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
        $(xpath("//input[@id=\"login\"]")).click();
        $(xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\First test upload.csv");
        $(xpath("//input[@id=\"submitbutton\"]")).click();
   //     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        open("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
        Assert.assertEquals("2280", $(xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
        open("http://bob-shop.westwing.ru/stock/upload");
        $(xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\secondarily test upload.csv");
        $(xpath("//input[@id=\"submitbutton\"]")).click();
   //     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        open("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
        Assert.assertEquals("2823", $(xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
    }
    @Test
    public void stockUploadOnStage() {
        open("https://bob.shop-stage.ww-ru.ru/stock/upload/");
        $(xpath("//a[@href=\"http://bob.shop-stage.ww-ru.ru/stock/upload/?setWebsite=8\"]")).click();
        $(xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
        $(xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
        $(xpath("//input[@id=\"login\"]")).click();
        $(xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\first.csv");
        $(xpath("//input[@id=\"submitbutton\"]")).click();
 //       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        open("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
        Assert.assertEquals("421", $(xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
        open("https://bob.shop-stage.ww-ru.ru/stock/upload/");
        $(xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\second.csv");
        $(xpath("//input[@id=\"submitbutton\"]")).click();
  //      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        open("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
        Assert.assertEquals("936", $(xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
    }
    @After
    public void tierDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}

