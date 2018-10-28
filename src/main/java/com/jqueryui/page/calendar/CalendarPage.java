package com.jqueryui.page.calendar;

import com.jqueryui.annotations.WaitUntilVisible;
import com.jqueryui.page.AbstractPage;
import com.jqueryui.page.menu.MenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class CalendarPage extends AbstractPage<CalendarPage> {

    private MenuPage menu;

    @WaitUntilVisible
    @FindBy(css = ".demo-frame")
    private WebElement iFrame;

    @WaitUntilVisible
    @FindBy(xpath = "//*[@class='demo-list']//a[.='Dates in other months']")
    private WebElement datesInOtherMonthsButton;

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

    public CalendarPage(WebDriver driver, WebDriverWait wait, Actions actions, MenuPage menu) {
        super(driver, wait, actions);
        this.menu = menu;
        PageFactory.initElements(driver, this);
        waitUntilPageLoads();
    }

    public CalendarPage clickDatesInOtherMonthsButton() {
        datesInOtherMonthsButton.click();
        wait.until(ExpectedConditions.attributeContains(iFrame, "src", "/other-months.html"));
        return this;
    }

    public CalendarPage switchToIframe() {
        driver.switchTo().frame(iFrame);
        return this;
    }

    public CalendarPage switchToParentFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public CalendarPage clickInputField() {
        inputField.click();
        return this;
    }

    public CalendarPage clickCallendarNext() {
        callendarNext.click();
        return this;
    }

    public CalendarPage clickCallendarPrev() {
        callendarPrev.click();
        return this;
    }

    public CalendarPage moveToDate(LocalDate date) {
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
        return this;
    }

    public CalendarPage clickDay(LocalDate date) {
        calendarDays.stream()
                .filter(day -> Integer.valueOf(day.getAttribute("data-month")).equals(date.getMonthValue() - 1))
                .map(day -> day.findElement(By.cssSelector("a")))
                .filter(day -> Integer.valueOf(day.getText()).equals(date.getDayOfMonth()))
                .findAny()
                .ifPresent(WebElement::click);
        return this;
    }

    public MenuPage getMenu() {
        return menu;
    }

    public String getDateInputValue() {
        return inputField.getAttribute("value");
    }

    private LocalDate getCurrentYearAndMonth() {
        return LocalDate.of(Integer.valueOf(currentYearElement.getText()), Month.valueOf(currentMonthElement.getText().toUpperCase()), 1);
    }
}
