package christmas.domain.discount;


import static christmas.domain.discount.DiscountValue.CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT;
import static christmas.domain.discount.DiscountValue.CHRISTMAS_D_DAY_DEFAULT_DISCOUNT;
import static christmas.domain.discount.DiscountValue.NO_DISCOUNT;

import christmas.domain.date.EventDate;
import christmas.domain.order.Order;

public class ChristmasDiscount implements Discount {

    private static final int DISCOUNT_START_DAY_OFFSET = 1;

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isInEventPeriod(EventDate.CHRISTMAS_D_DAY_EVENT);
    }

    @Override
    public int discount(Order order) {
        if (isSupport(order)) {
            return -(CHRISTMAS_D_DAY_DEFAULT_DISCOUNT.getValue() + CHRISTMAS_D_DAY_ADDITIONAL_DISCOUNT.getValue() * (
                    order.getDayOfMonth() - DISCOUNT_START_DAY_OFFSET));
        }
        return NO_DISCOUNT.getValue();
    }
}
