package christmas.domain.discount.service;


import christmas.domain.discount.domain.DiscountDetails;
import christmas.domain.discount.policy.ChristmasDiscount;
import christmas.domain.discount.policy.Discount;
import christmas.domain.discount.policy.GiftDiscount;
import christmas.domain.discount.policy.StarDayDiscount;
import christmas.domain.discount.policy.WeekDayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import christmas.domain.order.domain.Order;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountService {
    
    private final List<Discount> discounts;

    public DiscountService(ChristmasDiscount christmasDiscount,
                           WeekDayDiscount weekDayDiscount,
                           WeekendDiscount weekendDiscount,
                           StarDayDiscount starDayDiscount,
                           GiftDiscount giftDiscount) {
        this.discounts = List.of(christmasDiscount,
                weekDayDiscount,
                weekendDiscount,
                starDayDiscount,
                giftDiscount);
    }

    public DiscountDetails calculateDiscountDetails(Order order) {
        LinkedHashMap<String, Integer> details = discounts.stream()
                .filter(discount -> discount.isSupport(order))
                .collect(Collectors.toMap(
                        Discount::getEventName,
                        discount -> discount.discount(order),
                        (discount1, discount2) -> discount1,
                        LinkedHashMap::new));
        return new DiscountDetails(details);
    }
}

