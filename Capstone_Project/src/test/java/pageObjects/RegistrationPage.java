package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    // Locators
    private By createAccountLink = By.linkText("CREATE NEW ACCOUNT");
    private By firstNameField    = By.name("first_name");
    private By lastNameField     = By.name("last_name");
    private By emailField        = By.name("email");
    private By passwordField     = By.name("password");
    private By confirmPasswordField = By.name("confirm_password");
    private By submitButton      = By.id("register_btn");
    private By successMessage    = By.xpath("//div[contains(@class,'success') and contains(text(),'successful')]");
    private By errorMessage      = By.xpath("//div[contains(@class,'error')]");
    private By loginPageLink     = By.linkText("SIGN IN");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRegistrationPage() {
        driver.get("https://www.advantageonlineshopping.com/#/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased timeout
        WebElement createLink = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
        createLink.click();  // Click the "CREATE NEW ACCOUNT" link
    }


    public void enterRegistrationDetails(String firstName, String lastName, String email, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        firstNameInput.sendKeys(firstName);

        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void submitRegistrationForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        btn.click();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return msg.getText();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return msg.getText();
    }

    public boolean isRedirectedToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageLink));
        return link.isDisplayed();
    }
}
