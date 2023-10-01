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
    void shouldTestIfFormCorrectly() {
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

        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + futureDate));
    }
    @org.junit.jupiter.api.Test
    void shouldTestIfFormWithNameOnEnglish() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Ни");
        $$(".menu-item").find(Condition.exactText("Нижний Новгород")).click();

        String futureDate = planingDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(futureDate);

        $("[data-test-id=name] input").setValue("Turskov Sergey");
        $("[data-test-id=phone] input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();

        $("[data-test-id=name] .input__sub")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }
    @org.junit.jupiter.api.Test
    void shouldTestIfFormIfCityIncorrect() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Выкса");

        String futureDate = planingDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(futureDate);

        $("[data-test-id=name] input").setValue("Турсков Сергей");
        $("[data-test-id=phone] input").setValue("+79200000000");

        $("[data-test-id=agreement]").click();
        $("button.button").click();

        $("[data-test-id=city] .input__sub")
                .shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }
    @org.junit.jupiter.api.Test
    void shouldTestIfFormIfDAteIncorrect() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Ни");
        $$(".menu-item").find(Condition.exactText("Нижний Новгород")).click();

        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue("04.10.2022");

        $("[data-test-id=name] input").setValue("Турсков Сергей");
        $("[data-test-id=phone] input").setValue("+79200000000");

        $("[data-test-id=agreement]").click();
        $("button.button").click();

        $("[data-test-id=date] .input__sub")
                .shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }
    @org.junit.jupiter.api.Test
    void shouldTestIfFormIfPhoneIncorrect() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Ни");
        $$(".menu-item").find(Condition.exactText("Нижний Новгород")).click();

        String futureDate = planingDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(futureDate);

        $("[data-test-id=name] input").setValue("Турсков Сергей");
        $("[data-test-id=phone] input").setValue("+792000000000");

        $("[data-test-id=agreement]").click();
        $("button.button").click();

        $("[data-test-id=phone] .input__sub")
                .shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                        "например, +79012345678."));
    }
}
