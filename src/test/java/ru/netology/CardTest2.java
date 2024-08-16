package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardTest2 {

    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");
    }

    @Test
    void fieldValidation() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("nik");
        form.$("[data-test-id=phone] input").setValue("+79881234567");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyLastNameField() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79881234567");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void fieldValidationPhone() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Николай");
        form.$("[data-test-id=phone] input").setValue("123456789");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void emptyPhoneField() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Николай");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        $(".button_view_extra").click();
        form.$("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void wellCheckTheBox() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Николай");
        form.$("[data-test-id=phone] input").setValue("+79881234567");
        $(".button_view_extra").click();
        form.$("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
