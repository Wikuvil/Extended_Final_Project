package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement registerButton = $x("//button[normalize-space()='Нет аккаунта']");
    private final SelenideElement loginButton = $x("//button[normalize-space()='Войти']");
    private final SelenideElement emailField = $("[name='email']");
    private final SelenideElement passwordField = $("[name='password']");

    public void clickRegisterButton(){
        registerButton.click();
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void setEmail(String email){
        emailField.setValue(email);
    }

    public void setPassword(String password){
        passwordField.setValue(password);
    }

}
