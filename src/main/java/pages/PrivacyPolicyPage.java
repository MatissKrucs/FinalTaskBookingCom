package pages;

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

import java.time.Duration;

public class PrivacyPolicyPage {

    protected AndroidDriver driver;


    @AndroidFindBy(id = "com.booking:id/web_view_activity_toolbar")
    private RemoteWebElement  activityToolbarWebView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Privacy and Cookies policy']")
    private RemoteWebElement privacyStatementTitle;

    @AndroidFindBy(xpath = "//android.webkit.WebView[@text='Privacy & Cookie Statement. | Booking.com']")
    private RemoteWebElement statementWebView;
    public PrivacyPolicyPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Privacy and Cookies policy page is loaded.")
    public boolean privacyPolicyPageLoaded() {

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(activityToolbarWebView)).isDisplayed();

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(privacyStatementTitle)).isDisplayed();

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until
                    (ExpectedConditions.visibilityOf(statementWebView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
