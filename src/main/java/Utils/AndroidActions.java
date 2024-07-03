package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	static AndroidDriver driver;
	public AndroidActions(AndroidDriver driver)
	{
//		super(driver);
	this.driver=driver;	
	}
	public static void longClickGestures(WebElement elementValue) {
		((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) elementValue).getId()), "duration", 2000);
	}

	public static void swipeGesture(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "direction", direction, "percent",
				0.25));

	}

	public static void scrollToEnd() {
		boolean canScroll = false;
		do {
			canScroll = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile:scrollGesture", ImmutableMap
					.of("top", 100, "left", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScroll);
	}
	
	public void scrollToElement(String elementText)
	{
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"));"));
	}

}
