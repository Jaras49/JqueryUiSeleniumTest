package jqueryui.dropable;

import com.jqueryui.page.droppable.DroppablePage;
import jqueryui.AbstractTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest extends AbstractTest {

    @Test
    public void droppableTest() {
        DroppablePage droppablePage = menu.goToDroppablePage()
                .switchToDemoFrame()
                .dragAndDropRectangle();
        assertEquals(droppablePage.getDroppableMessage(), "Dropped!");
    }
}
