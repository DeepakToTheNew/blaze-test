package com.ratecity.homeloan.automationFramework.utilities;


import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
	public void onFinish(ITestContext context) {
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
	}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) { 
    	//BaseClass.pass.add(BaseClass.passCount++);
    }
  
    public void onTestFailure(ITestResult result) {  
    	//BaseClass.fail.add(BaseClass.failCount++);
    }

    public void onTestSkipped(ITestResult result) { 
    	//BaseClass.skip.add(BaseClass.skipCount++);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) { 
    	//BaseClass.total.add(BaseClass.totalCount++);
    }
}  

