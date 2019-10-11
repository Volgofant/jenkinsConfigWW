import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestStock {public WebDriver driver;
    private MainPage mainPage;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("–no-sandbox");
        options.addArguments("–disable-dev-shm-usage");
        options.setExperimentalOption("useAutomationExtension", false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://alice-ru.shop-stage.ww-ru.ru/");
        mainPage = new MainPage(driver);
        mainPage.closeFuckingCoockie();
    }
    @Test
    public void stockUploadOnLive() throws InterruptedException {
        driver.get("http://bob-shop.westwing.ru/stock/upload");
        driver.findElement(By.xpath("//a[@href=\"http://bob-shop.westwing.ru/stock/upload?setWebsite=8\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
        driver.findElement(By.xpath("//input[@id=\"login\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\First test upload.csv");
        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
        Assert.assertEquals("2280", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
        driver.get("http://bob-shop.westwing.ru/stock/upload");
        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Лайв\\secondarily test upload.csv");
        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://bob-shop.westwing.ru/pet/product#120739/creation/simples");
        Assert.assertEquals("2823", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
    }
    @Test
    public void stockUploadOnStage() {
        driver.get("https://bob.shop-stage.ww-ru.ru/stock/upload/");
        driver.findElement(By.xpath("//a[@href=\"http://bob.shop-stage.ww-ru.ru/stock/upload/?setWebsite=8\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("nenashev");
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("nenashev4321");
        driver.findElement(By.xpath("//input[@id=\"login\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\first.csv");
        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
        Assert.assertEquals("421", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
        driver.get("https://bob.shop-stage.ww-ru.ru/stock/upload/");
        driver.findElement(By.xpath("//input[@id=\"doc_path\"]")).sendKeys("C:\\автозагрузка стока Стейдж\\second.csv");
        driver.findElement(By.xpath("//input[@id=\"submitbutton\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://bob.shop-stage.ww-ru.ru/pet/product#120365/creation/simples");
        Assert.assertEquals("936", driver.findElement(By.xpath("//div[@class=\"x-list-body-inner\"]/dl/dt[8]/em")).getText());
    }
    @After
    public void tierDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}

