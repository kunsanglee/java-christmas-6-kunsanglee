package christmas.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.date.domain.VisitDate;
import christmas.domain.date.service.DateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateServiceTest {

    @DisplayName("일정 서비스를 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new DateService())
                .doesNotThrowAnyException();
    }

    @DisplayName("올바른 일정을 입력하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 25, 26, 27, 28, 29, 30, 31})
    void saveVisitDateSuccessTest(int date) {
        DateService dateService = new DateService();
        assertThatCode(() -> dateService.createCusomterVisitDate(date))
                .doesNotThrowAnyException();
    }

    @DisplayName("올바르지 않은 일정을 입력하면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 33})
    void saveVisitDateFailTest(int date) {
        DateService dateService = new DateService();
        assertThatCode(() -> dateService.createCusomterVisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("반환된 방문 날짜 객체는 입력한 날짜를 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 25, 26, 27, 28, 29, 30, 31})
    void returnVisitDateTest(int date) {
        DateService dateService = new DateService();
        VisitDate cusomterVisitDate = dateService.createCusomterVisitDate(date);
        assertThat(cusomterVisitDate.getDayOfMonth()).isEqualTo(date);
    }
}
