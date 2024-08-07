package Supriti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCompletionPage {
    WebDriver driver;

    @FindBy(className = "inventory_item_name")
    WebElement itemName;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement orderCompletionMessage;

    public OrderCompletionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderDetails(String productName) {
        return itemName.getText().equals(productName);
    }

    public void clickFinish() {
        finishButton.click();
    }

    public boolean verifyOrderCompletion() {
        return orderCompletionMessage.getText().equals("Thank you for your order!");
    }
}
