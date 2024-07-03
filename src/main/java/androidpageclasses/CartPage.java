package androidpageclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/totalAmountLbl']")
	private WebElement totalAmountElement;

	public void comapreListOfPricesWithTotalAmount() {
		// TODO Auto-generated method stub
		Double sum = 0.0;
		for (int k = 0; k < productPrices.size(); k++) {
			Double amount = Double.parseDouble(productPrices.get(k).getText().substring(1));
			System.out.println(amount);
			sum = sum + amount;
		}
		System.out.println("Sum isssssss  " + sum);
		
		String totalAmount = totalAmountElement.getText();
		Double total = Double.parseDouble(totalAmount.substring(1));
		Assert.assertEquals(sum, total);
	}
}
