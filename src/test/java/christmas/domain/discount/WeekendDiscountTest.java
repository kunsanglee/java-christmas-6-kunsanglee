package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WeekendDiscountTest {

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 주말이면 주말 할인을 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("isSupportTrueTestArguments")
    void isSupportTrueTest(Order order) {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        boolean support = weekendDiscount.isSupport(order);
        assertThat(support).isTrue();
    }

    static Stream<Arguments> isSupportTrueTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 평일이면 주말 할인을 받을 수 없다.")
    @ParameterizedTest
    @MethodSource("isSupportFalseTestArguments")
    void isSupportFalseTest(Order order) {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        boolean support = weekendDiscount.isSupport(order);
        assertThat(support).isFalse();
    }

    static Stream<Arguments> isSupportFalseTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1)))),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))))
        );
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날이 주말이면, 주문한 메인코스 1개당 2,023원씩 할인한다.")
    @ParameterizedTest
    @MethodSource("weekendDiscountTestArguments")
    void discountTest(Order order, int expectedDiscount) {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        int discount = weekendDiscount.discount(order);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> weekendDiscountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("해산물파스타", 1, "티본스테이크", 1))), -4_046),
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1))), -2_023),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("바비큐립", 2))), -4_046),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("해산물파스타", 1))), 0),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("크리스마스파스타", 1))), 0),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("티본스테이크", 10))), 0),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("바비큐립", 2))), 0),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("크리스마스파스타", 1))), 0)
        );
    }
}
