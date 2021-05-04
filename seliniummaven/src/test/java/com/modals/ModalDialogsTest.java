package com.modals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class ModalDialogsTest {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}

	@Test
	public void ModalDialogTest() throws InterruptedException {
		driver.get("https://demoqa.com/modal-dialogs");

		WebDriverWait wait = new WebDriverWait(driver, 15);

		WebElement openSmallModalBtn = driver.findElement(By.id("showSmallModal"));
		WebElement openLargeModalBtn = driver.findElement(By.id("showLargeModal"));
		
		openSmallModalBtn.click();

		WebElement closeSmallModalBtn = driver.findElement(By.id("closeSmallModal"));
		wait.until(ExpectedConditions.visibilityOf(closeSmallModalBtn));

		// SmallModalElements
		WebElement modalContent = driver.findElement(By.xpath("//div[@class='modal-content']"));
		WebElement modalHeader = driver.findElement(By.xpath("//div[@class='modal-header']"));
		WebElement modalBody = driver.findElement(By.xpath("//div[@class='modal-body']"));

		Assert.assertTrue(modalHeader.getText().contains("Small Modal"), "Modal header Mismatch");
		
		Assert.assertTrue(modalBody.getText().contains("This is a small modal. It has very less content"), "Modal content Mismatch");

		closeSmallModalBtn.click(); 
		
		wait.until(ExpectedConditions.invisibilityOf(closeSmallModalBtn));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
