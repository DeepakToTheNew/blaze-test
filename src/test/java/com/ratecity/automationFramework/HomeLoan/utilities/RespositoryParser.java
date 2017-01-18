package com.ratecity.automationFramework.HomeLoan.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class RespositoryParser {

	private FileInputStream stream;
	private String RepositoryFile;
	private Properties propertyFile = new Properties();

	public RespositoryParser() throws IOException
	{
		String workingDir=System.getProperty("user.dir");
		this.RepositoryFile=workingDir+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"com"+File.separator+"ratecity"+File.separator+"automationFramework"+File.separator+"HomeLoan"+File.separator+"OR"+File.separator+"HomeLoan_ObjectRepo.properties";		
		stream = new FileInputStream(RepositoryFile);
		propertyFile.load(stream);
	}
	


/**
 * 
 * @param locatorName
 * @return
 */
	public By getobjectLocator(String locatorName)
	{
		
		String locatorProperty = propertyFile.getProperty(locatorName);
		String locatorType = locatorProperty.split(":")[0];
		String locatorValue = locatorProperty.split(":")[1];

		By locator = null;
		switch(locatorType)
		{
		case "Id":
			locator = By.id(locatorValue);
			break;
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}