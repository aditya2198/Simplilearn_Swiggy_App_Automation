package com.android.automation.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class SignUpPage{

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
//	By getStarted = By.xpath("//android.view.ViewGroup[@content-desc=\"Get started,Button\"]/android.widget.TextView");
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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"EDIT\"]")
	AndroidElement editProfile;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.EditText']")
	AndroidElement phoneNo;
	
	@AndroidFindBy(id = "in.swiggy.android:id/tv_country_code")
	AndroidElement countryCode;
	
	@AndroidFindBy(id = "in.swiggy.android:id/timerAndInfoText")
	AndroidElement retry;
	
	
	public SignUpPage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
//	@BeforeMethod
//	public void waitImplicitly() {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//	}
	
	public void getStartedSwiggy() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(getStarted));
		//Thread.sleep(30000);
		getStarted.click();
		//wait.until(ExpectedConditions.visibilityOf(noneOfAbove));
		//Thread.sleep(5000);
		noneOfAbove.click();
	}
	
	public void sendMobileNumberTextField() {
		wait.until(ExpectedConditions.visibilityOf(mobileNumberTextField));
		mobileNumberTextField.sendKeys("7972116952");
		
	}
	
	public void getOTPSMS() {
		wait.until(ExpectedConditions.visibilityOf(getOtp));
		getOtp.click();
			
	}
	
	public void location() {
		wait.until(ExpectedConditions.visibilityOf(LocationAccess));
		LocationAccess.click();
		wait.until(ExpectedConditions.visibilityOf(LocationAccessForeground));
		LocationAccessForeground.click();
	}
	
	public void profileIcon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(profile));
		profile.click();
		System.out.println("Click performed...");
		wait.until(ExpectedConditions.visibilityOf(editProfile));
		editProfile.click();
		Thread.sleep(3000);
		
		//TouchAction 
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(998, 564)).perform();
		wait.until(ExpectedConditions.visibilityOf(phoneNo));
		phoneNo.click();
		Thread.sleep(3000);
		touchAction.tap(PointOption.point(310, 585)).perform();
		String loggedInUser = phoneNo.getText();
		String phone = "7972116952";
		System.out.println(loggedInUser);
		driver.navigate().back();
		Assert.assertEquals(loggedInUser, phone);
		
	}
	
	public void logInWrongCredentials() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(mobileNumberTextField));
		mobileNumberTextField.sendKeys("123456789");
		getOtp.click();
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOf(LocationAccess));
		try {
			Boolean isdisplayed = LocationAccess.isDisplayed();
		}
		catch(Exception e){
			String Code = countryCode.getText();
			System.out.println(Code);
			System.out.println("Please enter 10 digit number");
			mobileNumberTextField.clear();
			Thread.sleep(3000);
			mobileNumberTextField.sendKeys("1234567890");

			getOtp.click();
			wait.until(ExpectedConditions.visibilityOf(retry));
			String Actual = retry.getText();
			System.out.println("Please check your number");
			Assert.assertEquals(Actual.contains("Retry in"),true );
			}

		}

	
	
	public void clearMobileNumberTextField() {
		mobileNumberTextField.clear();
	}
	
	
	
	
	
	
	
	
	
	
}
