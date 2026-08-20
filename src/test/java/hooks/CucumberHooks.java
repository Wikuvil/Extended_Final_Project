package hooks;

import api.clients.ListingApi;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import context.Context;
import context.ListingContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
    private final Context context;
    private final ListingContext listingContext;

    public CucumberHooks(Context context, ListingContext listingContext) {
        this.context = context;
        this.listingContext = listingContext;
    }

    @Before
    public void setUp(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-desk.education-services.ru/";
        Selenide.open(Configuration.baseUrl);
    }

    @After(order = 1)
    public void cleanUp() {
        String token = context.getToken();
        if (token == null) return;

        String listingId = listingContext.getId();
        String listingName = listingContext.getCurrentListingName();

        if (listingId != null) {
            ListingApi.deleteListing(token, listingId);
            listingContext.setId(null);
        } else if (listingName != null) {
            String idFromSearch = ListingApi.getListingIdByName(listingName);
            if (idFromSearch != null) {
                ListingApi.deleteListing(token, idFromSearch);
            }
            listingContext.setCurrentListingName(null);
        }
    }

    @After(order = 2)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
