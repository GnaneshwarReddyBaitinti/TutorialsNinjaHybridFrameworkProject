package com.tutorialninja.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static String generateEmailWithTimeStamp() {
		
		SimpleDateFormat formatter =new SimpleDateFormat("yyyyMMddHHmmss");
		
		
		
		String Email= formatter.format(new Date(0));
		System.out.println(Email);
		int random=(int) (Math.random()*100);
		return "gnaneshwareddy720"+random+Email+"23@gmail.com";
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		
	    try {
			FileHandler.copy(ss, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    
	    return destinationScreenshotPath;
	}
	
	

}
