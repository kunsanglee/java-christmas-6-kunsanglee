package christmas.domain.discount;

import static christmas.domain.discount.DiscountValue.GIFT_DISCOUNT;
import static christmas.domain.discount.DiscountValue.NO_DISCOUNT;

import christmas.domain.order.Order;

public class GiftDiscount implements Discount {

    @Override
    public boolean isSupport(Order order) {
        return order.isTotalPriceAcceptableGiftDiscount();
    }

    @Override
    public int discount(Order order) {
        if (isSupport(order)) {
            return -GIFT_DISCOUNT.getValue();
        }
        return NO_DISCOUNT.getValue();
    }
}
