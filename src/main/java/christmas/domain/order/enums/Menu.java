package christmas.domain.order.enums;

import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    APPETIZER(Arrays.asList(Appetizer.values())),
    MAIN_COURSE(Arrays.asList(MainCourse.values())),
    DESSERT(Arrays.asList(Dessert.values())),
    DRINK(Arrays.asList(Drink.values()));

    private final List<MenuItem> menuItem;

    Menu(List<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public boolean contains(String item) {
        return menuItem.stream()
                .anyMatch(menuItem -> menuItem.getName().equals(item));
    }

    public static MenuItem findMenuItemByName(String name) {
        return Arrays.stream(Menu.values())
                .flatMap(menu -> menu.getMenuItem().stream())
                .filter(menuItem -> menuItem.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_MENU.getMessage()));
    }
}
