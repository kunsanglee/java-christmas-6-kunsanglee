package christmas.domain.discount.policy;


import static christmas.domain.discount.enums.DiscountValue.CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT;
import static christmas.domain.discount.enums.DiscountValue.CHRISTMAS_D_DAY_DEFAULT_DISCOUNT;
import static christmas.domain.discount.enums.DiscountValue.DISCOUNT_START_DAY_OFFSET;

import christmas.domain.discount.enums.EventDate;
import christmas.domain.order.domain.Order;

public final class ChristmasDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isInEventPeriod(EventDate.CHRISTMAS_D_DAY_EVENT);
    }

    @Override
    public int discount(Order order) {
        return CHRISTMAS_D_DAY_DEFAULT_DISCOUNT.getValue() + CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT.getValue() * (
                order.getDayOfMonth() - DISCOUNT_START_DAY_OFFSET.getValue());
    }

    @Override
    public String getEventName() {
        return EventDate.CHRISTMAS_D_DAY_EVENT.getName();
    }
}
