package com.ratecity.automationFramework.HomeLoan.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.ratecity.automationFramework.HomeLoan.pages.HomeLoanMortgageRates;
import com.relevantcodes.extentreports.LogStatus;


public class Utility {




	/**
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return
	 */
	public static String CaptureScreen(WebDriver driver, String screenshotName){
	
		String dest=null;
		BaseClass.logger.log(LogStatus.INFO,"INTO Method ==> CaptureScreenShot and Return path");
		TakesScreenshot oScn = (TakesScreenshot)driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+File.separator+"Reports");
		if (!file.exists()) {
            if (file.mkdir()) {
            	dest = System.getProperty("user.dir")+File.separator+"Reports"+File.separator+screenshotName+".jpeg";
            } else {
               BaseClass.logger.log(LogStatus.ERROR, "INTO Method ==> Failed to create directory in specified position");
            }
		} 
            else{
            	dest = System.getProperty("user.dir")+File.separator+"Reports"+File.separator+screenshotName+".jpeg";
            }
		File oDest = new File(dest);
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {System.out.println(e.getMessage());}
		return dest;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static boolean isLinkBroken(URL url) throws Exception
	{

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try{
		     connection.connect();
		     if(connection.getResponseMessage().equalsIgnoreCase("OK") && connection.getResponseCode()==200 ){
		    	 return true;
		     }
		}catch(Exception exp)
		{
			
		}  				
      return false;
	}
	
	public static boolean ContainsIgnorecase(String str1, String str2){
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		System.out.println("Str1" + str1);
		System.out.println("Str2" + str2);
		if(str1.contains(str2))
			return true;
		return false;
	}
	
	public static boolean fn_VerifyURLStatus(String URL){
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				return true;
			}
	    }catch(Exception e){
	    	
	    }
		return false;
	}
	/**
	 * 
	 * @param prev_value
	 * @param updated_value
	 * @return
	 * @throws IOException 
	 */
	public static boolean fn_CompareTwocollectionvalues(String prev_value,String updated_value ) throws IOException{
		boolean flag  = false;
		if(Integer.parseInt(fn_ModifyString(prev_value))
				<= Integer.parseInt(fn_ModifyString(updated_value))){
			flag=true;
		}else{
			updated_value=com.ratecity.automationFramework.HomeLoan.pages.HomeLoanMortgageRates.fn_MonthlyRepaymentAfterUpdate(); 
			if(Integer.parseInt(fn_ModifyString(prev_value))
					< Integer.parseInt(fn_ModifyString(updated_value))){
				flag=true;
		}}
		return flag;
	}
	
	public static String fn_ModifyString(String value){
		value = value.replace("$","");
		return value;
	}

	/**
	 * 
	 * @param parameter
	 * @return
	 */

	public static String fn_ReaddataFronPropFile(String parameter){

		File file = new File("config.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = prop.getProperty(parameter);
		return value;
	}

	/**
	 * 
	 * @param element
	 */

	public static void highlightElementBorder(WebElement element) {

		try {
			if (true) {
				for (int i = 0; i < 1; i++) {
					JavascriptExecutor js = (JavascriptExecutor) BaseClass.getDriver();
					js.executeScript(
							"arguments[0].setAttribute('style', arguments[1]);",
							element, "color: yellow; border: 10px solid red;");
					// + "border: 4px solid red;");
					Thread.sleep(1200);
					js.executeScript(
							"arguments[0].setAttribute('style', arguments[1]);",
							element, "");
				}
			}
			Thread.sleep(3000);

		} catch (Exception e) {
			//ObjectManager.getLogger().error("Error Occured highlighting Border " + element);
		}
	}

	/**
	 * 
	 * @param element
	 */
	public static void scrollToElement( WebElement element) {
		//base.getLogger().info("Scrolling to Element");
		((JavascriptExecutor) BaseClass.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@SuppressWarnings("unused")
	public static boolean isElementPresent( By by) {
		WebElement element;
		try {
			element =BaseClass.getDriver().findElement(by);

			return true;
		} catch (NoSuchElementException e) {

			return false;
		}
	}


	/**
	 * 
	 * @param by
	 * @return
	 */
	public  static boolean isElementPresentAndDisplay( By by) {
		boolean flag=false;
		try {
			if(BaseClass.getDriver().findElement(by).isDisplayed()){
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>isElementPresentAndDisplay : Element Is found & displayed");flag=true;
				return flag;	
			}
			
		} catch (NoSuchElementException e) {
			GoToSleep(3000);
			System.out.println("*********************Going for sleep");
			BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>isElementPresentAndDisplay : Going for sleep & wait sometime for the element to be displayed");
			if(BaseClass.getDriver().findElement(by).isDisplayed()){
			 return flag=true;
			}
			
		}catch(ElementNotVisibleException e){
			GoToSleep(3000);
			System.out.println("*********************Going for sleep");
			BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>isElementPresentAndDisplay : Going for sleep & wait sometime for the element to be displayed");
			if(BaseClass.getDriver().findElement(by).isDisplayed()){
			 return flag=true;
			}
		}
		//BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>isElementPresentAndDisplay : Element not found after given time");
		return false;
	}

	/**
	 * 
	 * @param TimeInMillis
	 */
	public static void GoToSleep(int TimeInMillis) {
		try {
			Thread.sleep(TimeInMillis);
		} catch (Exception e) {
			e.printStackTrace();
			BaseClass.logger.log(LogStatus.FAIL, e.getMessage());
		}
	}

	/**
	 * 
	 * @param locator
	 * @throws IOException
	 */
	public static void selectcheckbox(String locator) throws IOException {
		By by=new RespositoryParser().getobjectLocator(locator);
		try {
			if (!BaseClass.getDriver().findElement(by).isSelected()) {
				System.out.println(">>>>>>>>>>>>>>>ElementIsNotSelected<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(locator)).click();
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				BaseClass.logger.log(LogStatus.INFO,"*****Checkbox is selected now*****");
			} else {
				BaseClass.logger.log(LogStatus.INFO,"*****Checkbox is already selected*****");
			}
		} catch (ElementNotVisibleException env) {
			GoToSleep(1000);
			if (!BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(locator))
					.isSelected())
				BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(locator)).click();
			env.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"*****Element is present in DOM but not visible on the page*****"
					+ env.getMessage());
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"*****The element could not be located on the page.*****"
					+ ne.getMessage());
		} catch (StaleElementReferenceException se) {
			se.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"*****Either the element has been deleted entirely or the element is no longer attached to DOM.*****"
					+ se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"*****Error in selecting a checkbox!!! *****"
					+ e.getMessage());
		}
	}
	
	public static boolean isCheckBoxSelected(WebElement locator){
		if(locator.isSelected()) return true;
		else return false;
	}
	/**
	 * 
	 * @param webelement
	 * @param movetoValue
	 * @throws Exception
	 */
	public static void dragAnddropBy(WebElement webelement,
			int movetoValue) throws Exception   {
		try{
			BaseClass.logger.log(LogStatus.INFO,"INTO Method ==> dragAnddropBy");
			 Actions move = new Actions(BaseClass.getDriver());
			 move.dragAndDropBy(webelement, movetoValue, 0);
			 move.build().perform();
			 Utility.GoToSleep(2000);
			 BaseClass.logger.log(LogStatus.INFO,"INTO Method ==> dragAnddropBy : Scroll Moved Successfully!!!!");
		   }catch(Exception e){
			   BaseClass.logger.log(LogStatus.ERROR,"INTO Method ==> dragAnddropBy"+e.getMessage());
			   throw new Exception("::::::::::::::::::::::::" + e.getMessage());
		   }
	}

	/**
	 * 
	 * @param we
	 */
	public static void selectcheckbox(WebElement we) {
		try {
			if (we.isDisplayed() && !we.isSelected()) {
				Actions action = new Actions(BaseClass.getDriver());
				action.moveToElement(we).click().perform();
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- Checkbox is selected");
			} else {
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- Checkbox is already selected");
			}
		} catch (ElementNotVisibleException env) {
			GoToSleep(1000);
			if (!we.isSelected())
				we.click();
			env.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- Element is present in DOM but not visible on the page"
					+ env.getMessage());
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- The element could not be located on the page."
					+ ne.getMessage());
		} catch (StaleElementReferenceException se) {
			se.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- Either the element has been deleted entirely or the element is no longer attached to DOM."
					+ se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>selectcheckbox -- Error in selecting a checkbox!!!"
					+ e.getMessage());
		}
	}

	/**
	 * 
	 * @param element
	 * @param iTimeOut
	 * @throws Exception
	 */
	public static void clickAndWait( WebElement element,int iTimeOut) throws Exception {
		try{
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>ClickAndWait");
			clickElement(element);
			GoToSleep(iTimeOut);

		}catch(Exception e){
			BaseClass.logger.log(LogStatus.WARNING,"INTO METHOD==>ClickAndWait : Not able to click on Element");
		}


	}


	/**
	 * 
	 * @param element
	 * @return
	 */
	public static String getText(WebElement element) {
		return element.getText();
	}


	/**
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public static boolean clickElement( WebElement element) throws Exception {
		boolean flag = false;
		try{
			element.click();flag=true;
			BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> ClickElement : Element clicked successfully!");
			return flag;
		}catch(ElementNotVisibleException ne){
			BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> ClickElement : Waiting for element to be visible");
			GoToSleep(2000);
			element.click();flag=true;
			BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> ClickElement : Element clicked successfully after waiting**");
			return flag;
		}
		catch(StaleElementReferenceException se){
			BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> ClickElement : Waiting for element to be visible after Stale Reference **");
			GoToSleep(2000);
			try{
				//Thread.sleep(10000);
				element.click();flag=true;
				return flag;
			}catch(ElementNotVisibleException e){
				element.click();
			}
		}catch(NoSuchElementException ne){
			BaseClass.logger.log(LogStatus.INFO, "INTO Method ==> ClickElement : Waiting for element In No Such element exception**");
			GoToSleep(2000);;
			try{
				//Thread.sleep(10000);
				element.click();flag=true;
				return flag;
			}catch(ElementNotVisibleException e){
				element.click();
			}
		}
		return flag;
	}
	
	/**
	 * 
	 */
	public static void goBack(){
		BaseClass.getDriver().navigate().back();
		GoToSleep(1000);
	}
	/**
	 * Method to verify, If image is present or not?
	 * @param by
	 * @return
	 */
	public static boolean isImagePresent(By by){
		WebElement ImageFile = BaseClass.getDriver().findElement(by);
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)BaseClass.getDriver()).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
		if(!ImagePresent)return false;
		else return true;
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement fluentWait(final By locator){
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(BaseClass.getDriver())
	        .withTimeout(30, TimeUnit.SECONDS)
	        .pollingEvery(5, TimeUnit.SECONDS)
	        .ignoring(NoSuchElementException.class);

	    WebElement element = wait.until(
	        new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        }
	    );
	    return element;
	};
}

