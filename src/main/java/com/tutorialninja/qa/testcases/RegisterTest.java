package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.Base;
import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utilities.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@BeforeMethod
	public void setUp() {

		driver = intializeBrowserAndOpenApplicationURL("chrome");
		HomePage homepage = new HomePage(driver);
		registerPage = homepage.navigateToRegisterPage();

//		homepage.clickOnMyAccount();
//		registerPage = homepage.selectRegisterOption();
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
//		System.out.println("clicked on account");
//		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyRegisteringAnAccountWithMandatoryFields() {

		accountSuccessPage = registerPage.registerWithMandatoryFields(prop.getProperty("firstName"),
				prop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				prop.getProperty("telephoneNumber"), prop.getProperty("validPass"));

		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),
				prop.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");

//		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.xpath("//input[@type='checkbox'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
//		String succmsg=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();	
//		Assert.assertEquals(succmsg , "Your Account Has Been Created!" , "getting error while registering");

	}

	@Test(priority = 4)
	public void VerifyRegisteringAnAccountWithAllFields() {

		accountSuccessPage = registerPage.registerWithAllFields(prop.getProperty("firstName"),
				prop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				prop.getProperty("telephoneNumber"), prop.getProperty("validPass"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),
				prop.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");

//		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();		
//		String succmsg=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
//		Assert.assertEquals(succmsg , "Your Account Has Been Created!" , "getting error while registering");

	}

	@Test(priority = 3)
	public void VerifyRegisteringAnAccountWithExistingEmailAddress() {

		registerPage.registerWithAllFields(prop.getProperty("firstName"), prop.getProperty("lastName"),
				prop.getProperty("validEmail"), prop.getProperty("telephoneNumber"), prop.getProperty("validPass"));
		Assert.assertTrue(
				registerPage.retrieveDuplicateEmailAddressWarning().contains(prop.getProperty("duplicateEmailWarning")),
				"Warning message regaring duplicate email address is not displayed");

//		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
//		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
//		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
//		String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
//		Assert.assertEquals(warn , prop.getProperty("duplicateEmailWarning") , "registering with existing email");

	}

	@Test(priority = 2)
	public void VerifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(prop.getProperty("privacyPolicyWarning"),
				prop.getProperty("firstNameWarning"), prop.getProperty("lastNameWarning"),
				prop.getProperty("emailWarning"), prop.getProperty("telephoneWarning"),
				prop.getProperty("passwordWarning")));

//		driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();
//		String warn=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
//		Assert.assertEquals(warn , prop.getProperty("privacyPolicyWarning") , "registering without fields");
//		String fnamewarn=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
//		Assert.assertEquals(fnamewarn , prop.getProperty("firstNameWarning") , "registering without fields");		
//		String lnamewarn=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
//		Assert.assertEquals(lnamewarn , prop.getProperty("lastNameWarning") , "registering without fields");
//		String emailwarn=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
//		Assert.assertEquals(emailwarn , prop.getProperty("emailWarning") , "registering without fields");		
//		String telewarn=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
//		Assert.assertEquals(telewarn , prop.getProperty("telephoneWarning") , "registering without fields");	
//		String passwarn=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
//		Assert.assertEquals(passwarn , prop.getProperty("passwordWarning") , "registering without fields");

	}
}
