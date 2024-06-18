package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager

import java.time.Duration;

public class LoginValidation {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Call the function to perform login validation
        validateLogin();
    }

    public static void validateLogin() {
        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        try {
            // Navigate to the login page
            driver.get("http://localhost:3000");

            // Enter username with delay
            WebElement usernameInput = driver.findElement(By.id("username"));
            usernameInput.sendKeys("admin");
            Thread.sleep(1000); // Add delay of 1 second

            // Enter password with delay
            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys("password");
            Thread.sleep(1000); // Add delay of 1 second

            // Click on the login button with delay
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
            loginButton.click();
            Thread.sleep(2000); // Add delay of 2 seconds

            // Wait for the dashboard page to load (assuming it redirects to /dashboard) with explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("/dashboard"));
            Thread.sleep(2000); // Add delay of 2 seconds

            System.out.println("Login successful!");

        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        } finally {
            // Close the browser window
            driver.quit();
        }
    }
}
