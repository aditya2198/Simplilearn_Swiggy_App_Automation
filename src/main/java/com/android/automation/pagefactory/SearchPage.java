package com.android.automation.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage extends TestCase1 {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Search for restaurant, item or more']/android.widget.TextView")
	private AndroidElement searchTextBox;
	
	@AndroidFindBy(id="in.swiggy.android:id/et_search_query_v2")
	private AndroidElement searchBox;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
	private List<AndroidElement> searchResults;
	
	@AndroidFindBy(xpath="//*[@resource-id='in.swiggy.android:id/layout_query_item']")
	private AndroidElement trendingSearch;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='fruits']")
	private AndroidElement instamartFruits;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView']")
	private AndroidElement resultPage;
	
	@AndroidFindBy(xpath="(//android.widget.ImageView[@content-desc=''])[3]")
	private AndroidElement cuisine;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc='Ice Cream'])[1]")
	private AndroidElement searchByFood;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Sort Restaurant List']/android.widget.LinearLayout/android.widget.TextView")
	private AndroidElement sortBy;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Restaurants filter, not selected']")
	private AndroidElement searchByRestaurants;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@content-desc='Rating (High to Low) radio button unchecked']")
	private AndroidElement sortByRatings;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Dishes filter, not selected']")
	private AndroidElement searchByDishes;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Instamart filter, not selected']")
	private AndroidElement searchByInstamart;
	
	@AndroidFindBy(id="in.swiggy.android:id/title")
	private AndroidElement seeAllResults;
	
	
	public SearchPage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void searchTextBox() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchTextBox));
		searchTextBox.click();
		driver.hideKeyboard();
		Thread.sleep(3000);
	}
	
	public void searchPageFunc() throws InterruptedException {
		trendingSearch.click();
		Thread.sleep(3000);
		takeScreenShots("01_TrendingSearch", driver);
		
		

		List<MobileElement> loop = driver.findElements(By.xpath("//*[@resource-id='in.swiggy.android:id/list_item_title']"));
		for (MobileElement ae : loop) {
			System.out.println("Results are :" + ae.getAttribute("text"));
			Thread.sleep(1000);
			}
		Thread.sleep(2000);

		
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOf(instamartFruits));
		instamartFruits.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(resultPage));
		String resultNumber = resultPage.getAttribute("text");
		System.out.println("results are:"+ resultNumber);
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(3000);
		
		if(driver.findElementByXPath("//*[contains(@text,'Most Searched and Bought')]") != null) {
			driver.navigate().back();
			Thread.sleep(2000);
		}
		
		wait.until(ExpectedConditions.visibilityOf(trendingSearch));
		cuisine.click();
		Thread.sleep(4000);
		takeScreenShots("01_SearchPage_Cuisine", driver);
		Thread.sleep(2000);
		driver.navigate().back();
		
		
	}
	
	public void searchByFoodItem() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.click();
		Thread.sleep(2000);
		searchBox.sendKeys("ice cream");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(searchByFood));
		String foodName = searchByFood.getAttribute("text");
		System.out.println(foodName);
		searchByFood.click();
		
		wait.until(ExpectedConditions.visibilityOf(searchByRestaurants));
		takeScreenShots("01_SearchPage_Results", driver);
		
		searchByRestaurants.click();
		Thread.sleep(3000);
		takeScreenShots("01_SearchPage_Restaurants", driver);
		
		wait.until(ExpectedConditions.visibilityOf(sortBy));
		
		sortBy.click();
		wait.until(ExpectedConditions.visibilityOf(sortByRatings));
		
		sortByRatings.click();
		Thread.sleep(3000);
		takeScreenShots("01_SearchPage_Restaurants_sorted", driver);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(searchByDishes));
		takeScreenShots("01_SearchPage_Results_Dishes", driver);
		searchByDishes.click();
		Thread.sleep(3000);
		takeScreenShots("01_SearchPage_Dishes", driver);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(searchByInstamart));
		takeScreenShots("01_SearchPage_searchByInstamart", driver);
		searchByInstamart.click();
		Thread.sleep(3000);
		takeScreenShots("01_SearchPage_Instamart", driver);
		Thread.sleep(2000);
		driver.navigate().back();
	}
	
	public void searchByInvalidFoodItem() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.click();
		Thread.sleep(2000);
		searchBox.sendKeys("^%$&*#^()");
		wait.until(ExpectedConditions.visibilityOf(seeAllResults));
		seeAllResults.click();
		String noResultsFound = driver.findElement(By.xpath("//*[contains(@text,'We')]")).getAttribute("text");
		System.out.println("Error: " + noResultsFound);
		Assert.assertEquals(noResultsFound.contains("We"), true);
		
	}
	

	
	}
	
	

