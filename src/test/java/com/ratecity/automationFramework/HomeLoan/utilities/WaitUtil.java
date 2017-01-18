package com.ratecity.automationFramework.HomeLoan.utilities;



import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class WaitUtil {
	
	private static WaitUtil Wait;
	private WaitUtil(){
		
	}
	public static WaitUtil getWait(){
		if(Wait==null){
			Wait=new WaitUtil();
		}
		return Wait;
		
	}
	
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingInMilliSeconds
	 * @return
	 * @throws Exception
	 */
	
	public  boolean waitForElementToAppear(
			final WebElement element, int timeOutInSeconds,
			int pollingInMilliSeconds) throws Exception {
		try {
			BaseClass.logger.log(LogStatus.INFO, "Wait for Element to Appear using Wait until element is Displayed");
			//LOGGER.info();
			return (new WebDriverWait(BaseClass.getDriver(), timeOutInSeconds,
					pollingInMilliSeconds))
					.until(new ExpectedCondition<Boolean>() {
	
						public Boolean apply(WebDriver driver) {
							BaseClass.logger.log(LogStatus.INFO,"Waiting for element to be displayed ");
							return element.isDisplayed();
						}
					});
	
		} catch (NoSuchElementException e) {
			BaseClass.logger.log(LogStatus.ERROR,"The element to be serach is not present in the page->waitForElementToAppear->"
					+ e.getMessage());
			throw new Exception(
					"The element to be serach is not present in the page->waitForElementToAppear->"
							+ e.getMessage());
		} catch (Exception e) {
			BaseClass.logger.log(LogStatus.ERROR,"Some exception in waitForElementToAppear"
					+ e.getMessage());
			throw new Exception("Some exception in waitForElementToAppear"
					+ e.getMessage());
		}
	
	}


}

