package christmas.domain.discount;

public enum DiscountValue {
    CHRISTMAS_D_DAY_DEFAULT_DISCOUNT(1_000),
    CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT(100),
    DISCOUNT_START_DAY_OFFSET(1),
    WEEKDAY_DESSERT_DISCOUNT(2_023),
    WEEKEND_MAIN_COURSE_DISCOUNT(2_023),
    SPECIAL_DAY_DISCOUNT(1_000),
    GIFT_DISCOUNT(25_000),
    RECOVER_GIFT_DISCOUNT_AMOUNT(25_000),
    DEFAULT_GIFT_COUNT(1),
    NO_DISCOUNT_AMOUNT(0),
    ;

    private final int value;

    DiscountValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
