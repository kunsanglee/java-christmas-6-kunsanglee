package christmas.domain.service;

import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.discount.policy.ChristmasDiscount;
import christmas.domain.discount.policy.GiftDiscount;
import christmas.domain.discount.policy.StarDayDiscount;
import christmas.domain.discount.policy.WeekDayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import christmas.service.DateService;
import christmas.service.DiscountService;
import christmas.service.OrderMenuService;
import christmas.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestaurantServiceTest {

    @DisplayName("레스토랑 서비스를 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new RestaurantService(new DateService(), new OrderMenuService(),
                new DiscountService(new ChristmasDiscount(), new WeekDayDiscount(), new WeekendDiscount(),
                        new StarDayDiscount(), new GiftDiscount())))
                .doesNotThrowAnyException();
    }
}
