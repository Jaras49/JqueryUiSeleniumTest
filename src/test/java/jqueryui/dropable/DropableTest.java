package jqueryui.dropable;

import com.jqueryui.page.droppable.DroppablePage;
import jqueryui.AbstractTest;
import org.testng.annotations.Test;

public class DropableTest extends AbstractTest {

    @Test
    public void droppableTest() {
        DroppablePage droppablePage = openDroppablePage();

        droppablePage.switchToDemoFrame()
                .dragAndDropRectangle()
                .assertEquals(droppablePage.getDroppableMessage(), "Dropped!", droppablePage);
    }

    private DroppablePage openDroppablePage() {
        return menu.goToDroppablePage();
    }
}
