package com.ratecity.homeloan.automationFramework.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ratecity.homeloan.automationFramework.pages.HomeLoanComparisonpage;
import com.ratecity.homeloan.automationFramework.pages.HomeLoanLandingpage;
import com.ratecity.homeloan.automationFramework.pages.HomeLoanMortgageRates;
import com.ratecity.homeloan.automationFramework.utilities.BaseClass;
import com.ratecity.homeloan.automationFramework.utilities.Utility;
import com.relevantcodes.extentreports.LogStatus;





public class HomeLoan_MortgageRates_Test extends BaseClass {

	@Test(priority=3,alwaysRun=true)
	public void MortgageRatesTest01_ToVerifyClearButtonFunctionality() throws Exception{
		System.out.println("*************MR_ToVerifyClearButtonFunctionalit****************");
		logger = report.startTest("HomeLoan_MortgageRates_VerifyClearButtonFunctionality");
		BaseClass.getDriver().navigate().to("http://www.ratecity.com.au/home-loans/mortgage-rates");
		HomeLoanMortgageRates.fn_ClickOnCompareCheckbox(2);
		HomeLoanLandingpage.fn_ClickOnClearButton();
		if(HomeLoanMortgageRates.fn_IsCompareCheckBoxSelected(2)){
			BaseClass.logger.log(LogStatus.ERROR,"Compare CheckBox is stil Selected"); 
			Assert.assertTrue(false);

		}else{
			Assert.assertTrue(true,"Check Box is not Selected");
			BaseClass.logger.log(LogStatus.PASS,"Compare CheckBox is not Selected"); 
		}
	}
	@Test(priority=1,alwaysRun=true)
	public void MortgageRatesTest02_toVerifyComparefunctionalityWith2Banks() throws Exception{
		System.out.println("*************MR_VerifyComparefunctionalityWith2Banks****************");
		logger = report.startTest("HomeLoan_MortgageRates_VerifyComparefunctionalityWith2Banks");
		Utility.GoToSleep(3000);
		HomeLoanMortgageRates.fn_ClickOnCompareCheckbox(2);
		Utility.GoToSleep(1500);
		HomeLoanMortgageRates.fn_ClickOnCompareButton();
		if(HomeLoanComparisonpage.fn_VerifyHomeLoanText()){
			if(HomeLoanComparisonpage.fn_VerifyComparisontable()==3){
				BaseClass.logger.log(LogStatus.PASS, "INTO METHOD==> CompareFunctionality :  Working Successfully!! :)");
				//Assert.assertTrue(true);
				Assert.assertTrue(true);
			}
			else{
				BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==> CompareFunctionality :  Not working Successfully!! (: ");
				Assert.assertTrue(false);
			}

		}
	}
	
	@Test(priority=3,alwaysRun=true)
	public void MortgageRatesTest03_toVerifyRateTableHas20Records()throws Exception{
		System.out.println("*************MR_VerifyRateTableHas20Records****************");
		logger = report.startTest("HomeLoan_MortgageRates_VerifyRateTableHas20Records");
		BaseClass.getDriver().navigate().to("http://www.ratecity.com.au/home-loans/mortgage-rates");
		Utility.GoToSleep(3000);
		if(HomeLoanMortgageRates.fn_CheckNumberOfRateRecord()==20){
			Assert.assertTrue(true); 
			BaseClass.logger.log(LogStatus.PASS, "20 Records are getting displayed");
		}else{
			Assert.assertTrue(false); 
			BaseClass.logger.log(LogStatus.FAIL, "20 Records is not getting displayed");
		}
	}

	@Test(priority=2,alwaysRun=true)
	public void MortgageRatesTest04_toVerifyCompareWithBig4Button() throws Exception{
		System.out.println("*************MR_VerifyCompareWithBig4Button****************");
		logger = report.startTest("HomeLoan_MortgageRates_VerifyCompareWithBig4Button");
		Utility.GoToSleep(2000);
		HomeLoanMortgageRates.fn_ClickOnCompareCheckbox(1);
		Utility.GoToSleep(1500);
		HomeLoanMortgageRates.fn_ClickOnComparewithBig4Button();
		if(HomeLoanComparisonpage.fn_VerifyHomeLoanText()){
			if(HomeLoanComparisonpage.fn_VerifyComparisontable()==6){
				BaseClass.logger.log(LogStatus.PASS, "INTO METHOD==> CompareWithBig4Button Functionality :  Working Successfully!! :)");
				Assert.assertTrue(true); 
			}
			else{
				BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==> CompareWithBig4 Functionality :  Not working Successfully!! (: ");
				Assert.assertTrue(false);
			}

		}
	}
	
}
