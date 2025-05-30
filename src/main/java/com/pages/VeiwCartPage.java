package com.pages;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Collectors; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VeiwCartPage {
	 private WebDriver driver;
	 private WebDriverWait wait;

	    public VeiwCartPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    }

	    @FindBy(xpath = "//span[text() = 'Cart']")
	    private WebElement cartButton;

	    @FindBy(xpath = "//div[@class=\"cPHDOP col-12-12\"]/descendant::div[@class=\"x9LoV+\"]/child::div[@class=\"gE4Hlh\"]/a")  // selector for product name
	    private List<WebElement> productNames;

	    @FindBy(xpath = "//div[@class=\"cPHDOP col-12-12\"]/descendant::div[@class=\"x9LoV+\"]/child::span[@class=\"LAlF6k re6bBo\"]") // selector for price
	    private List<WebElement> productPrices;

	    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[3]/div[2]/span")
	    private WebElement platformFee;
	    
	    @FindBy(xpath = "//div[@class=\"cPHDOP col-12-12\"]/descendant::div[@class=\"PWd9A7 xvz6eC\"]/descendant::span") // total sum price element
	    private WebElement totalAmount;

	    public void openCart() {
	    	//wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
	    }

	    public List<String> getProductNamesInCart() {
	    	  wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
	        return productNames.stream()
	                .map(WebElement::getText)
	                .collect(Collectors.toList());
	    }

	    public List<Integer> getProductPricesInCart() {
	        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
	        return productPrices.stream()
	                .map(e -> e.getText().replace("₹", "").replace(",", "").trim())
	                .map(Integer::parseInt)
	                .collect(Collectors.toList());
	    }

	    public int getPlatformFee() {
	    	 wait.until(ExpectedConditions.visibilityOf(platformFee));
	        String feeText = platformFee.getText().replace("₹", "").replace(",", "").trim();
	        return Integer.parseInt(feeText);
	    }

	    public int getTotalAmount() {
	    	  wait.until(ExpectedConditions.visibilityOf(totalAmount));
	        String total = totalAmount.getText().replace("₹", "").replace(",", "").trim();
	        return Integer.parseInt(total);
	    }
}
