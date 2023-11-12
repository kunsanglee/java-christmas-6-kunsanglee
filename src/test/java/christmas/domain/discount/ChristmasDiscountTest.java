package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.policy.ChristmasDiscount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChristmasDiscountTest {

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날짜가 1~25일 이내라면 크리스마스 디데이 할인을 적용할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,})
    void isSupportTrueTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu(Map.of("티본스테이크", 1));
        Order order = new Order(visitDate, orderMenu);
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();
        boolean support = christmasDiscount.isSupport(order);
        assertThat(support).isTrue();
    }

    @DisplayName("주문 총금액이 10,000원 이상이 아니거나 방문 날짜가 1~25일 이내가 아니라면 크리스마스 디데이 할인을 적용할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void isSupportFalseTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        OrderMenu orderMenu = new OrderMenu(Map.of("티본스테이크", 1));
        Order order = new Order(visitDate, orderMenu);
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();
        boolean support = christmasDiscount.isSupport(order);
        assertThat(support).isFalse();
    }

    @DisplayName("주문 총금액이 10,000원 이상이고 방문 날짜가 1~25일 사이라면, 기본금 1,000원을 시작으로 하루마다 100원씩 추가로 할인한다.")
    @ParameterizedTest
    @MethodSource("discountTestArguments")
    void discountTest(Order order, int expectedDiscount) {
        ChristmasDiscount christmasDiscount = new ChristmasDiscount();
        int discount = christmasDiscount.discount(order);
        assertThat(discount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> discountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 1_000),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 1_400),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 1_900),
                arguments(new Order(new VisitDate(15), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 2_400),
                arguments(new Order(new VisitDate(20), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 2_900),
                arguments(new Order(new VisitDate(25), new OrderMenu(Map.of("티본스테이크", 1, "제로콜라", 1))), 3_400),
                arguments(new Order(new VisitDate(25), new OrderMenu(Map.of("시저샐러드", 1, "제로콜라", 1))), 3_400)
        );
    }
}
