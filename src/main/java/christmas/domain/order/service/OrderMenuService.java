package christmas.domain.order.service;

import christmas.domain.order.domain.OrderMenu;
import java.util.Map;

public class OrderMenuService {

    public OrderMenu createOrderMenu(Map<String, Integer> orderBook) {
        return new OrderMenu(orderBook);
    }
}
