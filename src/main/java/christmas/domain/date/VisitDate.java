package christmas.domain.date;

import static christmas.EventConfig.MONTH;
import static christmas.EventConfig.YEAR;
import static christmas.exception.ExceptionMessage.INVALID_VISIT_DATE;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    public VisitDate(int date) {
        this.date = createLocalDate(date);
        this.dayOfWeek = this.date.getDayOfWeek();
    }

    private LocalDate createLocalDate(int date) {
        try {
            return LocalDate.of(YEAR.getNumber(), MONTH.getNumber(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public boolean isWeekEnd() {
        return this.dayOfWeek.equals(DayOfWeek.FRIDAY) || this.dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isInEventPeriod(EventDate eventDate) {
        return eventDate.contains(this.date.getDayOfMonth());
    }

    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }
}
