package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    private final SelenideElement registerButton = $x("//button[normalize-space()='Создать аккаунт']");
    @Getter
    private final SelenideElement registerError = $x("//span[normalize-space()='Ошибка']");
    private final SelenideElement emailField = $("[name='email']");
    private final SelenideElement passwordField = $("[name='password']");
    private final SelenideElement submitPasswordField = $("[name='submitPassword']");

    public void setEmail(String email){
        emailField.setValue(email);
    }

    public void setPassword(String password){
        passwordField.setValue(password);
    }

    public void setSubmitPassword(String password){
        submitPasswordField.setValue(password);
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

}


