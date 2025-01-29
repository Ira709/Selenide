import com.codeborne.selenide.*;
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
import java.util.List;

public class WorkshopTwoTest {

    @Test
    void testSelenideCollection() {

        Selenide.open("https://demoblaze.com");

        ElementsCollection cards = Selenide.elements(By.className("card"));
        cards.should(CollectionCondition.sizeGreaterThanOrEqual(6));

        int size = cards.size();
        System.out.println("Всего найдено карточек товаров: " + size);

        List<String> allCardsText = cards.texts();
        System.out.println("Текст всех карточек товаров (много текста): " + allCardsText);

        SelenideElement firstCard = cards.first();

        firstCard.should(Condition.text("Samsung galaxy S6"));

        SelenideElement SamsungGalaxyS7Card = cards.find(Condition.text("Samsung galaxy S7"));

        SamsungGalaxyS7Card.should(Condition.text("1.60Hz octa-core"));

        System.out.println("Содержание карточки Samsung galaxy S6: " + SamsungGalaxyS7Card.text());

        ElementsCollection sonyCrads = cards.filter(Condition.text("Sony"));

        sonyCrads.should(CollectionCondition.texts("$320", "$790", "$790"));

        SelenideElement iphone = cards
                .filter(Condition.text("Iphone"))
                .should(CollectionCondition.size(1))
                .get(0);

        iphone.click();
        iphone.should(Condition.disappear);
    }
}
