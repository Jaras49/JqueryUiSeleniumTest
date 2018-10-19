package jqueryui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";

    protected WebDriver driver;

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
}
