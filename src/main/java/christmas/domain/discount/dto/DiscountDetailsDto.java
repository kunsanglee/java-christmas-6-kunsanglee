package christmas.domain.discount.dto;

import christmas.domain.discount.enums.EventDate;
import java.util.Map;

public record DiscountDetailsDto(Map<String, Integer> detail) {

    public int getTotalDiscountAmount() {
        return this.detail.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isContainGift() {
        return this.detail.containsKey(EventDate.GIFT_EVENT.getName());
    }
}
