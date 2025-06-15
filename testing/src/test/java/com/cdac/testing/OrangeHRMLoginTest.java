package com.cdac.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrangeHRMLoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set path to chromedriver if not in PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(2000); // Wait for page to load

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginBtn.click();

        Thread.sleep(3000); // Wait for login to process

        // Verify login by checking for dashboard element
        boolean isLoggedIn = driver.getCurrentUrl().contains("/dashboard");
        Assert.assertTrue(isLoggedIn, "Login failed!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}