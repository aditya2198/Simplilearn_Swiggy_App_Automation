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

public class MenuPage {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Search for restaurant, item or more']/android.widget.TextView")
	private AndroidElement searchTextBox;
	
	@AndroidFindBy(id="in.swiggy.android:id/et_search_query_v2")
	private AndroidElement searchBox;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup")
	private List<AndroidElement> searchResults;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Restaurants filter, not selected']")
	private AndroidElement searchByRestaurants;
	
	@AndroidFindBy(id="in.swiggy.android:id/radio_button")
	private List<AndroidElement> choiceRadioBtn;
	
	@AndroidFindBy(id="in.swiggy.android:id/progressive_variants_continue_button")
	private AndroidElement continueBtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout/android.widget.TextView[contains(@text,'MORE')]")
	private AndroidElement moreBtn;
	
	@AndroidFindBy(id="in.swiggy.android:id/check_box")
	private List<AndroidElement> addOnCheckBox;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD ITEM']")
	private AndroidElement addItem;
	
	@AndroidFindBy(xpath="//*[@resource-id='in.swiggy.android:id/fab_container_layout']//android.widget.TextView[@resource-id='in.swiggy.android:id/card_fab_item_count']")
	private AndroidElement numberOfItem;
	
	@AndroidFindBy(xpath="//*[@resource-id='in.swiggy.android:id/fab_container_layout']"
			+ "//android.widget.TextView[@resource-id='in.swiggy.android:id/card_fab_item_count']/following-sibling::android.widget.TextView[2]")
	private AndroidElement getPrice;
	
	
	@AndroidFindBy(id="in.swiggy.android:id/tv_checkout")
	private AndroidElement viewCart;
	
	@AndroidFindBy(id="in.swiggy.android:id/extras_text")
	private AndroidElement addOn;
	
	
	public MenuPage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
public void selectFoodByRestaurantName() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchTextBox));
		searchTextBox.click();
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.click();
		Thread.sleep(2000);
		searchBox.sendKeys("Savali");
		
		//Printing all the results shown as savali
		
		
		//clicking on first result
		
		//Validating results page "Restaurants matching"
		
		//clicking on Savali
		
		//searching dish using search field - "Paneer Chilli"
		
		//Adding Paneer chilli
		
		//clearing the search field searching -"Roti" (add multiple quantities)
		
		//clearing the search field searching adding paneer handi
		
		//clicking on veg toggle
		
		//addon  radio button - dosa + juice
		
		//validate view cart and validating the cart page
		
			//click on view cart
			//apply WELCOME50 offer
			//after applying coupon it should display savings made :assertion
			//click on yay
			//click on select address
		//	click on home and validate proceed to pay button
			//click on proceed to pay and get list of payment methods, validate payment options text on top using assertion

		
		//apply one offer coupon
		
		// print bill details
		
		//clcik on proceed to pay- screenshot validations -payment options
		
		
		//search by food: in search field enter food item name: chicken biryani
		//click on first result
		//click on add button in first displayed rest : eg reddy
		//select 1 kg radio button and click on add item to cart
		//click on checkout
		//apply coupon validate 319 savings with this  coupon click on yay
		//select address and follow same process

		
		String noResultsFound = driver.findElement(By.xpath("//*[contains(@text,'We')]")).getAttribute("text");
		System.out.println("Error: " + noResultsFound);
		Assert.assertEquals(noResultsFound.contains("We"), true);
		
	}
	public void addToCart(String whatToAdd) {
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.TextView[@text='"+whatToAdd+"']"
				+ "/parent::android.view.ViewGroup"
				+ "/parent::android.widget.LinearLayout"
				+ "//following-sibling::android.widget.FrameLayout[2]"))));
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+whatToAdd+"']"
				+ "/parent::android.view.ViewGroup"
				+ "/parent::android.widget.LinearLayout"
				+ "//following-sibling::android.widget.FrameLayout[2]")).click();
	}
	
	public void choiceRadioBtn(int num) {
		choiceRadioBtn.get(num).click();
	}
	
	public void clickContinueBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
	}
	
	public void clickMore() {
		wait.until(ExpectedConditions.elementToBeClickable(moreBtn));
		moreBtn.click();
	}
	
	public void addOnCheckBox(int num) {
		addOnCheckBox.get(num).click();
	}
	
	public void clickAddItem() {
		wait.until(ExpectedConditions.elementToBeClickable(addItem));
		addItem.click();
	}
	
	public String getNumberOfItem() {
		wait.until(ExpectedConditions.visibilityOf(numberOfItem));
		return numberOfItem.getText();
	}
	
	public String getPrice() {
		wait.until(ExpectedConditions.visibilityOf(getPrice));
		return getPrice.getText();
	}
	
	public void clickViewCart() {
		wait.until(ExpectedConditions.elementToBeClickable(viewCart));
		viewCart.click();
	}
	
	public int getNumberOfAddOn() {
		Integer numberOfAddOn = Integer.parseInt(addOn.getText().replaceAll("[^0-9]", ""));
		return numberOfAddOn;
	}

		
}
