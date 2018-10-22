package com.jqueryui.droppable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppablePage {

    public static final String DROPPABLE_PAGE_URL = "https://jqueryui.com/droppable/";

    private WebDriver driver;

    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @FindBy(css = "#draggable")
    private WebElement draggableElement;

    @FindBy(css = "#droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
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
}
