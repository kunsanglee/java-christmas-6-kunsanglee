package christmas.service;

import christmas.domain.order.OrderMenu;
import java.util.Map;

public class OrderMenuService {

    public OrderMenu createOrderMenu(Map<String, Integer> orderBook) {
        return new OrderMenu(orderBook);
    }
}
