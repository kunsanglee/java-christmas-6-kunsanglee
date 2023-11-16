package christmas.domain.discount.domain;

import christmas.domain.discount.enums.EventDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscountDetails {

    private final Map<String, Integer> discountDetails;

    public DiscountDetails(Map<String, Integer> discountDetails) {
        this.discountDetails = new LinkedHashMap<>(discountDetails);
    }

    public Map<String, Integer> getDiscountDetails() {
        LinkedHashMap<String, Integer> copy = new LinkedHashMap<>(this.discountDetails);
        return Collections.unmodifiableMap(copy);
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
