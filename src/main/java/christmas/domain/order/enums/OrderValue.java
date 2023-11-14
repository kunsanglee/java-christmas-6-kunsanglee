package christmas.domain.order.enums;

public enum OrderValue {
    MIN_ORDER_AMOUNT_FOR_DISCOUNT(10_000),
    MIN_ORDER_AMOUNT_FOR_GIFT_DISCOUNT(120_000),
    ;

    private final int value;

    OrderValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
