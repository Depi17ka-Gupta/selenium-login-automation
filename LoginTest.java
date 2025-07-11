package actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) {
    	
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        // Maximize window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to OrangeHRM demo site
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // -------- Valid Login Test --------
        try {
            // Find elements
            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            // Enter credentials
            username.sendKeys("Admin");
            password.sendKeys("admin123");

            // Click login
            loginButton.click();

            // Wait for page to load and verify URL
            Thread.sleep(2000); // Use WebDriverWait in real projects
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("dashboard")) {
                System.out.println(" Login successful – Dashboard loaded.");
            } else {
                System.out.println(" Login failed – Dashboard not found.");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred during login test: " + e.getMessage());
        }

        // -------- Invalid Login Test --------
        try {
            driver.get("https://opensource-demo.orangehrmlive.com/");

            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            username.sendKeys("Admin");
            password.sendKeys("wrongpassword");
            loginButton.click();

            Thread.sleep(2000); // Wait for error message

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Invalid credentials')]"));
            if (errorMsg.isDisplayed()) {
                System.out.println("Invalid login test passed – Error message displayed.");
            } else {
                System.out.println(" Invalid login test failed – No error message.");
            }

        } catch (Exception e) {
            System.out.println(" Exception during invalid login test: " + e.getMessage());
        }

        // Close browser
        driver.quit();
	}
}
