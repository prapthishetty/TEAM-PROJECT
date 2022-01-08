package com.POM;

import com.Base.Test_Base;
import org.openqa.selenium.JavascriptExecutor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.AssertJUnit;import org.testng.annotations.Test;

public class Search_Flights extends Test_Base
{
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 public static WebElement onewayTrip;
	 public static WebElement roundTrip;
	 public static WebElement multiTrip;
	
	public DashboardPage validateLogin(String username, String password)
	{
		driver.findElement(By.xpath("//p[@class=\"font14 latoBold blackText appendBottom5 cursorPointer makeRelative\"]")).click();
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@data-cy='continueBtn']")).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@data-cy='login']")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		return new DashboardPage();
	}
	
	public DashboardPage validate_phlogin(String phonenumber, String otp)
	{
		driver.findElement(By.xpath("//p[@class=\"font14 latoBold blackText appendBottom5 cursorPointer makeRelative\"]")).click();
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(phonenumber);
		driver.findElement(By.xpath("//button[@data-cy='continueBtn']")).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.id("otp")).sendKeys(otp);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@data-cy='login']")).click();
		String msg= driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[2]/div[2]/section[1]/form[1]/div[1]/p[2]")).getText();
		String expmsg="Incorrect OTP! Please enter the OTP delivered to you.";
		Assert.assertEquals(msg,expmsg);
		return new DashboardPage();
		}
	
	public DashboardPage validate_invalidpassword(String username, String pwd){
		driver.findElement(By.xpath("//p[@class=\"font14 latoBold blackText appendBottom5 cursorPointer makeRelative\"]")).click();
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//button[@data-cy='continueBtn']")).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@data-cy='login']")).click();
		String msg= driver.findElement(By.xpath("//span[@data-cy='serverError']")).getText();
		String expmsg="Either Username or Password is incorrect.";
		assertEquals(msg,expmsg);
		driver.quit();
		return new DashboardPage();
	}
	
	
	 public String flights()
	{
		 String actualurl = "https://www.makemytrip.com/flights/";
		 driver.get(actualurl);	
		 return actualurl;
	}
	 
	 
	 public DashboardPage search_flights(String fromLoc, String toLoc)
	 {
		 driver.get("https://www.makemytrip.com/flights/");
		 driver.manage().window().maximize();
		 onewayTrip= driver.findElement(By.xpath("//li[@data-cy='oneWayTrip']/span"));
		 roundTrip= driver.findElement(By.xpath("//li[@data-cy='roundTrip']/span"));
		 multiTrip= driver.findElement(By.xpath("//li[@data-cy='mulitiCityTrip']/span"));
		 if(!roundTrip.isSelected() && !multiTrip.isSelected())
		 	onewayTrip.click();
		 //if(!onewayTrip.isSelected() && !multiTrip.isSelected())
			 //roundTrip.click();
		//if(!onewayTrip.isSelected() && !roundTrip.isSelected())
			//multiTrip.click();	
		 driver.findElement(By.xpath("//span[contains(text(),'From')]")).click();
		 driver.findElement(By.xpath("//input[@data-cy='fromCity']")).sendKeys(fromLoc);
		 driver.findElement(By.xpath("//input[@data-cy='fromCity']")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		 driver.findElement(By.xpath("//span[contains(text(),'To')]")).click();
		 driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(toLoc);
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']"))).sendKeys(Keys.ARROW_DOWN);
		 driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(Keys.ENTER);
		 driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Tue Feb 01 2022']"))).click();
		 driver.findElement(By.xpath("//span[text()='Travellers & CLASS']")).click();
		 driver.findElement(By.xpath("//li[@data-cy='adults-1']")).click();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']")).click();
		 driver.findElement(By.xpath("//a[text()=\"Search\"]")).click();
		return new DashboardPage();
	 }
	 
//	 public boolean validtravellers() {
//		 driver.get("https://www.makemytrip.com/flights/");
//		 driver.findElement(By.xpath("//span[text()='Travellers & CLASS']")).click();
//		 driver.findElement(By.xpath("//li[@data-cy='adults-1']")).click();
//		 driver.findElement(By.xpath("//li[@data-cy='infants-3']")).click();
//		 driver.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']")).click();
//		 boolean msg=driver.findElement(By.xpath("//p[@data-cy='infantWarning']")).isDisplayed();
//		return msg;
//	 }
	 
	 	
	 public DashboardPage bookingTicket(String firstName,String phno,String email){ 
		 driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/span")).click();
		 driver.get("https://bit.ly/3HJa0H6");
		 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Flights Summary')]"))).click();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"INSURANCE\"]/div/div[4]/div[1]/label/span[1]/span"))).click();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Flights Summary')]"))).click();
		 driver.findElement(By.xpath("//button[contains(text(),'+ ADD NEW ADULT')]")).click();
		 driver.findElement(By.xpath("//input[@placeholder='First & Middle Name']")).sendKeys(firstName);
		 driver.findElement(By.xpath("//label[@tabindex='0']")).click();
		 driver.findElement(By.xpath("//input[@placeholder='Mobile No']")).sendKeys(phno);
		 driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
		 Actions actions = new Actions(driver);
		 WebElement subMenu = driver.findElement(By.xpath("//div[@class='emailId']"));
		 actions.moveToElement(subMenu);
		 actions.click().build().perform();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"lato-black button buttonPrimary extraPadBtn fontSize16 \"]"))).click();
		 driver.findElement(By.xpath("//button[text()='Got it']")).click();
		 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		 driver.findElement(By.xpath("//button[text()='Continue']")).click();
		 driver.findElement(By.xpath("//button[text()='CONFIRM']")).click();
		 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainSection_1\"]/div[2]/span"))).click();
		 driver.findElement(By.xpath("//button[text()='Proceed to pay']")).click();
		 return new DashboardPage();
	 }
	 
	 
	 public boolean booking_credentials(){ 
		 driver.get("https://bit.ly/3HJa0H6");
		 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Flights Summary')]"))).click();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"INSURANCE\"]/div/div[4]/div[1]/label/span[1]/span"))).click();
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Flights Summary')]"))).click();
		 //driver.findElement(By.xpath("//button[contains(text(),'+ ADD NEW ADULT')]")).click();
		 //driver.findElement(By.xpath("//input[@placeholder='First & Middle Name']"));
		 //driver.findElement(By.xpath("//label[@tabindex='0']")).click();
		 //driver.findElement(By.xpath("//input[@placeholder='Mobile No']"));
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"lato-black button buttonPrimary extraPadBtn fontSize16 \"]"))).click();
		boolean numerror=driver.findElement(By.xpath("//p[contains(text(),'Mobile No is required.')]")).isDisplayed();
		return numerror;
	 }
	 

	 
 
	 
}