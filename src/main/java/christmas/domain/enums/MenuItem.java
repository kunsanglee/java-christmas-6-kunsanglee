package christmas.domain.enums;

public interface MenuItem {
    String getName();

    int getPrice();

    boolean isSameName(String name);

    void validate(String name);
}
