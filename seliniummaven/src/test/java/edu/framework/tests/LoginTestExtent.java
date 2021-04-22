package edu.framework.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import edu.framework.listerners.MyListenerExtent;
import edu.framework.reports.ExtentTestManager1;
import lombok.extern.slf4j.Slf4j;

@Listeners(MyListenerExtent.class)
@Slf4j
public class LoginTestExtent {
	
	WebDriver driver;
	//public static final Logger logger = LogManager.getLogger(LoginTest2.class);
	
	@BeforeClass
	public void setUp() {
		//driver = DriverFactory.getDriver();
		//driver.get("http://a.testaddressbook.com");
	}

	@Test
	public void successTest() throws InterruptedException {
		ExtentTestManager1.getTest().log(Status.INFO, "Success test");
		log.trace("successTest");
	}
	
	@Test
	public void failureTest() throws InterruptedException {
		ExtentTestManager1.getTest().log(Status.WARNING, "Failure test");
		log.debug("failure test");
		Assert.fail();
	}
	
	@Test
	public void SkippedTest() throws InterruptedException {
		ExtentTestManager1.getTest().log(Status.SKIP, "Skipped test");
		log.debug("Skip test");
		throw new SkipException("Skipped");
	}

	
	@AfterClass
	public void tearDown() {
//		driver.close();
//		driver.quit();  
	}
}
