package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.extentreports;

import java.time.Duration;

public class TabletListPage 
{
    public static WebDriver driver;
    private static WebDriverWait wait;
    
    private static By productNames = By.className("productName");
    
    public TabletListPage(WebDriver driver)
    {
        TabletListPage.driver = driver;
        TabletListPage.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public static boolean isTabletPageDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(productNames));
            DriverManager.pause(1500);
            extentreports.logPass("Tablet products page displayed");
            return true;
        }
        catch (Exception e) 
        {
            extentreports.logFail("Tablet page not displayed: " + e.getMessage());
            return false;
        }
    }
    
    public static void selectProduct(String productName) 
    {
        try
        {
            DriverManager.pause(1500);
            extentreports.logInfo("Selecting product: " + productName);
            
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'productName') and contains(text(),'" + productName + "')]")));
            product.click();
            
            wait.until(ExpectedConditions.urlContains("product"));
            DriverManager.pause(2000);
            
            extentreports.logPass("Product selected: " + productName);
        } 
        catch (Exception e)
        {
            extentreports.logFail("Failed to select product: " + productName + " - " + e.getMessage());
            throw new RuntimeException("Product selection failed");
        }
    }
    
    public static void navigateBack()
    {
        DriverManager.pause(1000);
        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(productNames));
        DriverManager.pause(2000);
        extentreports.logInfo("Navigated back to tablets page");
    }
}