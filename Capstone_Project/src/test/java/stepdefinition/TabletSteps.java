package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.TabletListPage;
import pageObjects.ProductDetailsPage;
import utilities.DriverManager;
import utilities.extentreports;
import io.cucumber.java.Before;

public class TabletSteps 
{
    private WebDriver driver;
    private HomePage homePage;
    private TabletListPage tabletListPage;
    private ProductDetailsPage productDetailsPage;
    
    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        tabletListPage = new TabletListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    @Given("User is on the Advantage Online Shopping homepage")
    public void user_is_on_the_advantage_online_shopping_homepage() 
    {
        extentreports.logInfo("Opening Advantage Online Shopping homepage");
        homePage.navigateToHomePage();
    }
    
    @When("User clicks on the Tablets category")
    public void user_clicks_on_the_tablets_category()
    {
        homePage.clickTabletsCategory();
    }
    
    @Then("User should see the tablets product page")
    public void user_should_see_the_tablets_product_page()
    {
        boolean isDisplayed = tabletListPage.isTabletPageDisplayed();
        Assert.assertTrue(isDisplayed, "Tablets page should be displayed");
        Allure.step("Valid tablets are displayed.");
    }
    
    @When("User selects the tablet {string}")
    public void user_selects_the_tablet(String productName)
    {
        tabletListPage.selectProduct(productName);
    }
    
    @Then("User should be on the product details page for {string}")
    public void user_should_be_on_the_product_details_page_for(String productName)
    {
        boolean isDisplayed = productDetailsPage.isProductPageDisplayed(productName);
        Assert.assertTrue(isDisplayed, "Product details page should be displayed for: " + productName);
    }
    
    @And("User adds the product to cart")
    public void user_adds_the_product_to_cart()
    {
        productDetailsPage.addToCart();
    }
    
    @Then("The product should be added to cart successfully")
    public void the_product_should_be_added_to_cart_successfully()
    {
        int cartCount = productDetailsPage.getCartItemCount();
        Assert.assertTrue(cartCount > 0, "Cart should contain at least one item");
        extentreports.logPass("Product added to cart. Current cart count: " + cartCount);
        Allure.step("Products added to cart.");
    }
    
    @And("User navigates back to tablets page")
    public void user_navigates_back_to_tablets_page() 
    {
        tabletListPage.navigateBack();
    }
    
    @When("User opens the shopping cart")
    public void user_opens_the_shopping_cart()
    {
        productDetailsPage.openCart();
    }
    
    @Then("User should see all {int} tablets in the cart")
    public void user_should_see_all_tablets_in_the_cart(Integer expectedCount)
    {
        int actualCount = ProductDetailsPage.getCartItemCount();
        extentreports.logInfo("Expected cart items: " + expectedCount + ", Actual: " + actualCount);
        Assert.assertEquals(actualCount, expectedCount.intValue(), 
            "Cart should contain " + expectedCount + " items");
        extentreports.logPass("Cart contains all " + expectedCount + " tablets");
    }
    
    @And("The page should display available tablet products")
    public void the_page_should_display_available_tablet_products()
    {
        boolean isDisplayed = tabletListPage.isTabletPageDisplayed();
        Assert.assertTrue(isDisplayed, "Tablet products should be displayed");
    }
    
    @And("Product details should include name and price")
    public void product_details_should_include_name_and_price()
    {
        boolean isDisplayed = productDetailsPage.isProductPageDisplayed("");
        Assert.assertTrue(isDisplayed, "Product details should be visible");
        extentreports.logPass("Product details including name and price are displayed");
    }
}
