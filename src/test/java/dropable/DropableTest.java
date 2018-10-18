package dropable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropableTest {

    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    private static final String DROPABLE_URL = "https://jqueryui.com/droppable/";

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String driverPath = this.getClass().getClassLoader().getResource(CHROME_DRIVER_NAME).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

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
