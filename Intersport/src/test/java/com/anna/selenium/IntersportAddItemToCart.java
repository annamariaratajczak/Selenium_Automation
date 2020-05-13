package com.anna.selenium;

import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IntersportAddItemToCart {

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
	public void addToCart() throws InterruptedException {
		
		

		WebElement cookie = findCookie();
		cookie.click();
		
		Actions action = new Actions(driver);
        WebElement category = driver.findElement(By.xpath("/html/body/div[1]/nav/ul/li[2]/a/span"));
        action.moveToElement(category).moveToElement(driver.findElement(By.xpath("/html/body/div[1]/nav/ul/li[2]/div/div/div/div/div[1]/ul/li[1]/form/input[2]"))).click().build().perform();
		
        WebElement article1 = driver.findElements(By.cssSelector(".listing--container a.box-link")).get(0);
		article1.click();
		
		waitFor(".buybox--button-text");
		
		findAndClick(".buybox--button-text");
		

		waitFor(".modal--close-icon");
				
		findAndClick(".modal--close-icon");
		
		
		WebElement cartQuantity = driver.findElement(By.cssSelector(".cart--quantity"));
		assertEquals("1", cartQuantity.getText());
		
		findAndClick(".cart--link");
		
		
		waitFor(".item--price");
		
		WebElement price1 = driver.findElement(By.cssSelector(".item--price"));
		
		WebElement price2 = driver.findElement(By.cssSelector(".prices--articles-amount"));
		
		assertEquals(price1.getText().replace("*", ""), price2.getText());
		
		findAndClick(".action--remove");
		
		waitFor(".cart--empty-text");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart--empty-text")));

		WebElement cartIsEmpty = driver.findElement(By.cssSelector(".cart--empty-text"));
		assertEquals("Ihr Warenkorb ist leer", cartIsEmpty.getText());

	}
	
	void waitFor(String cssS) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssS)));
	}
	
	void findAndClick(String cssS) {
		WebElement el = driver.findElement(By.cssSelector(cssS));
		el.click();
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
