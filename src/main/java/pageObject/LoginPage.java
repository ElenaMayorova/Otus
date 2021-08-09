package pageObject;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static webFactory.BaseHooks.getClickableElement;

public class LoginPage extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private final By emailField = By.xpath("//input[@placeholder='Электронная почта'][contains(@class, 'email-input')]");
    private final By passwordField = By.xpath("//input[contains(@class, 'psw-input')]");
    public final String URL = config.otusLoginPageUrl();
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    public static WebDriverWait waitExplicit;

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);
        Assert.assertEquals("OTUS - Онлайн-образование",
                driver.getTitle());
        logger.info("Открыта страница авторизации");
        return this;
    }

    public void loginToOtus(String username, String password) {
        getClickableElement(emailField).sendKeys(username);
        getClickableElement(passwordField).sendKeys(password, Keys.ENTER);
        logger.info("Ввели логин и пароль");
    }
}