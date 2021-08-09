package pageObject;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Otus extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(Otus.class);
    private final By headerMenu = By.xpath("//span[contains(text(),'и регистрация')]");

    public Otus(WebDriver driver) {
        super(driver);
    }

    public Otus open() {
        driver.get(config.otusUrl());
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям",
                driver.getTitle());
        logger.info("Запущен сайт", config.otusUrl());
        return this;
    }

    public Otus openHeader() {
        driver.findElement(headerMenu).click();
        logger.info("Открыт раздел \"Вход и регистрация\"");
        return this;
    }
}
