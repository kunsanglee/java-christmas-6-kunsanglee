package christmas.domain.order.menu;

public interface MenuItem {
    String getName();

    int getPrice();

    boolean isSameName(String name);

    void validate(String name);
}
