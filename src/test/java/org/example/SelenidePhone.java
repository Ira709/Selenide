package org.example;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class SelenidePhone {
     @Test
     void SelenideTest() {
         Selenide.open("https://demoblaze.com");

         //Добавляем товары в корзину

         Selenide.element(Selectors.byTagAndText("a", "Nokia lumia 1520")).click();
         Selenide.element(Selectors.byText("Add to cart")).click();

         Selenide.confirm();

         Selenide.element(Selectors.byText("Home")).click();

         Selenide.element(Selectors.byTagAndText("a", "Iphone 6 32gb")).click();
         Selenide.element(Selectors.byText("Add to cart")).click();

         Selenide.confirm();

         Selenide.element(Selectors.byText("Home")).click();

         Selenide.element(Selectors.byTagAndText("a", "Sony vaio i7")).click();
         Selenide.element(Selectors.byText("Add to cart")).click();

         Selenide.confirm();

         Selenide.element(Selectors.byText("Home")).click();

         //Проверяем совпадает ли сумма товаров в корзине

         Selenide.element(Selectors.byText("Cart")).click();
         Selenide.sleep(3000);
         ElementsCollection prices = Selenide.elements("td:nth-child(3)");

         int totalSum = prices.stream()
                 .mapToInt(price -> Integer.parseInt(price.getText()))
                 .sum();

         SelenideElement totalElement = Selenide.element("#totalp");
         int displayedTotal = Integer.parseInt(totalElement.getText());

         Assertions.assertEquals(totalSum, displayedTotal, "Итоговая сумма в корзине неверная!");

         //Оформляем заказ

         Selenide.element(Selectors.byText("Place Order")).click();

         Selenide.element(By.id("name")).setValue("Ivan");
         Selenide.element(By.id("country")).setValue("Russia");
         Selenide.element(By.id("city")).setValue("Moscow");
         Selenide.element(By.id("card")).setValue("1234567890123456");
         Selenide.element(By.id("month")).setValue("March");
         Selenide.element(By.id("year")).setValue("2025");

         Selenide.element(Selectors.byText("Purchase")).click();

         //Проверяем подтверждение заказа
         SelenideElement confirmationPopup = Selenide.element(".sweet-alert");
         confirmationPopup.shouldBe(Condition.visible);

         String confirmationText = confirmationPopup.getText();
         System.out.println("Текст подтверждения: " + confirmationText);

         String amountText = confirmationText.split("\n")[1]; 
         int amount = Integer.parseInt(amountText.replaceAll("[^0-9]", ""));
         System.out.println("Сумма заказа: " + amount);

         String orderIdText = confirmationText.split("\n")[0]; 
         String orderId = orderIdText.replaceAll("[^0-9]", "");
         System.out.println("ID заказа: " + orderId);

         displayedTotal = Integer.parseInt(Selenide.element("#totalp").getText());

         Selenide.element(Selectors.byText("OK")).click();

         confirmationPopup.shouldNotBe(Condition.visible);

     }
}
