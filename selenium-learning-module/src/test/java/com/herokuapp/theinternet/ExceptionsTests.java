
package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {
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

	}

	@Test(priority = 1)

	public void notVisibleTest() {

		System.out.println("Dynamic-loading page starting in browser");
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";

		driver.get(url);
		sleep(3000);
		System.out.println("Dynamic-loading page Opened");

		//inspect start button and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// then get finish element text 
		WebElement finishElement = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOf(finishElement));
		
		
        
		
		String finishText=finishElement.getText();
        
        //compare actual finishelement text with expected "hello world!" using testng assert class
		Assert.assertTrue(finishText.contains("Hello World!"),"finishText:"+finishText);
		//startButton.click();
		
        
	}
	@Test
	public void timeOutTests() {

		System.out.println("Dynamic-loading page starting in browser");
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";

		driver.get(url);
		sleep(3000);
		System.out.println("Dynamic-loading page Opened");

		//inspect start button and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		// then get finish element text 
		WebElement finishElement = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOf(finishElement));
		try {
			
			wait.until(ExpectedConditions.visibilityOf(finishElement));
		} catch (TimeoutException exception) {
			// TODO Auto-generated catch block
			System.out.println("Exception catche:"+exception.getMessage());
		
		}
		
        
		
		String finishText=finishElement.getText();
        
        //compare actual finishelement text with expected "hello world!" using testng assert class
		Assert.assertTrue(finishText.contains("Hello World!"),"finishText:"+finishText);
		//startButton.click();
	}
	@Test(priority = 2)
	public void noSuchElementTest() {

		System.out.println("Dynamic-loading page starting in browser");
		String url = "http://the-internet.herokuapp.com/dynamic_loading/2";

		driver.get(url);
		sleep(3000);
		System.out.println("Dynamic-loading page Opened");

		//inspect start button and click on it
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();
        
		WebDriverWait wait = new WebDriverWait(driver,40);
		Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"),"Hello World!" )),"could not verify expected text 'hello world!'");
		/*
		 * WebElement
		 * finishElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "finish"))); // then get finish element text
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * String finishText=finishElement.getText();
		 * 
		 * //compare actual finishelement text with expected "hello world!" using testng
		 * assert class
		 * Assert.assertTrue(finishText.contains("Hello World!"),"finishText:"+
		 * finishText);
		 * 
		 */
	}
	@Test
	public void staleElementTest() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_controls");
	  WebElement checkBox=driver.findElement(By.id("checkbox"));
	
	  WebElement removeButton=driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
     WebDriverWait wait=new WebDriverWait(driver,10);
     removeButton.click();
     //wait.until(ExpectedConditions.invisibilityOf(checkBox));
	  
	  //Assert.assertFalse(checkBox.isDisplayed());
     
		/*
		 * Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkBox))
		 * ,"checkbox is still visible but it shouldn't be");
		 */
    
     Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkBox)),"checkbox is still visible but it shouldn't be");
	
	WebElement addButton=driver.findElement(By.xpath("//button[contains(text(),''Add]"));
	addButton.click();
	WebElement checkBox2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
	Assert.assertTrue(checkBox2.isDisplayed(), "checkbox is not visible but i should be ");
	}
	
	
	@Test
	public void disabledElementTest() {

		System.out.println("Dynamic-controls page starting in browser");
		//navigate to the page
		String url = "http://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);
		sleep(3000);
		System.out.println("Dynamic-controls page Opened");

		//create the webElement for enable button  and click on it
		
		WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
		WebElement textFeild=driver.findElement(By.xpath("//input[@type='text']"));
		enableButton.click();
		
        
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(textFeild));
		
		//Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"),"It's enabled!" )),"could not verify expected text 'It's enabled!' ");
		
		
		
	

		textFeild.sendKeys("dummyText");
		sleep(3000);
		
		Assert.assertEquals(textFeild.getAttribute("value"), "dummyText","expected text is not the same as entered text" );
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
