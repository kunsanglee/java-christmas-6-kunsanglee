package christmas.domain.order.enums;

public enum OrderMenuCount {
    MIN_ORDER_MENU_COUNT(1),
    MAX_ORDER_MENU_COUNT(20),
    ;

    private final int count;

    OrderMenuCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }
}
