package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.exceptions.InvalidBrowserException;
import com.utils.Config;

public class Base {
	protected WebDriver driver;
	Config config = new Config();



	@BeforeMethod
	public WebDriver setUp() throws InvalidBrowserException {
		String browser = config.openBrowser();
		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito", "--start-maximized");
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-private");
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("-inprivate");
			driver = new EdgeDriver(edgeOptions);
			driver.manage().window().maximize();
			break;

		default:
			throw new InvalidBrowserException("Invalid browser: " + browser);
		}

		driver.get(config.launchUrl());
		return driver;

	}

	@AfterMethod
	public void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}

	}

}
