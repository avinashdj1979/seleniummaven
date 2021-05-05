package com.factory;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.SWITCH;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.constants.UIConstants;
import com.utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static final String USERNAME = "avinash139";
	public static final String AUTOMATE_KEY = "Ehdxz9iyqbZUyFmqvnT2";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	private static WebDriver driver;

	public static WebDriver getDriver() {
		String browser = new PropertyReader().getProperty(UIConstants.BROWSER);
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			chromeOptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeOptions);
			break;
		case "chromedebug":
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9014");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "chromeheadless":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("window-size=1920,1080");
			options.addArguments("start-maximized");
			//options.addArguments("--headless");
			options.setHeadless(true);
			driver = new ChromeDriver(options);
			break;
		case "browserstack":
			ChromeOptions caps = new ChromeOptions();
			caps.addArguments("start-maximized");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "80");
			caps.setCapability("name", "avinash139's Test");
			try {
				driver = new RemoteWebDriver(new URL(URL), caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		case "grid":     
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			ChromeOptions opt = new ChromeOptions();
			opt.setHeadless(true);
			opt.addArguments("window-size=1920,1080");
			opt.merge(cap);
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), opt);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		case "gridwithoptions":     
			//ChromeOptions chromeopt   = new ChromeOptions();
			DesiredCapabilities chromecap = DesiredCapabilities.chrome();
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromecap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
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
		int waitTime = Integer.parseInt(new PropertyReader().getProperty(UIConstants.IMPLICITLY_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		
		return driver;
	}

}
