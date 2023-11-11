package christmas.domain.discount;


import christmas.domain.order.Order;

public interface Discount {
    boolean isSupport(Order order);

    int discount(Order order);
}
