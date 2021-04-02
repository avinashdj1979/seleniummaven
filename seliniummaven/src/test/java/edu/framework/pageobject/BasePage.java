package edu.framework.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.framework.constants.UIConstants;
import edu.framework.utils.PropertyReader;

public class BasePage {
	
	WebDriver driver;
	int waitTime = Integer.parseInt(new PropertyReader().getProperty(UIConstants.EXPLICIT_WAIT_TIME));
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, waitTime);
	}
	
	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
}
