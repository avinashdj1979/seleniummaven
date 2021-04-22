package edu.framework.factory;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.SWITCH;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import com.utils.PropertyReader;

import edu.framework.constants.UIConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		PropertyReader props = new PropertyReader();
		String browser = props.getProperty(UIConstants.BROWSER);
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(ffOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			driver = null;
			break;
		}
		
		driver.manage().window().maximize();
		Integer waitTime = Integer.parseInt(props.getProperty(UIConstants.IMPLICITLY_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		driver.get(props.getProperty(UIConstants.BASE_URL));
		
		return driver;
	}

}