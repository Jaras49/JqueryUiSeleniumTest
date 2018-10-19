package jqueryui.calendar;

import jqueryui.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CalendarTest extends AbstractTest {

    private static final String CALENDAR_URL = "https://jqueryui.com/datepicker/#other-months";
    private static final String DATE_FORMAT = "d.MM.yyyy";

    @Override
    @BeforeMethod
    public void setUp() throws InterruptedException {
        super.setUp();
        driver.get(CALENDAR_URL);
        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        Thread.sleep(3000);
        driver.switchTo().frame(iframeElement);
    }

    @DataProvider(name = "calendarData")
    public static Object[][] calendarData() {
        return new Object[][]{{"1.02.2019", "02/01/2019"},
                {"today", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))},
                {"06.07.2018", "07/06/2018"},
                {"20.11.2017", "11/20/2017"}
        };
    }

    @Test(dataProvider = "calendarData")
    public void datePickerTest(String date, String expected) {

        LocalDate parsedDate = parseDate(date);
        LocalDate desiredYearAndMonth = LocalDate.of(parsedDate.getYear(), parsedDate.getMonth(), 1);

        openCalendar();

        WebElement calendarDiv = driver.findElement(By.cssSelector("#ui-datepicker-div"));

        iterateToYearAndMonth(calendarDiv, desiredYearAndMonth);
        clickDate(parsedDate, calendarDiv, desiredYearAndMonth);

        WebElement dateInput = driver.findElement(By.cssSelector("#datepicker"));
        assertEquals(dateInput.getAttribute("value"), expected);
    }

    private void iterateToYearAndMonth(WebElement calendarDiv, LocalDate desiredYearAndMonth) {
        LocalDate currentYearAndMonth = getCurrentYearAndMonth(calendarDiv);

        String actionSelector = getActionSelector(desiredYearAndMonth, currentYearAndMonth);

        while (!desiredYearAndMonth.equals(currentYearAndMonth)) {
            WebElement nextElement = calendarDiv.findElement(By.cssSelector(actionSelector));
            nextElement.click();
            currentYearAndMonth = getCurrentYearAndMonth(calendarDiv);
        }
    }

    private LocalDate parseDate(String date) {
        if (date.equalsIgnoreCase("today")) {
            return LocalDate.now();
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    private void clickDate(LocalDate parsedDate, WebElement calendarDiv, LocalDate desiredYearAndMonth) {
        WebElement datepicker = calendarDiv.findElement(By.cssSelector(".ui-datepicker-calendar"));
        List<WebElement> daysElements = datepicker.findElements(By.cssSelector("tbody tr td"));
        daysElements.stream()
                .filter(day -> Integer.valueOf(day.getAttribute("data-month")).equals(desiredYearAndMonth.getMonthValue() - 1))
                .map(dayElement -> dayElement.findElement(By.cssSelector("a")))
                .filter(dayElement -> !dayElement.getText().isEmpty())
                .filter(dayElement -> Integer.valueOf(dayElement.getText()).equals(parsedDate.getDayOfMonth()))
                .forEach(WebElement::click);
    }

    private void openCalendar() {
        WebElement dateInput = driver.findElement(By.cssSelector("#datepicker"));
        dateInput.click();
    }

    private String getActionSelector(LocalDate desirableDate, LocalDate currentDate) {
        String prevElementSelector = "div .ui-datepicker-prev";
        String nextElementSelector = "div .ui-datepicker-next";

        if (desirableDate.isBefore(currentDate)) {
            return prevElementSelector;
        } else {
            return nextElementSelector;
        }
    }

    private LocalDate getCurrentYearAndMonth(WebElement calendarDiv) {
        WebElement currentMonthElement = calendarDiv.findElement(By.cssSelector("div .ui-datepicker-month"));
        WebElement currentYearElement = calendarDiv.findElement(By.cssSelector("div .ui-datepicker-year"));

        return LocalDate.of
                (Integer.valueOf(currentYearElement.getText()), Month.valueOf(currentMonthElement.getText().toUpperCase()), 1);
    }
}
