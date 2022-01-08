package com.Base;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.asserts.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Base {
	
	public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports extent;
    public static ExtentSparkReporter reporter;
    
    
   
    public Test_Base() {
        prop = new Properties();
        try {
            FileInputStream fs = new FileInputStream("./src/main/java/com/config/config.properties");
            prop.load(fs);
            
      } catch (FileNotFoundException e) {
            System.out.println("No property file found");
        } catch (IOException e) {
            System.out.println("Unable to load the property file");
        }
    }
 
    public static void initialization() {
        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } 
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.get(prop.getProperty("url"));
    }
    
    public static void tearDown() {
        driver.quit();
       
    }
    
    public static void reportSetUp()
    {
    	String reportPath=System.getProperty("user.dir")+"/ExtentReport/report.html";
        reporter=new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        reporter.config().setDocumentTitle("Make My Trip");
        reporter.config().setReportName("Web Automation Report");
        extent.attachReporter(reporter);
        extent.setSystemInfo("Team Lead", "Shalini");
        extent.setSystemInfo("Member", "Lavanya");
        extent.setSystemInfo("Member", "prapthi");
        extent.setSystemInfo("Member", "pooja");
        extent.setSystemInfo("Member", "nandini");
    }
 
    
}
