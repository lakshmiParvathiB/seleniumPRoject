package com.herokuapp.theinternet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {
		// Create driver
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src//main//resources//geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		default :
			
			System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
			
		}
		

		sleep(3000);
		// maximize window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })

	public void PositiveLoginTest() {

		System.out.println("browser starting");
		/*
		 * // Create driver System.setProperty("webdriver.chrome.driver",
		 * "src//main//resources//chromedriver.exe"); WebDriver driver = new
		 * ChromeDriver();
		 * 
		 * sleep(3000); //maximize window driver.manage().window().maximize();
		 */

		// WebDriver driver=new ChromeDriver()
		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(3000);
		System.out.println("Test Page1 Opened");

		// enter user name
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(2000);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(2000);
		// click login button
		WebElement loginbutton = driver.findElement(By.tagName("button"));
		loginbutton.click();
		sleep(2000);

		// verifications
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "logout button is not visisble");

		// succesful login message
		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String actualMessage = successMessage.getText();
		String expectedMessage = "You logged into a secure area!";
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual Message does not contain expected message .\nActualMessage:" + actualMessage
						+ "\nExpectedMessage:" + expectedMessage);

	

	}

	@Parameters({ "username", "password", "expectedmessage" })

	@Test(priority = 2, groups = { "nagativeTests", "smokeTests" })
	public void incorrectLoginTests(String username, String password, String expectedmessage) {
		System.out.println("incorrectLoginTests with " + username + " and " + password);
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "src//main//resources//chromedriver.exe"); WebDriver driver = new
		 * ChromeDriver();
		 * 
		 * driver.manage().window().maximize(); sleep(3000);
		 */

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

		
	}
    @AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
