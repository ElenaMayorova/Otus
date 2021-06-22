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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class lesson8 {

    public static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(lesson8.class);
    private static ProjectServerConfig projectServerConfig = ConfigFactory.create(ProjectServerConfig.class);

    @BeforeClass
    public static void webDriverOpen() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Запуск ChromeDriver перед тестом");
    }

    @Test
    public void openMarketYandex() throws InterruptedException {
        chromeDriver.get(projectServerConfig.urlyandex());
        logger.info("Открываем сайт ЯндексМаркет");
        Assert.assertEquals("Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
        chromeDriver.manage().window().maximize();


        WebElement element = chromeDriver.findElement(By.xpath("//span[text()='Электроника']"));
        element.click();
        logger.info("Открыт раздел \"Электроника\"");

        element = chromeDriver.findElement(By.xpath("//ul[@data-autotest-id='subItems']//a[text()='Смартфоны']"));
        element.click();
        logger.info("Открыт раздел \"Смартфоны\"");

        element = chromeDriver.findElement(By.xpath("//fieldset[@data-autotest-id='7893318']//button[text()='Показать всё']"));
        Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.click();
        logger.info("Раскрываем список всех производителей");

        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        element = chromeDriver.findElement(By.xpath("//input[@id='7893318-suggester']"));
        element.clear();
        element.sendKeys("Samsung");

        element = chromeDriver.findElement(By.xpath("//input[@id='7893318_153061' and @type ='checkbox' ]"));
        Actions action = new Actions(chromeDriver);
        action.moveToElement(element).click().perform();
        logger.info("Выбираем первого производителя Samsung");

        element = chromeDriver.findElement(By.xpath("//input[@id='7893318-suggester']"));
        element.clear();
        element.sendKeys("Xiaomi");

        element = chromeDriver.findElement(By.xpath("//input[@id='7893318_7701962' and @type ='checkbox' ]"));
        action.moveToElement(element).click().perform();
        logger.info("Выбираем второго производителя  Xiaomi");


        element = chromeDriver.findElement(By.xpath("//button[@data-autotest-id='dprice']"));
        element.click();
        logger.info("Сортируем по возрастанию цены");

        chromeDriver.navigate().refresh();
        element = chromeDriver.findElement((By.xpath("//article[@data-autotest-id='product-snippet']//a[contains(@title,'Samsung')]")));
        wait = new FluentWait(chromeDriver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);

        String eddSamsung = element.getAttribute("title");
        logger.info("Запоминаем имя выбранного телефона" + eddSamsung);

        action.moveToElement(element);

        element = chromeDriver.findElement((By.xpath("//article[contains(@data-autotest-id,'product-snippet') and contains(.,'Samsung')]/descendant::div[contains(@aria-label, 'сравнению')]")));
        element.click();
        logger.info("Добавлем телефон samsung к сравнению" + element.getText());

        element = chromeDriver.findElement((By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(),'добавлен к сравнению')]")));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        Assert.assertEquals("Товар " + eddSamsung + " добавлен к сравнению", element.getText());
        logger.info("Проверяем,что товар " + eddSamsung + " добавлен в сравнение");

        element = chromeDriver.findElement((By.xpath("//article[@data-autotest-id='product-snippet']//a[contains(@title,'Xiaomi')]")));
        wait = new FluentWait(chromeDriver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);

        String eddXiaomi = element.getAttribute("title");
        logger.info("Запоминаем имя выбранного телефона" + eddXiaomi);

        action.moveToElement(element);

        element = chromeDriver.findElement((By.xpath("//article[contains(@data-autotest-id,'product-snippet') and contains(.,'Xiaomi')]/descendant::div[contains(@aria-label, 'сравнению')]")));
        element.click();
        logger.info("Добавлем телефон Xiaomi к сравнению" + element.getText());

        element = chromeDriver.findElement((By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//div[contains(text(),'добавлен к сравнению')]")));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        Assert.assertEquals("Товар " + eddXiaomi + " добавлен к сравнению", element.getText());
        logger.info("Проверяем,что товар " + eddXiaomi + " добавлен в сравнение");

        element = chromeDriver.findElement((By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//span[contains(text(),'Сравнить')]")));
        element.click();
        logger.info("Нажимаем кнопку \"Сравнить\"");

        List <WebElement> smart =chromeDriver.findElements(By.xpath("//div[@data-apiary-widget-id='/content/compareContent']//a[contains (text(),'Смартфон')]"));
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
