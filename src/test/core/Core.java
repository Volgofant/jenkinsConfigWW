package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import io.qameta.allure.Attachment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Core {


	@Before
	public void setUp() {
		//Configuration.baseUrl = "https://dev-club.ww-ru.ru";
		//Configuration.baseUrl = "https://www.westwing.ru";
		Configuration.browserSize = "1920x1080";
		Configuration.reportsFolder = "target/allure-results";
		Configuration.headless = true;
	}

	@Rule
	public TestWatcher screenshotOnFailure = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			makeScreenshotOnFailure();
		}

		@Override
		protected void finished(Description description) {
			WebDriverRunner.getWebDriver().close();
		}

		@Attachment("Screenshot on failure")
		public byte[] makeScreenshotOnFailure() {
			return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
		}

	};

}
