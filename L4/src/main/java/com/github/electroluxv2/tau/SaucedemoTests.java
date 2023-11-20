package com.github.electroluxv2.tau;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class SaucedemoTests {
    static Logger logger = Logger.getLogger("SaucedemoTests");
    static By reactBurgerMenuSelector = By.id("react-burger-menu-btn");
    static By logoutSidebarLink = By.id("logout_sidebar_link");

    public static void main(String[] args) {
        final var driver = new ChromeDriver();
        try {
            final var wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            logger.info("Using locally available Google Chrome");

            driver.get("https://www.saucedemo.com/");

            final var usernameInput = driver.findElement(By.id("user-name"));
            final var passwordInput = driver.findElement(By.id("password"));

            usernameInput.click();
            usernameInput.sendKeys("standard_user");

            passwordInput.click();
            passwordInput.sendKeys("secret_sauce");

            final var loginButton = driver.findElement(By.id("login-button"));

            loginButton.click();

            logger.info("Logging as standard_user");
            wait.until(ExpectedConditions.visibilityOfElementLocated(reactBurgerMenuSelector));

            logger.info("Logged in");

            final var burgerMenu = driver.findElement(reactBurgerMenuSelector);
            logger.info("Opening burger menu");
            burgerMenu.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutSidebarLink));

            final var logoutLink = driver.findElement(logoutSidebarLink);

            assertEquals("Logout", logoutLink.getText());
        } finally {
            driver.close();
            System.exit(0);
        }
    }
}
