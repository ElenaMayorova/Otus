import config.UserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pageObject.LoginPage;
import pageObject.Otus;
import pageObject.PersonalPage;
import webFactory.BaseHooks;

public class Lesson12 extends BaseHooks {
    private Otus otus = new Otus(driver);
    LoginPage loginPage = new LoginPage(driver);
    UserConfig userConfig = ConfigFactory.create(UserConfig.class);
    private static final Logger logger = LogManager.getLogger(Lesson12.class);
    PersonalPage personalPage = new PersonalPage(driver);

    @Test
    public void loginOtus() {

        //Открываем сайт Otus.ru
        otus.open();
        //Открываем страницу авторизации
        loginPage.open();
        //Вводим логин и пароль
        loginPage.loginToOtus(userConfig.otusLogin(), userConfig.otusPassword());
        //Открываем страницу персональных данных
        otus.open();
        personalPage.open();
        personalPage.enterLocalFirstName("Ученик")
                .enterLatinFirstName("Student")
                .enterLocalSecondName("Отуса")
                .enterLatinSecondName("Otus")
                .enterBlogName("OtusStudent")
                .enterDateOfBirth("11.05.1988")
                .enterCompany("Отус")
                .enterPosition("Student");
        personalPage.addContactField();
        personalPage.addContactField();
        personalPage.addNewContact(0, "Facebook", userConfig.facebookcontact(), personalPage);
        personalPage.addNewContact(1, "OK", userConfig.okcontact(), personalPage);
        personalPage.addNewContact(2, "VK", userConfig.vkcontact(), personalPage);
        // Нажимаем сохранить
        personalPage.saveAndFillLater();
        //Переоткрываем браузер
        driver.manage().deleteAllCookies();
        logger.info("Открыта новая сессия браузера");
        otus.open();
        loginPage.open();
        loginPage.loginToOtus(userConfig.otusLogin(), userConfig.otusPassword());
        otus.open();
        personalPage.open();
        //проверка введенных данных
        Assert.assertEquals("Ученик", personalPage.getElementValue(personalPage.LOCAL_FIRST_NAME));
        Assert.assertEquals("Student", personalPage.getElementValue(personalPage.LATIN_FIRST_NAME));
        Assert.assertEquals("Отуса", personalPage.getElementValue(personalPage.LOCAL_SECOND_NAME));
        Assert.assertEquals("Otus", personalPage.getElementValue(personalPage.LATIN_SECOND_NAME));
        Assert.assertEquals("OtusStudent", personalPage.getElementValue(personalPage.BLOG_NAME));
        Assert.assertEquals("11.05.1988", personalPage.getElementValue(personalPage.DATE_OF_BIRTH));
        Assert.assertEquals("Отус", personalPage.getElementValue(personalPage.COMPANY));
        Assert.assertEquals("Student", personalPage.getElementValue(personalPage.POSITION));
        Assertions.assertAll(
                () -> personalPage.checkContactInfo(0, "Facebook", userConfig.facebookcontact(), personalPage),
                () -> personalPage.checkContactInfo(1, "OK", userConfig.okcontact(), personalPage),
                () -> personalPage.checkContactInfo(2, "VK", userConfig.vkcontact(), personalPage)
        );
    }

}
