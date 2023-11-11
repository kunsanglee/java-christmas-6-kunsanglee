package christmas.domain.discount;

import static christmas.domain.discount.DiscountValue.NO_DISCOUNT;
import static christmas.domain.discount.DiscountValue.WEEKEND_MAIN_COURSE_DISCOUNT;

import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isWeekend();
    }

    @Override
    public int discount(Order order) {
        if (isSupport(order)) {
            return -(order.getMainCourseCount() * WEEKEND_MAIN_COURSE_DISCOUNT.getValue());
        }
        return NO_DISCOUNT.getValue();
    }
}
