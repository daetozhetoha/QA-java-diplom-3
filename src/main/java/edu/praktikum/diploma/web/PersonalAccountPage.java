package edu.praktikum.diploma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");

    //Кнопка "Выход"
    private By exitButton = By.xpath(".//button[text()='Выход']");

    //Логотип "stellar burgers"
   private By stellarBurgersLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");

    //Кнопка "Профиль"
    private By profileButton = By.xpath(".//a[text()='Профиль']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    public PersonalAccountPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }
    public PersonalAccountPage clickExitButton() {
        driver.findElement(exitButton).click();
        return this;
    }
    public PersonalAccountPage clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
        return this;
    }
    public boolean isProfileButtonDisplayed() {
        return driver.findElement(profileButton).isDisplayed();
    }
}