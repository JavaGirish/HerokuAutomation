package com.backbase.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddComputerPage {

	/************** Define the page objects of 'Add a computer' page ***********/
	
	private WebDriver driver;

	private By txtComName = By.id("name");
	private By txtIntroDate = By.id("introduced");
	private By txtDisConDate =By.id("discontinued");
	private By comCompany = By.id("company");
	private By btnCreteCom = By.xpath(".//*[@id=\"main\"]/form/div/input");
	private By btnCancel = By.xpath(".//*[@id='main']/form/div/a");
	private By comFieldWrapper =By.xpath("//*[@id=\"main\"]/form/fieldset/div[1]");
	private By introducedDateWrapper = By.xpath(".//*[@id=\"main\"]/form/fieldset/div[2]");
	private By discontinuedDateWrapper =By.xpath("//*[@id=\"main\"]/form/fieldset/div[3]");
	
	
	public AddComputerPage(WebDriver driver) // Constructor of the class
	{
		this.driver = driver; // Assign test cases driver to local driver
	}

	public WebElement getComputerNameField() 
	{
		 return driver.findElement(txtComName);
	}

	public WebElement getCreateComputerBtn() 
	{
		return driver.findElement(btnCreteCom);
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
	
	public WebElement getCancelButton ()
	{
		return driver.findElement(btnCancel);
	}
	
	public WebElement getComputerFieldWrapper ()
	{
		return driver.findElement(comFieldWrapper);
	}
	
	public WebElement getintroducedDateWrapper ()
	{
		return driver.findElement(introducedDateWrapper);
	}
	
	public WebElement getdiscontinuedDateWrapper ()
	{
		return driver.findElement(discontinuedDateWrapper);
	}
}
