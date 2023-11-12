package christmas.domain.discount;

import christmas.domain.discount.event.EventDate;
import java.util.Map;

public class DiscountDetails {

    private final Map<String, Integer> discountDetails;

    public DiscountDetails(Map<String, Integer> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public Map<String, Integer> getDiscountDetails() {
        return this.discountDetails;
    }

    public int getTotalDiscountAmount() {
        return this.discountDetails.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isContainGift() {
        return this.discountDetails.containsKey(EventDate.GIFT_EVENT.getName());
    }
}
