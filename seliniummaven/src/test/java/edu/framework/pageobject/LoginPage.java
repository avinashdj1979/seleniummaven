package edu.framework.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@type='email']")
	private WebElement email;
	
	@FindBy(id="session_password")
	private WebElement password;
	
	@FindBy(name="commit")
	private WebElement signInButton;
	
	
	public void login() {
		type(email, "avinash.vcentry1@gmail.com");
		type(password, "Sadu2006$");
		click(signInButton);
	}
	
}
