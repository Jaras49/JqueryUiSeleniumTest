package com.jqueryui.page.slider;

import com.jqueryui.page.AbstractPage;
import com.jqueryui.page.menu.MenuPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage extends AbstractPage {

    private MenuPage menu;

    @FindBy(id = "custom-handle")
    private WebElement slider;

    @FindBy(xpath = "//a[contains(text(), 'Custom handle')]")
    private WebElement customHandleButton;

    public SliderPage(WebDriver driver, MenuPage menu) {
        super(driver);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public SliderPage switchToIframe() {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public SliderPage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public SliderPage clickCustomHandleButton() {
        customHandleButton.click();
        return this;
    }

    public SliderPage moveSlider(int moveToValue) {
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
        return this;
    }

    public int getSliderValue() {
        return Integer.valueOf(slider.getText());
    }

    public MenuPage getMenu() {
        return menu;
    }
}
