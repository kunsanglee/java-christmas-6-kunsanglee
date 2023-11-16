package christmas.domain.discount.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum EventDate {
    CHRISTMAS_D_DAY_EVENT("크리스마스 디데이 할인", IntStream.rangeClosed(1, 25).boxed().collect(Collectors.toList())),
    WEEKDAY_EVENT("평일 할인", List.of()),
    WEEKEND_EVENT("주말 할인", List.of()),
    STAR_DAY_EVENT("특별 할인", Arrays.asList(3, 10, 17, 24, 25, 31)),
    GIFT_EVENT("증정 이벤트", List.of());

    private final String name;
    private final List<Integer> dates;

    EventDate(String name, List<Integer> dates) {
        this.name = name;
        this.dates = dates;
    }

    public boolean contains(int date) {
        return dates.contains(date);
    }

    public String getName() {
        return name;
    }
}

