package edu.framework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import edu.framework.listerners.MyListener;
import lombok.extern.slf4j.Slf4j;

@Listeners(MyListener.class)
@Slf4j
public class LoginTestWithLogger{
	
	WebDriver driver;
	//public static final Logger logger = LogManager.getLogger(LoginTest2.class);
	
	@BeforeClass
	public void setUp() {
		//driver = DriverFactory.getDriver();
		//driver.get("http://a.testaddressbook.com");
	}

	@Test
	public void successTest() throws InterruptedException {
		log.trace("successTest");
	}
	
	@Test
	public void failureTest() throws InterruptedException {
		log.debug("failure test");
		Assert.fail();
	}
	
	@Test
	public void SkippedTest() throws InterruptedException {
		log.debug("Skip test");
		throw new SkipException("Skipped");
	}

	
	@AfterClass
	public void tearDown() {
//		driver.close();
//		driver.quit();  
	}
}
