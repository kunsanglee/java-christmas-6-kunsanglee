package christmas.domain.discount.domain;

import christmas.domain.discount.enums.EventDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DiscountDetails {

    private final Map<String, Integer> discountDetails;

    public DiscountDetails(Map<String, Integer> discountDetails) {
        this.discountDetails = discountDetails;
    }

    public Map<String, Integer> getDiscountDetails() {
        return this.discountDetails.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
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
