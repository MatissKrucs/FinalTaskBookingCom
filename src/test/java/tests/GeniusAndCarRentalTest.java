package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Regression tests")
@Feature("Booking.com tests")
public class GeniusAndCarRentalTest extends DriverSetup {

    @Severity(SeverityLevel.NORMAL)
    @Description("Test description: Genius and car rental functionality test")
    @Test(testName = "Genius and car rental test")
    public void termsAndConditions() throws InterruptedException {

        Assert.assertTrue(cookiesPage.cookiesPageLoaded(), "Cookies page is not loaded");

        cookiesPage.clickAcceptCookiesButton();
        Assert.assertTrue(identityPage.identityPageLoaded(), "Identity page is not loaded");

        identityPage.clickCloseButton();
        Assert.assertTrue(staysPage.staysPageLoaded(), "Stays page is not loaded");

        staysPage.clickSignInButton();
        Assert.assertTrue(signInPage.signInPageLoaded(), "Sign in page is not loaded");

        signInPage.clickGeniusLoyaltyButton();
        Assert.assertTrue(geniusInfoPage.geniusInfoPageLoaded(), "Genius info page is not loaded");

        geniusInfoPage.scrollAndClickGeniusButton();

        Assert.assertTrue(geniusLevelsPage.geniusLevelsPageLoaded(), "Genius levels page is not loaded");

        geniusLevelsPage.scrollToGeniusLevelThree();
        Assert.assertTrue(geniusLevelsPage.validateGeniusLevelThree(), "Genius Levels Three text is not found");

        geniusLevelsPage.clickGotItButton();
        Assert.assertTrue(geniusInfoPage.geniusInfoPageLoaded(), "Genius info page is not loaded");

        geniusInfoPage.clickBackButton();
        Assert.assertTrue(signInPage.signInPageLoaded(), "Sign in page is not loaded");

        signInPage.clickSearchButton();
        Assert.assertTrue(staysPage.staysPageLoaded(), "Stays page is not loaded");

        staysPage.clickCarRentalButton();
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.clickReturnToLocationSwitch();

        Assert.assertTrue(carRentalPage.pickUpLocationFieldDisplayed(), "Pick Up location is not displayed");
        Assert.assertTrue(carRentalPage.dropOffLocationFieldDisplayed(), "Drop off location is not displayed");

        carRentalPage.clickPickUpLocationField();
        Assert.assertTrue(carLocationPage.carLocationPageLoaded(), "Car location page is not loaded");
        carLocationPage.enterLocation("Skopje");
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.clickDropOffLocationField();
        Assert.assertTrue(carLocationPage.carLocationPageLoaded(), "Car location page is not loaded");
        carLocationPage.enterLocation("Ohrid");
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.chooseDateInCalendar("24 April 2024", "27 April 2024");
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.clickStartTimeButton();
        carRentalPage.selectTime("8", "15", "AM");
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.clickEndTimeButton();
        carRentalPage.selectTime("11", "00", "AM");
        Assert.assertTrue(carRentalPage.carRentalPageLoaded(), "Car rental page is not loaded");

        carRentalPage.fillDriversAgeField();
        carRentalPage.clickSearchButton();

        Assert.assertTrue(carRentalResultsPage.carRentalContainerLoaded(), "Result page is not loaded");

        carRentalResultsPage.selectAutomaticTransition();
        Assert.assertTrue(carRentalResultsPage.validateFirstCarIsAutomatic(), "First displayed car is not automatic");



        Thread.sleep(4000);
    }
}
