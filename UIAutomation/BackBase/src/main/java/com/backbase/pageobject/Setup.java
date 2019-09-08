package com.backbase.pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Setup 
{
public WebDriver driver;
public WebDriver initializeDriver () throws IOException
{
	Properties pro = new Properties ();
	FileInputStream file = new FileInputStream("src\\main\\resources\\Config.properties"); // Pass the configuration file
	pro.load(file); //Load the file
	String browserName = pro.getProperty("browser");	// Get browser name to string
	
	if (browserName.equals("Chrome")) 
	{
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		ChromeOptions op= new ChromeOptions();
		op.addArguments("window-size=1400,800");
		op.addArguments("headless");
		driver = new ChromeDriver(op);
	}
	else if (browserName.equals("FireFox"))
	{
		System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe"); 
		driver = new FirefoxDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	// Wait to load the browser 
	return driver;
}


}
