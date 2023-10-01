package ru.netology.selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$$;
import static java.nio.channels.Selector.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeliveryCardTest {

    private WebDriver driver;

    //@BeforeAll
    // static void setUpAll() {
        // WebDriverManager.chromedriver().setup():
    // }

    @Test
    void shouldRegisteredByAccountNumberModification() {
        open("");
        $$(".tab-item").last().click();
        $()
    }
}
