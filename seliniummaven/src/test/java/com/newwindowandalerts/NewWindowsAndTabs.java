package com.newwindowandalerts;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewWindowsAndTabs {
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path or use WebDriverManager class
		//System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		//Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void newWindowAndTabs() throws InterruptedException  {

		driver.get("https://demoqa.com/browser-windows");
	
		String parent = driver.getWindowHandle();
		
		WebElement newWindowButton = driver.findElement(By.xpath("//button[@id='windowButton']"));
		newWindowButton.click();
		
		Set<String> newWindows = driver.getWindowHandles();
		System.out.println(newWindows.size());
		
		for(String windowHandle: newWindows) {
			if(!(windowHandle.equals(parent))) {
				driver.switchTo().window(windowHandle);
				WebElement h1 = driver.findElement(By.cssSelector("#sampleHeading"));
				Assert.assertEquals(h1.getText(), "This is a sample page", "Message mismatch");
				driver.close();
			}
		}
		
		driver.switchTo().window(parent);
		
		WebElement newTabButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
		newTabButton.click();
		
		Set<String> tabs = driver.getWindowHandles();
		System.out.println(tabs.size());
		
		for(String tabHandle: tabs) {
			if(!(tabHandle.equals(parent))) {
				driver.switchTo().window(tabHandle);
				WebElement h1 = driver.findElement(By.cssSelector("#sampleHeading"));
				Assert.assertEquals(h1.getText(), "This is a sample page", "Message mismatch");
				driver.close();
			}
		}
		
		driver.switchTo().window(parent);
		
		WebElement newMessageWindow = driver.findElement(By.xpath("//button[@id='messageWindowButton']"));
		newMessageWindow.click();
		
		Set<String> openWindows = driver.getWindowHandles();
		System.out.println(openWindows.size());
		
		for(String messageWindow: openWindows) {
			if(!(messageWindow.equals(parent))) {
				driver.switchTo().window(messageWindow);
				driver.close();
			}
		}
		
		driver.switchTo().window(parent);
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
