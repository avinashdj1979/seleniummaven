package com.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class DatePicker2 {
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

		WebElement datePicker = driver.findElement(By.id("datepicker1"));

		datePicker.click();

		WebElement monthPicker = driver.findElement(By.className("ui-datepicker-month"));
		WebElement yearPicker = driver.findElement(By.className("ui-datepicker-year"));
		
		Month month = Month.valueOf(monthPicker.getText().toUpperCase());
		int year = Integer.parseInt(yearPicker.getText());
		
		LocalDate currentMonthAndYear = getDate(year, month);
		
		LocalDate monthAndYearToBeSelected = getDate(2021, Month.APRIL);
		
		chooseMonth(currentMonthAndYear, monthAndYearToBeSelected);
		
		List<WebElement> dayList = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td//a"));

		System.out.println(dayList.size());

		boolean dateSelected = false;

		for (WebElement day : dayList) {
			//System.out.println(day.getText());
			if (day.getText().equals("16")) {
				day.click();
				dateSelected = true;
				break;
			}
		}

		driver.findElement(By.tagName("h8")).click();
		Assert.assertTrue(dateSelected, "Invalid date");
		
		Thread.sleep(5000);
	}
	
	public LocalDate getDate(final int year, final Month month) {
		return LocalDate.of(year, month, 1);
	}
	
	public void chooseMonth(LocalDate currentMonth, LocalDate monthToBeSelected) {
		By prev = By.xpath("//a[contains(@class,'ui-datepicker-prev')]");
		By next = By.xpath("//a[contains(@class,'ui-datepicker-next')]");
		
		long monthsAway = ChronoUnit.MONTHS.between(currentMonth, monthToBeSelected);
		
		By arrow = monthsAway > 0 ? next : prev;
		
		for(int i = 0; i < Math.abs(monthsAway) ; i++) {
			driver.findElement(arrow).click();
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
