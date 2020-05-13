package com.anna.selenium;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntersportRegistration {
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

	private WebElement findCookie() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement c = driver.findElement(By.cssSelector(".js--cookie-banner-accept"));
		wait.until(ExpectedConditions.elementToBeClickable(c));
		return c;
	}

	@Test
	public void Registration() throws InterruptedException {

		WebElement cookie = findCookie();
		cookie.click();

		findAndClick(".account--link");

		findAndClick("#registration button");

		findAndClick("#personal_salutation_ms");

		insertText("#firstname", "TestName");
		insertText("#lastname", "TestLastName");

		waitFor("#register_personal_email");

		findAndClick("#register_personal_email");

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		insertText("#register_personal_email", "test" + randomInt + "@gmail.com");

		insertText("#register_personal_password", "Testing!234");

		waitFor("#street");

		insertText("#street", "TestStreet");
		insertText("#housenumber", "1");
		insertText("#zipcode", "12345");
		insertText("#city", "Testville");

		WebElement regCountry = driver.findElement(By.cssSelector("#country"));
		Select select = new Select(regCountry);


		select.selectByIndex(1);
		
		WebElement btn = driver.findElement(By.cssSelector(".register--content .js--step-submit.register--btn"));
		assertEquals(false, btn.isEnabled());
		
		findAndClick("#dpacheckbox");
		Thread.sleep(1000L);

		assertEquals(true, btn.isEnabled());


	}

	void waitFor(String cssS) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssS)));
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
