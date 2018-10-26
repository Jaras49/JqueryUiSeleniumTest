package jqueryui.calendar;

import com.jqueryui.page.calendar.CalendarPage;
import com.jqueryui.page.menu.MenuPage;
import jqueryui.AbstractTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

public class CalendarTest extends AbstractTest {

    private static final String INPUT_DATE_FORMAT = "d.MM.yyyy";
    private static final String OUTPUT_DATE_FORMAT = "MM/dd/yyyy";

    @DataProvider(name = "calendarData")
    public static Object[][] calendarData() {
        return new Object[][]{{"1.02.2019", "02/01/2019"},
                {LocalDate.now().format(DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)),
                        LocalDate.now().format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT))},
                {"06.07.2018", "07/06/2018"},
                {"20.11.2017", "11/20/2017"}
        };
    }

    @Test(dataProvider = "calendarData")
    public void calendarTest(String date, String expected) {
        CalendarPage calendarPage = new CalendarPage(driver, new MenuPage(driver));

        calendarPage.getMenu().clickDatepicker();

        calendarPage.switchToIframe();
        calendarPage.clickInputField();
        calendarPage.moveToDate(LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
        calendarPage.clickDay(LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));

        assertEquals(calendarPage.getInputValue(), expected);
    }
}