package Framework.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Utils.AppiumUtils;
import androidpageclasses.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Utility extends AppiumUtils {
	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);

		String ipAddress = prop.getProperty("");
		String portNumber = prop.getProperty("");
	service=startAppiumServer(ipAddress, Integer.parseInt(portNumber));
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("SaiEmulator");
//		options.setChromedriverExecutable("C:\\Users\\Admin\\Downloads\\chromedriver-win64");
//		options.setApp(
//				"\\Users\\Admin\\eclipse-workspace\\RahulShettyAcademy\\src\\test\\java\\Resources\\ApiDemos-debug.apk");
		options.setApp(
				"\\Users\\Admin\\eclipse-workspace\\RahulShettyAcademy\\src\\test\\java\\Resources\\General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	public static void longClickGestures(WebElement elementValue) {
		((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) elementValue).getId()), "duration", 2000);
	}

	public static void swipeGesture(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile:swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.25));

	}

	public static void scrollToEnd() {
		boolean canScroll = false;
		do {
			canScroll = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile:scrollGesture", ImmutableMap
					.of("top", 100, "left", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScroll);
	}

	{

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		// service.stop();
	}
}
