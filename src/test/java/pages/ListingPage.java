package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ListingPage {
    private final SelenideElement nameField = $x("//div[contains(@class, 'description_shell')]//h1");
    private final SelenideElement descriptionField = $x("//div[contains(@class, 'description_shell')]//p");
    private final SelenideElement priceField = $x("//div[contains(@class, 'listing_adressPrice')]//h1");
    private final SelenideElement editListingButton = $x("//button[normalize-space()='Редактировать объявление']");
    private final SelenideElement submitEditListingButton = $x("//button[normalize-space()='Сохранить изменения']");
    private final SelenideElement deleteListingButton = $x("//button[normalize-space()='Удалить']");
    private final SelenideElement editNameField = $x("//input[@name='name']");
    private final SelenideElement editDescriptionField = $("textarea[name='description']");
    private final SelenideElement editPriceField = $("[name='price']");

    public String getName(){
        return nameField.getText();
    }

    public String getDescription(){
        return descriptionField.getText();
    }

    public int getPrice(){
        String price = priceField.getText();
        return Integer.parseInt(price.replaceAll("\\D+", ""));
    }

    public void clickEditListingButton(){
        editListingButton.click();
    }

    public void clickSubmitEditListingButton(){
        submitEditListingButton.click();
    }

    public void clickDeleteListingButton(){
        deleteListingButton.click();
    }

    public void setEditName(String editedName){
        editNameField.setValue(editedName);
    }

    public void setEditDescription(String editedDescription){
        editDescriptionField.setValue(editedDescription);
    }

    public void setEditPrice(String editedPrice){
        editPriceField.setValue(editedPrice);
    }
}
