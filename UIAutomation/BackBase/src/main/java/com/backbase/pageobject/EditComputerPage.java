package com.backbase.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditComputerPage 
{
	private WebDriver driver;
	
	private By txtComName = By.id("name");
	private By txtIntroDate = By.id("introduced");
	private By txtDisConDate =By.id("discontinued");
	private By comCompany = By.id("company");
	private By btnSaveCom = By.xpath(".//*[@id=\"main\"]/form[1]/div/input");
	private By btnCancel = By.xpath(".//*[@id='main']/form/div/a");
	private By btnDeleteCom = By.xpath(".//*[@id=\"main\"]/form[2]/input");
	private By lblEditCom = By.xpath(".//*[@id=\"main\"]/h1");
	private By comFieldWrapper =By.xpath("//*[@id=\"main\"]/form/fieldset/div[1]");

		
	public EditComputerPage (WebDriver driver)	// Constructor of the class 
	{
		this.driver = driver;	// Assign test cases driver to local driver
	}
	
	public WebElement getComputerNameField() 
	{
		return driver.findElement(txtComName);
	}

	public WebElement getIntroduceDateField ()
	{
		return driver.findElement(txtIntroDate);
	}
	
	public WebElement getDiscontinuedDateField ()
	{
		return driver.findElement(txtDisConDate);
	}
	
	public WebElement getCompanyCombo ()
	{
		return driver.findElement(comCompany);
	}
	
	public WebElement getSaveComputer ()
	{
		return driver.findElement(btnSaveCom);
	}
	
	public WebElement getCancelButton ()
	{
		return driver.findElement(btnCancel);
	}
	
	public WebElement getDeleteComBtn ()
	{
		return driver.findElement(btnDeleteCom);
	}
	
	public WebElement getEditComputerLabel ()
	{
		return driver.findElement(lblEditCom);
	}
	
	public WebElement getComputerFieldWrapper ()
	{
		return driver.findElement(comFieldWrapper);
	}
}
