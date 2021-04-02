package edu.framework.listerners;

import java.util.Set;

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
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test passed");
		System.out.println("Test Success");
	}
		
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail("Test failed");
		System.out.println("Test Failure");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().fail("Test skipped");
		System.out.println("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on Finish");
		extent.flush();
	}
	
}
