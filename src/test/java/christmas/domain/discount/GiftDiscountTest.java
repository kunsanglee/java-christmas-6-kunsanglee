package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.policy.GiftDiscount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GiftDiscountTest {

    @DisplayName("총 주문 금액이 120,000원 이상이면 증정 이벤트 대상이다.")
    @ParameterizedTest
    @MethodSource("isSupportTrueTestArguments")
    void isSupportTrueTest(Order order) {
        GiftDiscount giftDiscount = new GiftDiscount();
        boolean support = giftDiscount.isSupport(order);
        assertThat(support).isTrue();
    }

    static Stream<Arguments> isSupportTrueTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 3)))),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 15)))),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 12)))),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1)))),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1)))),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("제로콜라", 15, "바비큐립", 2)))),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))))
        );
    }

    @DisplayName("총 주문 금액이 120,000원 이하면 증정 이벤트 대상이 아니다.")
    @ParameterizedTest
    @MethodSource("isSupportFalseTestArguments")
    void isSupportFalseTest(Order order) {
        GiftDiscount giftDiscount = new GiftDiscount();
        boolean support = giftDiscount.isSupport(order);
        assertThat(support).isFalse();
    }

    static Stream<Arguments> isSupportFalseTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1)))),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 11)))),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 1)))),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("레드와인", 1, "양송이수프", 1)))),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("샴페인", 4, "초코케이크", 1)))),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("제로콜라", 14, "바비큐립", 1)))),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("해산물파스타", 1, "크리스마스파스타", 2))))
        );
    }

    @DisplayName("총 주문 금액이 120,000원 이상이면 샴페인 증정을 받아서 25,000원 할인이 추가된다.")
    @ParameterizedTest
    @MethodSource("discountTestArguments")
    void discountTest(Order order, int expectedDiscount) {
        GiftDiscount giftDiscount = new GiftDiscount();
        int discount = giftDiscount.discount(order);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> discountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 3))), 25_000),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 15))), 25_000),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 12))), 25_000),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1))), 25_000),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1))), 25_000),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("제로콜라", 15, "바비큐립", 2))), 25_000),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))), 25_000)
        );
    }
}
