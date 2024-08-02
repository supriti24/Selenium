package Supriti.tests;

import Supriti.pages.LoginPage;
import Supriti.pages.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StandAloneTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductCatalogue productCatalogue;
    String productName = "Sauce Labs Backpack";

    @BeforeClass
    public void setup() {
    	
    	
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
        loginPage = new LoginPage(driver); 
        loginPage.goTo();
        
    }

    @Test
    public void e2e() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        loginPage = new LoginPage(driver); 
        productCatalogue = new ProductCatalogue(driver);
     //   List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }
    
   
    
    

    @AfterClass
    public void teardown() {
    	  if (driver != null) {
              driver.quit();
          }
    }
}
