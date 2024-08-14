package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardTest2 {


    @Test
    void fieldValidation(){
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("nik");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void fieldValidationPhone(){
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Николай");
        form.$("[data-test-id=phone] input").setValue("123456789");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
