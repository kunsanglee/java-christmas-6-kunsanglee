package christmas;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LocalDateTimeTest {

    @Test
    void createTest() {
        LocalDate date = LocalDate.of(2023, 12, 1);
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        int value = dayOfWeek.getValue();
        System.out.println("value = " + value);
        System.out.println(DayOfWeek.of(1));
        System.out.println(DayOfWeek.of(2));
        System.out.println(DayOfWeek.of(3));
        System.out.println(DayOfWeek.of(4));
        System.out.println(DayOfWeek.of(5));
        System.out.println(DayOfWeek.of(6));
        System.out.println(DayOfWeek.of(7));
        assertThatCode(() -> LocalDate.of(2023, 12, 50))
                .isInstanceOf(DateTimeException.class);
        Arrays.stream(DayOfWeek.values())
                .forEach(week -> {
                    if (week.equals(DayOfWeek.FRIDAY) || week.equals(DayOfWeek.SATURDAY)) {
                        System.out.println("주말");
                        return;
                    }
                    System.out.println("평일");
                });
    }

    @Test
    void nowTest() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        System.out.println("year = " + year);
        Month month = now.getMonth();
        System.out.println("month = " + month);
        int dayOfMonth = now.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
    }
}
