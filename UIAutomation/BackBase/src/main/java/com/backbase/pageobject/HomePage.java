package com.backbase.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage 
{
WebDriver driver;

public HomePage(WebDriver driver)
{
	this.driver = driver;
}

// Define the pageObjects (web elements) 

private By btnFilterByName = By.id("searchsubmit");	//
private By txtSearchBox = By.id("searchbox");
private By btnAddNewCom =By.id("add");
private By lblAlert = By.xpath(".//*[@id=\"main\"]/div[1]");
private By tblLblComName = By.xpath(".//*[@id=\"main\"]/table/thead/tr/th[1]/a");
private By tblLblIntroduced = By.xpath(".//*[@id=\"main\"]/table/thead/tr/th[2]/a");
private By tblLblDiscontinued =By.xpath(".//*[@id=\"main\"]/table/thead/tr/th[3]/a");
private By tblLblCompany =By.xpath(".//*[@id=\"main\"]/table/thead/tr/th[4]/a");
private By createComputerAlert = By.xpath(".//*[@id=\"main\"]/div[1]");
private By firstRowFirstColumndata = By.xpath(".//*[@id=\"main\"]/table/tbody/tr[1]/td[1]/a");
private By firstRowSecondColumndata =By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[2]");
private By firstRowThirdColumndata =By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[3]");
private By firstRowForthColumndata =By.xpath("//*[@id=\"main\"]/table/tbody/tr[1]/td[4]");
private By lblNoDataToDisplay =By.xpath("//*[@id=\"main\"]/div[2]/em");


public WebElement getAddComputerbtn ()
{
	return driver.findElement(btnAddNewCom);
}

public WebElement getAlert ()
{
	return driver.findElement(lblAlert);
}

public WebElement getFilterByNameButton ()
{
	return driver.findElement(btnFilterByName);
}

public WebElement getTableComputerName()
{
	return driver.findElement(tblLblComName);
}

public WebElement getTableIntroduced()
{
	return driver.findElement(tblLblIntroduced);
}

public WebElement getTableDiscontinued()
{
	return driver.findElement(tblLblDiscontinued);
}

public WebElement getTableCompany()
{
	return driver.findElement(tblLblCompany);
}

public WebElement getCreateComputerAlert ()
{
	return driver.findElement(createComputerAlert);
}

public WebElement getSesrchField ()
{
	return driver.findElement(txtSearchBox);
}

public WebElement getFirstDataOfTable ()
{
return driver.findElement(firstRowFirstColumndata);
}

public WebElement getFirstRowSecondColData ()
{
	return driver.findElement(firstRowSecondColumndata);
}

public WebElement getFirstRowThirdColData ()
{
	return driver.findElement(firstRowThirdColumndata);
}

public WebElement getFirstRowForthColData ()
{
	return driver.findElement(firstRowForthColumndata);
}

public WebElement getNoDataDisplatAlert ()
{
	return driver.findElement(lblNoDataToDisplay);
}

}
