package christmas.domain.discount.policy;

import static christmas.domain.discount.DiscountValue.WEEKDAY_DESSERT_DISCOUNT;

import christmas.domain.discount.event.EventDate;
import christmas.domain.order.Order;

public class WeekDayDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && !order.isWeekend();
    }

    @Override
    public int discount(Order order) {
        return order.getDessertCount() * WEEKDAY_DESSERT_DISCOUNT.getValue();
    }

    @Override
    public String getEventName() {
        return EventDate.WEEKDAY_EVENT.getName();
    }
}
