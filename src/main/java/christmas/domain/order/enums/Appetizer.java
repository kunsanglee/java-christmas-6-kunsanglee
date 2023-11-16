package christmas.domain.order.enums;

import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

public enum Appetizer implements MenuItem {
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    ;

    private final String name;
    private final int price;

    Appetizer(String name, int price) {
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
