package edu.praktikum.diploma;

import com.github.javafaker.Faker;
import edu.praktikum.diploma.api.clients.UserClient;
import edu.praktikum.diploma.api.models.UserCreds;
import edu.praktikum.diploma.web.LoginPage;
import edu.praktikum.diploma.web.RegistrationPage;
import static edu.praktikum.diploma.web.driver.WebDriverCreator.createWebDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    private Faker faker;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private UserClient userClient;
    private String accessToken;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        faker = new Faker();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Check user registration with valid credentials")
    @Description("Checking if user registration with valid credentials was successful")
    public void registrationWithValidCredsSuccessful() {
        loginPage = new LoginPage(driver);
        name = faker.name().username();
        email = faker.internet().safeEmailAddress();
        password = faker.internet().password(6, 16);

        openUserRegistrationPage();
        inputName();
        inputEmail();
        inputPassword();
        clickRegisterButton();
        assertTrue("Элемент не виден на странице", loginPage.isEnterHeaderDisplayed());
    }

    @Test
    @DisplayName("Check user registration with short password (less than 6 characters)")
    @Description("Checking if user registration with short password (less than 6 characters) returns error message")
    public void registrationWithShortPasswordReturnsErrorMessage() {
        name = faker.name().username();
        email = faker.internet().safeEmailAddress();
        password = faker.internet().password(1, 6);

        openUserRegistrationPage();
        inputName();
        inputEmail();
        inputPassword();
        clickRegisterButton();
        assertTrue("Сообщение о некорректном пароле не отобразилось", registrationPage.isWrongPassMessageDisplayed());
        assertEquals("Текст ошибки не совпадает", "Некорректный пароль", registrationPage.checkWrongPassMessage());
    }

    @Step("Open user registration page")
    public void openUserRegistrationPage() {
        registrationPage.open();
    }

    @Step("Input name")
    public void inputName() {
        registrationPage.inputName(name);
    }

    @Step("Input email")
    public void inputEmail() {
        registrationPage.inputEmail(email);
    }

    @Step("Input password")
    public void inputPassword() {
        registrationPage.inputPassword(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        registrationPage.clickRegisterButton();
    }

    @After
    public void tearTest() {
        driver.quit();
        if (email != null && password != null) {
            UserCreds userCreds = new UserCreds()
                    .setEmail(email)
                    .setPassword(password);
            Response loginResponse = userClient.login(userCreds);
            accessToken = loginResponse.jsonPath().getString("accessToken");
            if (accessToken !=null && !accessToken.isEmpty()) {
                userClient.delete(accessToken);
            }
        }
    }
}
