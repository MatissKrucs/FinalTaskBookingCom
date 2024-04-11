package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
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
import util.Helpers;

import java.time.Duration;

public class CarRentalPage extends Helpers {
    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Car rental search box']")
    private RemoteWebElement carRentalSearchBox;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'you will return the car to')]")
    private RemoteWebElement returnToLocationSwitch;
    @AndroidFindBy(accessibility = "Enter a pick-up location")
    private RemoteWebElement pickUpLocationField;
    @AndroidFindBy(accessibility = "Enter a drop-off location")
    private RemoteWebElement dropOffLocationField;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'Pick-up date:')]")
    private RemoteWebElement dateFields;
    @AndroidFindBy(id = "com.booking:id/calendar_month_list")
    private RemoteWebElement calendarMonthListView;
    @AndroidFindBy(id = "com.booking:id/calendar_confirm")
    private RemoteWebElement selectDatesButton;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'Pick-up time:')]")
    private RemoteWebElement startTimeButton;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'Drop-off time:')]")
    private RemoteWebElement endTimeButton;
    @AndroidFindBy(xpath = "//android.widget.RadioButton")
    private RemoteWebElement timeRadioButton;
    @AndroidFindBy(accessibility = "Enter the driver's age")
    private RemoteWebElement driversAgeField;
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Search'])/following-sibling::android.widget.Button")
    private RemoteWebElement searchButton;


    public CarRentalPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Car rental page is loaded")
    public boolean carRentalPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(carRentalSearchBox)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click return to location switch")
    public void clickReturnToLocationSwitch() {
        returnToLocationSwitch.click();
    }

    @Step("Pick up location field is displayed")
    public boolean pickUpLocationFieldDisplayed() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(pickUpLocationField)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Drop off location field is displayed")
    public boolean dropOffLocationFieldDisplayed() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(dropOffLocationField)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click pick up location field")
    public void clickPickUpLocationField() {
        pickUpLocationField.click();
    }

    @Step("Click drop off location field")
    public void clickDropOffLocationField() {
        dropOffLocationField.click();
    }

    @Step("Choose date in calendar. Start date: {0}, end date {1}")
    public void chooseDateInCalendar(String startDate, String endDate) {
        dateFields.click();

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(calendarMonthListView)).isDisplayed();

        new WebDriverWait(driver, GlobalVariables.globalTimeout).
                until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.accessibilityId(startDate)))).click();
        WebElement elementEnd = driver.findElement(AppiumBy.accessibilityId(endDate));
        elementEnd.click();

        selectDatesButton.click();
    }

    @Step("Click start time button")
    public void clickStartTimeButton() {

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(startTimeButton)).click();

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(timeRadioButton)).isDisplayed();
    }

    @Step("Click end time button")
    public void clickEndTimeButton() {
        endTimeButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(timeRadioButton)).isDisplayed();
    }

    @Step("Select time: {0}:{1} {2}")
    public void selectTime(String hour, String minutes, String daySegment) {
        String xpath = "//android.widget.TextView[@text='" + hour + ":" + minutes + " " + daySegment + "']";
        try {
            scrollToXpath(driver, xpath, Directions.UP, 10);
        } catch (NoSuchElementException e) {
            scrollToXpath(driver, xpath, Directions.DOWN, 15);
        }
        driver.findElement(AppiumBy.xpath(xpath)).click();
    }

    @Step("Fill drivers age field")
    public void fillDriversAgeField() throws InterruptedException {

        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(driversAgeField)).click();

        Thread.sleep(1000);

        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        Thread.sleep(1000);

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Step("Click search button")
    public void clickSearchButton() {
        searchButton.click();
    }
}
