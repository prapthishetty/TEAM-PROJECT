package com.Test_Cases;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.io.Closeable;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Base.Test_Base;
import com.POM.Search_Flights;
import com.POM.DashboardPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Test_Search_Flights extends Test_Base{
	
	com.POM.DashboardPage dp;
	boolean dp1;
	Search_Flights l1;
	
	
	@BeforeTest
	public void extentReportSetUp()
	{
		reportSetUp();
	}

	
	@BeforeMethod
	public void browserSetUp()
	{
	    initialization();
	    l1 = new Search_Flights();
	}
	
	
	@Test
	public void LoginTest()
	{
	    dp = l1.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
	    System.out.println("validating login with valid Credentials passed");
	}
	
	@Test(enabled=false)
	public void invalid_Pwd()
	{
	    dp = l1.validate_invalidpassword(prop.getProperty("username"),prop.getProperty("pwd"));
	    System.out.println("validating login with invalid password passed");
	}
	
	@Test
	public void phLogin()
	{
	    dp = l1.validate_phlogin(prop.getProperty("phonenumber"),prop.getProperty("otp"));
	    System.out.println("validating login with number passed");
	}
	
	@Test
	public void m_flights_page() {
		String expectedUrl = "https://www.makemytrip.com/flights/";
		String actualurl = l1.flights();
		if (actualurl.equals(expectedUrl))  {  
			System.out.println("Verification Successful - User is in Flights page.");  
		}  
		else  {  
			System.out.println("Verification Failed - User is Not in Flights page.");  
		}  
	}
	
	@Test(enabled=false)
	public void travellerserror() {
		dp = l1.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		//boolean msg=l1.validtravellers();
		//assertTrue(msg);
	}
	
	@Test
	public void v_booking_cred() {
		dp = l1.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		dp = l1.search_flights(prop.getProperty("fromLoc"),prop.getProperty("toLoc"));
		dp1=l1.booking_credentials();
		boolean numerror=l1.booking_credentials();
		assertTrue(numerror);
	}

	@Test
	public void searchFlights() {
		dp = l1.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		dp = l1.search_flights(prop.getProperty("fromLoc"),prop.getProperty("toLoc"));
		dp=l1.bookingTicket(prop.getProperty("firstName"),prop.getProperty("phno"),prop.getProperty("email"));
	}
	
	@AfterMethod
	public void close_window() {
		tearDown();
	}
	

	@AfterSuite
	public void afterMethod() {
		extent.flush();
	}
	
	

}

