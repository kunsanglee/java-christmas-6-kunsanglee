package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.policy.WeekDayDiscount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WeekDayDiscountTest {

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 평일이면 평일 할인을 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("isSupportTrueTestArguments")
    void isSupportTrueTest(Order order) {
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount();
        assertThat(weekDayDiscount.isSupport(order)).isTrue();
    }

    static Stream<Arguments> isSupportTrueTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이 아니거나 방문 날이 주말이면 평일 할인을 받을 수 없다.")
    @ParameterizedTest
    @MethodSource("isSupportFalseTestArguments")
    void isSupportFalseTest(Order order) {
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount();
        assertThat(weekDayDiscount.isSupport(order)).isFalse();
    }

    static Stream<Arguments> isSupportFalseTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 평일이면, 주문한 디저트 1개당 2,023원씩 할인한다.")
    @ParameterizedTest
    @MethodSource("weekDayDiscountTestArguments")
    void discountTest(Order order, int expectedDiscount) {
        WeekDayDiscount weekDayDiscount = new WeekDayDiscount();
        int discount = weekDayDiscount.discount(order);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> weekDayDiscountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 1))), 2_023),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("초코케이크", 10))), 20_230),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("아이스크림", 2))), 4_046),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("초코케이크", 1, "아이스크림", 1))), 4_046),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("티본스테이크", 1))), 0)
        );
    }
}
