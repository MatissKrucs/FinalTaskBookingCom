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

public class CurrencySettingPage {

    protected AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All currencies']")
    private RemoteWebElement allCurrenciesTitle;

    public CurrencySettingPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Currency setting page is loaded")
    public boolean allCurrenciesTitleLoaded() {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(allCurrenciesTitle)).isDisplayed();
    }

    @Step("Select currency: {0}")
    public void selectCurrency(String currency) {
        try {
            String currencySelector = String.format("//android.widget.TextView[@text='%s']", currency);
            new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.xpath(currencySelector)))).click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Invalid currency provided");
        }
    }
}
