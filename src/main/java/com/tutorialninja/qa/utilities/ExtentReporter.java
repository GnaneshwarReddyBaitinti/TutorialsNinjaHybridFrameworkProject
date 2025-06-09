package com.tutorialninja.qa.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tutorialninja.qa.Base.Base;

public class ExtentReporter {

	public static Base baseInstance = new Base();


	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();

		File file = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);

		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("First Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Application URL", baseInstance.prop.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", baseInstance.prop.getProperty("browserName"));
		extentReport.setSystemInfo("Email", baseInstance.prop.getProperty("validEmail"));
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("JAVA VER", System.getProperty("java.version"));

		return extentReport;

	}
}
