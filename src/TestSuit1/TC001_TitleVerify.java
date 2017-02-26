package TestSuit1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC001_TitleVerify 
{
	ExtentReports Report;
	ExtentTest Logger;
	WebDriver driver;
	String BaseURL="http://www.demoaut.com";
	String ReportPath="D:\\MySeleniumWP\\DemoautPR1\\src\\Reports1\\TitleRep2.html";
	String SSPath="D:\\MySeleniumWP\\DemoautPR1\\src\\screenShots";
//	public static ITestResult result=null;
	
	@Test
	public void verifyTitle()
	{
		
		Report=new ExtentReports(ReportPath);
		Logger=Report.startTest("TitleVerification");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Logger.log(LogStatus.INFO, "Browser launched");
		
		driver.get(BaseURL);
		Logger.log(LogStatus.INFO, "Page up and running");
//		Sleeper.sleepTightInSeconds(2);
		String PageTitle=driver.getTitle();
		
		try
		{
			Assert.assertTrue(PageTitle.contains("Welcome: Mercury Toursx"));
			System.out.println("Title verified - Pass");
			Logger.log(LogStatus.PASS, "TitleVerified");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
/*			File sFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String dPath="D:\\MySeleniumWP\\DemoautPR1\\src\\screenShots\\"+result.getName()+result.getEndMillis()+".png";
			File dFile=new File(dPath);
			FileUtils.copyFile(sFile, dFile);*/
			
			String sPath=utility.SreenCaptureTest.captureScreenShot(driver,SSPath,result.getName()+result.getEndMillis());
			String image=Logger.addScreenCapture(sPath);
			Logger.log(LogStatus.FAIL,"TitleVerified",image);	
		}
		Report.endTest(Logger);
		Report.flush();
		driver.get(ReportPath);
	}
	
	
/*	public String captureScreenShot(WebDriver driver, String SSPath,String SName) throws IOException
	{
		File sFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String dPath=ScreenShotPath+result.getName()+result.getEndMillis()+".png";
		String dPath=SSPath+"\\"+SName+".png";
		File dFile=new File(dPath);
		FileUtils.copyFile(sFile, dFile);
		return dPath;
	}*/
	
	
	
}
