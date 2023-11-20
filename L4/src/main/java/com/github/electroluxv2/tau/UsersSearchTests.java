package com.github.electroluxv2.tau;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class UsersSearchTests {
    static Logger logger = Logger.getLogger("UsersSearchTests");

    public static void main(String[] args) {
        final var driver = new FirefoxDriver();
        try {
            final var wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            logger.info("Using locally available Firefox");

            driver.get("https://gh-users-search.netlify.app/");

            final var searchBar = driver.findElement(new By.ByCssSelector("[data-testid=search-bar]"));
            searchBar.click();
            searchBar.sendKeys("ElectroluxV2");

            final var searchButton = driver.findElement(new By.ByCssSelector("button[type=submit]"));
            searchButton.click();
            logger.info("Searching for ElectroluxV2");

            final var bioParagraph = driver.findElement(new By.ByClassName("bio"));
            wait.until(ExpectedConditions.textToBePresentInElement(bioParagraph, "SIGABRT"));

            Assert.assertEquals("SIGABRT", bioParagraph.getText());
        } finally {
            driver.close();
            System.exit(0);
        }
    }
}
