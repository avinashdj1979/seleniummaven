package com.calendar;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class CalendarTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}

	@Test
	public void testCalendar() throws InterruptedException {

		driver.get("http://djangovinoth.pythonanywhere.com/login/");

		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		Thread.sleep(5000);

		driver.get("http://djangovinoth.pythonanywhere.com/datepicker/");

		Thread.sleep(2000);

		WebElement datePicker = driver.findElement(By.id("datepicker"));

		datePicker.click();

		WebElement monthPicker = driver.findElement(By.className("ui-datepicker-month"));
		Select month = new Select(monthPicker);
		month.selectByVisibleText("Jun");

		WebElement yearPicker = driver.findElement(By.className("ui-datepicker-year"));
		Select year = new Select(yearPicker);
		year.selectByVisibleText("2022");

		List<WebElement> dateList = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td//a"));

		System.out.println(dateList.size());

		boolean dateSelected = false;

		for (WebElement date : dateList) {
			if (date.getText().equals("13")) {
				date.click();
				dateSelected = true;
				break;
			}
		}
		// System.out.println(date.getText());
		driver.findElement(By.tagName("h8")).click();
		Assert.assertTrue(dateSelected, "Invalid date");
		
		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
