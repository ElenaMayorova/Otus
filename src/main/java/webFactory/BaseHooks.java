package webFactory;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static sun.plugin2.os.windows.Windows.WAIT_TIMEOUT;


public class BaseHooks {
    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseHooks.class);

    @BeforeClass
    public static void setup() {
        logger.info("Инициализация драйвера");
        WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);

        if (config.browser() != null && browsersContain(config.browser().toUpperCase(Locale.ROOT))) {
            driver = WebDriverFactory.createDriver(WebDriverType.valueOf(config.browser().toUpperCase(Locale.ROOT)));
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else {
            driver = WebDriverFactory.createDriver(WebDriverType.OPERA);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        logger.info("Запущен webdriver {}", driver);
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Закрытие браузера \n\n");
        }
    }

    @After
    public void cleanUp() {
        driver.manage().deleteAllCookies();
        logger.info("Очистка cookies");
    }

    private static boolean browsersContain(String browserName) {
        for (WebDriverType c : WebDriverType.values()) {
            if (c.name().equals(browserName)) {
                return true;
            }
        }
        return false;
    }


    public static WebElement getClickableElement(By locator) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement getVisibilityElement(By locator) {
        return new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
