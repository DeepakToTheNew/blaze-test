package com.ratecity.automationFramework.HomeLoan.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;

import com.ratecity.automationFramework.HomeLoan.utilities.BaseClass;
import com.ratecity.automationFramework.HomeLoan.utilities.RespositoryParser;
import com.ratecity.automationFramework.HomeLoan.utilities.Utility;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class HomeLoanComparisonpage {
	
	 

public HomeLoanComparisonpage() {
	BaseClass.logger.log(LogStatus.INFO, "INTO CLASS ==> HomeLoanComparisonpage");
	if(BaseClass.getDriver().getTitle().contains("home-loans Comparison")){
		BaseClass.logger.log(LogStatus.INFO, "Expected page Loaded Successfully!!!!");
	}
	else{
		BaseClass.logger.log(LogStatus.FAIL, "!!Wrong page is getting displayed");
	    Assert.assertTrue(false);
	}
	}

public static void fn_ClickOnGetTheWidgetButton() throws Exception{
	By by =  new RespositoryParser().getobjectLocator("HomeLoan.ComparisonPage.WidgetButton");
	WebElement widgetbutton = BaseClass.getDriver().findElement(by);
	if(Utility.isElementPresent(by)){
		BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_ClickOnGetTheWidgetButton :" + widgetbutton.getText()+"-- Is getting displayed");
		Utility.clickAndWait(widgetbutton, 1500);
	}else{
		BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_ClickOnGetTheWidgetButton :" + widgetbutton.getText()+"-- Is not getting displayed");
	}
}

public static boolean fn_CheckEmbedWindowisDisplayed() throws Exception{
	Utility.GoToSleep(1000);
	By EmbedHeader = new RespositoryParser().getobjectLocator("HomeLoan.ComparisonPage.EmbedHeader");
	By EmbedWindow = new RespositoryParser().getobjectLocator("HomeLoan.ComparisonPage.EmbedWindow");
	WebElement embedHeader = BaseClass.getDriver().findElement(EmbedHeader);
	WebElement widgetWindow = BaseClass.getDriver().findElement(EmbedWindow);
	try{
		if(widgetWindow.isDisplayed()){
			BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Window -- Is getting displayed");
			if(embedHeader.getText().equalsIgnoreCase("Embed our comparison widget on your website")){
				Utility.highlightElementBorder(embedHeader);
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Header -- Is matched");
				return true;
			}else {
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Header -- Is not matched");
				return false;
			}
		}
	}catch(NoSuchWindowException we){
		BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Catched Block -- Waiting for window to appear");
		Utility.GoToSleep(1500);
		if(widgetWindow.isDisplayed()){
			BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Window -- Is getting displayed");
			if(embedHeader.getText().equalsIgnoreCase("Embed our comparison widget on your website")){
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Header -- Is matched");
				return true;
			}else {
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed : Embed Header -- Is not matched");
				return false;
			}
		}	
	}catch(Exception e){
		BaseClass.logger.log(LogStatus.INFO, "INTO METHOD ==> fn_CheckEmbedWindowisDisplayed");
	}
	return false;
}

public static boolean fn_verifytitle(){	
		
	if(BaseClass.getDriver().getTitle().contains("Home Loan Comparison | RateCity")){
		return true;
	}
	else
		return false;
	}



public static int fn_VerifyComparisontable() throws Exception{
	List<WebElement> we =  BaseClass.getDriver().findElements(new RespositoryParser()
			.getobjectLocator("HomeLoan.Compareblocks"));
	for (WebElement webElement : we.subList(1, we.size())) {
		Utility.highlightElementBorder(webElement);
	}
	return we.size();
	
}
public static boolean fn_VerifyHomeLoanText() throws Exception{
	BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> fn_VerifyHomeLoanText");
	boolean flag = false;
	WebElement we  = BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator("HomeLoan.Comparedpage"));
	if(we.isDisplayed()&& we.getText().equalsIgnoreCase("Home Loan Comparison")){
		BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> Expected element is displayed");
		Utility.highlightElementBorder(we);
		flag=true;return flag;
		}
	else{
		BaseClass.logger.log(LogStatus.WARNING, "Expected Text is not found!!!");
		return flag;
	}
	
	
	
	
	
}
}
