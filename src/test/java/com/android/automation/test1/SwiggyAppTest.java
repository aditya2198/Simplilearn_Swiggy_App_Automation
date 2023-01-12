package com.android.automation.test1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.android.automation.pagefactory.BasePage;
import com.android.automation.pagefactory.MainPage;
import com.android.automation.pagefactory.MenuPage;
import com.android.automation.pagefactory.SearchPage;
import com.android.automation.pagefactory.SelectLocationPage;
import com.android.automation.pagefactory.SignUpPage;
import com.android.automation.utils.ReadProperties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;

public class SwiggyAppTest extends TestCase{

	public static AppiumDriver<MobileElement> driver;
	public static WebDriverWait wait;
	static URL URL;
	static BasePage base;
	static SelectLocationPage loc;
	static MainPage mainPage;
	static SearchPage searchPage;
	static MenuPage menuPage;
	SignUpPage signupPage;

	@BeforeMethod
	public void setup() throws InterruptedException {

		try {

			ReadProperties configProperties = new ReadProperties();

			try {
				configProperties.loadProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String url = configProperties.get("URL");

			URL = new URL(url);
			DesiredCapabilities capabilties = new DesiredCapabilities();

			capabilties.setCapability("platformName", "Android");
			capabilties.setCapability("appium:deviceName", "RedmiK50i");
			capabilties.setCapability("appium:automationName", "UiAutomator2");
			capabilties.setCapability("appium:udid", "CE5PUKF6HMU8LRWG");
			capabilties.setCapability("appium:app", "C:\\Users\\aavadhoo\\OneDrive - Capgemini\\Desktop\\Appium\\Swiggy Food & Grocery Delivery_4.23.2_Apkpure.apk");
			capabilties.setCapability("appium:appWaitActivity", "*");
			
			driver = new AppiumDriver<MobileElement>(URL, capabilties);
			wait = new WebDriverWait(driver, 15000);
			System.out.println(driver.getSessionId());

			base = new BasePage(driver, wait);
//			deliveryPage = new SelectLocationPage(driver, wait);
			mainPage = new MainPage(driver, wait);
			searchPage = new SearchPage(driver, wait);
			menuPage = new MenuPage(driver, wait);
			signupPage = new SignUpPage(driver, wait);
			
			loc = new SelectLocationPage(driver, wait);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Thread.sleep(1000);

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}


	@Test(priority=0)
	public void runLoginTest() throws Throwable {
		
		signupPage.getStartedSwiggy();
		takeScreenShot("01_Login_screen", driver);
		signupPage.sendMobileNumberTextField();
		takeScreenShot("02_Phone_number_entered", driver);
		signupPage.getOTPSMS();
		takeScreenShot("03_Waiting_OTP", driver);
		signupPage.location();
		takeScreenShot("04_Location_Access", driver);
		signupPage.profileIcon();
		takeScreenShot("05_Account_logged_in", driver);

	}
	
	
	@Test(priority=1)
	public void runLogoutTest() throws Throwable {
		
		signupPage.getStartedSwiggy();
		takeScreenShot("01_Login_screen", driver);
		signupPage.logInWrongCredentials();

	}
	
	@Test(priority=2)
	public void swiggyHomePage() throws Throwable {
		
		base.loginSwiggy();
		takeScreenShot("01_Swiggy_HomePage", driver);
		
		if(driver.findElement(By.id("in.swiggy.android:id/cancel_icon")) != null) {
			driver.findElement(By.id("in.swiggy.android:id/cancel_icon")).click();
			Thread.sleep(2000);
		}
		base.profileSection();
		takeScreenShot("02_Profile_Section", driver);
		driver.navigate().back();
		
		for(int i=0; i<=2; i++) {
			base.swipe(503, 1200, 551, 454);
			Thread.sleep(2000);
			takeScreenShot("03_HomePageLength", driver);
		}
		
		 mainPage.foodSection();
		 takeScreenShot("05_foodSection", driver);
		 mainPage.instamart();
		 takeScreenShot("06_instamart", driver);
		 mainPage.dineOut();
		 takeScreenShot("07_dineOut", driver);
		 driver.navigate().back();
		 mainPage.genie();
		 takeScreenShot("08_genie", driver);
		 driver.navigate().back();
		 //refresh page
		 //base.swipe(538, 469, 529, 1958 );
		 
		
	}
	
	@Test(priority=3)
	public void runLocationTest() throws Throwable {
		
		base.loginSwiggy();
		takeScreenShot("01_Swiggy_HomePage", driver);
		
		if(driver.findElement(By.id("in.swiggy.android:id/cancel_icon")) != null) {
			driver.findElement(By.id("in.swiggy.android:id/cancel_icon")).click();
			Thread.sleep(2000);
		}
		
		loc.clickChangeLocation();
		takeScreenShot("01_clickChangeLocation", driver);
		loc.sendLocationTextBox();
		takeScreenShot("02_sendLocationTextBox", driver);
		loc.getSearchResults();
		takeScreenShot("03_getSearchResults", driver);
		loc.getLocation();
		takeScreenShot("04_getLocation", driver);
		loc.clickLocateMe();
		takeScreenShot("05_clickLocateMe", driver);
		
		loc.clickConfirmLocation();
		takeScreenShot("05_clickConfirmLocation", driver);
		loc.clickLogoutBtn();
		takeScreenShot("05_clickLogoutBtn", driver);
		
	}
	
	@Test(priority=4)
	public void swiggySearchPage() throws Throwable {
		
		base.loginSwiggy();
		takeScreenShot("01_Swiggy_HomePage", driver);
		
		if(driver.findElement(By.id("in.swiggy.android:id/cancel_icon")) != null) {
			driver.findElement(By.id("in.swiggy.android:id/cancel_icon")).click();
			Thread.sleep(2000);
		}
		searchPage.searchTextBox();
		takeScreenShot("02_SearchPage_Options", driver);
		
		searchPage.searchPageFunc();
		searchPage.searchByFoodItem();
		searchPage.searchByInvalidFoodItem();
		
	}
	
//	@Test
//	public void endToEnd() throws Throwable {
//		
//	}
	
	
	@Test(priority=5)
	public void Add_To_Cart() throws InterruptedException {

		System.out.println("Check able to check checkbox and radio");
		System.out.println("Check able to add item");


		// Searching
		mainPage.clickBtmSearchBtn();
		searchPage.searchTextBox();
		Thread.sleep(1000);
		base.scrollToElement("Domino's Pizza");
		Thread.sleep(1000);
		searchPage.searchByFoodItem();

		// Adding
		Thread.sleep(1000);
		base.scrollToElement("Farmhouse");
		Thread.sleep(5000);
		menuPage.addToCart("Farmhouse");
		Thread.sleep(10000);
		menuPage.choiceRadioBtn(3);
		menuPage.clickContinueBtn();
		menuPage.choiceRadioBtn(1);
		menuPage.clickContinueBtn();
		menuPage.addOnCheckBox(0);
		Assert.assertEquals(menuPage.getNumberOfAddOn(), 1);
		menuPage.addOnCheckBox(1);
		Assert.assertEquals(menuPage.getNumberOfAddOn(), 2);
		menuPage.addOnCheckBox(2);
		Assert.assertEquals(menuPage.getNumberOfAddOn(), 3);
		menuPage.addOnCheckBox(3);
		Assert.assertEquals(menuPage.getNumberOfAddOn(), 4);
		menuPage.clickAddItem();

		// Checking
		System.out.println("Item: " +menuPage.getNumberOfItem());
		Assert.assertEquals(menuPage.getNumberOfItem(), "1");
		System.out.println("Price: " + menuPage.getPrice());

	}
	
	
}
