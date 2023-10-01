package ru.netology.selenide;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
    @org.junit.jupiter.api.Test
    public void fillFormFields() {
        open("http://localhost:9999/");
        $("[data-test-id=\"city\"] input").setValue("Нижний Новгород");
        $("[data-test-id=\"date\"] input").setValue("04.10.2023");
        $("[data-test-id=\"name\"] input").setValue("Турсков Сергей");
        $("[data-test-id=\"phone\"] input").setValue("+79200000000");
        $("[data-test-id=\"agreement\"]").click();
        $("button.button").click();
        $("[data-test-id=\"notification\"]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на 04.10.2023"));
    }
}
