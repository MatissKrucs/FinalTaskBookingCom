package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.checkerframework.checker.mustcall.qual.CreatesMustCallFor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;
import util.Helpers;

public class GeniusInfoPage extends Helpers {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/genius_info_page_facet_container")
    private RemoteWebElement geniusInfoPageContainer;
    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement backButton;





    public GeniusInfoPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Genius info page is loaded")
    public boolean geniusInfoPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(geniusInfoPageContainer)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Scroll and click Genius button")
    public void scrollAndClickGeniusButton() {
        String xpath = "//android.widget.TextView[@text='About Genius Levels']";
        scrollToXpath(driver, xpath, Directions.UP, 5);
        driver.findElement(AppiumBy.xpath(xpath)).click();
    }

    @Step("Click back button")
    public void clickBackButton() {
        backButton.click();
    }











}
