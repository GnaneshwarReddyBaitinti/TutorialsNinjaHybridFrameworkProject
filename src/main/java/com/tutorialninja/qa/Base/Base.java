package com.tutorialninja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public Properties prop;
	public WebDriver driver;
	
	public Base() {
		
		 prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		
		try {
			FileInputStream fls=new FileInputStream(propFile);
			prop.load(fls);
			
//			System.out.println("Browser: " + prop.getProperty("browserName"));
//			System.out.println("URL: " + prop.getProperty("url"));
//			System.out.println("Email: " + prop.getProperty("validEmail"));
//			System.out.println("Password: " + prop.getProperty("validPass"));

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver intializeBrowserAndOpenApplicationURL(String browserName) {
		 
		
		WebDriverManager.chromedriver().setup();
		WebDriverManager.edgedriver().setup();  
		
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			
        driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
		}		
		
		
		
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
		System.out.println(prop.getProperty("url"));

		return driver;
	}
}
