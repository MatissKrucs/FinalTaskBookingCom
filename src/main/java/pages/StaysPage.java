package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class StaysPage {
    protected AndroidDriver driver;

    @AndroidFindBy(accessibility = "Accommodation search box")
    private RemoteWebElement accommodationSearchBoxView;
    @AndroidFindBy(accessibility = "Sign in")
    private RemoteWebElement signInButton;
    @AndroidFindBy(accessibility = "Enter your destination")
    private RemoteWebElement destinationField;
    @AndroidFindBy(id = "com.booking:id/facet_date_picker_confirm")
    private RemoteWebElement confirmDatesButton;
    @AndroidFindBy(accessibility = "1 room, 2 adults, 0 children")
    private RemoteWebElement guestCountSelection;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.booking:id/group_config_rooms_count']" +
            "/android.widget.LinearLayout/android.widget.Button[@content-desc='Increase']")
    private RemoteWebElement roomCountIncrease;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.booking:id/group_config_adults_count']" +
            "/android.widget.LinearLayout/android.widget.Button[@content-desc='Increase']")
    private RemoteWebElement adultCountIncrease;
    @AndroidFindBy(id = "com.booking:id/group_config_apply_button")
    private RemoteWebElement guestCountApply;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Accommodation search box']" +
            "/android.view.View/android.widget.TextView[@text='Search']")
    private RemoteWebElement searchButton;
    @AndroidFindBy(accessibility = "Saved")
    private RemoteWebElement savedButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Car rental')]")
    private RemoteWebElement carRentalButton;

    public StaysPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Stays page is loaded")
    public boolean staysPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(accommodationSearchBoxView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click destination field")
    public void clickDestinationField() {
        destinationField.click();
    }

    @Step("Choose date in calendar. Start date: {0}, end date: {1}")
    public void chooseDateInCalendar(String startDate, String endDate) {
        WebElement elementStart = driver.findElement(AppiumBy.accessibilityId(startDate));
        elementStart.click();
        WebElement elementEnd = driver.findElement(AppiumBy.accessibilityId(endDate));
        elementEnd.click();
        confirmDatesButton.click();
    }

    @Step("Select guest rooms: {0}, adults guest count: {1}")
    public void selectGuestCount(int rooms, int adults) {
        guestCountSelection.click();

        for (int i = 1; i < rooms; i++) {
            roomCountIncrease.click();
        }

        for (int i = rooms; i < adults; i++) {
            adultCountIncrease.click();
        }

        guestCountApply.click();
    }

    @Step("Click search button")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Click saved button")
    public void clickSavedButton() {
        savedButton.click();
    }

    @Step("Click SignIn button")
    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("Click car rental button")
    public void clickCarRentalButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(carRentalButton)).click();
    }


}
