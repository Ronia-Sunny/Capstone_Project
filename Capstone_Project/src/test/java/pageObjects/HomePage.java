package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.extentreports;
import java.time.Duration;

public class HomePage
{
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    
   
    private static By tabletsCategory = By.id("tabletsTxt");
    private static By speakersCategory = By.id("speakersTxt");
    
    public HomePage(WebDriver driver) 
    {
        HomePage.driver = driver;
        HomePage.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public static void navigateToHomePage() 
    {
        try 
        {
			driver.get("https://www.advantageonlineshopping.com/#/");
		} 
        catch (Exception e)
        {
			
			e.printStackTrace();
		}
        wait.until(ExpectedConditions.presenceOfElementLocated(speakersCategory));
        DriverManager.pause(2000);
        extentreports.logInfo("Navigated to homepage");
    }
    
    public static void clickTabletsCategory()
    {
        DriverManager.pause(1500);
        WebElement tablets = wait.until(ExpectedConditions.elementToBeClickable(tabletsCategory));
        extentreports.logInfo("Clicking on Tablets category");
        tablets.click();
        wait.until(ExpectedConditions.urlContains("category"));
        DriverManager.pause(2000);
        extentreports.logPass("Tablets category clicked successfully");
    }
}