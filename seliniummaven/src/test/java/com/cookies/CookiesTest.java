package com.cookies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class CookiesTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}

	@Test
	public void testTable() throws InterruptedException {
		driver.get("http://djangovinoth.pythonanywhere.com/login/");

		HashMap<String, String> cookieMap = new HashMap<String, String>();

		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		Thread.sleep(3000);

		driver.get("http://djangovinoth.pythonanywhere.com/FileUploadDownload/");

		Thread.sleep(3000);
		
		Date tomorrow = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));

		Set<Cookie> cookieSet = driver.manage().getCookies();

		for (Cookie c : cookieSet) {
			System.out.printf("%s-%s-%s-%s\n", c.getName(), c.getValue(), c.getDomain(), c.getExpiry());
			cookieMap.put(c.getName(), c.getValue());
		}

		driver.manage().deleteAllCookies();

		driver.findElement(By.linkText("Home")).click();

		Thread.sleep(5000);

		for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
			Cookie newCookie = new Cookie.Builder(entry.getKey(), entry.getValue()) // Name & value pair of the cookie
					// (mandatory)
					.domain("djangovinoth.pythonanywhere.com") // Domain of the cookie
					.path("/") // Path of the cookie
					.expiresOn(tomorrow) // Expiration date
					//.isSecure(true) // Is it secure or not?
					.build(); // Finally build it with .build() call
			driver.manage().addCookie(newCookie);
		}

		Set<Cookie> newCookieSet = driver.manage().getCookies();
		System.out.println(newCookieSet.size());
		
		driver.findElement(By.linkText("Home")).click();

		for (Cookie c : newCookieSet) {
			System.out.printf("%s-%s-%s-%s\n", c.getName(), c.getValue(), c.getDomain(), c.getExpiry());
		}

		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
