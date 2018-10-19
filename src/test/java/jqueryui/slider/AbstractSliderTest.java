package jqueryui.slider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractSliderTest {

    private static final String DRIVER_NAME = "geckodriver.exe";

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {

        String driverPath = this.getClass().getClassLoader().getResource(DRIVER_NAME).getPath();
        System.setProperty("webdriver.gecko.driver", driverPath);

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
