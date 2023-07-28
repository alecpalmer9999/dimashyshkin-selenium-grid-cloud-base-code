package com.practicetestautomation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest {

	protected WebDriver driver;
	protected Logger log;

	@Parameters({ "browser", "environment" })  // these are passed in from .xml file's <parameter>
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, @Optional("chrome") String browser, @Optional("Local") String environment, ITestContext ctx) {
		log = LogManager.getLogger(ctx.getCurrentXmlTest().getSuite().getName());
//		driver = new BrowserDriverFactory(browser, log).createDriver();

		switch (environment) {
			case "Local":
				driver = new BrowserDriverFactory(browser, log).createDriver();
				break;
			case "grid":
				driver = new GridFactory(browser, log).createDriver();
				break;
			default:
				driver = new BrowserDriverFactory(browser, log).createDriver();
				break;
		}

//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setBrowserName("edge");
//
//		try {
//			driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}

		driver.manage().window().maximize();
	}


	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");
		driver.quit();
	}

}
