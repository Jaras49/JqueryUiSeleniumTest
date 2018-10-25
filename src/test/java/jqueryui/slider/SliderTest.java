package jqueryui.slider;

import com.jqueryui.menu.MenuPage;
import com.jqueryui.slider.SliderPage;
import jqueryui.AbstractTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SliderTest extends AbstractTest {

    @DataProvider(name = "sliderData")
    public static Object[][] sliderData() {
        return new Object[][]{{80, 80, 35, 35}, {50, 50, 15, 15}, {55, 55, 80, 80}, {55, 55, 12, 12}};
    }

    @Test(dataProvider = "sliderData")
    public void sliderTest(int moveTo1, int expected1, int moveTo2, int expected2) {
        SliderPage sliderPage = new SliderPage(driver, new MenuPage(driver));

        sliderPage.getMenu().clickSlider();
        sliderPage.clickCustomHandleButton();
        sliderPage.switchToIframe();

        sliderPage.moveSlider(moveTo1);
        assertEquals(sliderPage.getSliderValue(), expected1);

        sliderPage.moveSlider(moveTo2);
        assertEquals(sliderPage.getSliderValue(), expected2);
    }
}