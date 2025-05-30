package com.tests;


import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.HomePage;
import com.pages.ProductAddToCartPage;
import com.pages.SearchResultsPage;
import com.pages.VeiwCartPage;
import com.utils.Config;

public class AddToCartTest extends Base{
	Config config = new Config();
	 
	@Test
	public void validateAddToCartFunctionality() throws Exception{
		HomePage hp = new HomePage(driver);
		SearchResultsPage sRPage = new SearchResultsPage(driver);

		hp.searchProduct(config.searchTerm());
		sRPage.applySort();
		
		ProductAddToCartPage productPage = new ProductAddToCartPage(driver);
		Thread.sleep(6000);
        productPage.clickProductByIndex(5);
        productPage.switchToProductTab();
        Thread.sleep(6000);
        productPage.clickAddToCart();

        
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());

        Thread.sleep(6000);
        productPage.clickProductByIndex(6);
        productPage.switchToProductTab();
        Thread.sleep(6000);
        productPage.clickAddToCart();

        VeiwCartPage cart = new VeiwCartPage(driver);
        Thread.sleep(6000);
        cart.openCart();

        List<String> productNames = cart.getProductNamesInCart();
        List<Integer> productPrices = cart.getProductPricesInCart();
        int platformFee = cart.getPlatformFee();
        int actualTotal = cart.getTotalAmount();

     
        System.out.println("Products added to cart: " + productNames);
        System.out.println("Product prices: " + productPrices);
        System.out.println("Platform fee: ₹" + platformFee);
        System.out.println("Displayed total amount: ₹" + actualTotal);

       
        Assert.assertEquals(productPrices.size(), 2, "Cart should contain 2 products");

        int expectedTotal = productPrices.stream().mapToInt(Integer::intValue).sum() + platformFee;
        Assert.assertEquals(actualTotal, expectedTotal, "Displayed total should match expected total including platform fee");
    
	}
}
