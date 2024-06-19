package Test1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginValidation {

    private WebDriver driver;

//    @Before
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
    
    @Before
    public void setUp() {
    	WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().setup(); // Automatically downloads and manages ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Example of setting Chrome options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void validateLogin() throws InterruptedException {
        try {
            driver.get("http://localhost:3000");

            WebElement usernameInput = driver.findElement(By.id("username"));
            usernameInput.sendKeys("admin");
            Thread.sleep(1000);

            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys("password");
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
            loginButton.click();
            Thread.sleep(2000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/dashboard"));
            Thread.sleep(2000);

            System.out.println("Login successful!");

        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
