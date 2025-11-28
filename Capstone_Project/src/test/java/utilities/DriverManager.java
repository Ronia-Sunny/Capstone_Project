package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

public class DriverManager
{
    private static WebDriver driver;
    
    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            initializeDriver();
        }
        return driver;
    }
    
    private static void initializeDriver()
    {
        
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
       
    }
    
    public static void quitDriver() 
    {
        if (driver != null)
        {
            driver.quit();
            driver = null;
            System.out.println("WebDriver closed");
        }
    }
    
    public static void pause(int milliseconds)
    {
        try 
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}