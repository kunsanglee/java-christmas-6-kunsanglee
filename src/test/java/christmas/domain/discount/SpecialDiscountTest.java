package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.domain.VisitDate;
import christmas.domain.discount.policy.StarDayDiscount;
import christmas.domain.order.domain.Order;
import christmas.domain.order.domain.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SpecialDiscountTest {

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 특별한 날이면 특별 할인을 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("isSupportTrueTestArguments")
    void isSupportTrueTest(Order order) {
        StarDayDiscount starDayDiscount = new StarDayDiscount();
        boolean support = starDayDiscount.isSupport(order);
        assertThat(support).isTrue();
    }

    static Stream<Arguments> isSupportTrueTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(17), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(24), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(25), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(31), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 특별한 날이 아니면 특별 할인을 받을 수 없다.")
    @ParameterizedTest
    @MethodSource("isSupportFalseTestArguments")
    void isSupportFalseTest(Order order) {
        StarDayDiscount starDayDiscount = new StarDayDiscount();
        boolean support = starDayDiscount.isSupport(order);
        assertThat(support).isFalse();
    }

    static Stream<Arguments> isSupportFalseTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("시저샐러드", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("초코케이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(15), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(18), new OrderMenu(Map.of("바비큐립", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(30), new OrderMenu(Map.of("해산물파스타", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 특별한 날이면, 총 주문 금액에서 할인한다.")
    @ParameterizedTest
    @MethodSource("weekendDiscountTestArguments")
    void discountTest(Order order, int expectedDiscount) {
        StarDayDiscount starDayDiscount = new StarDayDiscount();
        int discount = starDayDiscount.discount(order);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> weekendDiscountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("시저샐러드", 1, "티본스테이크", 1))), 1_000),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("초코케이크", 1))), 1_000),
                arguments(new Order(new VisitDate(17), new OrderMenu(Map.of("티본스테이크", 2))), 1_000),
                arguments(new Order(new VisitDate(24), new OrderMenu(Map.of("바비큐립", 1))), 1_000),
                arguments(new Order(new VisitDate(25), new OrderMenu(Map.of("해산물파스타", 1))), 1_000),
                arguments(new Order(new VisitDate(31), new OrderMenu(Map.of("타파스", 10))), 1_000),
                arguments(new Order(new VisitDate(25), new OrderMenu(Map.of("해산물파스타", 10))), 1_000),
                arguments(new Order(new VisitDate(31), new OrderMenu(Map.of("타파스", 10))), 1_000)
        );
    }
}
