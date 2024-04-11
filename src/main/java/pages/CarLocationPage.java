package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

import java.time.Duration;

public class CarLocationPage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/search_hint_text")
    private RemoteWebElement searchHintTextView;

    @AndroidFindBy(id = "com.booking:id/search_query_edittext")
    private RemoteWebElement locationSearchField;
    @AndroidFindBy(id = "com.booking:id/results_recycler")
    private RemoteWebElement locationResultsField;


    public CarLocationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Car location page is loaded")
    public boolean carLocationPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(searchHintTextView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Enter location: {0}")
    public void enterLocation(String location) {

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(locationSearchField)).sendKeys(location);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(locationResultsField)).isDisplayed();

        String locationSelector = String.format("//android.widget.TextView[@text='%s']", location);
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.xpath(locationSelector)))).click();
    }
}
