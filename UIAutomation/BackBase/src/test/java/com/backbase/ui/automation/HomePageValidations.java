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

public class HomePageValidations extends Setup
{
private HomePage homePage;
private AddComputerPage addComputerPage;
private EditComputerPage editComputerPage;
private String baseURL = "http://computer-database.herokuapp.com/computers";

	
@BeforeMethod	
public void loadHomePage() throws IOException
{
	driver = initializeDriver();
	driver.get(baseURL);
	homePage = new HomePage(driver);
	addComputerPage = new AddComputerPage(driver);
	editComputerPage = new EditComputerPage(driver);
}

@AfterMethod
public void closeTheBrowser () throws InterruptedException
{
	Thread.sleep(3000);
	driver.close();
}

@Test // Verify that the Add a new Computer button is Enabled 
public void verifyAddNewComputerButtonIsEnabled()
{
	Assert.assertEquals(homePage.getAddComputerbtn().isDisplayed(), true); // Assert that Add a new computer button is displayed
	Assert.assertEquals(homePage.getAddComputerbtn().isEnabled(), true); // Assert that Add a new computer button is enabled
}

@Test // Verify that Filter by Name Button is available 
public void verifyFilterByNameButtonAvailability ()
{
	Assert.assertEquals(homePage.getFilterByNameButton().isDisplayed(), true);	// Assert that the Filter By name button is displayed
}

@Test // Verify the Column heading of the Table
public void verifyColumnHeadingOfTable ()
{
	Assert.assertEquals(homePage.getTableComputerName().getText(), "Computer name"); // Assert the first column heading of the table
	Assert.assertEquals(homePage.getTableIntroduced().getText(), "Introduced");	// Assert the second column heading of the table
	Assert.assertEquals(homePage.getTableDiscontinued().getText(), "Discontinued");	// Assert the third column heading of the table
	Assert.assertEquals(homePage.getTableCompany().getText(), "Company");	// Assert the forth column heading of the table
}

@Test // Verify that when search for an available computer name it is display in the table
public void verifyAvailableComputerName()
{
	homePage.getAddComputerbtn().click();	// Click on Add a new computer button 
	//Set Computer information
	addComputerPage.getComputerNameField().sendKeys("VK_Computer6");
	addComputerPage.getIntroduceDateField().sendKeys("2018-09-04");
	addComputerPage.getDiscontinuedDateField().sendKeys("2019-09-04");
	Select companyComboBox = new Select (addComputerPage.getCompanyCombo());
	companyComboBox.selectByValue("2");
	addComputerPage.getCreateComputerBtn().click();	// Click on Create This computer button 
	
	// Search for the computer
	homePage.getSesrchField().sendKeys("VK_Computer6");	// Set value to search field 
	homePage.getFilterByNameButton().click(); //Click on Find by name button 
	
	Assert.assertEquals(homePage.getFirstDataOfTable().getText(), "VK_Computer6"); // Assert that searched computer is available 
}

@Test // Verify the Filter with not existing date 
public void veryForNotExistingDate()
{
			homePage.getSesrchField().sendKeys("No Computer with This name");	// Set value to search field 
			homePage.getFilterByNameButton().click(); //Click on Find by name button 
			Assert.assertEquals(homePage.getNoDataDisplatAlert().getText(), "Nothing to display"); // Assert the message alert 
}

}
