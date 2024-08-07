package Supriti.tests;

import Supriti.pages.CartPage;
import Supriti.pages.CheckoutPage;
import Supriti.pages.LoginPage;
import Supriti.pages.ProductCatalogue;
import Supriti.pages.OrderCompletionPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrderTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderCompletionPage orderCompletionPage;
    String productName = "Sauce Labs Bike Light";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.goTo();
    }

    @Test
    public void completeOrderTest() throws InterruptedException {
    	
    // login with valid details
        loginPage.login("standard_user", "secret_sauce");
        
     // Adding product to cart
        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart(productName);

        // Navigate to Cart Page
        cartPage = new CartPage(driver);
        cartPage.clickCart();
        boolean isItemInCart = cartPage.verifyItemInCart(productName);
        Assert.assertTrue(isItemInCart, "The item was not found in the cart!");

        // Proceed to Checkout
        cartPage.checkout();

        // Fill in checkout information and continue
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();

        // Verify order details and finish
        orderCompletionPage = new OrderCompletionPage(driver);
        Assert.assertTrue(orderCompletionPage.verifyOrderDetails(productName), "Order details do not match!");

        orderCompletionPage.clickFinish();

        // Verify order completion
        Assert.assertTrue(orderCompletionPage.verifyOrderCompletion(), "Order was not completed successfully!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
