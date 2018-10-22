package jqueryui.dropable;

import com.jqueryui.droppable.DroppablePage;
import jqueryui.AbstractTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest extends AbstractTest {

    private static final String DROPPABLE_URL = "https://jqueryui.com/droppable/";

    @Test
    public void droppableTest() {
        driver.get(DroppablePage.DROPPABLE_PAGE_URL);
        DroppablePage droppablePage = new DroppablePage(driver);

        droppablePage.switchToDemoFrame();
        droppablePage.dragRectangle();
        assertEquals(droppablePage.getDroppableMessage(), "Dropped!");
    }
}
