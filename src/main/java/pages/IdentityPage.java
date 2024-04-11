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
import util.GlobalVariables;

public class IdentityPage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/identity_screen_frame_layout")
    private RemoteWebElement identityPageFrame;
    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement closeButton;

    public IdentityPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Identity page is loaded")
    public boolean identityPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(identityPageFrame)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click close button")
    public void clickCloseButton() {
        closeButton.click();
    }
}
