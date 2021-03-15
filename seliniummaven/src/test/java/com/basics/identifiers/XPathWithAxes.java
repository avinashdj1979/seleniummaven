package com.basics.identifiers;

import java.util.LinkedList;
import java.util.List;
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

public class XPathWithAxes {
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
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void xPaths() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");

		//Absolute Path
		//Start with a single slash to start from the root of the DOM
		WebElement signIn = driver.findElement(By.xpath("/html/body/nav/div/div[1]/a[2]"));
		signIn.click();

		//Absolute Path
		//Start from anywhere in HTML DOM and skip the elements in the middle
		///html/body/div/div/h2
		WebElement subTitle = driver.findElement(By.xpath("//body//h2"));
		Assert.assertEquals(subTitle.getText(), "Sign in");
		
		Thread.sleep(2000);

		//Tag Attribute value trio
		//tagName[@atrib='value']
		//$x("//input[@class='form-control']")
		WebElement email = driver.findElement(By.xpath("//input[@class ='form-control']"));
		email.sendKeys("avinash.vcentry@gmail.com");
		
		Thread.sleep(2000);

		//Mutliple tags with attribute and Values
		WebElement password = driver.findElement(By.xpath("//input[@type='password'][@name='session[password]']"));
		//WebElement password = driver.findElement(By.xpath("//input[@id='session_password']"));
		password.sendKeys("vcentry2021");

		Thread.sleep(2000);
		
		//Contains
		WebElement signInButton= driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
		signInButton.click();

		Thread.sleep(10000);
		
		//Contains() and text()
		WebElement addressesLink= driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
		addressesLink.click();
		
		//Starts-With
		WebElement newAddressLink= driver.findElement(By.xpath("//a[starts-with(@href,'/addresses/n')]"));
		newAddressLink.click();
		
		Thread.sleep(2000);
		
		//Using Ancestor - parent and grand parents
		List<WebElement> ancestors = new LinkedList<WebElement>();
		ancestors = driver.findElements(By.xpath("//input[@id='address_street_address']/ancestor::*"));
		printTags(ancestors);
		
		//Using Ancestor or Self-  - parent and grand parents including self
		List<WebElement> ancestorsOrSelf = new LinkedList<WebElement>();
		ancestorsOrSelf = driver.findElements(By.xpath("//input[@id='address_street_address']/ancestor-or-self::*"));
		printTags(ancestorsOrSelf);
		
		//Using Descendant - children and grand children of the element
		List<WebElement> descendants = new LinkedList<WebElement>();
		descendants = driver.findElements(By.xpath("//form/descendant::*"));
		System.out.printf("Number of children and grand children under form tag - %d\n", descendants.size());
		
		//Using Descendant or Self - all children, grand Children of the element including self
		List<WebElement> descendantsOrSelf = new LinkedList<WebElement>();
		descendantsOrSelf = driver.findElements(By.xpath("//form/descendant-or-self::*"));
		System.out.printf("Number of children, grand children and self under form tag including form - %d\n", descendantsOrSelf.size());
		
		//Using Child Axes - Only Direct Children
		List<WebElement> children = new LinkedList<WebElement>();
		children = driver.findElements(By.xpath("//form/child::*"));
		System.out.printf("Number of children under form tag - %d\n", children.size());

		//Parent
		WebElement parent = driver.findElement(By.xpath("//input[@id='address_street_address']/parent::*"));
		System.out.println("Parent tag of Address1 " + parent.getTagName());
		
		//Get the element Address1 input field using Parent and Child
		WebElement address1 = driver.findElement(By.xpath
				("//label[@for='address_street_address']/parent::div/child::input"));
		address1.sendKeys("Address1");
		
		Thread.sleep(3000);
		
		//following Sibling
		address1 = driver.findElement(By.xpath
				("//label[contains(text(), 'Address1')]/following-sibling::input"));
		address1.clear();
		address1.sendKeys("Address 1 using Sibling");
		
		Thread.sleep(3000);
		
		//Preceding-sibling
		address1 = driver.findElement(By.xpath
				("(//label[contains(text(), 'Address2')]/parent::div/preceding-sibling::*/child::input)[3]"));
		address1.clear();
		address1.sendKeys("Address 1 using Address 2");
		
		//Following
		List<WebElement> divsAfterPhone = driver.findElements(By.xpath("//input[@id='address_phone']/following::*"));
		printTags(divsAfterPhone);
		
		//Preceding
		List<WebElement> divsBeforeLastName = driver.findElements(By.xpath("//input[@id='address_last_name']/preceding::*"));
		printTags(divsBeforeLastName);

		Thread.sleep(5000);
	} 
	
	private void printTags(List<WebElement> tags) {
		for(WebElement tag: tags) {
			System.out.printf("%s >", tag.getTagName());
		}
		System.out.println();
	}
		
	private void printList(List<WebElement> tags) {
		for(WebElement tag: tags) {
			System.out.println(tag.getTagName());
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
