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

public class TestListener implements ITestListener  {

	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		String reportPath = UIConstants.ROOT_DIR + "\\reports\\report.html";
		sparkReporter= new ExtentSparkReporter(reportPath);
		sparkReporter.config().setReportName("Address book Report");
		sparkReporter.config().setDocumentTitle("Test Address Book");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester", "Automated Tester");

		System.out.println("Test started");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest( result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		System.out.println("Test Started :: " + methodName);
		extent.createTest(methodName);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass("Test passed");
		System.out.println("Test Success");
	}
		
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail("Test failed");
		System.out.println("Test Failure");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip("Test skipped");
		System.out.println("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on Finish");
		extent.flush();
	}
	
}
