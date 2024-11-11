package UIAutomationTesting.testComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UIAutomation.resources.ExtentReporterTestNG;

public class Listeners extends BaseTestDriversSetUp implements ITestListener{

//Always there Listeners methods will be executed based on test execution status *****
	
	ExtentTest test;
	ExtentReports htmlReport = ExtentReporterTestNG.reportConfig();
	
	//Implementing Thread Safe - for test object
	//Each test object would have own Thread so it will not interrupt other object properties nd behaviour
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread Safe
		
	@Override
	public void onTestStart(ITestResult result) {
		
		test = htmlReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id(ErrorVlidtionTest)->test
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	 extentTest.get().log(Status.PASS, "Test Passed Successfully");
	 
	 String filePath = null;
	 
	 try {
		driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").
				 		get(result.getInstance());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 
	 //Took Screen Shot
	 try {
		filePath = getScreenShot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 //Add Screen shot on HTML report on test success
	   extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		//Take Screen Shot - from defined method in Base Test class
		String filePath=null;
		
		//Get Driver From Respective executing test case
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").
					get(result.getInstance());
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Attach Take Screen Shot 
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	@Override
	public void onFinish(ITestContext context) {
		htmlReport.flush();
	  }
}
