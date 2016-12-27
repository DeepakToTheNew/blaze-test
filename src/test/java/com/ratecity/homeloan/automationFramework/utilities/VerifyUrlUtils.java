package com.ratecity.homeloan.automationFramework.utilities;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;


public class VerifyUrlUtils {

	static final Map<String , String> PopularHomeLoansURL = new HashMap<String , String>() {/**
	 * 
	 */
		private static final long serialVersionUID = 1L;

		{
			put("Fixed rate home loans",    "Fixed Rate Home Loan Rates from");
			put("Variable rate home loans", "Variable Rate Home Loanss");
			put("Variable Rate Mortgage","Variable Rate Home Loans");
			put("Investment Property","Investment Property");
			put("Low Interest Home Loans", "Low Interest Home Loans"); 
			put("No deposit home loans", "No Deposit Home Loan Rates"); 
			put("Construction loans", "Construction loans"); 
			put("Owner builder loans", "Owner builder"); 
			put("Under 4% club", "Under 4 club loans"); 
			put("LOW DOC Home Loans", "Low Doc Home Loans"); 
			put("Low Interest Mortgage", "Low Interest Home Loans"); 
			put("Best Mortgage", "Mortgages"); 
			put("Home Loan Ratings", "Compare Home Loan Rates"); 
			put("First Mortgage", "First Home Loans"); 
			put("Compare Home Loans", "Home Loan Comparison"); 
			put("Mortgage Rates", "Mortgage Rates"); 
			put("1 year Fixed", "1 Year Fixed Mortgage"); 
			put("3 year Fixed", "3 Year Fixed Mortgage"); 
			put("SMSF Loans", "Self Managed Super Loans"); 
			put("1 Year Fxd Investment Property", "1 year fixed investment mortgages"); 
			put("3 Year Fxd Investment Property", "3 year fixed investment mortgages "); 
			put("Home Equity", "Compare home equity rates"); 
			put("Deals", "Compare home loan deals"); 
			put("Stamp Duty Calculator","Calculator");
			put("Product Comparison", "Compare home loan product"); 
			put("Banks variable rate mortgages", "Compare bank variable home loans"); 
			put("Search", "Compare 3000+ home loans"); 
			put("Fixed Rate Mortgage","Fixed Rate Home Loan");
			put("No Doc Home Loans","No Doc Home Loans");
			put("All Popular Searches","Compare Home Loan");
			put("Popular Home Loans Searches","Compare Home Loan");
			put("Best Home Loan Rates","Best Home Loan Rates");
		}};

		static final Map<String , String> ArticlesLinks = new HashMap<String , String>() {/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			{
				put("mortgage calculator","Home Loan Calculator");
				put("SMSF loans", "Self Managed Super Loans");
				put("equity loans", "home equity rates"); 
				put("applying for your first home loan", "Are you buying your first home?"); 
				put("Read the Home Loans Guide", "Home Loans Guide"); 

			}};
			static final Map<String , String> CompanyProductsLinks = new HashMap<String , String>() {/**
			 * 
			 */
				private static final long serialVersionUID = 1L;

				{
					put("PROMOTEDMortgage Saver Home Loan","Mortgage Saver Home Loan");
					put("Basic Home Loan (LVR < 80%)", "Basic Home Loan");
					put("Value Offer - 20% Deposit Minimum", "Value Offer - 20% Deposit Minimum"); 
					put("Special Low Variable Rate Home Loan", "Special Low Variable Rate Home Loan"); 
					put("Breathe Easy Home Loan (LVR < 80%)", "Breathe Easy Home Loan"); 
					put("Kick Starter Home Loan", "Home Loans Kick Starter Home Loan");
					put("PROMOTEDBudget Home Loan (LVR < 90%)", "IMB Budget Home Loan");
					put("PROMOTEDMortgage Saver Home Loan", "Mortgage Saver Home Loan");
					put("PROMOTEDKick Starter Home Loan", "Home Loans Kick Starter Home Loan");
					put("Essentials Home Loan (Purchase) (Principal and Interest)", "Essentials Home Loan");
					

				}};
				static final Map<String , String> AboutUs_ContactUs = new HashMap<String , String>() {/**
					 * 
					 */
						private static final long serialVersionUID = 1L;

						{
							put("About Us","About Us");
							put("Contact Us", "Contact RateCity");
						}};


				public static boolean fn_VerifyTitle_PopularHomeLoans(String urlText){
					if(BaseClass.getDriver().getTitle().contains(PopularHomeLoansURL.get(urlText))){
						Utility.GoToSleep(1000);
						Utility.goBack();
						BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_VerifyTitle_PopularHomeLoans : "+urlText +" Page Loaded Succesfully!!");
						return true;
					}else{
						BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==>fn_VerifyTitle_PopularHomeLoans : "+urlText +" Page is not Loaded Succesfully!!");
						Assert.assertTrue(false,urlText +" Page is not Loaded Succesfully!!");
						return false;
					}
				}
				public static boolean fn_VerifyTitle_ArticlesLinks(String urlText){

					if(BaseClass.getDriver().getTitle().contains((ArticlesLinks.get(urlText)))){
						Utility.GoToSleep(1000);
						Utility.goBack();
						BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_VerifyTitle_ArticlesLinks : "+urlText +" Page Loaded Succesfully!!");
						return true;
					}else{
						BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==>fn_VerifyTitle_ArticlesLinks : "+urlText +" Page is not Loaded Succesfully!!");
						Assert.assertTrue(false,urlText +" Page is not Loaded Succesfully!!");
						return false;
					}
				}

				public static boolean fn_VerifyTitle_CompanyProductsLinks(String urlText){
                    
					if(BaseClass.getDriver().getTitle().contains(CompanyProductsLinks.get(urlText))){
						Utility.GoToSleep(1000);
						Utility.goBack();
						BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_VerifyTitle_CompanyProductsLinks : "+urlText +" Page Loaded Succesfully!!");
						return true;
					}else{
						BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==>fn_VerifyTitle_CompanyProductsLinks : "+urlText +" Page is not Loaded Succesfully!!");
						Assert.assertTrue(false,urlText +" Page is not Loaded Succesfully!!");
						return false;
					}
				}
				public static boolean fn_VerifyTitle_About_ContactUs(String urlText){
					if(BaseClass.getDriver().getTitle().contains(AboutUs_ContactUs.get(urlText))){
						Utility.GoToSleep(1000);
						BaseClass.logger.log(LogStatus.INFO, "INTO METHOD==>fn_VerifyTitle_About_ContactUs : "+urlText +" Page Loaded Succesfully!!");
						Utility.goBack();
						return true;
					}else{
						BaseClass.logger.log(LogStatus.FAIL, "INTO METHOD==>fn_VerifyTitle_About_ContactUs : "+urlText +" Page is not Loaded Succesfully!!");
						Assert.assertTrue(false,urlText +" Page is not Loaded Succesfully!!");
						return false;
					}
				}

}

