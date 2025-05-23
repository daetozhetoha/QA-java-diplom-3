package edu.praktikum.diploma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //Заголовок "Вход"
    private By enterHeader = By.xpath(".//h2[text() ='Вход']");

    //Поле ввода "Email"
    private By emailInput = By.xpath(".//input[@name='name']");

    //Поле ввода "Пароль"
    private By passwordInput = By.xpath(".//input[@name='Пароль']");

    //Кнопка "Войти"
    private By enterButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public LoginPage open() {
        driver.get(URL);
        return this;
    }
    public boolean isEnterHeaderDisplayed() {
        return driver.findElement(enterHeader).isDisplayed();
    }
    public LoginPage inputEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }
    public LoginPage inputPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }
    public LoginPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}

