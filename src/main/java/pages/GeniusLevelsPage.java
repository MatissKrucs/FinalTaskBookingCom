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

public class GeniusLevelsPage extends Helpers {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/genius_levels_view")
    private RemoteWebElement geniusLevelsView;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Genius Level 3')]")
    private RemoteWebElement geniusLevelThreeTextView;
    @AndroidFindBy(id = "com.booking:id/action_button")
    private RemoteWebElement gotItButton;


    public GeniusLevelsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Genius levels page is loaded")
    public boolean geniusLevelsPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(geniusLevelsView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Scroll to Genius level three")
    public void scrollToGeniusLevelThree() {
        String xpath = "//android.widget.TextView[@text='Genius Level 3']";
        scrollToXpath(driver, xpath, Directions.LEFT, 5);
    }

    @Step("Validate Genius level three")
    public boolean validateGeniusLevelThree() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(geniusLevelThreeTextView)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click got it button")
    public void clickGotItButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(gotItButton)).click();
    }












}
