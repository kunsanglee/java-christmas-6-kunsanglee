package christmas.domain.discount;

import static christmas.domain.discount.DiscountValue.NO_DISCOUNT;
import static christmas.domain.discount.DiscountValue.SPECIAL_DAY_DISCOUNT;

import christmas.domain.date.EventDate;
import christmas.domain.order.Order;

public class StarDayDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isInEventPeriod(EventDate.STAR_DAY_EVENT);
    }

    @Override
    public int discount(Order order) {
        if (isSupport(order)) {
            return -SPECIAL_DAY_DISCOUNT.getValue();
        }
        return NO_DISCOUNT.getValue();
    }
}
