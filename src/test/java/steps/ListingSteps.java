package steps;

import api.clients.ListingApi;
import api.models.ListingData;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import context.Context;
import context.ListingContext;
import generators.ListingGenerator;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class ListingSteps {

    private final Context context;
    private final ListingContext listingContext;

    @Допустим("Уже существует объявление")
    public void listingAlreadyCreated(){
        String listingName = ListingGenerator.randomName();
        String listingDescription = ListingGenerator.randomDescription();
        int listingPrice = ListingGenerator.randomPrice();
        String category = "Авто";
        String condition = "Новый";
        String city = "Москва";
        listingContext.setCurrentListingName(listingName);
        String token = context.getToken();
        ValidatableResponse response = ListingApi.createListingReturnValidatableResponse(new ListingData(listingName, category, condition, city, listingDescription, String.valueOf(listingPrice)), token);
        listingContext.setId(response.extract().path("id").toString());
        Selenide.refresh();
    }

    @Когда("Пользователь редактирует уже существующее объявление")
    public void editListing(){
        String listingName = listingContext.getCurrentListingName();

        String expectedListingName = ListingGenerator.randomName();
        String expectedListingDescription = ListingGenerator.randomDescription();
        int expectedListingPrice = ListingGenerator.randomPrice();

        findAndOpenListingByName(listingName);
        context.listingPage.clickEditListingButton();
        context.listingPage.setEditName(expectedListingName);
        context.listingPage.setEditDescription(expectedListingDescription);
        context.listingPage.setEditPrice(String.valueOf(expectedListingPrice));
        context.listingPage.clickSubmitEditListingButton();

        Selenide.open("/");
        listingContext.setCurrentListingName(expectedListingName);
        listingContext.setCurrentListingDescription(expectedListingDescription);
        listingContext.setCurrentListingPrice(expectedListingPrice);
    }

    @Когда("Пользователь удаляет объявление")
    public void deleteListing(){
        String listingName = listingContext.getCurrentListingName();

        findAndOpenListingByName(listingName);
        context.listingPage.clickDeleteListingButton();

        Selenide.open("/");
    }

    @Тогда("Изменения успешно применились")
    public void successfulEdit(){
        String expectedListingName = listingContext.getCurrentListingName();
        String expectedListingDescription = listingContext.getCurrentListingDescription();
        int expectedListingPrice = listingContext.getCurrentListingPrice();
        findAndOpenListingByName(expectedListingName);

        assertEquals(expectedListingName, context.listingPage.getName(), "Названия не совпадают");
        assertEquals(expectedListingDescription, context.listingPage.getDescription(),"Описание не совпадает");
        assertEquals(expectedListingPrice, context.listingPage.getPrice(), "Цена не совпадает");
    }

    @Тогда("Объявление отсутствует в списке")
    public void successfulDelete(){
        String listingName = listingContext.getCurrentListingName();
        context.homePage.setSearch(listingName);
        context.homePage.enterSearchField();
        context.homePage.getListingByName(listingName).shouldNotBe(Condition.exist);

    }

    private void findAndOpenListingByName(String listingName){
        context.homePage.setSearch(listingName);
        context.homePage.enterSearchField();
        context.homePage.openListing(listingName);
    }

}
