package com.jqueryui.page.slider;

import com.jqueryui.annotations.WaitUntilVisible;
import com.jqueryui.page.AbstractPage;
import com.jqueryui.page.menu.MenuPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SliderPage extends AbstractPage<SliderPage> {

    private MenuPage menu;

    @WaitUntilVisible
    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @WaitUntilVisible
    @FindBy(xpath = "//a[contains(text(), 'Custom handle')]")
    private WebElement customHandleButton;

    @FindBy(id = "custom-handle")
    private WebElement slider;

    public SliderPage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
        waitUntilPageLoads();
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
        wait.until(ExpectedConditions.elementToBeClickable(slider));

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
