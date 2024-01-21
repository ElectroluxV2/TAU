package com.github.electroluxv2.tau;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com.github.electroluxv2.tau")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.github.electroluxv2.tau")
public class SaucedemoTests {
    static Logger logger = Logger.getLogger("SaucedemoTests");
    static By reactBurgerMenuSelector = By.id("react-burger-menu-btn");
    static By logoutSidebarLink = By.id("logout_sidebar_link");

    private final WebDriver driver = new ChromeDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));


    @Given("user navigates to the login page by opening Google Chrome")
    public void userNavigates() {
        logger.info("Using locally available Google Chrome");

        driver.get("https://www.saucedemo.com/");
    }

    @When("user enters correct username and password values")
    public void enterCredentials() {
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
    }

    @Then("user may click hamburger menu and logout")
    public void hamburgerMenu() {
        final var burgerMenu = driver.findElement(reactBurgerMenuSelector);
        logger.info("Opening burger menu");
        burgerMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutSidebarLink));

        final var logoutLink = driver.findElement(logoutSidebarLink);

        assertEquals("Logout", logoutLink.getText());
    }

    @After()
    public void closeBrowser() {
        driver.close();
//        System.exit(0);
    }
}
