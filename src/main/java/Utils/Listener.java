package Utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listener  implements ITestListener{
	AppiumUtils util=new AppiumUtils();
	   ExtentReports report= util.getReportObject();
	   ExtentTest test;
	   AppiumDriver driver;
	@Override  
    public void onTestStart(ITestResult result) {  
        // TODO Auto-generated method stub  
      
       test=  report.createTest(result.getMethod().getMethodName());
    }  
  
    @Override  
    public void onTestSuccess(ITestResult result) {  
        // TODO Auto-generated method stub  
        System.out.println("Success of test cases and its details are : "+result.getName());  
        test.log(Status.PASS, "test is passed");
    }  
  
    @Override  
    public void onTestFailure(ITestResult result) {  
        // TODO Auto-generated method stub  
        System.out.println("Failure of test cases and its details are : "+result.getName()); 
        try {
			driver=(AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        test.fail(result.getThrowable());
        try {
			test.addScreenCaptureFromPath(util.getTheScreenshot(driver, result.getMethod().getMethodName()), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
  
    @Override  
    public void onTestSkipped(ITestResult result) {  
        // TODO Auto-generated method stub  
        System.out.println("Skip of test cases and its details are : "+result.getName());  
    }  
  
    @Override  
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
        // TODO Auto-generated method stub  
        System.out.println("Failure of test cases and its details are : "+result.getName());  
    }  
  
    @Override  
    public void onStart(ITestContext context) {  
        // TODO Auto-generated method stub  
          
    }  
  
    @Override  
    public void onFinish(ITestContext context) {  
        // TODO Auto-generated method stub  
         report.flush(); 
    }  
}
