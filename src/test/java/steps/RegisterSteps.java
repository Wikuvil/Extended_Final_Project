package steps;

import api.clients.UserApi;
import api.models.UserCreds;
import com.codeborne.selenide.Condition;
import context.Context;
import generators.UserCredsGenerator;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterSteps {

    private final Context context;

    @Допустим("Пользователь открыл форму регистрации")
    public void navigateToRegistrationForm(){
        context.homePage.clickAuthButton();
        context.loginPage.clickRegisterButton();
    }

    @Когда("Пользователь попытался зарегистрироваться с уже существующими данными")
    public void registerDuplicateUser(){
        String email = UserCredsGenerator.randomSafeEmail();
        String password = UserCredsGenerator.randomPassword();
        UserApi.createUser(new UserCreds(email, password, password));

        context.registerPage.setEmail(email);
        context.registerPage.setPassword(password);
        context.registerPage.setSubmitPassword(password);
        context.registerPage.clickRegisterButton();
    }

    @Когда("Пользователь попытался зарегистрироваться с новыми данными")
    public void registerUniqueUser(){
        String email = UserCredsGenerator.randomSafeEmail();
        String password = UserCredsGenerator.randomPassword();

        context.registerPage.setEmail(email);
        context.registerPage.setPassword(password);
        context.registerPage.setSubmitPassword(password);
        context.registerPage.clickRegisterButton();
    }

    @Тогда("Пользователь успешно зарегистрировался в системе")
    public void registrationSuccess() {
        context.homePage.getLogoutButton().shouldBe(Condition.visible);
    }

    @Тогда("Пользователь получил ошибку")
    public void registrationFail() {
        context.registerPage.getRegisterError().shouldBe(Condition.visible);
    }
}
