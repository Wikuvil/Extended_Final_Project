package steps;

import context.Context;
import context.ListingContext;
import generators.ListingGenerator;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class CreateListingSteps {

    private final Context context;
    private final ListingContext listingContext;

    @Допустим("Пользователь открыл форму размещения объявления")
    public void openAuthPage(){
        context.homePage.clickCreateListingButton();
    }

    @Когда("Пользователь опубликовал объявления, заполнив название, описание и стоимость")
    public void publishListing(){
        String listingName = ListingGenerator.randomName();
        String listingDescription = ListingGenerator.randomDescription();
        int listingPrice = ListingGenerator.randomPrice();

        context.createListingPage.setName(listingName);
        context.createListingPage.setDescription(listingDescription);
        context.createListingPage.setPrice(listingPrice);
        context.createListingPage.clickPublishButton();

        listingContext.setCurrentListingName(listingName);
        listingContext.setCurrentListingDescription(listingDescription);
        listingContext.setCurrentListingPrice(listingPrice);
    }

    @Тогда("Объявление корректно заполнено и находится в списке")
    public void searchListing(){
        String expectedListingName = listingContext.getCurrentListingName();
        String expectedListingDescription = listingContext.getCurrentListingDescription();
        int expectedListingPrice = listingContext.getCurrentListingPrice();
        context.homePage.setSearch(expectedListingName);
        context.homePage.enterSearchField();
        context.homePage.openListing(expectedListingName);
        assertEquals(expectedListingName, context.listingPage.getName(), "Названия не совпадают");
        assertEquals(expectedListingDescription, context.listingPage.getDescription(),"Описание не совпадает");
        assertEquals(expectedListingPrice, context.listingPage.getPrice(), "Цена не совпадает");
    }
}
