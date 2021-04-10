package com.iframes;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedIFramesDemo {
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
	public void iFrameHandling() throws InterruptedException  {
		driver.get("https://www.demoqa.com/nestedframes");
		
		//Number of Frames using JavaScriptExecutor
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int noOfFrames = Integer.parseInt(js.executeScript("return window.length;").toString());
		System.out.printf("Number of frames using jsexecutor %d\n", noOfFrames);
		
		//Number of Frames using tagName
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.printf("Number of frames using tag name %d\n", frames.size() );
		
		//Switch to Frame using Index
		WebDriver parentFrame = driver.switchTo().frame(0);
		Assert.assertTrue(parentFrame.getPageSource().contains("Parent frame"));
		
		WebDriver childFrame = driver.switchTo().frame(0);
		System.out.println(driver.findElement(By.tagName("p")).getText());
		driver.switchTo().parentFrame();
		
		Assert.assertTrue(parentFrame.getPageSource().contains("Parent frame"));
		
		driver.switchTo().defaultContent();
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
