package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreateListingPage {
    private final SelenideElement nameField = $x("//input[@name='name']");
    private final SelenideElement descriptionField = $x("//textarea[@name='description']");
    private final SelenideElement priceField = $x("//input[@name='price']");
    private final SelenideElement publishButton = $x("//button[normalize-space()='Опубликовать']");

    public void clickPublishButton(){
        publishButton.click();
    }

    public void setName(String name){
        nameField.setValue(name);
    }

    public void setDescription(String description){
        descriptionField.setValue(description);
    }

    public void setPrice(int price){
        priceField.setValue(String.valueOf(price));
    }
}
