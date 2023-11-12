package christmas.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.order.OrderMenu;
import christmas.service.OrderMenuService;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderMenuServiceTest {

    @DisplayName("주문 서비스를 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new OrderMenuService())
                .doesNotThrowAnyException();
    }

    @DisplayName("주문메뉴를 입력하면 주문한 메뉴를 담은 주문 객체를 생성한다.")
    @ParameterizedTest
    @MethodSource("createOrderMenuTestArguments")
    void createOrderMenuTest(Map<String, Integer> orderBook, int expectedTotalPrice) {
        OrderMenuService orderMenuService = new OrderMenuService();
        OrderMenu orderMenu = orderMenuService.createOrderMenu(orderBook);
        assertThat(orderMenu.getTotalPrice()).isEqualTo(expectedTotalPrice);
    }

    static Stream<Arguments> createOrderMenuTestArguments() {
        return Stream.of(
                arguments(Map.of("양송이수프", 1, "레드와인", 1), 66_000),
                arguments(Map.of("티본스테이크", 2, "레드와인", 1), 170_000),
                arguments(Map.of("초코케이크", 10, "바비큐립", 10), 690_000),
                arguments(Map.of("양송이수프", 20), 120_000),
                arguments(Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1), 142_000)
        );
    }
}
