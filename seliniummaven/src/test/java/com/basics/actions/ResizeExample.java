package com.basics.actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizeExample {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		// setting Chrome driver executable path or use WebDriverManager class
		// System.setProperty("webdriver.chrome.driver",
		// userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		// Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void resizeExamples() throws InterruptedException {
		
		/*driver.get("http://djangovinoth.pythonanywhere.com/login/");
		
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
		
		Thread.sleep(5000);*/
		
		NavigateToActions np = new NavigateToActions();
		np.navigateToActionPages(driver);
		
		WebElement actionsLink = driver.findElement(By.partialLinkText("Actions"));
		actionsLink.click();
		
		WebElement resize = driver.findElement(By.partialLinkText("Resize"));
		resize.click();
		
		WebElement resizeWidthHandle = driver.findElement(By.xpath("(//div[contains(@class,'ui-resizable-handle')])[1]"));
		
		WebElement resizeHeightHandle = driver.findElement(By.xpath("(//div[contains(@class,'ui-resizable-handle')])[2]"));

		WebElement resizeBothHandle = driver.findElement(By.xpath("(//div[contains(@class,'ui-resizable-handle')])[3]"));

		Actions action = new Actions(driver);

		/*action.dragAndDropBy(resizeWidthHandle, 300, 0).build().perform();
		
		action.clickAndHold(resizeBothHandle).pause(Duration.ofSeconds(5))
			.moveByOffset(100, 300).build().perform();*/
		
		Thread.sleep(5000);
		
		WebElement resizeTextArea = driver.findElement(By.xpath("(//div[contains(@class,'ui-resizable-handle')])[4]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		
		Thread.sleep(5000);
		
		action.clickAndHold(resizeTextArea).moveByOffset(200, 50).build().perform();
		
		Thread.sleep(5000);

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
