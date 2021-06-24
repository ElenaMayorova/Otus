import config.ProjectServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lesson8 {

    public static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(Lesson8.class);
    public static WebDriverWait waitExplicit;
    private static ProjectServerConfig projectServerConfig = ConfigFactory.create(ProjectServerConfig.class);

    @BeforeClass
    public static void webDriverOpen() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        waitExplicit = new WebDriverWait(chromeDriver, 10);
        logger.info("Запуск ChromeDriver перед тестом");
        chromeDriver.manage().window().maximize();
        logger.info("Разварачиваем окно  на весь экран, размер окна" + chromeDriver.manage().window().getSize());

    }

    @Test
    public void openMarketYandex() throws InterruptedException {
        chromeDriver.get(projectServerConfig.urlyandex());
        logger.info("Открываем сайт ЯндексМаркет");
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");

        WebElement electronics = chromeDriver.findElement(By.xpath("//span[text()='Электроника']"));
        electronics.click();
        logger.info("Открыт раздел \"Электроника\"");

        WebElement smartphone = chromeDriver.findElement(By.xpath("//ul[@data-autotest-id='subItems']//a[text()='Смартфоны']"));
        smartphone.click();
        logger.info("Открыт раздел \"Смартфоны\"");

        WebElement showAll = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@data-autotest-id='7893318']//button[text()='Показать всё']")));
        showAll.click();
        logger.info("Раскрываем список всех производителей");

        WebElement smartphoneSamsung = chromeDriver.findElement(By.xpath("//input[@id='7893318-suggester']"));
        smartphoneSamsung.clear();
        smartphoneSamsung.sendKeys("Samsung");

        WebElement chooseSamsung = chromeDriver.findElement(By.xpath("//input[@id='7893318_153061' and @type ='checkbox' ]"));
        Actions action = new Actions(chromeDriver);
        action.moveToElement(chooseSamsung).click().perform();
        logger.info("Выбираем первого производителя Samsung");

        WebElement smartphoneXiaomi = chromeDriver.findElement(By.xpath("//input[@id='7893318-suggester']"));
        smartphoneXiaomi.clear();
        smartphoneXiaomi.sendKeys("Xiaomi");

        WebElement chooseXiaomi = chromeDriver.findElement(By.xpath("//input[@id='7893318_7701962' and @type ='checkbox' ]"));
        action.moveToElement(chooseXiaomi).click().perform();
        logger.info("Выбираем второго производителя  Xiaomi");

        WebElement sortByPrice = chromeDriver.findElement(By.xpath("//button[@data-autotest-id='dprice']"));
        sortByPrice.click();
        logger.info("Сортируем по возрастанию цены");

        chromeDriver.navigate().refresh();
        WebElement chooseFirstsamsung = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-autotest-id='product-snippet']//a[contains(@title,'Samsung')]")));
        String eddSamsung = chooseFirstsamsung.getAttribute("title");
        logger.info("Запоминаем имя выбранного телефона" + eddSamsung);
        action.moveToElement(chooseFirstsamsung);

        WebElement addToComparisonSamsung = chromeDriver.findElement((By.xpath("//article[contains(@data-autotest-id,'product-snippet') and contains(.,'Samsung')]/descendant::div[contains(@aria-label, 'сравнению')]")));
        addToComparisonSamsung.click();
        logger.info("Добавлем телефон samsung к сравнению" + addToComparisonSamsung.getText());

        WebElement addedToComparisonSamsung = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(),'добавлен к сравнению')]")));
        Assert.assertEquals("Товар " + eddSamsung + " добавлен к сравнению", addedToComparisonSamsung.getText());
        logger.info("Проверяем,что товар " + eddSamsung + " добавлен в сравнение");

        WebElement chooseFirstXiaomi = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article[@data-autotest-id='product-snippet']//a[contains(@title,'Xiaomi')]")));
        String eddXiaomi = chooseFirstXiaomi.getAttribute("title");
        logger.info("Запоминаем имя выбранного телефона" + eddXiaomi);
        action.moveToElement(chooseFirstXiaomi);

        WebElement addToComparisonXiaomi = chromeDriver.findElement((By.xpath("//article[contains(@data-autotest-id,'product-snippet') and contains(.,'Xiaomi')]/descendant::div[contains(@aria-label, 'сравнению')]")));
        addToComparisonXiaomi.click();
        logger.info("Добавлем телефон Xiaomi к сравнению" + addToComparisonXiaomi.getText());

        WebElement addedToComparisonXiaomi = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(),'добавлен к сравнению')]")));
        Assert.assertEquals("Товар " + eddXiaomi + " добавлен к сравнению", addedToComparisonXiaomi.getText());
        logger.info("Проверяем,что товар " + eddXiaomi + " добавлен в сравнение");

        WebElement buttonCompare = chromeDriver.findElement((By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//span[contains(text(),'Сравнить')]")));
        buttonCompare.click();
        logger.info("Нажимаем кнопку \"Сравнить\"");

        List<WebElement> smart = chromeDriver.findElements(By.xpath("//div[@data-apiary-widget-id='/content/compareContent']//a[contains (text(),'Смартфон')]"));
        Assert.assertEquals(2, smart.size());
        logger.info("Проверяем,что в сравнении у нас 2 товара");
    }


    @AfterClass
    public static void webDriverClose() {
        logger.info("Закрываем ChromeDriver после теста");
        if (chromeDriver != null)
            chromeDriver.quit();
    }
}
