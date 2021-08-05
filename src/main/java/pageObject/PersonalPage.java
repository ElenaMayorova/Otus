package pageObject;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

import static webFactory.BaseHooks.getClickableElement;
import static webFactory.BaseHooks.getVisibilityElement;

public class PersonalPage extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(PersonalPage.class);
    private static final By SAVE = By.cssSelector("button[title='Сохранить и заполнить позже']");
    private static final By ADD_CONTACT = By.xpath("//button[contains(@class, 's-lk-cv-custom-select-add')]");
    public static final String CONTACT_TYPE_PATTERN = "//input[@name='contact-%s-service']/following-sibling::div";
    public static final String CONTACT_FIELD_PATTERN = "//input[@name='contact-%s-value']";
    public static final By LOCAL_FIRST_NAME = By.xpath("//input[@id='id_fname']");
    public static final By LATIN_FIRST_NAME = By.cssSelector("[name='fname_latin']");
    public static final By LOCAL_SECOND_NAME = By.cssSelector("[name='lname']");
    public static final By LATIN_SECOND_NAME = By.cssSelector("[name='lname_latin']");
    public static final By BLOG_NAME = By.cssSelector("[name='blog_name']");
    public static final By DATE_OF_BIRTH = By.cssSelector("[name='date_of_birth']");
    public static final By COMPANY = By.cssSelector("[name='company']");
    public static final By POSITION = By.cssSelector("[name='work']");

    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public PersonalPage enterLocalFirstName(String localFirstNameText) {
        fillField(LOCAL_FIRST_NAME, localFirstNameText);
        logger.info("Заполняем Имя");
        return this;
    }

    public PersonalPage enterLatinFirstName(String latinFirstName) {
        fillField(LATIN_FIRST_NAME, latinFirstName);
        logger.info("Заполняем Имя на латинице");
        return this;
    }
    public PersonalPage enterLocalSecondName(String localSecondName) {
        fillField(LOCAL_SECOND_NAME, localSecondName);
        logger.info("Заполняем Фамилию");
        return this;
    }

    public PersonalPage enterLatinSecondName(String latinSecondName) {
        fillField(LATIN_SECOND_NAME, latinSecondName);
        logger.info("Заполняем Фамилию на латинице");
        return this;
    }

    public PersonalPage enterBlogName(String blogName) {
        fillField(BLOG_NAME, blogName);
        logger.info("Заполняем Имя в блоге");
        return this;
    }

    public PersonalPage enterDateOfBirth(String dateOfBirth) {
        fillField(DATE_OF_BIRTH, dateOfBirth);
        logger.info("Заполняем дату рождения");
        return this;
    }

    public PersonalPage enterCompany(String company) {
        fillField(COMPANY, company);
        logger.info("Заполняем компанию");
        return this;
    }

    public PersonalPage enterPosition(String position) {
        fillField(POSITION, position);
        logger.info("Заполняем должность");
        return this;
    }


    public String getElementText(By locator) {
        return getVisibilityElement(locator).getText();
    }
    public String getElementValue(By locator) {
        return getVisibilityElement(locator).getAttribute("value");
    }

    public String getWebElementAttribute(By locator, String attributeName) {
        return getVisibilityElement(locator).getAttribute(attributeName);
    }

    public PersonalPage open() {
        driver.get(config.otusPersonalPageUrl());
        logger.info("Открыта страница персональных данных");
        return this;
    }

    public void addContactField() {
        getClickableElement(ADD_CONTACT).sendKeys(Keys.ENTER);
    }

    public void saveAndFillLater() {
        getClickableElement(SAVE).click();
        logger.info("Сохраняем данные");
    }


    public void fillField(By locator, String value) {
        getClickableElement(locator).clear();
        getClickableElement(locator).sendKeys(value);
    }
 
    public void selectDropdownValue(By locator, String value) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getClickableElement(locator)).click().perform();
        By xpath = By.xpath("//div[not(contains(@class, " +
                "'js-custom-select-options-container hide'))]/div/button[@title='" + value + "']");
        actions.moveToElement(getClickableElement(xpath)).perform();
        getClickableElement(xpath).click();
    }

    public void addNewContact(int contactOrder, String contactType, String contact, PersonalPage page) {
        page.selectDropdownValue(By.xpath(String.format(PersonalPage.CONTACT_TYPE_PATTERN, contactOrder)), contactType);
        page.fillField(By.xpath(String.format(PersonalPage.CONTACT_FIELD_PATTERN, contactOrder)), contact);
        logger.info("Добавлен новый контакт: \"{}\" = {}", contactType, contact);
    }
    public void checkContactInfo(int contactOrder, String expectedContactType, String expectedVal, PersonalPage page) {
        String currentContactType = page.getElementText(
                By.xpath(String.format(PersonalPage.CONTACT_TYPE_PATTERN, contactOrder)));
        String currentValue = page.getWebElementAttribute(
                By.xpath(String.format(PersonalPage.CONTACT_FIELD_PATTERN, contactOrder)), "value");
        Map<String, String> currentContactData = new HashMap<>();
        currentContactData.put(currentContactType, currentValue);
        Map<String, String> expectedContactData = new HashMap<>();
        expectedContactData.put(expectedContactType, expectedVal);
        Assertions.assertEquals(expectedContactData, currentContactData, "Значения не совпали!");
        logger.info("Контакт \"{}\" равен {}", currentContactType, currentValue);
    }

}
