package edu.framework.pageobject;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class HomePage extends BasePage{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "sign-in" )
	private WebElement signIn;

	public void clickSignIn() {
		click(signIn);
	}
}
