package jqueryui.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SliderTest extends AbstractSliderTest {

    private static final String SLIDER_URL = "https://jqueryui.com/jqueryui.slider/#custom-handle";

    @Override
    @BeforeClass
    public void setUp() throws InterruptedException {
        super.setUp();
        driver.get(SLIDER_URL);
        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        Thread.sleep(3000);
        driver.switchTo().frame(iframeElement);
    }

    @DataProvider(name = "sliderData")
    public static Object[][] sliderData() {
        return new Object[][]{{80, 80}, {50, 50}, {55, 55}, {55, 55}};
    }

    @Test(dataProvider = "sliderData")
    public void sliderTest(int moveTo, int expected) {

        WebElement sliderElement = driver.findElement(By.xpath("//*[@id=\"custom-handle\"]"));

        int currentPosition = Integer.valueOf(sliderElement.getText());
        int moveBy;
        Keys key;
        if (currentPosition >= moveTo) {
            key = Keys.ARROW_LEFT;
            moveBy = currentPosition - moveTo;
        } else {
            key = Keys.ARROW_RIGHT;
            moveBy = moveTo - currentPosition;
        }

        Actions move = new Actions(driver);
        for (int i = 0; i < moveBy; i++) {
            move.sendKeys(sliderElement, key).perform();
        }

        assertEquals(sliderElement.getText(), String.valueOf(expected));
    }
}