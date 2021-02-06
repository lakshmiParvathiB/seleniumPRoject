package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTest {
	@Parameters({ "username", "password", "expectedmessage" })

	@Test(priority = 1, groups = { "nagativeTests", "smokeTests" })
	public void incorrectLoginTests(String username, String password, String expectedmessage) {
		System.out.println("incorrectLoginTests with "+username +" and " + password);
		System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		sleep(3000);

		// get page url ws
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(3000);
		System.out.println("testpage is opened");

		// enter incorrect username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// enter correct password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);

		// click login button
		WebElement loginbutton = driver.findElement(By.tagName("button"));
		loginbutton.click();
		sleep(2000);

		// check the error login msg

		WebElement errormessage = driver.findElement(By.id("flash"));
		String actualErrormessage = errormessage.getText();
		// String expectedErrormessage = "Your username is invalid!";
		Assert.assertTrue(actualErrormessage.contains(expectedmessage),
				"actualErrormessage is not the same as expectedErrormessage \n actualErrormessage:" + actualErrormessage
						+ "\nexpectedErrormessage:" + expectedmessage);
		sleep(3000);

		driver.quit();
	}

	/*
	 * @Test(priority = 2, groups = "nagativeTests") public void
	 * incorrectPasswordTest() {
	 * System.out.println("strting negative test page in browser");
	 * System.setProperty("webdriver.gecko.driver",
	 * "src//main//resources//geckodriver.exe"); WebDriver driver = new
	 * FirefoxDriver();
	 * 
	 * driver.manage().window().maximize(); sleep(3000);
	 * 
	 * // get page url and open it String url =
	 * "http://the-internet.herokuapp.com/login"; driver.get(url); sleep(3000);
	 * System.out.println("testpage is opened");
	 * 
	 * // enter incorrect username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith");
	 * 
	 * // enter correct password WebElement password =
	 * driver.findElement(By.id("password"));
	 * password.sendKeys("incorrectpassword!");
	 * 
	 * // click login button WebElement loginbutton =
	 * driver.findElement(By.tagName("button")); loginbutton.click(); sleep(2000);
	 * 
	 * // check the error login msg
	 * 
	 * WebElement errormessage = driver.findElement(By.id("flash")); String
	 * actualErrormessage = errormessage.getText(); String expectedErrormessage =
	 * "Your password is invalid!";
	 * Assert.assertTrue(actualErrormessage.contains(expectedErrormessage),
	 * "actualErrormessage is not the same as expectedErrormessage \n actualErrormessage:"
	 * + actualErrormessage + "\nexpectedErrormessage:" + expectedErrormessage);
	 * sleep(3000);
	 * 
	 * driver.quit(); }
	 */
	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
