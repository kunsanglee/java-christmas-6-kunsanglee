package christmas.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.DiscountDetails;
import christmas.domain.discount.policy.ChristmasDiscount;
import christmas.domain.discount.policy.GiftDiscount;
import christmas.domain.discount.policy.StarDayDiscount;
import christmas.domain.discount.policy.WeekDayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.service.DiscountService;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountServiceTest {

    @DisplayName("주문에 대한 할인을 적용하여 할인 금액을 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateDiscountDetailsTestArguments")
    void calculateDiscountDetailsTest(int date, Map<String, Integer> orderBook, int expectedDiscountAmount) {
        DiscountService discountService = new DiscountService(new ChristmasDiscount(), new WeekDayDiscount(),
                new WeekendDiscount(),
                new StarDayDiscount(), new GiftDiscount());

        DiscountDetails discountDetails = discountService.calculateDiscountDetails(
                new Order(new VisitDate(date), new OrderMenu(orderBook)));
        assertThat(discountDetails.getTotalDiscountAmount()).isEqualTo(expectedDiscountAmount);
    }

    static Stream<Arguments> calculateDiscountDetailsTestArguments() {
        return Stream.of(
                arguments(26, Map.of("타파스", 1, "제로콜라", 1), 0),
                arguments(3, Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1), 31_246)
        );
    }
}
