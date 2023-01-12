package com.android.automation.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelectLocationPage extends BasePage{

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	//BasePage bp = new BasePage(driver, wait);

	@AndroidFindBy(id = "in.swiggy.android:id/title_tv")
	private AndroidElement setDeliveryLocation;
	
	@AndroidFindBy(id = "in.swiggy.android:id/search_query_edittext")
	private AndroidElement addressField;
	
	@AndroidFindBy(xpath = "//*[@resource-id='in.swiggy.android:id/tv_area_name']")
	private AndroidElement address;
	
	@AndroidFindBy(id = "in.swiggy.android:id/google_place_search_title_text1")
	private AndroidElement confirmLocation;
	
	
	@AndroidFindBy(xpath="//*[@class='android.view.ViewGroup']")
	private AndroidElement searchResults;
	
	
	@AndroidFindBy(id="in.swiggy.android:id/tv_description")
	private AndroidElement locateMeLocation;
	
	@AndroidFindBy(id="in.swiggy.android:id/item_menu_top_header_restaurant_name")
	private AndroidElement locationName;
	
	@AndroidFindBy(id="in.swiggy.android:id/title")
	private AndroidElement invalidPageTitle;
	
	@AndroidFindBy(id="in.swiggy.android:id/disc_text_change_location")
	private AndroidElement invalidPageEditLocation;
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"Logout Options\"]")
	private AndroidElement logout;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Show account\"]")
	AndroidElement profile;
	
	public SelectLocationPage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		super(driver, wait);
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void clickChangeLocation() {
		wait.until(ExpectedConditions.visibilityOf(setDeliveryLocation));
		setDeliveryLocation.click();	
	}
	
	public void sendLocationTextBox() {
		wait.until(ExpectedConditions.visibilityOf(addressField));
		addressField.sendKeys("Sapphire sai");
	}
	
	
	public void getSearchResults() {
		wait.until(ExpectedConditions.visibilityOf(searchResults));
		
		List<MobileElement> list = driver.findElements(By.xpath("//*[@class='android.view.ViewGroup']"));
		for (MobileElement ae : list) {
			String addressList = ae.getAttribute("text");
			System.out.println(addressList);
			}
	}
	
	public void clickConfirmLocation() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(confirmLocation));
		confirmLocation.click();
		Thread.sleep(4000);
	}
	
	public void clickLocateMe() {
		wait.until(ExpectedConditions.visibilityOf(locateMeLocation));
		locateMeLocation.click();
	}
	
	public void getLocation() {
		wait.until(ExpectedConditions.visibilityOf(address));
		String addressResult =  address.getAttribute("text");
		System.out.println(addressResult);
	}
	
	
	public void clickLogoutBtn() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(profile));
		profile.click();
		swipe(546, 2147,564, 202);
		
		wait.until(ExpectedConditions.visibilityOf(logout));
		logout.click();
		Thread.sleep(3000);
		tapXY(968, 503);
		Thread.sleep(3000);
		driver.findElement(By.id("in.swiggy.android:id/md_button_positive")).click();
		
		Thread.sleep(3000);
		String Login = driver.findElement(By.id("in.swiggy.android:id/login_container")).getText();
		Assert.assertEquals(Login.contains("LOGIN"), true);
		
		
	}
		
}
