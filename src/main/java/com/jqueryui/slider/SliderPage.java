package com.jqueryui.slider;

import com.jqueryui.menu.Menu;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage {

    private WebDriver driver;
    private Menu menu;

    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @FindBy(id = "custom-handle")
    private WebElement slider;

    public SliderPage(WebDriver driver, Menu menu) {
        this.driver = driver;
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public void moveSlider(int moveToValue) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iFrame);

        int currentPosition = getSliderValue();
        int moveBy;
        Keys key;
        if (currentPosition >= moveToValue) {
            key = Keys.ARROW_LEFT;
            moveBy = currentPosition - moveToValue;
        } else {
            key = Keys.ARROW_RIGHT;
            moveBy = moveToValue - currentPosition;
        }
        Actions move = new Actions(driver);
        for (int i = 0; i < moveBy; i++) {
            move.sendKeys(slider, key).perform();
        }
    }

    public int getSliderValue() {
        return Integer.valueOf(slider.getText());
    }

    public Menu getMenu() {
        return menu;
    }
}
