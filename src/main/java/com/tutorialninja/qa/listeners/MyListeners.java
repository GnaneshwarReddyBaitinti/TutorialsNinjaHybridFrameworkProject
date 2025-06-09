package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utilities.ExtentReporter;
import com.tutorialninja.qa.utilities.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	private ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + "started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, result.getName() + "got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + "got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + "Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {

		extentReport.flush();

		String Path = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";

		File file = new File(Path);

//	  Desktop.getDesktop().browse(((File) extentReport).toURI());

	}

}
