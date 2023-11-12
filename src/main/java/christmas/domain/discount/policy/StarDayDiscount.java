package christmas.domain.discount.policy;

import static christmas.domain.discount.DiscountValue.SPECIAL_DAY_DISCOUNT;

import christmas.domain.discount.event.EventDate;
import christmas.domain.order.Order;

public class StarDayDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isInEventPeriod(EventDate.STAR_DAY_EVENT);
    }

    @Override
    public int discount(Order order) {
        return SPECIAL_DAY_DISCOUNT.getValue();
    }

    @Override
    public String getEventName() {
        return EventDate.STAR_DAY_EVENT.getName();
    }
}
