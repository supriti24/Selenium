package Supriti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Supriti.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
    WebDriver driver;
    
    //constructor
    public LoginPage(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    
    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    WebElement errorMessage;

    public void login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }
    
    public void goTo() {
    	driver.get("https://www.saucedemo.com");
    }

    public boolean isOnProductPage() {
        // Check if the user is on the product catalog page
        WebElement titleElement = driver.findElement(By.className("title"));
        return titleElement.isDisplayed() && titleElement.getText().equals("Products");
    }
    
    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }
    
    
}
