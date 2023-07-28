package com.practicetestautomation.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class GridFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private String browser;
	private Logger log;

	public GridFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}


	public WebDriver createDriver() {
		log.info("Connecting to the node with: " + browser);
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (browser) {
		case "chrome":
			capabilities.setBrowserName("chrome");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--remote-allow-origins=*");
////			WebDriver driver = new ChromeDriver(options);
//
//			// Make sure to upgrade chromedriver to work with your browser version: https://chromedriver.chromium.org/downloads
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//			driver.set(new ChromeDriver(options));
			break;

		case "firefox":
			capabilities.setBrowserName("firefox");
//			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
//			driver.set(new FirefoxDriver());
			break;

		case "edge":
			capabilities.setBrowserName("edge");
//			EdgeOptions edgeOptions = new EdgeOptions();
//			edgeOptions.addArguments("--remote-allow-origins=*");
//			System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
//			System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//			driver.set(new EdgeDriver(edgeOptions));
			break;

		default:
			capabilities.setBrowserName("chrome");
//			log.debug("Do not know how to start: " + browser + ", starting chrome.");
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//			driver.set(new ChromeDriver());
			break;
		}

		URL url = null;
		try {
			url = new URL("http://localhost:4444");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.set(new RemoteWebDriver(url, capabilities));

		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		return driver.get();
	}
}
