package Supriti.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue {
    WebDriver driver;

    // Constructor
    public ProductCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    @FindBy(css = ".inventory_item")
    List<WebElement> products;

    @FindBy(css = ".cart_item")
    List<WebElement> cartItems;

    By productsBy = By.cssSelector(".inventory_item");
    By addToCart = By.cssSelector(".btn_primary");
    By cartItemBy = By.cssSelector(".cart_item");
    By removeButton = By.id("remove-sauce-labs-bike-light");

    public List<WebElement> getProductList() {
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product ->
                product.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public boolean isItemInCart(String productName) {
        return getCartItems().stream().anyMatch(cartItem ->
                cartItem.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName));
    }

    public void removeItemFromCart(String productName) {
        WebElement cartItem = getCartItems().stream().filter(item ->
                item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        if (cartItem != null) {
            cartItem.findElement(removeButton).click();
        }
    }
}
