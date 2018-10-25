package jqueryui.dropable;

import com.jqueryui.droppable.DroppablePage;
import com.jqueryui.menu.MenuPage;
import jqueryui.AbstractTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest extends AbstractTest {

    @Test
    public void droppableTest() {
        DroppablePage droppablePage = new DroppablePage(driver, new MenuPage(driver));

        droppablePage.getMenu().clickDroppable();

        droppablePage.switchToDemoFrame();
        droppablePage.dragRectangle();

        assertEquals(droppablePage.getDroppableMessage(), "Dropped!");
    }
}
