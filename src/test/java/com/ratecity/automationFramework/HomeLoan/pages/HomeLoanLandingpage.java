package com.ratecity.automationFramework.HomeLoan.pages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.ratecity.automationFramework.HomeLoan.utilities.ActionsUtil;
import com.ratecity.automationFramework.HomeLoan.utilities.BaseClass;
import com.ratecity.automationFramework.HomeLoan.utilities.RespositoryParser;
import com.ratecity.automationFramework.HomeLoan.utilities.Utility;
import com.ratecity.automationFramework.HomeLoan.utilities.VerifyUrlUtils;
import com.relevantcodes.extentreports.LogStatus;



public class HomeLoanLandingpage {




	public static boolean fn_verifytitle(){

		if(BaseClass.getDriver().getTitle().equalsIgnoreCase("Home Loan Comparison | Compare Mortgages | RateCity")){
			return true;
		}
		else
			return false;
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws IOException
	 */

	public static boolean fn_CheckIfAds(String value) throws IOException{
		boolean visibility=false;
		WebElement ads  = BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(value));
		Utility.scrollToElement(ads);
		try{
			if(ads.isDisplayed()){
				Utility.highlightElementBorder(ads);
				BaseClass.logger.log(LogStatus.INFO, value +"is highlighted Successfully!!!!");
				visibility = true;
			}
		}catch(ElementNotVisibleException e){
			System.out.println("Element not visible"+e.getMessage());

		}catch (Exception e) {
			System.out.println("Element not visible"+e.getMessage());
		}

		return visibility;

	}

	/**
	 * 
	 * @param container
	 * @param value
	 * @return
	 * @throws IOException
	 */

	public static boolean fn_CheckIfRateTableHas6Records(String container,String value) throws IOException{
		WebElement rateRecordContainer = BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator(container));
		List<WebElement> rows = BaseClass.getDriver().findElements(new RespositoryParser().getobjectLocator(value));
		try{
			if(rows.size()==6){
				Utility.scrollToElement(rateRecordContainer);
				BaseClass.logger.log(LogStatus.INFO," :Successfull scroll till element visible");
				Utility.highlightElementBorder(rateRecordContainer);
				BaseClass.logger.log(LogStatus.INFO, value+" :is highlighted Successfully!!!!");
				//System.out.println("Size********************"+rows.size());
				return true;

			}
		}catch(NoSuchElementException e){
			BaseClass.logger.log(LogStatus.WARNING, e.getMessage());

		}
		return false;
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws IOException
	 */

	public static boolean fn_CheckIfhasArticles(String value) throws IOException{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		Utility.GoToSleep(500);
		WebElement highlightedArticle = BaseClass.getDriver().findElement(new RespositoryParser().getobjectLocator("HomeLoan.articles"));
		List<WebElement> allArticles = BaseClass.getDriver().findElements(new RespositoryParser().getobjectLocator("HomeLoan.articles"));
		try{
			if(allArticles.size()>0){				
				Utility.scrollToElement(highlightedArticle);
				BaseClass.logger.log(LogStatus.INFO,"From Method : "+name+" : Successfull scroll till element visible");
				Utility.highlightElementBorder(highlightedArticle);
				BaseClass.logger.log(LogStatus.INFO,"From Method : "+name+" : is highlighted Successfully!!!!");
				return true;
			}
		}catch (ElementNotFoundException e) {
			BaseClass.logger.log(LogStatus.WARNING, e.getMessage());

		}			
		return false;
	}

	public static boolean fn_CheckBrowseMoreLink()throws Exception{
		boolean flag=false;
		System.out.println("From ChekcBrowser Method************************************");
		WebElement browsemore = BaseClass.getDriver().findElement(new RespositoryParser().
				getobjectLocator("HomeLoan.BrowseMore"));
		if(Utility.isLinkBroken(new URL(browsemore.getAttribute("href")))){
			BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_CheckBrowseMoreLink : "+
					browsemore.getAttribute("href")+" is landed Successfully!!");
			Utility.GoToSleep(1000);
			browsemore.click();
			BaseClass.getDriver().navigate().back();
            flag=true;
		}
		else{
			BaseClass.logger.log(LogStatus.ERROR, "INTO METHOD==>fn_CheckBrowseMoreLink : "+
					browsemore.getAttribute("href")+" is not working");
		}
		return flag;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public static boolean fn_HomeLoanTopLinks() throws Exception{
		List<WebElement> homeloanType =null;
		boolean flag=false;
		homeloanType = BaseClass.getDriver().findElements(new RespositoryParser().
				getobjectLocator("HomeLoan.LoanTypes"));
		for(int i=0;i<homeloanType.size()-1;i++){
			
			if(!Utility.isLinkBroken(new URL(homeloanType.get(i).getAttribute("href")))){
				BaseClass.logger.log(LogStatus.ERROR, "INTO METHOD==>fn_HomeLoanTopLinks : "+homeloanType.get(i).getAttribute("href")+" is not working");
				break;
			}
			else{ 
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_HomeLoanTopLinks : "+homeloanType.get(i).getAttribute("href")+" is landed Successfully!!");
				if(homeloanType.get(i).isDisplayed()){
					homeloanType.get(i).click();
					Utility.GoToSleep(2000);
					BaseClass.getDriver().navigate().back();
					flag=true;
				}else{
					   if(!homeloanType.get(i+1).isDisplayed()){
			   					homeloanType.get(i+2).click();
					            Utility.GoToSleep(2000);
								BaseClass.getDriver().navigate().back();
								flag=true;}
					   else{
							homeloanType.get(i+1).click();
				            Utility.GoToSleep(2000);
							BaseClass.getDriver().navigate().back();
							flag=true;
					   }
     				}
			}
			homeloanType = BaseClass.getDriver().findElements(new RespositoryParser().
					getobjectLocator("HomeLoan.LoanTypes"));
		}
		return flag;
	}

	public static int fn_CountvaluesOnComparebar() throws IOException{

		List<WebElement> items =  BaseClass.getDriver().findElements(new RespositoryParser().
				getobjectLocator("HomeLoan.CompareBarValues"));
		if(items.size()!=0)
			return items.size()-1;
		return items.size();

	}

	public static boolean fn_isCompareButtonVisible() throws IOException{
		boolean flag=false;
		try{
			if(Utility.isElementPresentAndDisplay(new RespositoryParser()
					.getobjectLocator("HomeLoan.MortgageRate.CompareButton"))){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>ClickOnCompareButton : Compare button is visible");
				return flag=true;
			}
		}catch(ElementNotVisibleException env){
			Utility.GoToSleep(1500);
			if(Utility.isElementPresentAndDisplay(new RespositoryParser()
					.getobjectLocator("HomeLoan.MortgageRate.CompareButton"))){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>ClickOnCompareButton : Compare button is visible");   
			}
			BaseClass.logger.log(LogStatus.INFO,"*****Element is present in DOM but not visible on the page*****"
					+ env.getMessage());
		}
		return flag;
	}
	

	public static boolean fn_isClearButtonvisible()throws IOException{
		boolean flag=false;
		try{
			if(Utility.isElementPresentAndDisplay(new RespositoryParser()
					.getobjectLocator("HomeLoan.ClearButton"))){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_isClearButtonvisible : Clear button is visible");
				return flag=true;
			}
			return flag;
		}catch(ElementNotVisibleException env){
			Utility.GoToSleep(1500);
			if(Utility.isElementPresentAndDisplay(new RespositoryParser()
					.getobjectLocator("HomeLoan.ClearButton"))){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_isClearButtonvisible : Clear button is visible");
			}
			BaseClass.logger.log(LogStatus.INFO,"*****Element is present in DOM but not visible on the page*****"
					+ env.getMessage()); 
		}
		return flag;
	}

	public static void fn_ClickOnClearButton() throws Exception{
		if(Utility.isElementPresentAndDisplay(new RespositoryParser()
				.getobjectLocator("HomeLoan.ClearButton"))){
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickOnClearButton : Clear button is visible");
			WebElement clearbutton = BaseClass.getDriver().findElement(new RespositoryParser()
					.getobjectLocator("HomeLoan.ClearButton"));
			Utility.clickAndWait(clearbutton, 1000);
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickOnClearButton : Clear button is clicked Successfully!!!");
		}
		else{
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickOnClearButton : Clear button is not clicked ");
		}
	}

	/**
	 * 
	 * 
	 * @throws Exception
	 */
	public static boolean fn_HomeLoanNews() throws Exception{
		boolean flag=false;
		List<WebElement> homeloanNews =null;
		List<WebElement> homeloanNewsBlocks =null;
		Utility.GoToSleep(1000);
		homeloanNews = BaseClass.getDriver().findElements(By.cssSelector("div.news-item >a"));
		homeloanNewsBlocks = BaseClass.getDriver().findElements(new RespositoryParser().
				getobjectLocator("HomeLoan.News.Blocks"));
		for(int i=0;i<homeloanNews.size();i++){
			if(!Utility.fn_VerifyURLStatus(homeloanNews.get(i).getAttribute("href"))){
				BaseClass.logger.log(LogStatus.ERROR, "INTO METHOD==>fn_HomeLoanNews : "+homeloanNews.get(i).getAttribute("href")+" is not working");
				flag=false;
				break;
			}
			else{ 
				BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_HomeLoanNews : "+homeloanNews.get(i).getAttribute("href")+" is landed Successfully!!");
				Utility.scrollToElement(homeloanNewsBlocks.get(i)); 
				Utility.GoToSleep(1500);
				Utility.highlightElementBorder(homeloanNewsBlocks.get(i));
				homeloanNewsBlocks.get(i).click();
				Utility.GoToSleep(2000);
				Utility.goBack();
				flag=true;
			}
			homeloanNews = BaseClass.getDriver().findElements(By.cssSelector("div.news-item >a"));
			homeloanNewsBlocks = BaseClass.getDriver().findElements(new RespositoryParser().
					getobjectLocator("HomeLoan.News.Blocks"));
		}
		return flag;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public static void fn_ClickonMoreHomeLoanNews() throws Exception{
		if(Utility.isElementPresentAndDisplay(new RespositoryParser()
				.getobjectLocator("HomeLoan.MoreNews"))){
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickonMoreHomeLoanNews :"
					+ " More News Button is visible");
			WebElement moreNewsButton = BaseClass.getDriver().findElement(new RespositoryParser()
					.getobjectLocator("HomeLoan.MoreNews"));
			Utility.clickAndWait(moreNewsButton, 1000);
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickonMoreHomeLoanNews : "
					+ "More News Button is clicked Successfully!!");
			BaseClass.getDriver().navigate().back();
		}else{
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_ClickonMoreHomeLoanNews : More Home loan news is not visible!!");  
		}
	}

	/**
	 * 
	 * 
	 */
	public static boolean fn_HomeLoanTools(){
		boolean flag=false;
		List<WebElement> homeLoanTools=null;
		try {
			homeLoanTools = BaseClass.getDriver().findElements(new RespositoryParser()
					.getobjectLocator("HomeLoan.Tools"));
			for (int i=0;i<homeLoanTools.size();i++) {
				if (homeLoanTools.get(i) != null) {
					String url = homeLoanTools.get(i).getAttribute("href");
					if (url != null && !url.contains("#")) {
						if(Utility.fn_VerifyURLStatus(url)){
							homeLoanTools.get(i).click();
							Utility.GoToSleep(1000);
							BaseClass.getDriver().navigate().back();
							homeLoanTools = BaseClass.getDriver().findElements(new RespositoryParser()
									.getobjectLocator("HomeLoan.Tools"));
							BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_HomeLoanTools :"
									+homeLoanTools.get(i).getText()+" page is displayed successfully!!");
							flag=true;
						}else{
							BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_HomeLoanTools :"
									+ homeLoanTools.get(i).getText()+ " Link is not working!!"); 
						}
					} else {
						BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_HomeLoanTools :"
								+homeLoanTools.get(i).getText()+ " Contains #,So links displayed on same page!!");
						Utility.scrollToElement(homeLoanTools.get(i));
						if(homeLoanTools.get(i).getText().equalsIgnoreCase("Contact a broker")){
							homeLoanTools.get(i).click();
							String windowHandle = BaseClass.getDriver().getWindowHandle();
							BaseClass.getDriver().switchTo().window(windowHandle);
							Utility.GoToSleep(1500);
							//BaseClass.getDriver().findElement(By.name("firstName")).sendKeys("I M here");
							BaseClass.getDriver().findElement(By.xpath("//button[text()='Close']")).click();
							//Utility.GoToSleep(1000);
							BaseClass.getDriver().switchTo().defaultContent();
							flag=true;
						}
						else{
							homeLoanTools.get(i).click();
							Utility.GoToSleep(1000);
							homeLoanTools.get(i).click();
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
     return flag;
	}

	public static boolean fn_PopularHomeLoan()throws IOException{
		boolean flag=false;
		List<WebElement> popularHomeLoans=null;
		popularHomeLoans = BaseClass.getDriver().findElements(new RespositoryParser()
				.getobjectLocator("HomeLoan.PopularHomeLoanLinks"));
		try{
			for(int i=0;i<popularHomeLoans.size();i++){

				if(popularHomeLoans.get(i)!=null){
					if(Utility.fn_VerifyURLStatus(popularHomeLoans.get(i).getAttribute("href"))){
						String urlText = popularHomeLoans.get(i).getText();
						popularHomeLoans.get(i).click();
						if(VerifyUrlUtils.fn_VerifyTitle_PopularHomeLoans(urlText)){
							flag=true;
						}else{
							flag=false;
						}
						popularHomeLoans = BaseClass.getDriver().findElements(new RespositoryParser()
								.getobjectLocator("HomeLoan.PopularHomeLoanLinks"));
					}else{
						BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_PopularHomeLoan :"
								+ popularHomeLoans.get(i).getText()+ " Link is not working!!"); 
						flag=false;
					}
				}else{
					BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_PopularHomeLoan : not having values inside collection");
					flag=false;
				}
			}
		}catch(Exception e){

		}
		return flag;
	}
	public static boolean fn_CheckIfArticlesLinksAreWorking()throws IOException{
		List<WebElement> articlesLinks=null;
		boolean flag=false;
		articlesLinks = BaseClass.getDriver().findElements(new RespositoryParser()
				.getobjectLocator("HomeLoan.Articles.Links"));
		try{
			for(int i=0;i<articlesLinks.size();i++){
				if(articlesLinks.get(i)!=null){
					if(Utility.fn_VerifyURLStatus(articlesLinks.get(i).getAttribute("href"))){
						String urlText = articlesLinks.get(i).getText();
						articlesLinks.get(i).click();
						if(VerifyUrlUtils.fn_VerifyTitle_ArticlesLinks(urlText)){
							flag=true;
						}else {
							flag=false;
						}
						articlesLinks = BaseClass.getDriver().findElements(new RespositoryParser()
								.getobjectLocator("HomeLoan.Articles.Links"));
					}else{
						BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_CheckIfArticlesLinksAreWorking :"
								+ articlesLinks.get(i).getText()+ " Link is not working!!"); 
						flag=false;
					}	
				}else{
					BaseClass.logger.log(LogStatus.INFO,"INTO METHOD==>fn_CheckIfArticlesLinksAreWorking : not having values inside collection");
					flag=false;
				}
			}
		}catch(Exception e){

		}
		return flag;
	}

	public static boolean fn_CheckCompany_ProductLink() throws IOException{
		List<WebElement> companyProductLinks=null;
		boolean flag=false;
		companyProductLinks=BaseClass.getDriver().findElements(new RespositoryParser()
				.getobjectLocator("HomeLoan.Company_ProductsLink"));
		try{
			for(int i=0;i<companyProductLinks.size();i++){
				if(companyProductLinks.get(i)!=null){
					if(Utility.fn_VerifyURLStatus(companyProductLinks.get(i).getAttribute("href"))){
						String urlText = companyProductLinks.get(i).getText();
						companyProductLinks.get(i).click();
						if(!VerifyUrlUtils.fn_VerifyTitle_CompanyProductsLinks(urlText)){
							flag=false;
							BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_CheckCompany_ProductLink :"
									+ companyProductLinks.get(i).getText()+ "Wrong page is getting displayed"); 
							break;
						}
						companyProductLinks = BaseClass.getDriver().findElements(new RespositoryParser()
								.getobjectLocator("HomeLoan.Company_ProductsLink"));
						flag=true;
					}else{
						BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_CheckCompany_ProductLink :"
								+ companyProductLinks.get(i).getText()+ " Link is not working!!"); 
						flag=false;
					}	
				}else{
					BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD==>fn_CheckCompany_ProductLink : not having values inside collection");
					flag=false;
				}
			}
		}catch(Exception e){
           System.out.println("From catch Block!!!!");
		}
      return flag;
	}

	/**
	 * 
	 * @param locator
	 * @return
	 * @throws IOException
	 */

	public static boolean fn_CheckIfMenuIsPresent(String locator) throws IOException{
		boolean flag=false;
		By by=null;
		locator=locator.replace("\n", "").replaceAll("\\s","").toUpperCase();
		switch (locator) {
		case "HOMELOANS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.HomeMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" -- element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(1) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"HomeLoan menu is not Visible");
			}
			break;
		case "PERSONALLOANS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.PersonalMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(2) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"SAVE menu is not Visible");
			}
			break;
		case "CARLOANS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.CarMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(3) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"INVEST menu is not Visible");
			}
			break;
		case "CREDITCARDS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.CreditMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(4) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"CALCULATE menu is not Visible");
			}
			break;
		case "BANKING":
			by = new RespositoryParser().getobjectLocator("HomeLoan.BankingMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(5) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"NEWS menu is not Visible");
			}
			break;
		case "SUPERANNUATION&INVESTMENT":
			by = new RespositoryParser().getobjectLocator("HomeLoan.SuperannuationMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(6) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"NEWS menu is not Visible");
			}
			break;
		case "CALCULATORS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.CalculatorsMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(7) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"NEWS menu is not Visible");
			}
			break;
		case "NEWS&VIEWS":
			by = new RespositoryParser().getobjectLocator("HomeLoan.NewsMenu");
			if(Utility.isElementPresentAndDisplay(by)){
				BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckIfMenuIsPresent :"+locator+" --element is present!!");
				new ActionsUtil(BaseClass.getDriver()).fn_PerformMouseHover(BaseClass.getDriver().findElement(by));
				if(fn_CheckSubMenuIsPresent("return document.querySelectorAll('li.dropdown:nth-child(8) ul div a').length")){
					Assert.assertTrue(true,"SubMenu are available");
				}else{
					Assert.assertTrue(false,"SubMenu are not available!!");
				}
				flag= true;
			}else{
				BaseClass.logger.log(LogStatus.FATAL,"NEWS menu is not Visible");
			}
			break;

		default:
			System.out.println("********Menu with this name is not exist*******");
			BaseClass.logger.log(LogStatus.FAIL,"Menu with this name is not exist");
			flag=false;
			break;
		}
		return flag;
	}

	public static boolean fn_CheckSubMenuIsPresent(String locator) throws IOException  {
		boolean flag = false;
		JavascriptExecutor js = (JavascriptExecutor)BaseClass.getDriver();
		Long subMenu =  (Long) js.executeScript(locator);
		if(subMenu!=0){
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_CheckSubMenuIsPresent :"
					+subMenu + " - elements are present!!");
			flag =  true;
		}
		else{
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CheckSubMenuIsPresent :"
					+subMenu + "elements are present!!");
		}

		return flag;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean fn_CheckAboutUsIsWorking()throws Exception{
		boolean flag = false;
		By by;
		by = new RespositoryParser().getobjectLocator("HomeLoan.AboutUs");
		WebElement aboutUs = BaseClass.getDriver().findElement(by);
		if(Utility.fn_VerifyURLStatus(aboutUs.getAttribute("href"))){
			String text = aboutUs.getText();
			aboutUs.click();
			if(VerifyUrlUtils.fn_VerifyTitle_About_ContactUs(text)){
				flag=true;
			}
		}else{
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CheckAboutUsIsWorking :"
					+aboutUs.getText() + "-- URL is not working!!");
		}
		return flag;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean fn_CheckContactUsIsWorking()throws Exception{
		boolean flag = false;
		By by;
		by = new RespositoryParser().getobjectLocator("HomeLoan.ContactUs");
		WebElement aboutUs = BaseClass.getDriver().findElement(by);
		if(Utility.fn_VerifyURLStatus(aboutUs.getAttribute("href"))){
			String text = aboutUs.getText();
			aboutUs.click();
			if(VerifyUrlUtils.fn_VerifyTitle_About_ContactUs(text)){
				flag=true;
			}
		}else{
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CheckAboutUsIsWorking :"
					+aboutUs.getText() + "-- URL is not working!!");
		}
		return flag;

	}

	public static boolean fn_VerifyFooterMenuisDisplayed() throws Exception{
		By by=null;
		by = new RespositoryParser().getobjectLocator("HomeLoan.FooterMenu");
		Utility.scrollToElement(BaseClass.getDriver().findElement(by));
		//WebElement footerMenu = BaseClass.getDriver().findElement(by);
		if(Utility.isElementPresentAndDisplay(by)){
			Utility.highlightElementBorder(BaseClass.getDriver().findElement(by));
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_FooterMenuisDisplayed :"
					+"FOOTER MENU is getting displayed");
			return true;
		}else {
			return false;
		}
	}


	public static boolean fn_DisclaimerisDisplayed() throws Exception{
		By by=null;
		by = new RespositoryParser().getobjectLocator("HomeLoan.Disclaimer");
		Utility.scrollToElement(BaseClass.getDriver().findElement(by));
		//WebElement footerMenu = BaseClass.getDriver().findElement(by);
		if(Utility.isElementPresentAndDisplay(by)){
			Utility.highlightElementBorder(BaseClass.getDriver().findElement(by));
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_DisclaimerisDisplayed :"
					+"Disclaimer is getting displayed");
			return true;
		}else {
			return false;
		}
	}

	public static boolean fn_VerifyRateCityLogoisDisplayed() throws Exception{
		By by=null;
		by=	new RespositoryParser().getobjectLocator("HomeLoan.RateCityLogo");
		Utility.scrollToElement(BaseClass.getDriver().findElement(by));
		if(Utility.isImagePresent(by)){
			Utility.highlightElementBorder(BaseClass.getDriver().findElement(by));
			BaseClass.logger.log(LogStatus.INFO,"INTO METHOD ==>fn_VerifyRateCityLogoisDisplayed :"
					+"RateCityImage is getting displayed");
			return true;
		}else {
			return false;
		}
	}

	public static boolean fn_CopyRightedInfoIsPresent() throws Exception{
		By by=null;
		boolean flag = false;
		by=new RespositoryParser().getobjectLocator("HomeLoan.CopyRightInfo");
		List<WebElement> info = BaseClass.getDriver().findElements(by);
		if(!info.isEmpty()){
			if(info.get(0).isDisplayed()){
				Utility.scrollToElement(info.get(0));
				Utility.highlightElementBorder(info.get(0));
				if(info.get(1).isDisplayed()){
					Utility.scrollToElement(info.get(1));
					Utility.highlightElementBorder(info.get(1));
					if(info.get(2).isDisplayed()){
						Utility.scrollToElement(info.get(2));
						Utility.highlightElementBorder(info.get(2));
						flag=true;
					}else{
						BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CopyRightedInfoIsPresent :"
								+"© 2016 RateCity is not getting displayed");
						flag=false;
					}
				}else{
					BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CopyRightedInfoIsPresent :"
							+"AFSL & ACL 316710 is not getting displayed");
					flag=false;
				}}else{
					BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CopyRightedInfoIsPresent :"
							+"ABN 12122743542 is not getting displayed");
				}
		}else{
			BaseClass.logger.log(LogStatus.ERROR,"INTO METHOD ==>fn_CopyRightedInfoIsPresent :"
					+"Info is not present");
			flag=false;
	  }
		return flag;
	}
}
