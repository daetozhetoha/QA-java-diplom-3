package edu.praktikum.diploma.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }

        switch (browser) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver",
                String.format("%s/%s", System.getenv("WEBDRIVERS"),
                        System.getenv("YANDEX_BROWSER_DRIVER_FILENAME")));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }
}
