package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDayTest {

    @DisplayName("입력한 날짜가 1 ~ 31 범위면 에러를 반환하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 25, 31})
    void createSuccessTest(int day) {
        assertThatCode(() -> new VisitDate(day))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력한 날짜가 1 ~ 31 범위가 아니면 에러를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void createFailTest(int day) {
        assertThatCode(() -> new VisitDate(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @MethodSource("dayOfWeekTestArguments")
    void dayOfWeekTest(int day, boolean expectedIsWeekEnd) {
        VisitDate visitDate = new VisitDate(day);
        assertThat(visitDate.isWeekEnd()).isEqualTo(expectedIsWeekEnd);
    }

    static Stream<Arguments> dayOfWeekTestArguments() {
        return Stream.of(
                arguments(1, true),
                arguments(2, true),
                arguments(3, false),
                arguments(4, false),
                arguments(5, false),
                arguments(6, false),
                arguments(7, false)
        );
    }
}
