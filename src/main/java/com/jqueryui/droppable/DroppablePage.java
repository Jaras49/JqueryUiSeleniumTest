package com.jqueryui.droppable;

import com.jqueryui.AbstractPage;
import com.jqueryui.menu.MenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppablePage extends AbstractPage {

    private MenuPage menu;

    @FindBy(css = "#draggable")
    private WebElement draggableElement;

    @FindBy(css = "#droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver, MenuPage menu) {
        super(driver);
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public void dragRectangle() {
        new Actions(driver).dragAndDrop(draggableElement, droppableElement)
                .perform();
    }

    public String getDroppableMessage() {
        return droppableElement.findElement(By.cssSelector("p")).getText();
    }

    public void switchToDemoFrame() {
        driver.switchTo().frame(iFrame);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public MenuPage getMenu() {
        return menu;
    }
}
