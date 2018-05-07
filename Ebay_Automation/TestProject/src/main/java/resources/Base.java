package resources;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

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

}
