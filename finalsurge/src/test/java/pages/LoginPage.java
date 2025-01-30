package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    final SelenideElement signUp = $x(
            "//*[@class='signup']//a[@href='register.cshtml?page_redirect=%2f']"
    );

//    @Step("Нажать на кнопку регистрации")
    public RegisterPage clickSignUp() {
        signUp.click();
        return new RegisterPage();
    }
}
