package christmas.domain;


import christmas.dto.OrderMenuDto;
import java.util.Map;

public class OrderMenu {
    private final OrderBook orderBook;

    public OrderMenu(OrderMenuDto orderMenuDto) {
        Map<String, Integer> orderBook = orderMenuDto.orderMenu();
        this.orderBook = new OrderBook(orderBook);
    }
}
