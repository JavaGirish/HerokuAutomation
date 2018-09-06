package com.backbase.ui.automation;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.backbase.pageobject.AddComputerPage;
import com.backbase.pageobject.HomePage;
import com.backbase.pageobject.Setup;

public class AddNewComputers extends Setup {
	private AddComputerPage addComputerPage;
	private HomePage homePage;
	public String baseURL = "http://computer-database.herokuapp.com/computers";

	@BeforeMethod
	public void loadHomePage() throws IOException {
		driver = initializeDriver();
		driver.get(baseURL);
		addComputerPage = new AddComputerPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void closeTheBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

	@Test // Verify that the system allows to Create new computer
	public void verifySystemAllowsToCreateNewComputer() throws InterruptedException {
		
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		//Set Computer information
		addComputerPage.getComputerNameField().sendKeys("VK_Computer1");
		addComputerPage.getIntroduceDateField().sendKeys("2018-09-04");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-09-04");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 

		Assert.assertEquals(homePage.getCreateComputerAlert().getText(), "Done! Computer VK_Computer1 has been created"); // Assert the alert message 
	}

	
	@Test // Verify that the system does not allows to Create a computer without mandatory data
	public void veritySystemNotAllowsToCreateWithoutMandatoryDate() 
	{
		homePage.getAddComputerbtn().click();
		addComputerPage.getIntroduceDateField().sendKeys("2018-09-04");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-09-04");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(addComputerPage.getComputerFieldWrapper().getAttribute("class"), "clearfix error"); // Assert the error class
		
		addComputerPage.getComputerNameField().sendKeys("VK_Computer2"); // Set a computer name
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		Assert.assertEquals(homePage.getCreateComputerAlert().getText(), "Done! Computer VK_Computer2 has been created"); // Assert the alert message 
	}
	
	
	@Test	// Verify that system goes to HomePage when Click on Cancel Button 
	public void verifySystemGoesToHomePageWhenClickCancel ()
	{
		homePage.getAddComputerbtn().click();
		addComputerPage.getCancelButton().click();
		Assert.assertEquals(homePage.getAddComputerbtn().isDisplayed(), true);	// Assert that Add a new Computer button in Home page
	}
	
	
	@Test // Verify that the system allows to add a computer when only mandatory fields values are added
	public void verifySystemAllowsToAddComputerWhenMandatoryDataThere()
	{
		homePage.getAddComputerbtn().click();
		addComputerPage.getComputerNameField().sendKeys("VK_Computer3");
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(homePage.getAlert().getText(),  "Done! Computer VK_Computer3 has been created"); // Assert the message alert after  add a computer
	}
	
	
	@Test // Verify that the system validate the invalid date formats 
	public void verifyThatTheSystemValidateInvalidDateFormat () throws InterruptedException
	{
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		//Set Computer information
		addComputerPage.getComputerNameField().sendKeys("VK_Computer4");
		addComputerPage.getIntroduceDateField().sendKeys("2018/02/10");	// Set invalid date format 
		addComputerPage.getDiscontinuedDateField().sendKeys("2019/02/10"); // Set invalid date format 
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		Thread.sleep(3000);
		Assert.assertEquals(addComputerPage.getintroducedDateWrapper().getAttribute("class"), "clearfix error"); // Assert the error class
		Assert.assertEquals(addComputerPage.getdiscontinuedDateWrapper().getAttribute("class"), "clearfix error"); // Assert the error class

		addComputerPage.getIntroduceDateField().clear(); 
		addComputerPage.getDiscontinuedDateField().clear();
		addComputerPage.getIntroduceDateField().sendKeys("2018 Feb 10");	// Set invalid date format 
		addComputerPage.getDiscontinuedDateField().sendKeys("2019 Feb 10"); // Set invalid date format 
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(addComputerPage.getintroducedDateWrapper().getAttribute("class"), "clearfix error"); // Assert the error class
		Assert.assertEquals(addComputerPage.getdiscontinuedDateWrapper().getAttribute("class"), "clearfix error"); // Assert the error class
	}
	
}
