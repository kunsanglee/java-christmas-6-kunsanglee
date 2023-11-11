package christmas.domain.enums;

import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

public enum MainCourse implements MenuItem {
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIB("바비큐립", 54_000),
    SEA_FOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    ;

    private final String name;
    private final int price;

    MainCourse(String name, int price) {
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
