package com.jqueryui.calendar;

import com.jqueryui.menu.Menu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class CalendarPage {

    private WebDriver driver;
    private Menu menu;

    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @FindBy(css = "#ui-datepicker-div")
    private WebElement calendarDiv;

    @FindBy(css = "#datepicker")
    private WebElement inputField;

    @FindBy(css = "div .ui-datepicker-month")
    private WebElement currentMonthElement;

    @FindBy(css = "div .ui-datepicker-year")
    private WebElement currentYearElement;

    @FindBy(css = "div .ui-datepicker-next")
    private WebElement callendarNext;

    @FindBy(css = "div .ui-datepicker-prev")
    private WebElement callendarPrev;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> calendarDays;

    public CalendarPage(WebDriver driver, Menu menu) {
        this.driver = driver;
        this.menu = menu;
        PageFactory.initElements(driver, this);
    }

    public void switchToIframe() {
        driver.switchTo().frame(iFrame);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void clickInputField() {
        inputField.click();
    }

    public void clickCallendarNext() {
        callendarNext.click();
    }

    public void clickCallendarPrev() {
        callendarPrev.click();
    }

    public void moveToDate(LocalDate date) {
        LocalDate desiredYearAndMonth = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate currentYearAndMonth = getCurrentYearAndMonth();

        while (!desiredYearAndMonth.equals(currentYearAndMonth)) {
            if (desiredYearAndMonth.isAfter(currentYearAndMonth)) {
                clickCallendarNext();
            } else if (desiredYearAndMonth.isBefore(currentYearAndMonth)) {
                clickCallendarPrev();
            }
            currentYearAndMonth = getCurrentYearAndMonth();
        }
    }

    public void clickDay(LocalDate date) {
        calendarDays.stream()
                .filter(day -> Integer.valueOf(day.getAttribute("data-month")).equals(date.getMonthValue() - 1))
                .map(day -> day.findElement(By.cssSelector("a")))
                .filter(day -> Integer.valueOf(day.getText()).equals(date.getDayOfMonth()))
                .findAny()
                .ifPresent(WebElement::click);
    }

    public Menu getMenu() {
        return menu;
    }

    public String getInputValue() {
        return inputField.getAttribute("value");
    }

    private LocalDate getCurrentYearAndMonth() {
        return LocalDate.of(Integer.valueOf(currentYearElement.getText()), Month.valueOf(currentMonthElement.getText().toUpperCase()), 1);
    }
}
