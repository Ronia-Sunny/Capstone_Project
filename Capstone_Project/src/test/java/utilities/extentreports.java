package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;

public class extentreports
{
    
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;
    
    public static ExtentReports getInstance() 
    {
        if (extent == null)
        {
            createInstance();
        }
        return extent;
    }
    
    private static void createInstance()
    {
        reportPath = System.getProperty("user.dir") + "/TestReports";
        File reportDir = new File(reportPath);
        if (!reportDir.exists())
        {
            reportDir.mkdirs();
        }
        
        String reportFile = reportPath + "/Report.html";
        extent = new ExtentReports(reportFile, true);
        
        extent.addSystemInfo("Application", "Advantage Online Shopping");
        extent.addSystemInfo("Environment", "QA");
        extent.addSystemInfo("Browser", "Microsoft Edge");
        extent.addSystemInfo("OS", System.getProperty("os.name"));
        extent.addSystemInfo("User", System.getProperty("user.name"));
        extent.addSystemInfo("Java Version", System.getProperty("java.version"));
        
        
        System.out.println(" Extent Report Initialized");
        System.out.println(" Report: " + reportFile);
        
    }
    
    public static ExtentTest createTest(String testName, String description)
    {
        ExtentTest extentTest = extent.startTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }
    
    public static ExtentTest getTest() 
    {
        return test.get();
    }
    
    public static void endTest()
    {
        if (extent != null && test.get() != null) 
        {
            extent.endTest(test.get());
        }
    }
    
    public static void logInfo(String message)
    {
        if (test.get() != null) 
        {
            test.get().log(LogStatus.INFO, message);
            System.out.println("  " + message);
        }
    }
    
    public static void logPass(String message)
    {
        if (test.get() != null)
        {
            test.get().log(LogStatus.PASS, " " + message);
            System.out.println(" " + message);
        }
    }
    
    public static void logFail(String message)
    {
        if (test.get() != null)
        {
            test.get().log(LogStatus.FAIL, " " + message);
            System.err.println(" " + message);
        }
    }
    
    public static void logWarning(String message)
    {
        if (test.get() != null)
        {
            test.get().log(LogStatus.WARNING, "  " + message);
            System.out.println("  " + message);
        }
    }
    
    public static void attachScreenshot(String screenshotPath)
    {
        if (test.get() != null && screenshotPath != null)
        {
            test.get().log(LogStatus.INFO, "Screenshot: " + test.get().addScreenCapture(screenshotPath));
        }
    }
    
    public static void flush() 
    {
        if (extent != null)
        {
            extent.flush();
            extent.close();
            
            System.out.println(" Extent Report Generated");
            System.out.println(" Location: " + reportPath + "/Report.html");
            
        }
    }
}