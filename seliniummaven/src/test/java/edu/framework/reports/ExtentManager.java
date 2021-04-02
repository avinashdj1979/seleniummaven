package edu.framework.reports;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import edu.framework.constants.UIConstants;

public class ExtentManager {
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;

	public synchronized static ExtentReports getReporter() {
		String reportPath = UIConstants.ROOT_DIR + "\\reports\\report.html";
		sparkReporter= new ExtentSparkReporter(reportPath);
		sparkReporter.config().setReportName("Address book Report");
		sparkReporter.config().setDocumentTitle("Test Address Book");
		if(extentReports == null) {
			extentReports = new ExtentReports();
			extentReports.attachReporter(sparkReporter);
		}
		return extentReports;
	}	
	
	public synchronized static void printResults() {
		extentReports.flush();
	}	
}
