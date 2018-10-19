package jqueryui.dropable;

import jqueryui.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest extends AbstractTest {

    private static final String DROPABLE_URL = "https://jqueryui.com/droppable/";

    @Test
    public void dropableTest() throws InterruptedException {
        driver.get(DROPABLE_URL);
        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        Thread.sleep(3000);
        driver.switchTo().frame(iframeElement);

        WebElement draggableElement = driver.findElement(By.cssSelector("#draggable"));
        WebElement droppableElement = driver.findElement(By.cssSelector("#droppable"));

        Actions drag = new Actions(driver);
        drag.dragAndDrop(draggableElement, droppableElement).perform();

        assertEquals(droppableElement.findElement(By.cssSelector("p")).getText(), "Dropped!");
    }
}
