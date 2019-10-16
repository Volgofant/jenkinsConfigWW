package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.openqa.selenium.By.xpath;

public class Other extends Core {

	@Step("Find by text")
	public static SelenideElement findByText(String type, String text) {
		return $(xpath("//" + type + "[contains(text(),'" + text + "')]"));
	}

	@Step("Try to see something by text")
	public static void visibilityByText(String type, String text) {
		$(xpath("//" + type + "[contains(text(),'" + text + "')]")).should(visible);
	}

	@Step("Try to see something by xpath")
	public static void visibilityByXpath(String xpath) {
		$(xpath(xpath)).should(visible);
	}

	@Step("Check element for existence by text")
	public static boolean existenceByText(String type, String text) {
		return $(xpath("//" + type + "[contains(text(),'" + text + "')]")).exists();
	}

	@Step("Element should not be exist")
	public static SelenideElement shouldNotBeExistByText(String type, String text) {
		return $(xpath("//" + type + "[contains(text(),'" + text + "')]")).shouldNotBe(exist);
	}

	@Step("Check element for existence by xpath")
	public static boolean existenceByFullXpath(String xpath) {
		return ($(xpath(xpath)).exists());
	}

	@Step("Take url on new tab")
	public static String takeUrlOnNewTab() {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		return getWebDriver().switchTo().window(tabs.get(1)).getCurrentUrl();
	}

	@Step("Take current url")
	public static String takeCurrentUrl() {
		return getWebDriver().getCurrentUrl();
	}

	@Step("Switch to new tab")
	public static void switchToNewTab() {
		ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		getWebDriver().switchTo().window(tabs.get(1));
	}

	public static String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < randomString.length(); k++) {

			char ch = randomString.charAt(k);

			if (((ch >= 'a' && ch <= 'z')
					|| (ch >= 'A' && ch <= 'Z')
					|| (ch >= '0' && ch <= '9'))
					&& (n > 0)) {

				r.append(ch);
				n--;
			}
		}

		// return the resultant string
		return r.toString();
	}

	@Attachment("Just take screenshot")
	public static byte[] takeScreen() {
		return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Step("Refresh page")
	public static void refresh() {
		Selenide.refresh();
	}

	@Step("Ignore test for this site")
	public static void ignoreTestIfUrlContain(String urlForAvoid) {
		assumeFalse(Configuration.baseUrl.contains(urlForAvoid));
	}


}
