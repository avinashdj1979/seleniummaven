package com.basics.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigateToActions {
	
	public void navigateToActionPages(WebDriver driver) {
		driver.get("http://djangovinoth.pythonanywhere.com/login/");
		
		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");
		
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();
		
		WebElement skillEnhancmentMenu = driver.findElement(By.id("navbarDropdownMenuLink"));
		skillEnhancmentMenu.click();
		
		WebElement labPractice = driver.findElement(By.linkText("Lab Practice"));
		labPractice.click();
		
		WebElement actionsLink = driver.findElement(By.partialLinkText("Actions"));
		actionsLink.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
