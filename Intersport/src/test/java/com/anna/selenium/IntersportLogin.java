package com.anna.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntersportLogin {
	private WebDriver driver;
	WebDriverWait wait;

	@Before
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
		driver.manage().window().maximize();
		driver.get("https://www.intersport.de/");
	}

	
	@Test
	public void Login() throws InterruptedException {

		WebElement cookie = findCookie();
		cookie.click();
		
		findAndClick(".account--link");
		
		waitFor("#email");
		
		insertText("#email", "test.automation.account@yandex.com");
		
		insertText("#passwort", "Testing!234");
		
		findAndClick(".register--login-form button");
		
		WebElement welcomeMessage = driver.findElement(By.cssSelector(".account--welcome h1"));
	
		assertEquals("WILLKOMMEN, TEST TEST", welcomeMessage.getText());
	}
	
	 WebElement findCookie() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// Find the text input element by its Xpath selector
		WebElement c = driver.findElement(By.cssSelector(".js--cookie-banner-accept"));
		wait.until(ExpectedConditions.elementToBeClickable(c));
		return c;	
	}
	
	void waitFor(String cssS) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssS)));
	}
	
	void findAndClick(String cssS) {
		WebElement el = driver.findElement(By.cssSelector(cssS));
		el.click();
	}
	
	void insertText(String cssS, String text) {
		WebElement el = driver.findElement(By.cssSelector(cssS));
		el.sendKeys(text);
	}
	
	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}

}
