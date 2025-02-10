package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.browser;

public class RegistrationTest extends BasicClassTest {

    @DataProvider(name = "registrationData")
    public Object[][] loginData() {
        return new Object[][]
                {
                        {"", "Test", "test@gmail.com", "TestTest123@", "TestTest123@"},
                        {"Test", "", "test@gmail.com", "TestTest123@", "TestTest123@"},
                        {"Test", "Test", "test@ gmail.com", "TestTest123@", "TestTest123@"},
                        {"Test", "Test", "test@gmail.com", "Test", "Test"},
                        {"Test", "Test", "test@gmail.com", "TestTest123@", "Test"},
                        {"Test", "Test", "test@gmail.com", "TestTest123@", ""},
                        {"Test", "Test", "test@gmail.com", "", "TestTest123@"},
                        {"", "", "", "", ""}
                };
    }

    @Test(
            testName = "Регистрация с пустым именем",
            alwaysRun = true,
            priority = 1,
            timeOut = 50000,
            description = "Проверка регистрации с корректными данными, но пустым полем для имени",
            dataProvider = "registrationData"
    )
    @Severity(SeverityLevel.NORMAL)
    @Feature("Registration")
    @Story("Регистрация с корректными данными, но пустым полем для имени")
    public void incorrectFirstName(
           final String name,
           final String lastName,
           final String email,
           final String password,
           final String repeatPassword
            ) {
        setUp(browser);
        loginPage.clickSignUp()
                .checkRegisterPage();
        registerPage.checkRegistration(
                name,
                lastName,
                email,
                password,
                repeatPassword
        );
        if (name.isEmpty()) {
            registerPage.checkFirstName();
        }
        else if (lastName.isEmpty()) {
            registerPage.checkLastName();
        }
        else  if (email.equals("test@ gmail.com")) {
            registerPage.checkEmail();
        }
        else if (password.equals("Test") & repeatPassword.equals("Test")) {
            registerPage.lengthPassword();
        }
        else if (!password.equals(repeatPassword)) {
            registerPage.checkPasswords();
        }
        else if (repeatPassword.isEmpty()) {
            registerPage.checkRepeatPassword();
        }
        else if (password.isEmpty()) {
            registerPage.checkNoPassword();
        }
        else registerPage.checkFirstName()
                .checkLastName()
                .checkEmail()
                .checkNoPassword()
                .checkRepeatPassword();
    }
}
