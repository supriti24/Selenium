package Supriti.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue {
    WebDriver driver;
    
    //constructor
    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    
//    @FindBy(xpath = "//div[@class='inventory_item'][1]//button")
//    WebElement firstItemAddToCartButton;
    
   @FindBy(css = ".inventory_item")
    List<WebElement> products;
   
   By productsBy = By.cssSelector(".inventory_item");
   By addToCart = By.cssSelector(".btn_primary");
   
   public List<WebElement> getProductList() {
		//waitForElementToAppear(productsBy);
		return products;
	}
   
   public WebElement getProductByName(String productName)
   
	{
		WebElement prod =	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod);
		return prod;
	}
   
   public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();


	}

}
