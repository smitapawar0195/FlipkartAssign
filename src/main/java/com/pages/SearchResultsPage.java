package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	private WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#container > div > div.nt6sNV.JxFEK3._48O0EI > div.DOjaWF.YJG4Cf > div:nth-child(2) > div.DOjaWF.YJG4Cf.col-12-12 > div > div > div.sHCOk2 > div:nth-child(4)")
	private WebElement sortDropdown;

	@FindBy(xpath = "/html/body/div/div/div[3]/div[1]/div[2]/div[12]/div/div/nav/a[11]/span")
	private WebElement nextPageButton;

	private final By priceLocator = By.xpath("//div[@class='cPHDOP col-12-12']//div[@class='Nx9bqj']");

	public void applySort() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();

		    // Wait for new prices to be loaded after sorting
		    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(priceLocator, 5));
	}

	public List<Integer> getProductPrices() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceLocator));

		List<WebElement> freshPriceElements = driver.findElements(priceLocator);
		List<Integer> prices = new ArrayList<>();

		for (WebElement e : freshPriceElements) {
			String text = e.getText().replace("₹", "").replace(",", "").trim();
			try {
				prices.add(Integer.parseInt(text));
			} catch (NumberFormatException ignored) {
			}
		}
		return prices;
	}

	public void goToNextPage() {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000);", nextPageButton);
		nextPageButton.click();

	}
}
