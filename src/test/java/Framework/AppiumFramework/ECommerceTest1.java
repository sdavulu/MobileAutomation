package Framework.AppiumFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Utils.AndroidActions;
import Utils.AppiumUtils;
import androidpageclasses.CartPage;
import androidpageclasses.FormPage;
import androidpageclasses.ProductCatalogPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ECommerceTest1 extends Utility {

	@Test(dataProvider = "getTheData",groups="Smoke")
	public void ecommerceTest(HashMap<String, String> data ) throws InterruptedException {

		formPage.setName(data.get("name"));
		formPage.setGender(data.get("gender"));
		formPage.selectCountry(data.get("country"));
		ProductCatalogPage catalogPage = formPage.submitForm();
		catalogPage.addProductsToCart(0);
		catalogPage.addProductsToCart(0);
		CartPage cartPage = catalogPage.cartButton();

		cartPage.comapreListOfPricesWithTotalAmount();

	}

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		Activity act = new Activity("com.androidsample.generalStore", "com.androidsample.generalstore.MainActivity}");
		((JavascriptExecutor) driver).executeScript("mobile:startActivity", ImmutableMap.of("intent",
				" com.androidsample.generalStore/com.androidsample.generalstore.MainActivity"));
	}

	@DataProvider
	public Object[][] getTheData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\Ecommerce.json");
		System.out.println(data);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
