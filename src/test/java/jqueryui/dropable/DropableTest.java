package jqueryui.dropable;

import com.jqueryui.droppable.DroppablePage;
import com.jqueryui.menu.Menu;
import jqueryui.AbstractTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest extends AbstractTest {

    private static final String DROPPABLE_PAGE_URL = "https://jqueryui.com/droppable/";

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        driver.get(DROPPABLE_PAGE_URL);
    }

    @Test
    public void droppableTest() {
        DroppablePage droppablePage = new DroppablePage(driver, new Menu(driver));

        droppablePage.getMenu().clickDroppable();
        droppablePage.switchToDemoFrame();
        droppablePage.dragRectangle();
        assertEquals(droppablePage.getDroppableMessage(), "Dropped!");
    }
}
