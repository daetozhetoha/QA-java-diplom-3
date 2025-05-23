package edu.praktikum.diploma;

import edu.praktikum.diploma.api.clients.UserClient;
import edu.praktikum.diploma.api.models.User;
import edu.praktikum.diploma.api.models.UserCreds;
import edu.praktikum.diploma.web.ConstructorPage;
import edu.praktikum.diploma.web.LoginPage;
import edu.praktikum.diploma.web.PassRecoveryPage;
import edu.praktikum.diploma.web.RegistrationPage;
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

import static edu.praktikum.diploma.api.generators.UserGenerator.randomUser;
import static edu.praktikum.diploma.web.driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private ConstructorPage constructorPage;
    private PassRecoveryPage passRecoveryPage;
    private UserClient userClient;
    private String accessToken;
    private String email;
    private String password;
    private User user;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        userClient = new UserClient();
        user = randomUser();
        this.email = user.getEmail();
        this.password = user.getPassword();
        userClient.create(user);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Check if user login after enter button click on the constructor page was successful")
    @Description("Checking if user login after enter button click on the constructor page was successful")
    public void loginUserByClickEnterButtonOnConstructorPageSuccessful() {
        openConstructorPage();
        clickEnterButtonOnConstructorPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        assertTrue("Элемент не отображается на странице", constructorPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Check if user login after personal account button click on the constructor page was successful")
    @Description("Checking if user login after personal account button click on the constructor page was successful")
    public void loginUserByClickPersonalAccountButtonOnConstructorPageSuccessful() {
        openConstructorPage();
        clickPersonalAccountButtonOnConstructorPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        assertTrue("Элемент не отображается на странице", constructorPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Check if user login from registration form was successful")
    @Description("Checking if user login from registration form was successful")
    public void loginUserFromRegistrationFormSuccessful() {
        registrationPage = new RegistrationPage(driver);

        openRegistrationPage();
        clickEnterButtonOnRegistrationPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        assertTrue("Элемент не отображается на странице", constructorPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Check if user login from password recovery form was successful")
    @Description("Checking if user login from password recovery form was successful")
    public void loginUserFromPassRecoveryFormSuccessful() {
        passRecoveryPage = new PassRecoveryPage(driver);

        openPassRecoveryPage();
        clickEnterButtonOnPassRecoveryPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        assertTrue("Элемент не отображается на странице", constructorPage.isPlaceOrderButtonDisplayed());
    }

    @Step("Open the login page")
    public void openConstructorPage() {
        constructorPage.open();
    }

    @Step("Click on enter button on the constructor page")
    public void clickEnterButtonOnConstructorPage() {
        constructorPage.clickEnterButton();
    }

    @Step("Click on personal account button on the constructor page")
    public void clickPersonalAccountButtonOnConstructorPage() {
        constructorPage.clickPersonalAccountButton();
    }

    @Step("Click on enter button on the registration page")
    public void clickEnterButtonOnRegistrationPage() {
        registrationPage.clickEnterButton();
    }

    @Step("Click on enter button on the password recovery page")
    public void clickEnterButtonOnPassRecoveryPage() {
        passRecoveryPage.clickEnterButton();
    }

    @Step("Input email")
    public void inputEmail() {
        loginPage.inputEmail(email);
    }

    @Step("Input password")
    public void inputPassword() {
        loginPage.inputPassword(password);
    }

    @Step("Click enter button on the login page")
    public void clickEnterButtonOnLoginPage() {
        loginPage.clickEnterButton();
    }

    @Step("Open registration page")
    public void openRegistrationPage() {
        registrationPage.open();
    }

    @Step("Open password recovery page")
    public void openPassRecoveryPage() {
        passRecoveryPage.open();
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
