package com.android.automation.pagefactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Get started,Button\"]/android.widget.TextView")
	AndroidElement getStarted;

	@AndroidFindBy(id = "in.swiggy.android:id/tv_phone_umber")
	AndroidElement mobileNumberTextField;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Choose an Account\"]/android.widget.LinearLayout/android.widget.Button")
	AndroidElement noneOfAbove;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Get OTP,Button\"]/android.widget.TextView")
	AndroidElement getOtp;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Allow location access,Button\"]/android.widget.TextView")
	AndroidElement LocationAccess;
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	AndroidElement LocationAccessForeground;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Show account\"]")
	AndroidElement profile;
	

	public BasePage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void loginSwiggy() {
		wait.until(ExpectedConditions.visibilityOf(getStarted));
		getStarted.click();
		noneOfAbove.click();
		wait.until(ExpectedConditions.visibilityOf(mobileNumberTextField));
		mobileNumberTextField.sendKeys("7972116952");
		wait.until(ExpectedConditions.visibilityOf(getOtp));
		getOtp.click();
		wait.until(ExpectedConditions.visibilityOf(LocationAccess));
		LocationAccess.click();
		wait.until(ExpectedConditions.visibilityOf(LocationAccessForeground));
		LocationAccessForeground.click();
		wait.until(ExpectedConditions.visibilityOf(profile));
	}
	
	public void profileSection() {
		profile.click();
	}
	
	public void tapXY(int x,int y) {
		TouchAction action = new TouchAction(driver);
		action.tap(PointOption.point(x,y)).perform();
	}
	
	public void swipe(int fromX,int fromY,int toX,int toY) throws InterruptedException {
		 @SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		 action.press(PointOption.point(fromX,fromY))
		 .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))) 
		 .moveTo(PointOption.point(toX, toY))
		 .release()
		 .perform();
		 Thread.sleep(1000);
		 }
	
	
	public void scrollToElement(String expectedView) throws InterruptedException {
		boolean found = false;
		
		while(found==false) {
		List<MobileElement> listViews = driver.findElements(By.xpath("//android.widget.TextView"));
		List<String> listViewsText = new ArrayList<>();	
		
		
		for(MobileElement view: listViews) {
			listViewsText.add(view.getText());
		}
		
		if(!listViewsText.contains(expectedView)) {
			swipe(632,1000,632,250);
			Thread.sleep(200);
			found = false;
		}else {
			System.out.println("Element found");
			found = true;
		}		
		
	}		
	}
}
