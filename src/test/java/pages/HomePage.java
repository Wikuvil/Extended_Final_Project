package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement authButton = $x("//button[normalize-space()='Вход и регистрация']");
    @Getter
    private final SelenideElement logoutButton = $x("//button[normalize-space()='Выйти']");
    private final SelenideElement searchField = $x("//input[@name='name']");
    private final SelenideElement createListingButton = $x("//button[normalize-space()='Разместить объявление']");

    public void clickAuthButton(){
        authButton.click();
    }

    public void clickCreateListingButton(){
        createListingButton.click();
    }

    public void setSearch(String name){
        searchField.setValue(name);
    }

    public void enterSearchField(){
        searchField.pressEnter();
    }

    public SelenideElement getListingByName(String name) {
        return $x("//h2[normalize-space()='" + name + "']");
    }

    public void openListing(String name){
        getListingByName(name).click();
    }
}
