package context;

import lombok.Getter;
import lombok.Setter;
import pages.*;

public class Context {
    public final CreateListingPage createListingPage = new CreateListingPage();
    public final HomePage homePage = new HomePage();
    public final ListingPage listingPage = new ListingPage();
    public final LoginPage loginPage = new LoginPage();
    public final RegisterPage registerPage = new RegisterPage();

    @Getter
    @Setter
    private String token;
}
