package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import pages.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


@Listeners({ITestListenerUtility.class})
public class DriverSetup extends util.ConfigReader {
    public static AndroidDriver driver;

    public DesiredCapabilities capabilities = new DesiredCapabilities();

    protected Helpers helpers;
    protected CookiesPage cookiesPage;
    protected IdentityPage identityPage;
    protected StaysPage staysPage;
    protected DestinationPage destinationPage;
    protected StaysResultPage staysResultPage;
    protected SavedPage savedPage;
    protected SettingsPage settingsPage;
    protected CurrencySettingPage currencySettingPage;
    protected PrivacyPolicyPage privacyPolicyPage;
    protected SignInPage signInPage;
    protected GeniusInfoPage geniusInfoPage;
    protected GeniusLevelsPage geniusLevelsPage;
    protected CarRentalPage carRentalPage;
    protected CarLocationPage carLocationPage;
    protected CarRentalResultsPage carRentalResultsPage;


    @BeforeMethod
    public void setUp() {

        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setCapability(UiAutomator2Options.AVD_OPTION, getProperty("device.name"));
        capabilities.setCapability(UiAutomator2Options.AUTOMATION_NAME_OPTION, getProperty("automation.name"));
        capabilities.setCapability(UiAutomator2Options.APP_OPTION, getProperty("path.to.app"));
        capabilities.setCapability(UiAutomator2Options.APP_ACTIVITY_OPTION, getProperty("app.activity"));
        capabilities.setCapability(UiAutomator2Options.APP_PACKAGE_OPTION, getProperty("app.package"));
        capabilities.setCapability(UiAutomator2Options.NO_RESET_OPTION, false);
        capabilities.setCapability(UiAutomator2Options.FULL_RESET_OPTION, true);
        capabilities.setCapability(UiAutomator2Options.AUTO_GRANT_PERMISSIONS_OPTION, true);
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("appium:disableIdLocatorAutocompletion", true);

        try {
            driver = new AndroidDriver(new URI(util.GlobalVariables.localAppiumServerUrl).toURL(), capabilities);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        cookiesPage = new CookiesPage(driver);
        identityPage = new IdentityPage(driver);
        staysPage = new StaysPage(driver);
        destinationPage = new DestinationPage(driver);
        staysResultPage = new StaysResultPage(driver);
        savedPage = new SavedPage(driver);
        settingsPage = new SettingsPage(driver);
        currencySettingPage = new CurrencySettingPage(driver);
        privacyPolicyPage = new PrivacyPolicyPage(driver);
        signInPage = new SignInPage(driver);
        geniusInfoPage = new GeniusInfoPage(driver);
        geniusLevelsPage = new GeniusLevelsPage(driver);
        carRentalPage = new CarRentalPage(driver);
        carLocationPage = new CarLocationPage(driver);
        carRentalResultsPage = new CarRentalResultsPage(driver);

        helpers = new Helpers();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
