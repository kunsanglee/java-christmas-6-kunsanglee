package christmas.domain.date;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum EventDate {
    STAR_DAY_EVENT(Arrays.asList(3, 10, 17, 24, 25, 31)),
    CHRISTMAS_D_DAY_EVENT(IntStream.rangeClosed(1, 25).boxed().collect(Collectors.toList()));

    private final List<Integer> dates;

    EventDate(List<Integer> dates) {
        this.dates = dates;
    }

    public boolean contains(int date) {
        return dates.contains(date);
    }
}

