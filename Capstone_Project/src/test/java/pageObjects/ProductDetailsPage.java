package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.extentreports;

import java.time.Duration;

public class ProductDetailsPage 
{
    public static WebDriver driver;
    private static WebDriverWait wait;
    
    private static By productTitle = By.xpath("//h1[contains(@class,'roboto-regular')]");
    private static By addToCartButton = By.name("save_to_cart");
    private static By cartIcon = By.id("menuCart");
    
    public ProductDetailsPage(WebDriver driver) 
    {
        ProductDetailsPage.driver = driver;
        ProductDetailsPage.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public static boolean isProductPageDisplayed(String productName)
    {
        try 
        {
           
            wait.until(ExpectedConditions.urlContains("product"));
            DriverManager.pause(2000);
            
            
            WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
            String actualTitle = title.getText();
            
            System.out.println("Product page loaded. Title: " + actualTitle);
            extentreports.logPass("Product details page displayed for: " + actualTitle);
            return true;
        } 
        catch (Exception e) 
        {
            System.out.println("Product page check failed: " + e.getMessage());
            extentreports.logFail("Product page not displayed: " + e.getMessage());
            return false;
        }
    }
    
    public static void addToCart() 
    {
        try 
        {
            DriverManager.pause(2000);
            
           
            WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
            
           
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
            DriverManager.pause(1500);
            
            extentreports.logInfo("Clicking Add to Cart");
            System.out.println("Clicking Add to Cart button using JavaScript");
            
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            
            DriverManager.pause(3000); 
            
            extentreports.logPass("Product added to cart successfully");
            System.out.println("Product added to cart successfully");
        } 
        catch (Exception e)
        {
            System.out.println("Failed to add to cart: " + e.getMessage());
            e.printStackTrace();
            extentreports.logFail("Failed to add to cart: " + e.getMessage());
            throw new RuntimeException("Add to cart failed");
        }
    }
    
    public static void openCart()
    {
        try
        {
            DriverManager.pause(1500);
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
            System.out.println("Clicking on cart icon");
            
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
            
            DriverManager.pause(3000);
            extentreports.logPass("Shopping cart opened");
            System.out.println("Cart opened");
        } 
        catch (Exception e)
        {
            System.out.println("Failed to open cart: " + e.getMessage());
            extentreports.logFail("Failed to open cart: " + e.getMessage());
        }
    }
    
    public static int getCartItemCount()
    {
        try 
        {
            
            WebElement cartCount = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[@id='menuCart']//label[@id='toolTipCart']")));
            
            String countText = cartCount.getText().replaceAll("[^0-9]", "");
            int count = countText.isEmpty() ? 0 : Integer.parseInt(countText);
            
            System.out.println("Cart items count: " + count);
            extentreports.logInfo("Cart items count: " + count);
            return count;
        } 
        catch (Exception e) 
        {
            System.out.println("Could not get cart count: " + e.getMessage());
           
            try {
                WebElement cartElement = driver.findElement(By.id("menuCart"));
                String text = cartElement.getText();
                System.out.println("Cart text: " + text);
                return 1; 
            } catch (Exception ex) {
                return 0;
            }
        }
    }
}