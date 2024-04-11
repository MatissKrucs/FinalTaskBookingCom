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

import java.time.Duration;

public class CarRentalResultsPage {
    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/fragment_container_view")
    private RemoteWebElement carRentalContainer;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Filter']")
    private RemoteWebElement filterButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Transmission']/following-sibling::android.view.View/android.widget.TextView[@text=\"Automatic\"]")
    private RemoteWebElement transmissionAutomatic;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Show results']")
    private RemoteWebElement showResults;
    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/" +
            "android.view.View[1]/android.view.View[2]/(//android.view.View[@content-desc='Air Conditioning'])[1]")
    private RemoteWebElement airConditioning;
    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/" +
            "android.view.View/android.view.View[1]/android.view.View[2]")
    private RemoteWebElement carContainer;


    public CarRentalResultsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Car rental result page is loaded")
    public boolean carRentalContainerLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(carRentalContainer)).isDisplayed();
    }

    @Step("Select automatic transmission")
    public void selectAutomaticTransition() {

        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(filterButton)).click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(transmissionAutomatic)).click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(showResults)).click();
    }

    @Step("Validate that first displayed car is automatic")
    public boolean validateFirstCarIsAutomatic() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(carContainer)).isDisplayed();

        try {
            return new WebDriverWait(driver, Duration.ofSeconds(30)).until
                    (ExpectedConditions.visibilityOf(airConditioning)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
