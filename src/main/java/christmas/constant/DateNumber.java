package christmas.constant;

public enum DateNumber {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    MIN_DATE(1),
    MAX_DATE(31),
    ;

    private final int date;

    DateNumber(int date) {
        this.date = date;
    }

    public int getValue() {
        return date;
    }
}
