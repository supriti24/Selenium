package Supriti.tests;

import Supriti.pages.CartPage;
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

public class RemoveItemFromCartTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
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
    public void removeItemFromCartTest() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");

        productCatalogue = new ProductCatalogue(driver);
        productCatalogue.addProductToCart(productName);

        // Navigate to Cart Page
        cartPage = new CartPage(driver);
        cartPage.clickCart();
        Assert.assertTrue(cartPage.verifyItemInCart(productName), "The item was not found in the cart!");

        // Remove item from cart
        cartPage.removeItemFromCart(productName);
        Assert.assertFalse(cartPage.verifyItemInCart(productName), "The item was not removed from the cart!");
        
        //continue shopping
        cartPage.continueShopping();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
