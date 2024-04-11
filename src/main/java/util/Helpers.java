package util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.List;
import java.util.stream.IntStream;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class Helpers {

    public enum Directions {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public void swipe(AndroidDriver driver, Directions direction) {
        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;

        int endX;

        switch (direction) {
            case UP, DOWN -> endX = startX;
            case LEFT -> endX = (int) (driver.manage().window().getSize().getWidth() * 0.2);
            case RIGHT -> endX = (int) (driver.manage().window().getSize().getWidth() * 0.8);
            default -> throw new IllegalArgumentException("Invalid direction selected: " + direction);
        }

        int endY;

        switch (direction) {
            case UP -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
            case DOWN -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.8);
            case LEFT, RIGHT -> endY = startY;
            default -> throw new IllegalArgumentException("Invalid direction selected: " + direction);
        }

        Sequence swipe = new Sequence(FINGER, 0);

        swipe.addAction(FINGER.createPointerMove(ZERO, viewport(), startX, startY));
        swipe.addAction(FINGER.createPointerDown(LEFT.asArg()));
        swipe.addAction(FINGER.createPointerMove(ofMillis(1000), viewport(), endX, endY));
        swipe.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(swipe));

    }

    public void scrollToXpath(AndroidDriver driver, String xpath, Directions direction, int swipeCount) {
        IntStream.range(0, swipeCount).forEach(obj -> {
            boolean test;
            try {
                test = driver.findElement(AppiumBy.xpath(xpath)).isDisplayed();
            } catch (NoSuchElementException e) {
                test = false;
            }
            if(!test)
                swipe(driver, direction);
        });
        driver.findElement(AppiumBy.xpath(xpath)).isDisplayed();
    }










    public void swipeNTimes(AndroidDriver driver, Directions direction, int numberOfSwipes) {
        for (int i = 0; i <= numberOfSwipes; i++) {
            swipe(driver, direction);
        }
    }

    public void scrollToElementWithText(AndroidDriver driver, String text) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))." +
                "setAsVerticalList().scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0));"));
    }

    public void scrollToElementWithId(AndroidDriver driver, String text) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))." +
                "setAsVerticalList().scrollIntoView(new UiSelector().resourceId(\"" + text + "\").instance(0));"));
    }






}
