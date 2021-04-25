package edu.framework.listerners;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import edu.framework.constants.UIConstants;
import edu.framework.reports.ExtentManager;
import edu.framework.reports.ExtentTestManager;

public class TestListenerExtent implements ITestListener  {
	ExtentReports extent;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getReporter();
		System.out.println("Test started");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		/*String methodName = result.getMethod().getMethodName();
		System.out.println("Test Started :: " + methodName);
		extent.createTest(methodName);*/
	}
	
	@Override
	public void onTestSuccess(ITestResult result){
		driver = (WebDriver) result.getTestContext().getAttribute("driver");
		ExtentTestManager.getTest().pass("Test passed");
		String root = System.getProperty("user.dir");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File ss = ts.getScreenshotAs(OutputType.FILE);
		long random = System.currentTimeMillis();
		String des = root + "//target//screenshots//ss" + result.getMethod().getMethodName() +random + ".png";
		try {
			FileUtils.copyFile(ss, new File(des));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentTestManager.getTest().addScreenCaptureFromPath(des);
		System.out.println("Test Success");
	}
		
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail("Test failed");
		System.out.println("Test Failure");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		driver = (WebDriver) result.getTestContext().getAttribute("driver");
		String root = System.getProperty("user.dir");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File ss = ts.getScreenshotAs(OutputType.FILE);
		long random = System.currentTimeMillis();
		String des = root + "//target//screenshots//ss" + result.getMethod().getMethodName() +random + ".png";
		try {
			FileUtils.copyFile(ss, new File(des));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentTestManager.getTest().fail("Test skipped");
		ExtentTestManager.getTest().addScreenCaptureFromPath(des);
		System.out.println("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on Finish");
		ExtentManager.printResults();
	}
	
}
