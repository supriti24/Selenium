package Supriti.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	
	//constructor
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		
	}

	public void waitForElementToAppear(By findBy) {


		WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

}