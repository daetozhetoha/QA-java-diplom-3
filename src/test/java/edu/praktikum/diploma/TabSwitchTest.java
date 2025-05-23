package edu.praktikum.diploma;

import edu.praktikum.diploma.api.clients.UserClient;
import edu.praktikum.diploma.api.models.User;
import edu.praktikum.diploma.api.models.UserCreds;
import edu.praktikum.diploma.web.ConstructorPage;
import edu.praktikum.diploma.web.LoginPage;
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
import static org.junit.Assert.assertEquals;

public class TabSwitchTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ConstructorPage constructorPage;
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
    @DisplayName("Check if click on the buns tab then buns tab become selected")
    @Description("Checking if click on the buns tab then buns tab become selected")
    public void clickBunsTabSwitchesBunsTabToSelected() {
        openLoginPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        clickSaucesTab();
        clickBunsTab();
        assertEquals("Вкладка 'Булки' не выбрана", "Булки", constructorPage.getTextFromSelectedTab());
    }

    @Test
    @DisplayName("Check if click on the sauces tab then sauces tab become selected")
    @Description("Checking if click on the sauces tab then sauces tab become selected")
    public void clickSaucesTabSwitchesSaucesTabToSelected() {
        openLoginPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        clickSaucesTab();
        assertEquals("Вкладка 'Соусы' не выбрана", "Соусы", constructorPage.getTextFromSelectedTab());
    }

    @Test
    @DisplayName("Check if click on the fillings tab then fillings tab become selected")
    @Description("Checking if click on the fillings tab then fillings tab become selected")
    public void clickFillingsTabSwitchesFillingsTabToSelected() {
        openLoginPage();
        inputEmail();
        inputPassword();
        clickEnterButtonOnLoginPage();
        clickFillingsTab();
        assertEquals("Вкладка 'Начинки' не выбрана", "Начинки", constructorPage.getTextFromSelectedTab());
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

    @Step("Click on the buns tab")
    public void clickBunsTab() {
        constructorPage.clickBunsTab();
    }

    @Step("Click on the sauces tab")
    public void clickSaucesTab() {
        constructorPage.clickSaucesTab();
    }

    @Step("Click on the fillings tab")
    public void clickFillingsTab() {
        constructorPage.clickFillingsTab();
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
