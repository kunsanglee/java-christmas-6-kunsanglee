package christmas.domain.discount.policy;

import static christmas.domain.discount.DiscountValue.GIFT_DISCOUNT;

import christmas.domain.discount.event.EventDate;
import christmas.domain.order.Order;

public class GiftDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableGiftDiscount();
    }

    @Override
    public int discount(Order order) {
        return GIFT_DISCOUNT.getValue();
    }

    @Override
    public String getEventName() {
        return EventDate.GIFT_EVENT.getName();
    }
}
