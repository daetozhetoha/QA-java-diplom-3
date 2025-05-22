package edu.praktikum.diploma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassRecoveryPage {
    private WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Кнопка "Войти"
    private By enterButton = By.className("Auth_link__1fOlj");

    public PassRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }
    public PassRecoveryPage open() {
        driver.get(URL);
        return this;
    }
    public PassRecoveryPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}
