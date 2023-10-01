package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    public String planingDate(int choiceDays, String pattern) {
        return LocalDate.now().plusDays(choiceDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @org.junit.jupiter.api.Test
    void shouldTestIfSuccess() {
        open("http://localhost:9999");
        
        $("[data-test-id=city] input").setValue("Ни");

        $$(".menu-item").find(Condition.exactText("Нижний Новгород")).click();

        String futureDate = planingDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(futureDate);

        $("[data-test-id=name] input").setValue("Турсков Сергей");
        $("[data-test-id=phone] input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();

        // Проверяем уведомление
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + futureDate));
    }
}
