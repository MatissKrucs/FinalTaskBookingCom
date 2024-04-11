package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

import java.time.Duration;

public class DestinationPage {

    protected AndroidDriver driver;

    @AndroidFindBy(id = "com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content")
    private RemoteWebElement destinationField;
    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView[contains" +
            "(@resource-id, 'booking_header_content')]/android.view.View/android.view.View")
    private RemoteWebElement SearchResultsContent;

    public DestinationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Destination page is loaded")
    public boolean destinationPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(destinationField)).isDisplayed();
    }

    @Step("Fill destination field: {0}")
    public void fillDestinationField(String destination) {

        destinationField.sendKeys(destination);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(SearchResultsContent)).isDisplayed();

        String destinationSelector = String.format("//android.widget.TextView[@text='%s']", destination);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.xpath(destinationSelector)))).click();
    }
}
