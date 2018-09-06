package com.backbase.ui.automation;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.backbase.pageobject.AddComputerPage;
import com.backbase.pageobject.EditComputerPage;
import com.backbase.pageobject.HomePage;
import com.backbase.pageobject.Setup;

public class EditComputer extends Setup
{
	private EditComputerPage editComputerPage;
	private HomePage homePage;
	private AddComputerPage addComputerPage;
	private String baseURL = "http://computer-database.herokuapp.com/computers";
	
	@BeforeMethod
	public void loadHomePage () throws IOException
	{
		driver =initializeDriver();
		driver.get(baseURL);
		editComputerPage = new EditComputerPage (driver);
		homePage =new HomePage(driver);
		addComputerPage =new AddComputerPage(driver);
	}
	
	@AfterMethod
	public void closeBrowser ()
	{
		driver.close();
	}
	
	@Test // Verify the Computer name after change it
	public void verifyComputerNameAfterChange () throws InterruptedException
	{
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		
		//Set Computer information to add a computer 
		addComputerPage.getComputerNameField().sendKeys("VK_Computer5");
		addComputerPage.getIntroduceDateField().sendKeys("2018-01-01");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-01-01");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("1");
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		
		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer5");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button 
		Thread.sleep(2000);
		homePage.getFirstDataOfTable().click(); // Click on the computer name
		Thread.sleep(3000);	
		
		// Edit the record
		addComputerPage.getComputerNameField().clear();
		addComputerPage.getComputerNameField().sendKeys("VK_Computer5_Editted"); // Change the computer name
		addComputerPage.getCreateComputerBtn().click();
		
		Assert.assertEquals(homePage.getCreateComputerAlert().getText(), "Done! Computer VK_Computer5_Editted has been updated"); // Assert the alert message after update 
	
		// Search for the Edited record
		homePage.getSesrchField().sendKeys("VK_Computer5_Editted");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button
		Assert.assertEquals(homePage.getFirstDataOfTable().getText(), "VK_Computer5_Editted"); // Assert the edit value in the table
	}
	
	@Test // Verify that the system allows to delete a computer
	public void deleteAComputer ()
	{
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		
		//Set Computer information to add a computer 
		addComputerPage.getComputerNameField().sendKeys("VK_Computer4");
		addComputerPage.getIntroduceDateField().sendKeys("2018-02-01");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-02-01");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		
		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer4");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button 
		homePage.getFirstDataOfTable().click(); // Click on the computer name
				
		// Delete the computer
		editComputerPage.getDeleteComBtn().click();	// Click on the Delete button 
		
		Assert.assertEquals(homePage.getCreateComputerAlert().getText(), "Done! Computer has been deleted"); // Assert the alert message after Delete 
		
	}
	
	
	
	@Test // Check that "Edit Computer" Label is displayed 
	public void checkEditComputerLabel ()
	{
		// Click on a computer
		Assert.assertEquals(editComputerPage.getEditComputerLabel().getText(), "Edit computer"); // Assert the Label name
	}
	
	@Test	// Verify that no changes to the data when click on cancel button 
	public void verifyThatNoChangesToDataWhenClickOnCancel ()
	{
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		
		//Set Computer information to add a computer 
		addComputerPage.getComputerNameField().sendKeys("VK_Computer5");
		addComputerPage.getIntroduceDateField().sendKeys("2018-01-01");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-01-01");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("1");
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		
		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer5");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button 
		homePage.getFirstDataOfTable().click(); // Click on the computer name
				
		// Edit the record
		addComputerPage.getComputerNameField().clear();
		addComputerPage.getComputerNameField().sendKeys("VK_Computer5_Editted"); // Change the computer name
		editComputerPage.getCancelButton().click(); // Click on the cancel button
		
		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer5");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button
		
		// Check whether the original record is available 
		Assert.assertEquals(homePage.getFirstDataOfTable().getText(), "VK_Computer5"); // Assert the that no changes to the record 
	}
	
	@Test // Verify that the system does not allow to save after removing the computer name
	public void verifySystemNotAllowstoSaveRemovingComputerName ()
	{
		homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
		
		//Set Computer information to add a computer 
		addComputerPage.getComputerNameField().sendKeys("VK_Computer6");
		addComputerPage.getIntroduceDateField().sendKeys("2018-01-01");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-01-01");
		Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("1");
		addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
		
		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer6");	// Set value to search field 
		homePage.getFilterByNameButton().click(); //Click on Find by name button 
		homePage.getFirstDataOfTable().click(); // Click on the computer name
				
		// Edit the record (Remove the Computer name) 
		addComputerPage.getComputerNameField().clear();
		editComputerPage.getSaveComputer().click(); // Save the record by removing the name
		
		Assert.assertEquals(editComputerPage.getComputerFieldWrapper().getAttribute("class"), "clearfix error"); // Assert the error class

	}
}
