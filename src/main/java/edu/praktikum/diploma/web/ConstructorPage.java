package edu.praktikum.diploma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage {
    private WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site";

    //Кнопка "Войти в аккаунт"
    private By enterButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    //Вкладка "Булки"
    private By bunsTab = By.xpath(".//span[text()='Булки']");

    //Вкладка "Соусы"
    private By saucesTab = By.xpath(".//span[text()='Соусы']");

    //Вкладка "Начинки"
    private By fillingsTab = By.xpath(".//span[text()='Начинки']");

    //Выбранная вкладка
    private By selectedTabClass = By.cssSelector(".tab_tab_type_current__2BEPc");

    //Заголовок "Соберите бургер"
    private By assembleBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");

    //Кнопка "Оформить заказ"
    private By placeOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    public ConstructorPage (WebDriver driver) {
        this.driver = driver;
    }
    public ConstructorPage open() {
        driver.get(URL);
        return this;
    }
    public ConstructorPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
    public ConstructorPage clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return this;
    }
    public ConstructorPage clickBunsTab() {
        driver.findElement(bunsTab).click();
        return this;
    }
    public ConstructorPage clickSaucesTab() {
        driver.findElement(saucesTab).click();
        return this;
    }
    public ConstructorPage clickFillingsTab() {
        driver.findElement(fillingsTab).click();
        return this;
    }
    public String getTextFromSelectedTab() {
        WebElement selectedTab = driver.findElement(selectedTabClass);
        return selectedTab.getText();
    }
    public boolean isAssembleBurgerHeaderDisplayed() {
        return driver.findElement(assembleBurgerHeader).isDisplayed();
    }
    public boolean isPlaceOrderButtonDisplayed() {
        return driver.findElement(placeOrderButton).isDisplayed();
    }
}
