package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.RegisterPage;

import static com.codeborne.selenide.Selenide.open;

@Listeners(TestListener.class)
public class BasicClassTest {
    LoginPage loginPage;
    RegisterPage registerPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
        } else if (browser.equalsIgnoreCase("edge")) {
            Configuration.browser = "edge";
        }
        open("https://log.finalsurge.com/login.cshtml?Assoc=&page_redirect=/");
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }
}
