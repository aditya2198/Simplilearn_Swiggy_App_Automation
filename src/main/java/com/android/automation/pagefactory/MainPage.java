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

public class MainPage {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;

	@AndroidFindBy(id = "in.swiggy.android:id/title_tv")
	private AndroidElement setDeliveryLocation;
	
	@AndroidFindBy(id = "in.swiggy.android:id/search_query_edittext")
	private AndroidElement addressField;
	
	@AndroidFindBy(xpath = "//*[@resource-id='in.swiggy.android:id/tv_area_name']")
	private AndroidElement address;
	
	@AndroidFindBy(id = "in.swiggy.android:id/google_place_search_title_text1")
	private AndroidElement confirmLocation;
	
	@AndroidFindBy(id = "in.swiggy.android:id/bottom_bar_explore")
	private AndroidElement btmSearchBtn;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"explore food deliveries\"]/android.widget.FrameLayout/android.widget.ImageView")
	private AndroidElement homePageFoodSection;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"shop for groceries at instamart and get it delivered in minutes\"]/android.widget.FrameLayout/android.widget.ImageView")
	private AndroidElement homePageinstamartSection;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"get discounts on your dine out experience with dine out\"]/android.widget.FrameLayout/android.widget.ImageView")
	private AndroidElement homePagedineOutSection;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"get pick up and drop service with genie\"]/android.widget.FrameLayout/android.widget.ImageView")
	private AndroidElement homePagegenieSection;
	
	
	public MainPage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void foodSection() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(homePageFoodSection));
		homePageFoodSection.click();
		Thread.sleep(3000);
		String foodValidation = driver.findElement(By.xpath("//*[@resource-id='in.swiggy.android:id/header_title']")).getText();
		Assert.assertEquals(foodValidation.contains("Top rated near you"), true);
	}
	
	public void instamart() {
		wait.until(ExpectedConditions.elementToBeClickable(homePageinstamartSection));
		homePageinstamartSection.click();
	
	}
	
	public void dineOut() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(homePagedineOutSection));
		homePagedineOutSection.click();
		Thread.sleep(3000);
	}
	
	public void genie() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(homePagegenieSection));
		homePagegenieSection.click();
		Thread.sleep(3000);
		String genieValidation = driver.findElement(By.xpath("//*[contains(@text,'genie')]")).getText();
		Assert.assertEquals(genieValidation.contains("genie"), true);
	}
	
	public void clickSetDeliveryLocation() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(setDeliveryLocation));
		setDeliveryLocation.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(addressField));
		addressField.sendKeys("Sapphire Sai Nisarg");
		
		wait.until(ExpectedConditions.elementToBeClickable(address));
		address.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(confirmLocation));
		confirmLocation.click();
		Thread.sleep(3000);
		
		
	}
	
	public void clickBtmSearchBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btmSearchBtn));
		btmSearchBtn.click();
	}
	
		
}
