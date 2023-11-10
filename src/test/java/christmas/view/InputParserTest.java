package christmas.view;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "25", "31"})
    void parseVisitDaySuccessTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseVisitDay(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ㄱ", "내일"})
    void parseVisitDayFailTest(String input) {
        InputParser inputParser = new InputParser();
        assertThatCode(() -> inputParser.parseVisitDay(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
