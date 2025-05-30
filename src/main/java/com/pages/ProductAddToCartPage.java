package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductAddToCartPage {
	private WebDriver driver;
	  private WebDriverWait wait;

    @FindBy(xpath = "//div[@class=\"DOjaWF gdgoEp\"]/div[@class=\"cPHDOP col-12-12\"]/div/div")
    private List<WebElement> productLinks;

    public ProductAddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickProductByIndex(int index) {
    	 //wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
        if (productLinks != null && productLinks.size() > index) {
            productLinks.get(index).click();
        } else {
            throw new RuntimeException("Product index out of bounds.");
        }
    }

    public void switchToProductTab() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
    private WebElement addToCartButton;

    public void clickAddToCart() {
    	 wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
}
