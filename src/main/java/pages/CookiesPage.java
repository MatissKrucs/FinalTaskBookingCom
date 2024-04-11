package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.qameta.allure.Step;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CookiesPage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/gdpr_consent_heading")
    private RemoteWebElement gdprConsentHeading;
    @AndroidFindBy(id = "com.booking:id/bt_accept")
    private RemoteWebElement acceptCookiesButton;

    public CookiesPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Cookies page is loaded")
    public boolean cookiesPageLoaded() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until
                    (ExpectedConditions.visibilityOf(acceptCookiesButton)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click accept cookies button")
    public void clickAcceptCookiesButton() {
        acceptCookiesButton.click();
    }






}
