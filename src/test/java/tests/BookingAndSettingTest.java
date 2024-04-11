package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Regression tests")
@Feature("Booking.com tests")
public class BookingAndSettingTest extends DriverSetup {

    @Severity(SeverityLevel.NORMAL)
    @Description("Test description: Booking accommodation & setting functionality test")
    @Test(testName = "Book accommodation & setting test")
    public void termsAndConditions() throws InterruptedException {

        Assert.assertTrue(cookiesPage.cookiesPageLoaded(), "Cookies page is not loaded");

        cookiesPage.clickAcceptCookiesButton();
        Assert.assertTrue(identityPage.identityPageLoaded(), "Identity page is not loaded");

        identityPage.clickCloseButton();
        Assert.assertTrue(staysPage.staysPageLoaded(), "Stays page is not loaded");

        staysPage.clickDestinationField();
        Assert.assertTrue(destinationPage.destinationPageLoaded(), "Destination page is not loaded");

        destinationPage.fillDestinationField("Skopje");

        staysPage.chooseDateInCalendar("24 April 2024", "28 April 2024");

        staysPage.selectGuestCount(2, 3);

        staysPage.clickSearchButton();

        Assert.assertTrue(staysResultPage.bookingHeaderLoaded(), "Stays result page is not loaded");
        Assert.assertEquals(staysResultPage.getSearchDestination(), "Skopje", "Destination is not correct");
        Assert.assertEquals(staysResultPage.getSearchDates(), "Apr 24 - Apr 28", "Dates are not correct");

        String savedPropertyName = staysResultPage.saveFirstProperty();
        staysResultPage.clickBackButton();

        Assert.assertTrue(staysPage.staysPageLoaded(), "Stays page is not loaded");

        staysPage.clickSavedButton();

        Assert.assertTrue(savedPage.bookingHeaderLoaded(), "Saved Items page is not loaded");
        Assert.assertTrue(savedPage.searchPropertyByName(savedPropertyName));

        savedPage.goToSearchView();

        Assert.assertTrue(staysPage.staysPageLoaded(), "Stays page is not loaded");

        staysPage.clickSignInButton();
        Assert.assertTrue(signInPage.signInPageLoaded(), "Sign in page is not loaded");
        Assert.assertTrue(signInPage.signInButtonLoaded(), "User is logged in, Sign in button is not present");

        signInPage.clickSettings();
        Assert.assertTrue(settingsPage.settingsPageLoaded(), "Settings in page is not loaded");

        settingsPage.clickCurrencySetting();
        Assert.assertTrue(currencySettingPage.allCurrenciesTitleLoaded(), "Currency setting page is not loaded");

        currencySettingPage.selectCurrency("Euro");
        Assert.assertTrue(settingsPage.settingsPageLoaded(), "Settings in page is not loaded");
        Assert.assertTrue(settingsPage.checkCurrencySettingValue("Euro"));

        settingsPage.clickPrivacyPolicyButton();
        Assert.assertTrue(privacyPolicyPage.privacyPolicyPageLoaded(), "Privacy and Cookies policy page is not loaded.");


        Thread.sleep(4000);
    }
}
