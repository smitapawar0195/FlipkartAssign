package com.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.Base;
import com.pages.HomePage;
import com.pages.SearchResultsPage;
import com.utils.Config;

public class SortValidationTest extends Base {
	Config config = new Config();

	@Test
	public void validatePriceSortAscending() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		SearchResultsPage sRPage = new SearchResultsPage(driver);
	
		hp.searchProduct(config.searchTerm());
		sRPage.applySort();

		List<Integer> allPrices = new ArrayList<>();
		
		for (int i = 0; i < config.pageLimit(); i++) {
			Thread.sleep(6000);
			allPrices.addAll(sRPage.getProductPrices());
			if (i < config.pageLimit() - 1) {
				Thread.sleep(6000);
				sRPage.goToNextPage();
			}
		}
		System.out.println("Prices before sorting: " + allPrices);

		List<Integer> sortedPrices = new ArrayList<>(allPrices);
		Collections.sort(sortedPrices);
		
		System.out.println("Prices after sorting: " + sortedPrices);

		Assert.assertEquals(allPrices, sortedPrices);
	}
}