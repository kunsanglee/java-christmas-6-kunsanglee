package christmas.domain.enums;

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
}
