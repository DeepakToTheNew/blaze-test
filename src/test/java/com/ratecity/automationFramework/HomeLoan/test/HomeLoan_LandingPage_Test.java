package com.ratecity.automationFramework.HomeLoan.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ratecity.automationFramework.HomeLoan.pages.HomeLoanComparisonpage;
import com.ratecity.automationFramework.HomeLoan.pages.HomeLoanLandingpage;
import com.ratecity.automationFramework.HomeLoan.pages.HomeLoanMortgageRates;
import com.ratecity.automationFramework.HomeLoan.utilities.BaseClass;
import com.ratecity.automationFramework.HomeLoan.utilities.RespositoryParser;
import com.ratecity.automationFramework.HomeLoan.utilities.Utility;
import com.relevantcodes.extentreports.LogStatus;



public class HomeLoan_LandingPage_Test extends com.ratecity.automationFramework.HomeLoan.utilities.BaseClass {



	@Test(priority=1,alwaysRun=true,description="Test to Verify Top Links should work!!")
	public void HomeLoanTest01_ToVerifyTopLinksAreWorking() throws Exception{
		System.out.println("*************From ToVerifyTopLinksAreWorking****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyTopLinks");
		if(com.ratecity.automationFramework.HomeLoan.pages.HomeLoanLandingpage.fn_HomeLoanTopLinks()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"All Top links are working");
		}else{
			Assert.assertTrue(false,"Links are not working");
			BaseClass.logger.log(LogStatus.FAIL,"All Top links not are working");
		}
	}

	@Test(priority=2,alwaysRun=true,description="Test to Verify Compare bar should not have more then 5 options")
	public void HomeLoanTest02_ToVerifyMax5ProductsOnCompareBar() throws Exception{
		System.out.println("***********ToVerifyMax5ProductsOnCompareBar**********");
		logger=report.startTest("HomeLoanLandingPage_VerifyMax5ProductsOnCompareBar");
		HomeLoanMortgageRates.fn_ClickOnCompareCheckbox(5);
		if(HomeLoanLandingpage.fn_CountvaluesOnComparebar()==5){
			if(HomeLoanLandingpage.fn_isCompareButtonVisible() && HomeLoanLandingpage.fn_isClearButtonvisible()){
				Assert.assertTrue(true);
				BaseClass.logger.log(LogStatus.PASS,"All Selected Values & Button's are getting displayed");
			}else{
				Assert.assertTrue(false,"Button's are not visible");
				BaseClass.logger.log(LogStatus.PASS,"Compare Or Clear Button's is not visible");
			}
		}else{
			Assert.assertTrue(false,"Selected value is not equal to 5");
			BaseClass.logger.log(LogStatus.PASS,"Value on Compare Bar is not equal to 5");
		}
	}

	/*@Test(priority=3,alwaysRun=true,description="Test to Verify,Products links are Working")
	public void HomeLoanTest03_ToVerifyCompanyProductslinksAreWorking() throws Exception{
		System.out.println("*************From ToVerifyCompanyProductslinksAreWorking****************");
		logger=report.startTest("HomeLoanLandingPage_VerifyCompanyProductslinksAreWorking");
		if(HomeLoanLandingpage.fn_CheckCompany_ProductLink()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"All Company/Products links are working");
		}else{
			Assert.assertTrue(false,"Links are not working");
			BaseClass.logger.log(LogStatus.FAIL,"links not are working");
		}
	}

	@Test(priority=4,alwaysRun=true,description="TestCase to Verify Browse More Link is working or Not")
	public void HomeLoanTest04_ToVerifyBrowseMoreLinkisWorking() throws Exception{
		System.out.println("*************ToVerifyBrowseMoreLinkisWorking****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyBrowseMoreLinkisWorking");
		if(HomeLoanLandingpage.fn_CheckBrowseMoreLink()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"Browse More link is working");
		}else{
			Assert.assertTrue(false,"Links are not working");
			BaseClass.logger.log(LogStatus.FAIL,"Browse More  link is not  working");
		}
	}

	@Test(priority=5,alwaysRun=true,description="Test to Verify,Home Loan tools should be working")
	public void HomeLoanTest05_ToVerifyHomeLoantoolsAreworking(){

		logger=report.startTest("HomeLoanLandingPage_VerifyHomeLoantoolsAreworking");
		if(HomeLoanLandingpage.fn_HomeLoanTools()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"All Tools are  working");
		}else{
			Assert.assertTrue(false,"Links are not working");
			BaseClass.logger.log(LogStatus.FAIL,"Tools are not working");
		}
	}
	
	@Test(priority=6,alwaysRun=true,description="Test to Verify,Home Loan News are Working")
	public void HomeLoanTest06_ToVerifyHomeLoanNewsAreWorking() throws Exception{
		System.out.println("*************ToVerifyHomeLoanNewsAreWorking****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyHomeLoanNewsAreWorking");
		if(HomeLoanLandingpage.fn_HomeLoanNews()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"All News Links  are  working");
		}else{
			BaseClass.logger.log(LogStatus.FAIL,"Issue in News links");
			Assert.assertTrue(false,"Links are not working");
		} 
	}

	@Test(priority=7,alwaysRun=true,description="Test to Verify,ContentLinks And PopularHomeLoan Links are Working")
	public void HomeLoanTest07_ToVerifyContentLinksAndPopularHomeLoanLinks()throws Exception{
		System.out.println("*************ToVerifyContentLinksAndPopularHomeLoanLinks****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyContentLinksAndPopularHomeLoanLinks");
		if(HomeLoanLandingpage.fn_CheckIfArticlesLinksAreWorking()){
			if(HomeLoanLandingpage.fn_PopularHomeLoan()){
				Assert.assertTrue(true);
				BaseClass.logger.log(LogStatus.PASS,"All Links  are  working");
			}else{
				Assert.assertTrue(false,"Issue in Popular Home Loan");
				BaseClass.logger.log(LogStatus.FAIL,"Issue found in Popular home Link");
			}
		}else{
			Assert.assertTrue(false,"Links are not working");
			BaseClass.logger.log(LogStatus.FAIL,"Issue found in Articles Links");
		}
	}

	//@Test(priority=8,alwaysRun=true)
	public void HomeLoanTest08_ToVerifyAdsRecordsArticles() throws IOException{
		System.out.println("*************ToVerifyAdsRecordsArticles****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyAdsRecordsArticles");
		Utility.GoToSleep(2000);
		if(HomeLoanLandingpage.fn_CheckIfAds("HomeLoan.Ads_top")|| HomeLoanLandingpage.fn_CheckIfAds("HomeLoan.Ads_Bottom")){

			if(HomeLoanLandingpage.fn_CheckIfRateTableHas6Records("HomeLoan.RateRecordsContainer","HomeLoan.RateRecords")){
				if(HomeLoanLandingpage.fn_CheckIfhasArticles("HomeLoan.articles")){
					logger.log(LogStatus.PASS,"HomeLoan page landed Successfully" );
					Assert.assertTrue(true, "HomeLoan page landed Successfully"); 
				}
			}
		}
		else{
			logger.log(LogStatus.FAIL,"HomeLoan page is not landed Successfully" );
			Assert.assertTrue(false, "HomeLoan page is not landed successfully");
		}
	}
	@Test(priority=9,alwaysRun=true,description="Test to Verify, EmbedButton Functionality should work")
	public void HomeLoanTest09_ToVerifyEmbedButtonFunctionality() throws Exception{
		System.out.println("*************ToVerifyEmbedButtonFunctionality****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyEmbedButtonFunctionality");
		HomeLoanMortgageRates.fn_ClickOnCompareCheckbox(2);
		HomeLoanMortgageRates.fn_ClickOnCompareButton();
		HomeLoanComparisonpage.fn_ClickOnGetTheWidgetButton();
		if(HomeLoanComparisonpage.fn_CheckEmbedWindowisDisplayed()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"Embed Window is displayed");
		}else{
			Assert.assertTrue(false,"Embed Window is not displayed");
			BaseClass.logger.log(LogStatus.FAIL,"Embed Window is not displayed");
		}
	}
	@Test(priority=10,alwaysRun=true,description="Test to Verify, AllTop Menu and SubMenu should work")
	public void HomeLoanTest10_ToVerifyTopMenuSubMenuisDisplayed()throws Exception{
		boolean flag=false;
		System.out.println("*************ToVerifyTopMenuSubMenuisDisplayed****************_");
		logger=report.startTest("HomeLoanLandingPage_VerifyTopMenuSubMenuisDisplayed");
		List<WebElement> menulist = BaseClass.getDriver().findElements(new RespositoryParser().getobjectLocator("HomeLoan.TopMenu"));
		for (WebElement webElement : menulist) {
			if(HomeLoanLandingpage.fn_CheckIfMenuIsPresent(webElement.getText())){
			   flag=true;
			}else{
				break;
			}
		}
		if(flag){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"All TopMenu & their SubMenu is getting displayed");
		}
		else {
			Assert.assertTrue(false);
			BaseClass.logger.log(LogStatus.FAIL,"Issue in TopMenu & their SubMenu ");
		}
	}

	@Test(priority=11,alwaysRun=true,description="Test to Verify, EmbedButton Functionality should work")
	public void HomeLoanTest11_ToVerifyFooterMenuisDisplayed() throws Exception{
		System.out.println("*************ToVerifyTopMenuSubMenuisDisplayed****************");	
		logger=report.startTest("HomeLoanLandingPage_VerifyFooterMenuisDisplayed");
		if(HomeLoanLandingpage.fn_VerifyFooterMenuisDisplayed()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"Footer Menus block is getting displayed");
		}else{
			Assert.assertTrue(false,"Footer Menus block are not getting displayed");
			BaseClass.logger.log(LogStatus.FAIL,"Footer Menus block are not getting displayed");
		}
	}

	@Test(priority=12,alwaysRun=true,description="Test to Verify, Disclaimer Text should displayed")
	public void HomeLoanTest12_ToVerifyDisclaimerTextisDisplayed() throws Exception{
		System.out.println("*************ToVerifyDisclaimerTextisDisplayed****************");	
		logger=report.startTest("HomeLoanLandingPage_VerifyDisclaimerTextisDisplayed");
		if(HomeLoanLandingpage.fn_DisclaimerisDisplayed()){
			Assert.assertTrue(true);
			BaseClass.logger.log(LogStatus.PASS,"DisclaimerText block is getting displayed");
		}else{
			Assert.assertTrue(false,"DisclaimerText block are not getting displayed");
			BaseClass.logger.log(LogStatus.FAIL,"DisclaimerText block is not getting displayed");
		}
	}

	@Test(priority=13,alwaysRun=true,description="Test to Verify,RateCity Logo & text should displayed")
	public void HomeLoanTest13_ToVerifyRateCityLogoAndText()throws Exception{
		System.out.println("*************ToVerifyRateCityLogoAndText****************");
		logger=report.startTest("HomeLoanLandingPage_VerifyRateCityLogoAndText");
		if(HomeLoanLandingpage.fn_VerifyRateCityLogoisDisplayed()){
			if(HomeLoanLandingpage.fn_CopyRightedInfoIsPresent()){
				Assert.assertTrue(true);
				BaseClass.logger.log(LogStatus.PASS,"Logo And text is getting displayed");
			}else{
				Assert.assertTrue(false);
				BaseClass.logger.log(LogStatus.FAIL,"CopyRightedInfo is not getting displayed");
			}
		}else {
			Assert.assertTrue(false);
			BaseClass.logger.log(LogStatus.FAIL,"RateCityLogo is not getting displayed");
		}
	}

	@Test(priority=14,alwaysRun=true,description="Test to Verify,AboutUs & Contact Link should work")
	public void HomeLoanTest14_ToVerifyAboutUsAndContactusLinks()throws Exception{
		System.out.println("*************ToVerifyAboutUsAndContactusLinks****************");
		logger=report.startTest("HomeLoanLandingPage_VerifyAboutUsAndContactusLinks");
		if(HomeLoanLandingpage.fn_CheckAboutUsIsWorking()){
			if(HomeLoanLandingpage.fn_CheckContactUsIsWorking()){
				Assert.assertTrue(true);
				BaseClass.logger.log(LogStatus.PASS,"AboutUs & Contact Us links are working");
			}else{
				Assert.assertTrue(false);
				BaseClass.logger.log(LogStatus.FAIL,"Issue with Contact Us link");
			}
		}else {
			Assert.assertTrue(false);
			BaseClass.logger.log(LogStatus.FAIL,"Issue with About Us link");
		}
	}*/
}
