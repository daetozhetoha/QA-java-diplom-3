package edu.praktikum.diploma;

import edu.praktikum.diploma.api.clients.UserClient;
import edu.praktikum.diploma.api.models.User;
import edu.praktikum.diploma.api.models.UserCreds;
import edu.praktikum.diploma.web.*;
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

public class PersonalAccountEnterTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private ConstructorPage constructorPage;
    private PersonalAccountPage personalAccountPage;
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
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @Test
    @DisplayName("Check if user`s enter to personal account page from constructor page was successful")
    @Description("Checking if user`s enter to personal account page from constructor page was successful")
    public void enterToPersonalAccountPageFromConstructorPageSuccessful() {
        openLoginPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        clickPersonalAccountButtonOnLoginPage();
        assertTrue("Элемент не отображается на странице", personalAccountPage.isProfileButtonDisplayed());
    }

    @Step("Open the login page")
    public void openLoginPage() {
        loginPage.open();
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

    @Step("Click personal account button on the constructor page")
    public void clickPersonalAccountButtonOnLoginPage() {
        constructorPage.clickPersonalAccountButton();
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
