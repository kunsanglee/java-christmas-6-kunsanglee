package christmas.domain.discount;

public enum DiscountValue {
    CHRISTMAS_D_DAY_DEFAULT_DISCOUNT(1_000),
    CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT(100),
    WEEKDAY_DESSERT_DISCOUNT(2_023),
    WEEKEND_MAIN_COURSE_DISCOUNT(2_023),
    SPECIAL_DAY_DISCOUNT(1_000),
    GIFT_DISCOUNT(25_000),
    NO_DISCOUNT(0),
    ;

    private final int value;

    DiscountValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
