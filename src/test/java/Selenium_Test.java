import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_Test {

    @Test
    void testSelenium() {
        WebDriver webDriver = new ChromeDriver();
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriver.get("https://demoblaze.com");

        WebElement loginMenuLink = webDriver.findElement(By.id("login2"));
        wait.until(ExpectedConditions.elementToBeClickable(loginMenuLink));
        loginMenuLink.click();

        WebElement usernameInput = webDriver.findElement(By.id("loginusername"));
        WebElement passwordInput = webDriver.findElement(By.id("loginpassword"));
        WebElement loginButton = webDriver.findElement(By.className("#btn btn-primary"));

        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys("test");
        passwordInput.sendKeys("test");

        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(usernameInput));

        WebElement welcomeLink = webDriver.findElement(By.id("nameofuser"));
        wait.until(ExpectedConditions.visibilityOf(welcomeLink));
        wait.until(ExpectedConditions.textToBePresentInElement(welcomeLink, "Welcome test"));

        webDriver.close();

    }

    @Test
    void testSelenide() {
        Selenide.open("https://demoblaze.com");

        SelenideElement loginMenuLink = Selenide.element(By.id("login2"));
        loginMenuLink.click();

        SelenideElement usernameInput = Selenide.element(By.id("loginusername"));
        SelenideElement passwordInput = Selenide.element(By.id("loginpassword"));
        SelenideElement loginButton = Selenide.element(Selectors.byTagAndText("button", "Log in"));

        usernameInput.setValue("test");
        passwordInput.setValue("test");

        loginButton.click();
        loginButton.should(Condition.disappear);

        Selenide.element(By.id("nameofuser"))
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Welcome test"));
    }

}
