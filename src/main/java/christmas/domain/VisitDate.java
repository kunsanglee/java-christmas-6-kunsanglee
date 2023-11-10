package christmas.domain;

import static christmas.constant.DateNumber.EVENT_MONTH;
import static christmas.constant.DateNumber.EVENT_YEAR;
import static christmas.constant.DateNumber.MAX_DATE;
import static christmas.constant.DateNumber.MIN_DATE;
import static christmas.exception.ExceptionMessage.INVALID_VISIT_DATE;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private final int date;
    private final DayOfWeek dayOfWeek;

    public VisitDate(int date) {
        validate(date);
        this.date = date;
        this.dayOfWeek = getDayOfWeek(date);
    }

    private void validate(int date) {
        if (date < MIN_DATE.getValue() || MAX_DATE.getValue() < date) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    private DayOfWeek getDayOfWeek(int date) {
        LocalDate localDate = getEventLocalDate(date);
        return localDate.getDayOfWeek();
    }

    private static LocalDate getEventLocalDate(int date) {
        return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), date);
    }

    public boolean isWeekEnd() {
        return this.dayOfWeek.equals(DayOfWeek.FRIDAY) || this.dayOfWeek.equals(DayOfWeek.SATURDAY);
    }
}
