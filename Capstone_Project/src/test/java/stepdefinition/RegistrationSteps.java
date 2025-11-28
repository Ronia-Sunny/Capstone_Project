package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.RegistrationPage;
import utilities.DriverManager;
import utilities.extentreports;

public class RegistrationSteps {

    private WebDriver driver;
    private RegistrationPage registrationPage;

    @Given("User is on the Advantage Online Shopping registration page")
    public void user_is_on_the_advantage_online_shopping_registration_page() {
        driver = DriverManager.getDriver();
        registrationPage = new RegistrationPage(driver);
        extentreports.logInfo("Opening registration page");
        registrationPage.navigateToRegistrationPage();
    }

    @When("User enters valid registration details")
    public void user_enters_valid_registration_details() {
        registrationPage.enterRegistrationDetails("John", "Doe", "john.doe@example.com", "Password123", "Password123");
    }

   
    

    @When("User submits the registration form")
    public void user_submits_the_registration_form() {
        registrationPage.submitRegistrationForm();
    }

    @Then("User should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        boolean isRedirected = registrationPage.isRedirectedToLoginPage();
        Assert.assertTrue(isRedirected, "User should be redirected to the login page");
        
    }

    @Then("A success message should be displayed")
    public void a_success_message_should_be_displayed() {
        String successMessage = registrationPage.getSuccessMessage();
        System.out.println("Success Message: " + successMessage);  // Debugging line
        Assert.assertTrue(successMessage.contains("Registration successful"), "Success message should be displayed");
        Allure.step("Valid registration details is passed.");
    }

    @Then("User should see an error message indicating that {string} is required")
    public void user_should_see_an_error_message(String errorType) {
        String errorMessage = registrationPage.getErrorMessage();
        System.out.println("Error Message: " + errorMessage);  // Debugging line
        Assert.assertTrue(errorMessage.contains(errorType), "Error message for " + errorType + " should be displayed");
        Allure.addAttachment("Invalid message occurs" , errorMessage);
    }
    
    @When("User enters invalid registration details missing email")
    public void user_enters_invalid_registration_details_missing_email() {
        registrationPage.enterRegistrationDetails("John", "Doe", "", "Password123", "Password123");
    }

    @When("User enters invalid registration details invalid email")
    public void user_enters_invalid_registration_details_invalid_email() {
        registrationPage.enterRegistrationDetails("John", "Doe", "invalid-email", "Password123", "Password123");
    }

    @When("User enters invalid registration details password mismatch")
    public void user_enters_invalid_registration_details_password_mismatch() {
        registrationPage.enterRegistrationDetails("John", "Doe", "john.doe@example.com", "Password123", "Password456");
    }

}
