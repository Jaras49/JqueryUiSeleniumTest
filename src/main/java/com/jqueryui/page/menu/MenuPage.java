package com.jqueryui.page.menu;

import com.jqueryui.factory.PageObjectFactory;
import com.jqueryui.page.calendar.CalendarPage;
import com.jqueryui.page.droppable.DroppablePage;
import com.jqueryui.page.slider.SliderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Droppable')]")
    private WebElement droppable;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Datepicker')]")
    private WebElement datepicker;

    @FindBy(xpath = "//*[@id='sidebar']//a[contains(text(), 'Slider')]")
    private WebElement slider;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DroppablePage goToDroppablePage() {
        droppable.click();
        return PageObjectFactory.createDroppablePage(driver);
    }

    public CalendarPage goToDatepickerPage() {
        datepicker.click();
        return PageObjectFactory.createCalendarPage(driver);
    }

    public SliderPage goToSliderPage() {
        slider.click();
        return PageObjectFactory.createSliderPage(driver);
    }
}
