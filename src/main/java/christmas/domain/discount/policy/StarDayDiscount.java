package christmas.domain.discount.policy;

import static christmas.domain.discount.enums.DiscountValue.SPECIAL_DAY_DISCOUNT;

import christmas.domain.discount.enums.EventDate;
import christmas.domain.order.domain.Order;

public final class StarDayDiscount implements Discount {

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
