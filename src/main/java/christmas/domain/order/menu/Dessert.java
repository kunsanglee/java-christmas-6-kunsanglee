package christmas.domain.order.menu;

import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

public enum Dessert implements MenuItem {
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    ;

    private final String name;
    private final int price;

    Dessert(String name, int price) {
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
