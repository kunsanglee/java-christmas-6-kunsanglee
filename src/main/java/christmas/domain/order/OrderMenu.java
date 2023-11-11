package christmas.domain.order;


import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

import christmas.domain.order.menu.Dessert;
import christmas.domain.order.menu.MainCourse;
import christmas.domain.order.menu.Menu;
import christmas.domain.order.menu.MenuItem;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMenu {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;
    private final Map<MenuItem, Integer> orderBook;

    public OrderMenu(Map<String, Integer> orderBook) {
        validate(orderBook);
        this.orderBook = convertToMenuItem(orderBook);
    }

    private void validate(Map<String, Integer> orderBook) {
        validateHasInvalidMenu(orderBook);
        if (containsOnlyDrink(orderBook) || isMoreThanMaxWholeOrderCounts(orderBook)
                || isLessThanMinOrderCount(orderBook)) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }
    }

    private void validateHasInvalidMenu(Map<String, Integer> orderBook) {
        orderBook.keySet()
                .forEach(Menu::findMenuItemByName);
    }

    private boolean containsOnlyDrink(Map<String, Integer> orderBook) {
        return orderBook.keySet().stream()
                .allMatch(Menu.DRINK::contains);
    }

    private boolean isLessThanMinOrderCount(Map<String, Integer> orderBook) {
        return orderBook.values().stream()
                .anyMatch(count -> count < MIN_COUNT);
    }

    private boolean isMoreThanMaxWholeOrderCounts(Map<String, Integer> orderBook) {
        return MAX_COUNT < orderBook.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Map<MenuItem, Integer> convertToMenuItem(Map<String, Integer> orderBook) {
        Map<MenuItem, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : orderBook.entrySet()) {
            MenuItem menu = Menu.findMenuItemByName(entry.getKey());
            result.put(menu, entry.getValue());
        }
        return result;
    }

    public int getTotalPrice() {
        return this.orderBook.entrySet().stream()
                .mapToInt(menuItem -> menuItem.getKey().getPrice() * menuItem.getValue())
                .sum();
    }

    public int getDessertCount() {
        return this.orderBook.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Dessert)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getMainCourseCount() {
        return this.orderBook.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof MainCourse)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
