package com.basics.actions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.constants.UIConstants;
import com.factory.DriverFactory;
import com.utils.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDropBy{
	WebDriver driver;

	@BeforeClass
	public void setUp() throws IOException {
		/*Properties prop = new Properties();

		String userDir = System.getProperty("user.dir");
		String filePath = userDir + UIConstants.DEFAULT_CONFIG_FILE;
		File file = new File(filePath);

		// FileInputStream fis = new FileInputStream(file);

		FileReader fileReader = new FileReader(file);

		prop.load(fileReader);*/
		
		PropertyReader propertyReader = new PropertyReader();
		String browser = propertyReader.getProperty(UIConstants.BROWSER);

		//String browser = prop.getProperty(UIConstants.BROWSER);

		System.out.println(browser);

		/*if (browser.equals(UIConstants.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}*/
		
		driver = DriverFactory.getDriver();
		
		//HashMap<String, String> propMap = propertyReader.getProperties();
		//int waitTime = Integer.parseInt(propMap.get(UIConstants.IMPLICITLY_WAIT_TIME));

		//driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	@Test
	public void dragAndDropExample() throws InterruptedException {

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

		Thread.sleep(5000);

		WebElement actionsLink = driver.findElement(By.partialLinkText("Actions"));
		actionsLink.click();

		WebElement slider = driver.findElement(By.partialLinkText("Slider"));
		slider.click();

		WebElement slider1 = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[1]"));

		WebElement slider2 = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[2]"));

		WebElement slider3 = driver.findElement(By.xpath("//div[contains(@class,'ui-slider-handle')]"));

		Actions action = new Actions(driver);

		/*
		 * action.dragAndDropBy(resizeWidthHandle, 300, 0).build().perform();
		 * 
		 * action.clickAndHold(resizeBothHandle).pause(Duration.ofSeconds(5))
		 * .moveByOffset(100, 300).build().perform();
		 */

		Thread.sleep(5000);

		action.clickAndHold(slider1).moveByOffset(20, 0).build().perform();

		action.dragAndDropBy(slider1, 100, 0).build().perform();

		action.clickAndHold(slider2).moveByOffset(0, 40).build().perform();

		action.dragAndDropBy(slider3, 100, 0).build().perform();

		Thread.sleep(5000);

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
