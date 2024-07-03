package androidpageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameoption;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femalOption;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radiomale")
	private WebElement maleOption;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitForm;
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement dropDown;

	public void setName(String name) {
		nameoption.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.equals("Male")) {
			maleOption.click();
		} else {
			femalOption.click();
		}
	}

	public void selectCountry(String country) {
		dropDown.click();
		scrollToElement(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click();
	}

	public ProductCatalogPage submitForm() {
		// TODO Auto-generated method stub
		submitForm.click();
		return new ProductCatalogPage(driver);
	}
}
