package resources;

import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.tests.*;

public class Base {

	public static Properties propfile;
	public final static Logger logger = Logger.getLogger(Base.class);
	private static String browser;
	private static String webDriverLocation = System.getProperty("user.dir") + "\\chromedriver.exe";

	public static void beforeClass() {

		PropertyConfigurator.configure("Log4j.properties");
		logger.info("Im inside here");
		Excel.buildExcel();
		Excel.readExcel();
		propfile = DataFile.propretiesfile();

	}

	public static WebDriver getBrowser(WebDriver driver) {

		browser = propfile.getProperty("BROWSER");
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", webDriverLocation);
			driver = new ChromeDriver();
		} else if (browser.equals("IE")) {
			driver = new InternetExplorerDriver();
		}
		return driver;
	}

	public static WebDriver getDriver(String className) {

		WebDriver temp = null;
		if (className.equals("CartInfoTest")) {
			temp = CartInfoTest.getDriver();
		} else if (className.equals("CheckNavberTest")) {
			temp = CheckNavberTest.getDriver();
		} else if (className.equals("ContactServiceTest")) {
			temp = ContactServiceTest.getDriver();
		} else if (className.equals("LoginAndLogOutTest")) {
			temp = LoginAndLogOutTest.getDriver();
		} else if (className.equals("ShoopingTest")) {
			temp = ShoopingTest.getDriver();
		}
		return temp;
	}

	public static void setDriver(String className, WebDriver tDriver) {

		if (className.equals("CartInfoTest")) {
			CartInfoTest.setDriver(tDriver);
		} else if (className.equals("CheckNavberTest")) {
			CheckNavberTest.setDriver(tDriver);
		} else if (className.equals("ContactServiceTest")) {
			ContactServiceTest.setDriver(tDriver);
		} else if (className.equals("LoginAndLogOutTest")) {
			LoginAndLogOutTest.setDriver(tDriver);
		} else if (className.equals("ShoopingTest")) {
			ShoopingTest.setDriver(tDriver);
		}
	}
}
