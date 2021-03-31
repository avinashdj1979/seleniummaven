package com.basics.actions;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.constants.UIConstants;
import com.utils.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DoubleClickExample {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws IOException {
		PropertyReader pr = new PropertyReader();
		String browser = pr.getProperty(UIConstants.BROWSER);
		if(browser.equals(UIConstants.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		long waitTime = Long.parseLong(pr.getProperty(UIConstants.IMPLICITLY_WAIT_TIME));
		System.out.println(waitTime);
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void doubleClick() throws InterruptedException {
		
		driver.get("http://djangovinoth.pythonanywhere.com/login/");

		Actions action = new Actions(driver);
		
		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");
		
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();
		
		WebElement skillEnhancmentMenu = driver.findElement(By.id("navbarDropdownMenuLink"));
		skillEnhancmentMenu.click();
		
		Thread.sleep(2000);

		WebElement labPractice = driver.findElement(By.linkText("Lab Practice"));
		labPractice.click();
		
		Thread.sleep(5000);
		
		WebElement actionsLink = driver.findElement(By.partialLinkText("Actions"));
		actionsLink.click();
		
		Thread.sleep(5000);
		
		WebElement doubleClick = driver.findElement(By.partialLinkText("Double Click"));
		doubleClick.click();
		
		WebElement dblClickButton = driver.findElement(By.id("dbclick1"));
		
		action.doubleClick(dblClickButton).build().perform();
		
		Thread.sleep(5000);
		
		WebElement dblClickImage = driver.findElement(By.id("dbclick2"));
		
		action.doubleClick(dblClickImage).build().perform();
		
		Thread.sleep(5000);

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
