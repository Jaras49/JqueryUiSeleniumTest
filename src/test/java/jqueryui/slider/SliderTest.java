package jqueryui.slider;

import com.jqueryui.menu.LeftMenu;
import com.jqueryui.slider.SliderPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SliderTest extends AbstractSliderTest {

    private static final String SLIDER_PAGE_URL = "https://jqueryui.com/slider/#custom-handle";

    @Override
    @BeforeClass
    public void setUp() {
        super.setUp();
        driver.get(SLIDER_PAGE_URL);
    }

    @DataProvider(name = "sliderData")
    public static Object[][] sliderData() {
        return new Object[][]{{80, 80}, {50, 50}, {55, 55}, {55, 55}};
    }

    @Test(dataProvider = "sliderData")
    public void sliderTest(int moveTo, int expected) {
        SliderPage sliderPage = new SliderPage(driver, new LeftMenu(driver));
        sliderPage.moveSlider(moveTo);
        assertEquals(sliderPage.getSliderValue(), expected);
    }
}