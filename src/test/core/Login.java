package core;

import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

import org.openqa.selenium.By;

import pages.Settings;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Login {

	@Step("Auth like a test or real user")
	public static void commonUser(String user) throws InterruptedException {
		Selenide.open("/");
		$(By.xpath("//span[contains(text(),'Войти')]")).click();
		switch (user) {
			case ("test"):
				StartPage.setEmailForAuth("club_test@test.ru");
				StartPage.setPasswordForAuth("qweqwe");
				break;
			case ("real"):
				StartPage.setEmailForAuth("andrey.elfimov@westwing.ru");
				StartPage.setPasswordForAuth("SomeTestPassword123");
				break;
		}
		$(By.xpath("//*[@id=\"customerAccountCreate\"]/section/div[2]/form/button[1]")).click();
		Thread.sleep(3000);

		int count = 0;
		while (!Other.existenceByFullXpath("//*[contains(text(),'Мой аккаунт')]")) {
			Other.refresh();
			count++;
			if (count >= 15) {
				break;
			}
		}
	}

	@Step("Auth like a custom user")
	public static void customUser(String email, String password) throws InterruptedException {
		Selenide.open("/");
		Other.findByText("*", "Войти").click();
		StartPage.setEmailForAuth(email);
		StartPage.setPasswordForAuth(password);
		$(By.xpath("//*[@id=\"customerAccountCreate\"]/section/div[2]/form/button[1]")).click();
		Thread.sleep(3000);

		int count = 0;
		while (!Other.existenceByFullXpath("//*[contains(text(),'Мой аккаунт')]")) {
			Other.refresh();
			sleep(1000);
			count++;
			if (count >= 15) {
				break;
			}
		}
	}

	@Step("Logout")
	public static void logout() throws InterruptedException {
		Thread.sleep(1500);
		Settings.myAccount().click();
		$(By.xpath("//*[contains(text(),'Выход')]")).click();
		Other.visibilityByText("*", "Войти");
	}

}
