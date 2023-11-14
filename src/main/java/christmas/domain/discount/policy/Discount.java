package christmas.domain.discount.policy;


import christmas.domain.order.domain.Order;

public sealed interface Discount permits ChristmasDiscount, WeekDayDiscount, WeekendDiscount, StarDayDiscount,
        GiftDiscount {
    boolean isSupport(Order order);

    int discount(Order order);

    String getEventName();
}
