package steps;

import api.clients.UserApi;
import api.models.CreateUserResponse;
import api.models.UserCreds;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import context.Context;
import generators.UserCredsGenerator;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import lombok.RequiredArgsConstructor;
import utils.Utils;

@RequiredArgsConstructor
public class LoginSteps {

    private final Context context;

    @Допустим("Пользователь открыл форму авторизации")
    public void openAuthPage(){
        context.homePage.clickAuthButton();
    }

    @Допустим("Пользователь авторизован")
    public void authorizedAccount(){
        String email = UserCredsGenerator.randomSafeEmail();
        String password = UserCredsGenerator.randomPassword();
        CreateUserResponse response = UserApi.createUserReturnResponse(new UserCreds(email, password, password));
        String token = response.getAccessToken().getAccessToken();
        context.setToken(token);
        int id = response.getUser().getId();


        Selenide.localStorage().setItem("token", token);
        Selenide.localStorage().setItem("islogin", "true");
        Selenide.localStorage().setItem("user", Utils.generateUserJson(id, email));
        Selenide.refresh();
    }

    @Когда("Пользователь попытался авторизоваться")
    public void registerDuplicateUser(){
        String email = UserCredsGenerator.randomSafeEmail();
        String password = UserCredsGenerator.randomPassword();
        UserApi.createUser(new UserCreds(email, password, password));

        context.loginPage.setEmail(email);
        context.loginPage.setPassword(password);
        context.loginPage.clickLoginButton();
    }

    @Тогда("Пользователь успешно авторизовался в системе")
    public void registrationSuccess() {
        context.homePage.getLogoutButton().shouldBe(Condition.visible);
    }
}
