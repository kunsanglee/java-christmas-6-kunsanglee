package christmas.view;


import static christmas.exception.ExceptionMessage.INVALID_ORDER_MENU;
import static christmas.exception.ExceptionMessage.INVALID_VISIT_DATE;
import static christmas.view.MenuInput.COMMA;
import static christmas.view.MenuInput.DASH;
import static christmas.view.MenuInput.REGEX;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class InputParser {

    private static final int NAME = 0;
    private static final int COUNT = 1;

    public int parseVisitDay(String inputDay) {
        try {
            return Integer.parseInt(inputDay);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public Map<String, Integer> parseOrderMenu(String input) {
        List<String> orderMenu = Arrays.stream(input.split(COMMA.getValue())).toList();
        validateRegex(orderMenu);
        return getOrderMenu(orderMenu);
    }

    private static void validateRegex(List<String> orderMenu) {
        boolean isInvalidRegex = orderMenu.stream()
                .anyMatch(menu -> !menu.matches(REGEX.getValue()));
        if (isInvalidRegex) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
        }
    }

    private static LinkedHashMap<String, Integer> getOrderMenu(List<String> orderMenu) {
        return orderMenu.stream()
                .map(menu -> menu.split(DASH.getValue()))
                .collect(Collectors.toMap(
                        menu -> menu[NAME],
                        menu -> Integer.parseInt(menu[COUNT]),
                        (oldValue, newValue) -> {
                            throw new IllegalArgumentException(INVALID_ORDER_MENU.getMessage());
                        }, LinkedHashMap::new
                ));
    }
}
