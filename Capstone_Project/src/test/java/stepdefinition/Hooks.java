package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utilities.DriverManager;
import utilities.Screenshots;
import utilities.extentreports;

public class Hooks
{
    
    private WebDriver driver;
    
    @Before
    public void setUp(Scenario scenario) 
    {
        
        System.out.println(" Starting Scenario: " + scenario.getName());
        
        
        driver = DriverManager.getDriver();
        extentreports.createTest(scenario.getName(), "Scenario: " + scenario.getName());
        extentreports.logInfo("Test execution started");
    }
    
    @After
    public void tearDown(Scenario scenario)
    {
        DriverManager.pause(2000);
        
        if (scenario.isFailed())
        {
            
            System.out.println("SCENARIO FAILED: " + scenario.getName());
           
            
            extentreports.logFail("Scenario Failed: " + scenario.getName());
            
           
            String screenshotPath = Screenshots.captureScreenshot(driver, scenario.getName());
            if (screenshotPath != null) 
            {
                extentreports.attachScreenshot(screenshotPath);
            }
        }
        else 
        {
           
            System.out.println(" SCENARIO PASSED: " + scenario.getName());
           
            
            extentreports.logPass("Scenario Passed: " + scenario.getName());
        }
        
        extentreports.endTest();
    }
}