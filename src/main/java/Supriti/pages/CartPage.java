package Supriti.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    @FindBy(css = ".cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "shopping_cart_container")
    WebElement cartButton;
    
    @FindBy(id = "continue-shopping")
    WebElement continueshopping;
    
    @FindBy(id = "checkout")
    WebElement checkout;

    By cartItemBy = By.cssSelector(".cart_item");
    By removeButton = By.cssSelector(".cart_button");

    public void clickCart() {
        cartButton.click();
    }

    public List<WebElement> getCartItems() {
      //  System.out.println("Cart Items: " + cartItems.size());
//        for (WebElement item : cartItems) {
//            System.out.println("Item: " + item.findElement(By.cssSelector(".inventory_item_name")).getText());
//        }
        return cartItems;
    }

    public boolean verifyItemInCart(String productName) {
        boolean isInCart = getCartItems().stream().anyMatch(cartItem ->
                cartItem.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName));
      //  System.out.println("Verify Item in Cart - Product Name: " + productName + " | Is In Cart: " + isInCart);
        return isInCart;
    }

    public void removeItemFromCart(String productName) {
        WebElement cartItem = getCartItems().stream().filter(item ->
                item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName)).findFirst().orElse(null);
        if (cartItem != null) {
          //  System.out.println("Removing Item: " + productName);
            cartItem.findElement(removeButton).click();
        } else {
          //  System.out.println("Item not found in cart: " + productName);
        }
    }
    
    public void continueShopping() {
    	continueshopping.click();
    }
    
    public void checkout() {
    	checkout.click();
    }
}
