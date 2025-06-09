package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	SearchPage searchPage;
	HomePage homePage;
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		
//		System.out.println("OPENED BROswer");
//		 driver.findElement(By.xpath("//span[text()='My Account']")).click();
//	     System.out.println("clicked on account");
//	     driver.findElement(By.linkText("Login")).click();
//	     System.out.println("clicked on login");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifySearchWithValidProduct() {

		searchPage = homePage.searchForAProduct(prop.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),
				"Valid product HP is not displayed in the search results");

//		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(prop.getProperty("validProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();	
//		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"valid product is not visible");
	}

	@Test
	public void verifySearchWithInvalidProduct() {

		searchPage = homePage.searchForAProduct(prop.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),
				"There is no product that matches the search criteria.",
				"No product message in search results is not displayed");

//		driver.findElement(By.name("search")).sendKeys(prop.getProperty("invalidProduct"));
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();	
//		String warn=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
//		Assert.assertEquals(warn , prop.getProperty("NoProductTextInSearchResults"),"not showing error msg when searched invalid product");

	}

	@Test
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), prop.getProperty("NoProductTextInSearchResults"),
				"No product message in search results is not displayed");

//		driver.findElement(By.name("search")).sendKeys("");
//		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
//		String warn=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
//		Assert.assertEquals(warn , "There is no product that matches the search criteria.","not showing error msg when searched invalid product");

	}
}
