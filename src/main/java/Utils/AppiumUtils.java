package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

//	AppiumDriver driver;
//	public AppiumUtils(AppiumDriver driver)
//	{
//		this.driver=driver;
//	}

	// write the functions common to both ios and android
	public AppiumDriverLocalService service;

	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int portNumber) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(portNumber).build();
		service.start();
		return service;
	}

	public ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\Reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("MobileAutomationResults");
		reporter.config().setReportName("test Result");

		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);

		return report;

	}

	public String getTheScreenshot(AppiumDriver driver, String fileName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\src\\test\\java\\Reports" + fileName + ".png";
		FileUtils.copyFile(source,new File( dest));
		return dest;

	}
}
