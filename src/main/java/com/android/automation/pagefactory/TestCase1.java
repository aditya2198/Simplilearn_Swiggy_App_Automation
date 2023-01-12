package com.android.automation.pagefactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.android.automation.utils.ReadProperties;
import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestCase1 {

	public AppiumDriver<MobileElement> driver;
	ReadProperties rp= new ReadProperties();
	
	

	public void takeScreenShots(String fileName, AppiumDriver<MobileElement> driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String destFile = fileName + "-" + dateFormat.format(new Date()) + ".png";

		try {
			Files.copy(scrFile, new File(rp.getScreenshotDir() + "\\" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
