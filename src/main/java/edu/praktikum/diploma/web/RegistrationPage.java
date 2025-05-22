package edu.praktikum.diploma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/register";

    //Поле ввода "Имя"
    private By nameInputField = By.xpath(".//label[text()='Имя']/following-sibling::input[@name='name']");

    //Поле ввода "Email"
    private By emailInputField = By.xpath(".//label[text()='Email']/following-sibling::input[@name='name']");

    //Поле ввода "Email"
    private By passwordInputField = By.xpath(".//input[@name='Пароль']");

    //Кнопка "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Сообщение "Некорректный пароль"
    private By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");

    //Кнопка "Войти"
    private By enterButton = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public RegistrationPage open() {
        driver.get(URL);
        return this;
    }
    public RegistrationPage inputName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
        return this;
    }
    public RegistrationPage inputEmail(String email) {
        driver.findElement(emailInputField).sendKeys(email);
        return this;
    }
    public RegistrationPage inputPassword(String password) {
        driver.findElement(passwordInputField).sendKeys(password);
        return this;
    }
    public RegistrationPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }
    public boolean isWrongPassMessageDisplayed() {
        return driver.findElement(wrongPasswordText).isDisplayed();
    }
    public String checkWrongPassMessage() {
        return driver.findElement(wrongPasswordText).getText();
    }
    public RegistrationPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}
