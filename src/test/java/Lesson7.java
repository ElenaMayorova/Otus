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
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Lesson7 {
    public static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(Lesson7.class);
    private static ProjectServerConfig projectServerConfig = ConfigFactory.create(ProjectServerConfig.class);
    public static WebDriverWait waitExplicit;

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
    public void openOtus() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container container-header2']//a[@title='Контакты']"))).click();
        String address = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c0qfa0-1 lblsQs']/div[@class='c0qfa0-5 cXQVNI']"))).getText();
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", address);
        logger.info("Проверяем.что адрес равен: 125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02");
    }

    @Test
    public void tele2() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт otus открыт");

        chromeDriver.get(projectServerConfig.urlTele2());
        logger.info("Открываем сайт msk.tele2.ru");
        Assert.assertEquals("Красивые номера - купить красивый федеральный номер телефона Tele2 Москва и Московская область, продажа красивых мобильных номеров", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт tele2 открыт");

        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='searchNumber']"))).sendKeys("97");
        logger.info("Вводим номер телефона для поиска");

        List<WebElement> elements = waitExplicit.until(ExpectedConditions.visibilityOfAllElements(chromeDriver.findElements(By.xpath("//div[@class='bundles-row row']//div[@class='phone-number-block']//span[@class='phone-number']//span[contains(text(),'97')]"))));
        Assert.assertEquals(false, elements.isEmpty());
        logger.info("Проверяем результат поиска, найденные номера:");
        for (int i = 0; i <= elements.size() - 1; i++) {
            logger.info(elements.get(i).getText());
        }

    }

    @Test
    public void openOtusFAQ() {
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
        WebElement faq = chromeDriver.findElement(By.xpath("//div[@class='container container-header2']//a[@title='FAQ']"));
        faq.click();
        logger.info("Открыт раздел FAQ");

        WebElement question = chromeDriver.findElement(By.xpath("//div[@data-linked-id='6']//div[@class='faq-question__question js-faq-question-question' and text()='Где посмотреть программу интересующего курса?']"));
        logger.info("Нажимаем \"Где посмотреть программу интересующего курса?\"");
        question.click();

        WebElement answer = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='faq-question__answer js-faq-answer']")));
        chromeDriver.findElement(By.xpath("//div[@class='faq-question__answer js-faq-answer']"));
        Assert.assertEquals("Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”", answer.getText());
        logger.info("Проверяем ответ на вопрос");
    }

    @Test
    public void otusEmail() {
        String email = "testOtus@mail.ru";
        chromeDriver.get(projectServerConfig.url());
        logger.info("Открываем сайт otus.ru");
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");

        WebElement emailInput = chromeDriver.findElement(By.xpath("//input[@class='input footer2__subscribe-input']"));
        emailInput.sendKeys(email);
        logger.info("Вводим email для подписки на новости");

        WebElement buttonSubscribe = chromeDriver.findElement(By.xpath("//button[@class='footer2__subscribe-button button button_blue button_as-input']"));
        buttonSubscribe.click();
        logger.info("нажимаем кнопку \"Подписаться\"");

        WebElement successSubscribe = waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='subscribe-modal__success']")));
        successSubscribe.click();

        Assert.assertEquals("Вы успешно подписались", successSubscribe.getText());
        logger.info("Проверяем, что подписка оформлена " + successSubscribe.getText());
    }

    @AfterClass
    public static void webDriverClose() {
        logger.info("Закрываем ChromeDriver после теста");
        if (chromeDriver != null)
            chromeDriver.quit();
    }
}
