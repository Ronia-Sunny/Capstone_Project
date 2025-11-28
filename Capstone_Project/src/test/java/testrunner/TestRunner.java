package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.DriverManager;
import utilities.extentreports;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"stepdefinition"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
    },
    monochrome = true,
    dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests
{
    
    @BeforeSuite
    public void setUpSuite()
    {
        
        System.out.println("Test Suite Starting");
       
        extentreports.getInstance();
    }
    
    @AfterSuite
    public void tearDownSuite() 
    {
       
        System.out.println(" Test Suite Finished");
        DriverManager.quitDriver();
        extentreports.flush();
    }
}