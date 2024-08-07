package Supriti.tests;

import Supriti.pages.CartPage;
import Supriti.pages.CheckoutPage;
import Supriti.pages.LoginPage;
import Supriti.pages.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyCheckoutWithMissingInformationTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    String productName = "Sauce Labs Backpack";

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
    public void checkoutWithMissingInformationTest() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        System.out.println("Login successful.");

        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart(productName);
        System.out.println("Product added to cart: " + productName);

        // Navigate to Cart Page
        cartPage = new CartPage(driver);
        cartPage.clickCart();
        System.out.println("Navigated to cart page.");
        Assert.assertTrue(cartPage.verifyItemInCart(productName), "The item was not found in the cart!");

        // Proceed to Checkout
        cartPage.checkout();
        checkoutPage = new CheckoutPage(driver);
        System.out.println("Proceeded to checkout page.");

        // Leave postal code empty and try to continue
        checkoutPage.enterCheckoutInformation("John", "Doe", "");
        checkoutPage.clickContinue();
        System.out.println("Checkout information entered with missing postal code.");

        // Verify the error message
        boolean isErrorDisplayed = checkoutPage.isErrorMessageDisplayed("Error: Postal Code is required");
        System.out.println("Error message displayed: " + isErrorDisplayed);
        Assert.assertTrue(isErrorDisplayed, "Error message was not displayed for missing postal code!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
