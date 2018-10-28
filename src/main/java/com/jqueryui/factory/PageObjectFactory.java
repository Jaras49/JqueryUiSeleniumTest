package com.jqueryui.factory;

import com.jqueryui.page.calendar.CalendarPage;
import com.jqueryui.page.droppable.DroppablePage;
import com.jqueryui.page.menu.MenuPage;
import com.jqueryui.page.slider.SliderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectFactory {

    private PageObjectFactory() {
    }

    public static SliderPage createSliderPage(WebDriver driver) {
        return new SliderPage
                (driver, new WebDriverWait(driver, 15), new Actions(driver), createMenuPage(driver));
    }

    public static DroppablePage createDroppablePage(WebDriver driver) {
        return new DroppablePage
                (driver, new WebDriverWait(driver, 15), new Actions(driver), createMenuPage(driver));
    }

    public static CalendarPage createCalendarPage(WebDriver driver) {
        return new CalendarPage
                (driver, new WebDriverWait(driver, 15), new Actions(driver), createMenuPage(driver));
    }

    public static MenuPage createMenuPage(WebDriver driver) {
        return new MenuPage
                (driver, new WebDriverWait(driver, 15), new Actions(driver));
    }
}
