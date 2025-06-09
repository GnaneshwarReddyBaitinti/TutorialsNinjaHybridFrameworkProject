package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utilities.Utilities;

public class LoginTest extends Base {

	LoginPage loginPage;
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.out.println(prop.getProperty("browserName"));
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

		HomePage homePage = new HomePage(driver);
		loginPage = homePage.naviageToLoginPage();
//		 driver.findElement(By.xpath("//span[text()='My Account']")).click();
//	     System.out.println("clicked on account");
//	     driver.findElement(By.linkText("Login")).click();
//	     System.out.println("clicked on login");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		String email = prop.getProperty("validEmail");
		String password = prop.getProperty("validPass");

		AccountPage accountPage = loginPage.login(email, password);

//	     driver.findElement(By.id("input-email")).sendKeys(); 
//	     driver.findElement(By.id("input-password")).sendKeys();
//	     driver.findElement(By.xpath("//input[@Value='Login']")).click();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit Your Account Information option is not displayed");
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText()
				.contains(prop.getProperty("emailPasswordNoMatchWarning")), "Expected warning is not showing");

//       driver.findElement(By.id("input-email")).sendKeys();
//	     driver.findElement(By.id("input-password")).sendKeys();	     
//	     driver.findElement(By.xpath("//input[@Value='Login']")).click();	
//	     String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();     
//	     String exwarn="Warning: No match for E-Mail Address and/or Password.";
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPass"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(
				prop.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

//	         driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		     driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));	     
//		     driver.findElement(By.xpath("//input[@Value='Login']")).click();
//		     String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();     
//		     String exwarn="Warning: No match for E-Mail Address and/or Password.";
//		     Assert.assertTrue(warn.contains(exwarn),"Expected warning is not showing");        
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		loginPage.login(prop.getProperty("validEmail"), prop.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(
				prop.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("invalidPassword"));	     
//		driver.findElement(By.xpath("//input[@Value='Login']")).click();
//		String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();     
//		String exwarn="Warning: No match for E-Mail Address and/or Password.";
//		Assert.assertTrue(warn.contains(exwarn),"Expected warning is not showing");	
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(
				prop.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys("");	     
//		driver.findElement(By.xpath("//input[@Value='Login']")).click();
//		String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();     
//		String exwarn="Warning: No match for E-Mail Address and/or Password.";
//		Assert.assertTrue(warn.contains(exwarn),"Expected warning is not showing");		
	}
}
