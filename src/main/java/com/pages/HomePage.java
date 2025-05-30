package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder=\"Search for Products, Brands and More\"]")
    private WebElement searchBox;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void closeLoginPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement closeLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(),'✕')]")));
            closeLogin.click();
        } catch (Exception e) {
            System.out.println("Login popup not displayed.");
        }
    }

    public void searchProduct(String product) {
        searchBox.clear();  
        searchBox.sendKeys(product);
        searchButton.click();
    }
}
