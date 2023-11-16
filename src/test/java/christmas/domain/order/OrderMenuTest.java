package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.order.domain.OrderMenu;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderMenuTest {

    @DisplayName("올바른 주문메뉴를 입력하면 에러가 발생하지 않는다.")
    @Test
    void createSuccessTest() {
        assertThatCode(() -> new OrderMenu((Map.of("해산물파스타", 2, "레드와인", 1, "초코케이크", 1))))
                .doesNotThrowAnyException();
    }

    @DisplayName("올바르지 않은 주문메뉴를 입력하면 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("createFailTestArguments")
    void createFailTest(Map<String, Integer> orderBook) {
        assertThatCode(() -> new OrderMenu(orderBook))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    static Stream<Arguments> createFailTestArguments() {
        return Stream.of(
                arguments(Map.of("없는메뉴", 1)),
                arguments(Map.of("제로콜라", 0, "양송이수프", 1)),
                arguments(Map.of("제로콜라", -1, "양송이수프", 1)),
                arguments(Map.of("제로콜라", 1, "샴페인", 1)),
                arguments(Map.of("제로콜라", 1, "양송이수프", 20)),
                arguments(Map.of("양송이수프", 100)
                ));
    }
}
