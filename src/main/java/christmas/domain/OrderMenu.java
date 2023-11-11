package christmas.domain;


import java.util.Map;

public class OrderMenu {
    private final OrderBook orderBook;

    public OrderMenu(Map<String, Integer> orderBook) {
        this.orderBook = new OrderBook(orderBook);
    }
}
