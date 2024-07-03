package Framework.AppiumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class EcommerceTest2 extends Utility {
	
	@BeforeMethod
	public void preSetup()
	{
		Activity act = new Activity("com.androidsample.generalStore",
				"com.androidsample.generalstore.MainActivity}");
		((JavascriptExecutor) driver).executeScript("mobile:startActivity", ImmutableMap.of("intent",
				" com.androidsample.generalStore/com.androidsample.generalstore.MainActivity"));
	}
	@Test
	public void Sample()
	{
		System.out.println("Sai Praneeth Chowdary Davuluri");
	}
	@Test
	public void Sample1()
	{
		System.out.println("Sai Praneeth Chowdary Davuluri");
	}
	@Test
	public void Sample2()
	{
		System.out.println("Sai Praneeth Chowdary Davuluri");
	}
	@Test
	public void Sample3()
	{
		System.out.println("Sai Praneeth Chowdary Davuluri");
	}
	@Test
	public void CompleteFlow()
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
				.sendKeys("Sai Praneeth Chowdary Davuluri");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getText();
		Assert.assertEquals(toastMessage, "Please enter your name");
	}
	
	@Test
	public void errorValidation()
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Australia']")).click();
//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))
//				.sendKeys("Sai Praneeth Chowdary Davuluri");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getText();
		Assert.assertEquals(toastMessage, "Please enter your name");
	}
	

}
