package christmas.domain.discount.policy;

import static christmas.domain.discount.DiscountValue.WEEKEND_MAIN_COURSE_DISCOUNT;

import christmas.domain.discount.event.EventDate;
import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableDiscount() && order.isWeekend();
    }

    @Override
    public int discount(Order order) {
        return order.getMainCourseCount() * WEEKEND_MAIN_COURSE_DISCOUNT.getValue();
    }

    @Override
    public String getEventName() {
        return EventDate.WEEKEND_EVENT.getName();
    }
}
