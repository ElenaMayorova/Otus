package stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {


    public static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(MyStepdefs.class);

    @Given("Open WebDriver")
    public void webDriverOpen() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Запуск ChromeDriver перед тестом");
    }

    @Given("Close WebDriver")
    public void webDriverClose() {
        logger.info("Закрываем ChromeDriver после теста");
        if (chromeDriver != null)
            chromeDriver.quit();
    }


    @When("User open Website {string}")
    public void userOpenWebsite(String url) {
        chromeDriver.get(url);
        logger.info("Открываем сайт " + url);
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();


    }

    @Then("User belived that website has open and title = {string}")
    public void userBelivedThatWebsiteHasOpen(String title) {
        Assert.assertEquals(title, chromeDriver.getTitle());
        logger.info("Проверяем, что сайт открыт");
    }

    @When("User searches for available phones that contain numbers {string}")
    public void findPhone(String number) {
        WebElement element = chromeDriver.findElement(By.xpath("//input[@id='searchNumber']"));
        Wait wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        element.sendKeys(number);
    }

    @When("User checks that there are available phone numbers {string}")
    public void availablePhoneNumbers(String number) {
        String xpath = "//div[@class='bundles-row row']//div[@class='phone-number-block']//span[@class='phone-number']//span[contains(text(),"+ number+")]";
        List<WebElement> elements = chromeDriver.findElements(By.xpath(xpath));
        Wait wait = new FluentWait(chromeDriver);
        wait = new FluentWait(chromeDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withMessage("Элемент не найден")
                .ignoring(NoSuchElementException.class);
        Assert.assertEquals(false, elements.isEmpty());
        logger.info("Проверяем результат поиска, найденные номера:");
        for (int i = 0; i <= elements.size() - 1; i++) {
            logger.info(elements.get(i).getText());
        }
    }
}
