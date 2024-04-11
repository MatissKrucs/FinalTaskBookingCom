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

public class SettingsPage {

    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Currency']")
    private RemoteWebElement currencySetting;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Device settings']")
    private RemoteWebElement deviceSettingsText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Currency']/following-sibling::android.widget.TextView")
    private RemoteWebElement currencySettingValue;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Privacy Policy']")
    private RemoteWebElement privacyPolicyButton;

    public SettingsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Settings in page is loaded")
    public boolean settingsPageLoaded() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until
                    (ExpectedConditions.visibilityOf(deviceSettingsText)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click currency setting button")
    public void clickCurrencySetting() {
        currencySetting.click();
    }

    @Step("Check currency setting value: {0}")
    public Boolean checkCurrencySettingValue(String currency) {
        try {
            return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Currency']/" +
                    "following-sibling::android.widget.TextView[contains(@text, '" + currency + "')]")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click privacy policy button")
    public void clickPrivacyPolicyButton() {
        privacyPolicyButton.click();
    }

}
