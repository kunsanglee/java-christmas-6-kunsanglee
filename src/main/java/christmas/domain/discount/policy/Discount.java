package christmas.domain.discount.policy;


import christmas.domain.order.domain.Order;

public interface Discount {
    boolean isSupport(Order order);

    int discount(Order order);

    String getEventName();
}
