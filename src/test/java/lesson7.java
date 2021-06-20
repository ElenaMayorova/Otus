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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class lesson7 {
    public static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(lesson2.class);
    private static ProjectServerConfig projectServerConfig = ConfigFactory.create(ProjectServerConfig.class);

    @BeforeClass
    public static void webDriverOpen() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Запуск ChromeDriver перед тестом");
    }

@Test
    public void openOtus() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
        WebElement element = chromeDriver.findElement(By.xpath("//div[@class='container container-header2']//a[@title='Контакты']"));
        Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.click();

        element = chromeDriver.findElement(By.xpath("//div[@class='c0qfa0-1 lblsQs']/div[@class='c0qfa0-5 cXQVNI']"));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        logger.info(element.getText());
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", element.getText());
        logger.info("Проверяем.что адрес равен: 125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02");
    }
@Test
    public void maximizeWindow() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
        chromeDriver.manage().window().maximize();
        logger.info("Разварачиваем окно  на весь экран, размер окна" + chromeDriver.manage().window().getSize());

    }

@Test
    public void tele2() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт otus открыт");

        chromeDriver.get(projectServerConfig.urlTele2());
        logger.info("Открываем сайт msk.tele2.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Красивые номера - купить красивый федеральный номер телефона Tele2 Москва и Московская область, продажа красивых мобильных номеров", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт tele2 открыт");

        WebElement element = chromeDriver.findElement(By.xpath("//input[@id='searchNumber']"));
        Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.sendKeys("97");
       List< WebElement> elements = chromeDriver.findElements(By.xpath("//div[@class='bundles-row row']//div[@class='phone-number-block']//span[@class='phone-number']//span[contains(text(),'97')]"));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        Assert.assertEquals(false,elements.isEmpty());
        logger.info("Проверяем результат поиска, найденные номера:" );
        for (int i=0;i<=elements.size()-1;i++) {
            logger.info(elements.get(i).getText());
        }

    }
@Test
    public void openOtusFAQ() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
    chromeDriver.manage().window().maximize();
    WebElement element = chromeDriver.findElement(By.xpath("//div[@class='container container-header2']//a[@title='FAQ']"));
    Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.click();
        logger.info("Открыт раздел FAQ");

        element = chromeDriver.findElement(By.xpath("//div[@data-linked-id='6']//div[@class='faq-question__question js-faq-question-question' and text()='Где посмотреть программу интересующего курса?']" ));
        wait = new FluentWait(chromeDriver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        logger.info("Нажимаем \"Где посмотреть программу интересующего курса?\"");
        element.click();

    element = chromeDriver.findElement(By.xpath("//div[@class='faq-question__answer js-faq-answer']"));
    wait = new FluentWait(chromeDriver)
            .withTimeout(20, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.SECONDS)
            .withMessage("Элемент не найден")
            .ignoring(NoSuchElementException.class);
    Assert.assertEquals("Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”", element.getText());
    logger.info("Проверяем ответ на вопрос");
    }

    @Test
    public void otusEmail() {
        String email = "testOtus@mail.ru";
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");

        WebElement element = chromeDriver.findElement(By.xpath("//input[@class='input footer2__subscribe-input']"));
        Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.sendKeys(email);
        logger.info("Вводим email для подписки на новости");

        element = chromeDriver.findElement(By.xpath("//button[@class='footer2__subscribe-button button button_blue button_as-input']"));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.click();
        logger.info("нажимаем кнопку \"Подписаться\"");

        element = chromeDriver.findElement(By.xpath("//p[@class='subscribe-modal__success']"));
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.click();


        Assert.assertEquals("Вы успешно подписались", element.getText());
        logger.info("Проверяем, что подписка оформлена "+ element.getText());
    }

    @AfterClass
    public static void webDriverClose() {
        logger.info("Закрываем ChromeDriver после теста");
        if (chromeDriver != null)
            chromeDriver.quit();
    }
}
