package christmas.domain.order.domain;


import static christmas.domain.order.enums.OrderMenuCount.MAX_ORDER_MENU_COUNT;
import static christmas.domain.order.enums.OrderMenuCount.MIN_ORDER_MENU_COUNT;
import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

import christmas.domain.order.enums.Dessert;
import christmas.domain.order.enums.MainCourse;
import christmas.domain.order.enums.Menu;
import christmas.domain.order.enums.MenuItem;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMenu {

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
                .anyMatch(count -> count < MIN_ORDER_MENU_COUNT.getCount());
    }

    private boolean isMoreThanMaxWholeOrderCounts(Map<String, Integer> orderBook) {
        return MAX_ORDER_MENU_COUNT.getCount() < orderBook.values().stream()
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
