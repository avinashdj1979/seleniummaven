package edu.framework.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import edu.framework.pageobject.HomePage;
import edu.framework.pageobject.HomePageB165;
import edu.framework.pageobject.LoginPage;

public class PageProvider {
	
	WebDriver driver;
	
	public PageProvider(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage getHomePage() {
		HomePage homePage  = new HomePage(driver);
		PageFactory.initElements(driver, homePage);
		return homePage;
	}
	
	public LoginPage getLoginPage() {
		LoginPage loginPage  = new LoginPage(driver);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

}
