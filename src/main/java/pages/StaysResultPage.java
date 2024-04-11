package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class StaysResultPage {

    protected AndroidDriver driver;

    @AndroidFindBy(id = "sr_list")
    private RemoteWebElement searchResultList;


    @AndroidFindBy(accessibility = "Navigate up")
    private RemoteWebElement backButton;

    @AndroidFindBy(id = "com.booking:id/searchbox_destination")
    private RemoteWebElement searchDestination;
    @AndroidFindBy(id = "com.booking:id/searchbox_dates")
    private RemoteWebElement searchDates;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc='Save property to list'])[1]")
    private RemoteWebElement saveFirstProperty;

    @AndroidFindBy(xpath = "((//android.widget.ImageView[@content-desc='Save property to list'])[1]/preceding-sibling::android.widget.TextView)[1]")
    private RemoteWebElement firstPropertyName;


    public StaysResultPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @Step("Stays result page is loaded")
    public boolean bookingHeaderLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(searchResultList)).isDisplayed();
    }

    @Step("Get search destination")
    public String getSearchDestination() {
        return searchDestination.getAttribute("text");
    }
    @Step("Get search dates")
    public String getSearchDates() {
        return searchDates.getAttribute("text");
    }

    @Step("Save first property")
    public String saveFirstProperty() {
        String propertyName = firstPropertyName.getAttribute("text");
        saveFirstProperty.click();
        return propertyName;
    }

    @Step("Click back button")
    public void clickBackButton() {
        backButton.click();
    }


}
