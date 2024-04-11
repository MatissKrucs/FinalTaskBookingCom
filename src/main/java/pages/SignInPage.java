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
import util.Helpers;

public class SignInPage extends Helpers{
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/facet_profile_header_sign_in_warning")
    private RemoteWebElement signInWarningTextView;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Genius loyalty program']")
    private RemoteWebElement GeniusLoyaltyButton;
    @AndroidFindBy(accessibility = "Search")
    private RemoteWebElement searchButton;
    @AndroidFindBy(id = "com.booking:id/facet_profile_header_sign_in_cta")
    private RemoteWebElement signInButton;



    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Sign in page is loaded")
    public boolean signInPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(signInWarningTextView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Sign in button is displayed")
    public boolean signInButtonLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }

    @Step("Click settings button")
    public void clickSettings() {
        String xpath = "//android.widget.TextView[@text='Settings']";
        scrollToXpath(driver, xpath, Helpers.Directions.UP, 6);
        driver.findElement(AppiumBy.xpath(xpath)).click();
    }

    @Step("Click Genius loyalty button")
    public void clickGeniusLoyaltyButton() {
        GeniusLoyaltyButton.click();
    }

    @Step("Click search button")
    public void clickSearchButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(searchButton)).click();
    }


}
