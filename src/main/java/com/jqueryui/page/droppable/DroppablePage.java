package com.jqueryui.page.droppable;

import com.jqueryui.page.AbstractPage;
import com.jqueryui.page.menu.MenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DroppablePage extends AbstractPage {

    private MenuPage menu;

    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @FindBy(css = "#draggable")
    private WebElement draggableElement;

    @FindBy(css = "#droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public DroppablePage dragAndDropRectangle() {
        new Actions(driver).dragAndDrop(draggableElement, droppableElement)
                .perform();
        return this;
    }

    public String getDroppableMessage() {
        return droppableElement.findElement(By.cssSelector("p")).getText();
    }

    public DroppablePage switchToDemoFrame() {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public DroppablePage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public MenuPage getMenu() {
        return menu;
    }
}
