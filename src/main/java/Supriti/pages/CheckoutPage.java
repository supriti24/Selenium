package Supriti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory
    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(css = ".error-message-container")
    WebElement errorMessageContainer;

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        System.out.println("Entered checkout information: First Name - " + firstName + ", Last Name - " + lastName + ", Postal Code - " + postalCode);
    }

    public void clickContinue() {
        continueButton.click();
        System.out.println("Clicked on continue button.");
    }

    public boolean isErrorMessageDisplayed(String expectedErrorMessage) {
        boolean isDisplayed = errorMessageContainer.isDisplayed();
        String actualErrorMessage = errorMessageContainer.getText();
        System.out.println("Error message: " + actualErrorMessage);
        return isDisplayed && actualErrorMessage.contains(expectedErrorMessage);
    }
}
