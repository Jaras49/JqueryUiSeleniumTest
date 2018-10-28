package jqueryui.slider;

import com.jqueryui.page.slider.SliderPage;
import jqueryui.AbstractTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SliderTest extends AbstractTest {

    @DataProvider(name = "sliderData")
    public static Object[][] sliderData() {
        return new Object[][]{{80, 80, 35, 35}, {50, 50, 15, 15}, {55, 55, 80, 80}, {55, 55, 12, 12}};
    }

    @Test(dataProvider = "sliderData")
    public void sliderTest(int moveTo1, int expected1, int moveTo2, int expected2) {
        SliderPage sliderPage = openSliderPage();

        sliderPage.clickCustomHandleButton()
                .switchToIframe()
                .moveSlider(moveTo1)
                .assertEquals(sliderPage.getSliderValue(), expected1, sliderPage)
                .moveSlider(moveTo2)
                .assertEquals(sliderPage.getSliderValue(), expected2, sliderPage);
    }

    private SliderPage openSliderPage() {
        return menu.goToSliderPage();
    }
}