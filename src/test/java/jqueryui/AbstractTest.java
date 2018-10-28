package jqueryui;

import com.jqueryui.factory.PageObjectFactory;
import com.jqueryui.page.menu.MenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    private static final String CHROME_DRIVER_NAME = "chromedriver.exe";
    private static final String JQUERY_UI_URL = "https://jqueryui.com/";

    protected WebDriver driver;
    protected MenuPage menu;

    @BeforeMethod
    public void setUp() {
        String driverPath = this.getClass().getClassLoader().getResource(CHROME_DRIVER_NAME).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(JQUERY_UI_URL);

        menu = PageObjectFactory.createMenuPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
