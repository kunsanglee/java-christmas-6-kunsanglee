package christmas.domain.order.enums;

import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

public enum Drink implements MenuItem {
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAIGN("샴페인", 25_000),
    ;

    private final String name;
    private final int price;

    Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isSameName(String name) {
        validate(name);
        return this.name.equals(name);
    }

    @Override
    public void validate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }
    }
}
