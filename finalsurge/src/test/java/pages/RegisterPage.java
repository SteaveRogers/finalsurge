package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPage {
    final SelenideElement createAccount = $x(
            "//p[@class='heading_main formpad' and contains(text(), 'Create a New Account for Free')]"
    );
    final SelenideElement firstName = $x(
            "//div[@class='formpad']//input[@name='create_first']"
    );
    final SelenideElement lastName = $x(
            "//div[@class='formpad']//input[@name='create_last']"
    );
    final SelenideElement email = $x(
            "//div[@class='formpad']//input[@name='create_email']"
    );
    final SelenideElement password = $x(
            "//div[@class='formpad']//input[@name='create_password']"
    );
    final SelenideElement retypePassword = $x(
            "//div[@class='formpad']//input[@name='create_passwordmatch']"
    );
    final SelenideElement create = $x("//div[@class='submit_sect']//button");
    final SelenideElement noName = $x(
            "//label[@for='create_first' and contains(text(), 'This field is required.')]"
    );
    final SelenideElement noLastName = $x(
            "//label[@for='create_last' and contains(text(), 'This field is required.')]"
    );
    final SelenideElement incorrectEmail = $x(
            "//label[@for='create_email' and contains(text(), 'Please enter a valid email address.')]"
    );
    final SelenideElement shortPassword = $x(
            "//strong[@style='font-weight: bold;' and contains(text(), 'Error:')]");
    final SelenideElement differentPasswords = $x(
            "//div[@class='alert alert-error']//strong[text()='Error:']\n" +
                    "/following-sibling::text()[contains(., 'The passwords you entered did not match.')]"
    );
    final SelenideElement repeatPassword = $x(
            "//label[@for='create_passwordmatch' and contains(text(), 'This field is required.')]"
    );
    final SelenideElement noPassword = $x(
            "//label[@for='password_meter' and contains(text(), 'This field is required.')]"
    );

    @Step("Открытие страницы формы регистрации")
    public void openRegisterPage() {
        open("https://log.finalsurge.com/register.cshtml");
    }

    @Step("Заполнение формы регистрации различными данными")
    public RegisterPage checkRegistration(
           final String name,
           final String surname,
           final String mail,
           final String parole,
           final String repeatParole
    ) {
        firstName.sendKeys(name);
        lastName.sendKeys(surname);
        email.sendKeys(mail);
        password.sendKeys(parole);
        retypePassword.sendKeys(repeatParole);
        create.click();
        return this;
    }

    @Step("Проверка перехода на страницу регистрации")
    public void checkRegisterPage() {
        createAccount.shouldHave(text("Create a New Account for Free")).shouldBe(visible);
    }

    @Step("Проверка валидации с пустым полем имени")
    public RegisterPage checkFirstName() {
        noName.shouldHave(text("This field is required.")).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации с пустым полем фамилии")
    public RegisterPage checkLastName() {
        noLastName.shouldHave(text("This field is required.")).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации с неверным форматом почты")
    public RegisterPage checkEmail() {
        incorrectEmail.shouldHave(text("Please enter a valid email address.")).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации с коротким паролем")
    public RegisterPage lengthPassword() {
        shortPassword.shouldHave(text("Error:")).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации совпадения паролей")
    public RegisterPage checkPasswords() {
        differentPasswords.shouldHave(
                text("The passwords you entered did not match.")
        ).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации с пустым полем повторения пароля")
    public RegisterPage checkRepeatPassword() {
        repeatPassword.shouldHave(
                text("The passwords you entered did not match.")
        ).shouldBe(visible);
        return this;
    }

    @Step("Проверка валидации с пустым полем пароля")
    public RegisterPage checkNoPassword() {
        noPassword.shouldHave(
                text("The passwords you entered did not match.")
        ).shouldBe(visible);
        return this;
    }
}
