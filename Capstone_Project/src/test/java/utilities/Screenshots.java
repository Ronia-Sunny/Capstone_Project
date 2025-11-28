package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots
{
    
    public static String captureScreenshot(WebDriver driver, String screenshotName)
    {
        try 
        {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            
            File screenshotDir = new File(System.getProperty("user.dir") + "/utilities/Screenshots");
            if (!screenshotDir.exists()) 
            {
                screenshotDir.mkdirs();
            }
            
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotDir, fileName);
            FileUtils.copyFile(sourceFile, destFile);
            
            String screenshotPath = destFile.getAbsolutePath();
            System.out.println(" Screenshot captured: " + fileName);
            return screenshotPath;
            
        } 
        catch (IOException e) 
        {
            System.err.println(" Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}