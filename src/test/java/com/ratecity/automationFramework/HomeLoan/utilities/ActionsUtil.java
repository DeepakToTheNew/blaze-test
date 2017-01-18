package com.ratecity.automationFramework.HomeLoan.utilities;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class ActionsUtil {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor executor;
	Actions action;
	WebElement menu=null;
	
	public ActionsUtil(WebDriver driver)
	{
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver, 60);	
		executor = (JavascriptExecutor)driver;;
		action = new Actions(driver);
		
	}
	
	
	public void fn_PerformMouseHover(WebElement locator){
		try
		{  
		    //menu =  BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(locator));
			action.moveToElement(locator).click().build().perform();
			Utility.GoToSleep(1000);
		}
		catch(NoSuchElementException ne)
		{
			ne.printStackTrace();
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_PerformMouseHover******The element could not be located on the page.******" + ne.getMessage());
		}
		catch(ElementNotVisibleException env)
		{
			Utility.GoToSleep(2000);
			action.moveToElement(locator).build().perform();
			env.printStackTrace();
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_PerformMouseHover******Element is present in DOM but not visible on the page******"+env.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_PerformMouseHover******Could not perform mouse hover******"+e.getMessage());
		}
		
	}
	
	}

