package com.jqueryui.factory;

import com.jqueryui.page.calendar.CalendarPage;
import com.jqueryui.page.droppable.DroppablePage;
import com.jqueryui.page.menu.MenuPage;
import com.jqueryui.page.slider.SliderPage;
import org.openqa.selenium.WebDriver;

public class PageObjectFactory {

    private PageObjectFactory() {
    }

    public static SliderPage createSliderPage(WebDriver driver) {
        return new SliderPage(driver, createMenuPage(driver));
    }

    public static DroppablePage createDroppablePage(WebDriver driver) {
        return new DroppablePage(driver, createMenuPage(driver));
    }

    public static CalendarPage createCalendarPage(WebDriver driver) {
        return new CalendarPage(driver, createMenuPage(driver));
    }

    public static MenuPage createMenuPage(WebDriver driver) {
        return new MenuPage(driver);
    }
}
