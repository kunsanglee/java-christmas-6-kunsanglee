package christmas.domain;


import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;

import christmas.domain.enums.Menu;
import christmas.domain.enums.MenuItem;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderMenu {
    public static final int MIN_COUNT = 1;
    public static final int MAX_COUNT = 20;
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
}
