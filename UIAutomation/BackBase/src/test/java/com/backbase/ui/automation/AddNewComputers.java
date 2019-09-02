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

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

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
	//

	@Severity(SeverityLevel.BLOCKER)
	@Description("Verify that the system allows to Create new computer")
	@Story("Add new Computers")

	@Test(enabled = true, priority = 1) // Verify that the system allows to Create new computer
	public void verifySystemAllowsToCreateNewComputer() throws InterruptedException {
		homePage.getAddComputerbtn().click(); // Click on Add a new computer button
		// Set Computer information
		addComputerPage.getComputerNameField().sendKeys("VK_Computer1");
		addComputerPage.getIntroduceDateField().sendKeys("2018-09-04");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-09-04");
		Select companyComboBox = new Select(addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		addComputerPage.getCreateComputerBtn().click(); // Click on Create This computer button

		// Assert the alert message
		Assert.assertEquals(homePage.getCreateComputerAlert().getText(),
				"Done! Computer VK_Computer1 has been created");
	}

	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify that the system does not allows to Create a computer without mandatory data")
	@Story("Add new Computers")
	
	
	@Test(enabled = true, priority = 2) 
	public void veritySystemNotAllowsToCreateWithoutMandatoryDate() {
		homePage.getAddComputerbtn().click();
		addComputerPage.getIntroduceDateField().sendKeys("2018-09-04");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-09-04");
		Select companyComboBox = new Select(addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(addComputerPage.getComputerFieldWrapper().getAttribute("class"), "clearfix error"); // Assert
																												// the
																												// error
																												// class

		addComputerPage.getComputerNameField().sendKeys("VK_Computer2"); // Set a computer name
		addComputerPage.getCreateComputerBtn().click(); // Click on Create This computer button
		Assert.assertEquals(homePage.getCreateComputerAlert().getText(),
				"Done! Computer VK_Computer2 has been created"); // Assert the alert message
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Verify that system goes to HomePage when Click on Cancel Button")
	@Story("Add new Computers")

	@Test(enabled = true, priority = 3) // 
	public void verifySystemGoesToHomePageWhenClickCancel() {
		homePage.getAddComputerbtn().click();
		addComputerPage.getCancelButton().click();
		Assert.assertEquals(homePage.getAddComputerbtn().isDisplayed(), true); // Assert that Add a new Computer button
																				// in Home page
	}
	
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify that the system allows to add a computer when only mandatory fields values are added")
	@Story("Add new Computers")
	

	@Test(enabled = true,priority=4) // 
	public void verifySystemAllowsToAddComputerWhenMandatoryDataThere() {
		homePage.getAddComputerbtn().click();
		addComputerPage.getComputerNameField().sendKeys("VK_Computer3");
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(homePage.getAlert().getText(), "Done! Computer VK_Computer3 has been created"); // Assert
																											// the
																											// message
																											// alert
																											// after add
																											// a
																											// computer
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify that the system validate the invalid date formats")
	@Story("Add new Computers")

	@Test(enabled = true,priority=5)
	public void verifyThatTheSystemValidateInvalidDateFormat() throws InterruptedException {
		homePage.getAddComputerbtn().click(); // Click on Add a new computer button
		// Set Computer information
		addComputerPage.getComputerNameField().sendKeys("VK_Computer4");
		addComputerPage.getIntroduceDateField().sendKeys("2018/02/10"); // Set invalid date format
		addComputerPage.getDiscontinuedDateField().sendKeys("2019/02/10"); // Set invalid date format
		Select companyComboBox = new Select(addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("2");

		addComputerPage.getCreateComputerBtn().click(); // Click on Create This computer button
		Thread.sleep(3000);
		Assert.assertEquals(addComputerPage.getintroducedDateWrapper().getAttribute("class"), "clearfix error"); // Assert
																													// the
																													// error
																													// class
		Assert.assertEquals(addComputerPage.getdiscontinuedDateWrapper().getAttribute("class"), "clearfix error"); // Assert
																													// the
																													// error
																													// class

		addComputerPage.getIntroduceDateField().clear();
		addComputerPage.getDiscontinuedDateField().clear();
		addComputerPage.getIntroduceDateField().sendKeys("2018 Feb 10"); // Set invalid date format
		addComputerPage.getDiscontinuedDateField().sendKeys("2019 Feb 10"); // Set invalid date format
		addComputerPage.getCreateComputerBtn().click();
		Assert.assertEquals(addComputerPage.getintroducedDateWrapper().getAttribute("class"), "clearfix error"); // Assert
																													// the
																													// error
																													// class
		Assert.assertEquals(addComputerPage.getdiscontinuedDateWrapper().getAttribute("class"), "clearfix error"); // Assert
																													// the
																													// error
																													// class
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Verify that the Added Computer details are available in the table")
	@Story("Add new Computers")

	@Test(enabled = true,priority=6) // Verify that the Added Computer details are available in the table
	public void verifyThatAddedDetailsAreAvailableInTable() {
		homePage.getAddComputerbtn().click(); // Click on Add a new computer button

		// Set Computer information to add a computer
		addComputerPage.getComputerNameField().sendKeys("VK_Computer5");
		addComputerPage.getIntroduceDateField().sendKeys("2018-01-01");
		addComputerPage.getDiscontinuedDateField().sendKeys("2019-01-01");
		Select companyComboBox = new Select(addComputerPage.getCompanyCombo());
		companyComboBox.selectByValue("1");
		String companyName = companyComboBox.getOptions().get(1).getText(); // Get company name to a variable
		addComputerPage.getCreateComputerBtn().click(); // Click on Create This computer button

		// Search for the created record
		homePage.getSesrchField().sendKeys("VK_Computer5"); // Set value to search field
		homePage.getFilterByNameButton().click(); // Click on Find by name button
		Assert.assertEquals(homePage.getFirstDataOfTable().getText(), "VK_Computer5");// Assert the computer name
		Assert.assertEquals(homePage.getFirstRowSecondColData().getText(), "01 Jan 2018");// Assert the IntroduceDate
		Assert.assertEquals(homePage.getFirstRowThirdColData().getText(), "01 Jan 2019");// Assert the DiscontinuedDate
		Assert.assertEquals(homePage.getFirstRowForthColData().getText(), companyName); // Assert the Company name

	}
}
