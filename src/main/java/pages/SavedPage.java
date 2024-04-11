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

public class SavedPage {


    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='My next trip'])[2]")
    private RemoteWebElement myNextTripText;

    @AndroidFindBy(xpath = "((//android.widget.ImageView[@content-desc='Save property to list'])[1]/preceding-sibling::android.widget.TextView)[1]")
    private RemoteWebElement firstPropertyName;

    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement backButton;

    @AndroidFindBy(accessibility = "Search")
    private RemoteWebElement searchButton;

    public SavedPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @Step("Saved Items page is loaded")
    public boolean bookingHeaderLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(myNextTripText)).isDisplayed();
    }

    @Step("Search property by name: {0}")
    public boolean searchPropertyByName(String name) {
        try {
            return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + name + "']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Go to search view")
    public void goToSearchView(){
        backButton.click();
        searchButton.click();
    }
}
