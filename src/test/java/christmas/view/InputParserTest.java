package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    @DisplayName("1~31 사이의 숫자를 입력하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "31"})
    void parseVisitDaySuccessTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseVisitDay(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("1~31 사이의 숫자를 입력하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ㄱ", "내일"})
    void parseVisitDayFailTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseVisitDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("한글 메뉴이름-숫자 형식을 입력하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void parseOrderMenuSuccessTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseOrderMenu(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("한글 메뉴이름-숫자 형식을 입력하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"cola-1,bbq-2", "ㅇㅇ-1,ㄱㄱ-2", "콜라/2", "콜라,1", "콜라 2", "해산물파스타-2, 레드와인-1"})
    void parseOrderMenuFailTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseOrderMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("한글 메뉴이름-숫자 형식을 입력하면 주문 메뉴를 반환한다.")
    @ParameterizedTest
    @MethodSource("parseOrderMenuReturnArguments")
    void parseOrderMenuSuccessReturnTest(String input, Map<String, Integer> expected) {
        InputParser inputParser = new InputParser();
        Map<String, Integer> orderBook = inputParser.parseOrderMenu(input);
        assertThat(orderBook).isEqualTo(expected);
    }

    static Stream<Arguments> parseOrderMenuReturnArguments() {
        return Stream.of(
                Arguments.of("해산물파스타-2,레드와인-1,초코케이크-1", Map.of("해산물파스타", 2, "레드와인", 1, "초코케이크", 1)),
                Arguments.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1))
        );
    }
}
