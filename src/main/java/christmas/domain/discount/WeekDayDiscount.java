package christmas.domain.discount;

import static christmas.domain.discount.DiscountValue.NO_DISCOUNT;
import static christmas.domain.discount.DiscountValue.WEEKDAY_DESSERT_DISCOUNT;

import christmas.domain.order.Order;

public class WeekDayDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && !order.isWeekend();
    }

    @Override
    public int discount(Order order) {
        if (isSupport(order)) {
            return -(order.getDessertCount() * WEEKDAY_DESSERT_DISCOUNT.getValue());
        }
        return NO_DISCOUNT.getValue();
    }
}
