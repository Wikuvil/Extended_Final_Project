package context;

import lombok.Getter;
import lombok.Setter;
import pages.CreateListingPage;

@Setter
@Getter
public class ListingContext {
    private CreateListingPage createListingPage = new CreateListingPage();
    private String currentListingName;
    private String currentListingDescription;
    private int currentListingPrice;
    private String id;
}
