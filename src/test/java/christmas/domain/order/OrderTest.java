package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.date.EventDate;
import christmas.domain.date.VisitDate;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderTest {

    @DisplayName("방문 날짜와 주문이 유효하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createSuccessTestArguments")
    void createSuccessTest(int date, Map<String, Integer> orderBook) {
        assertThatCode(() -> new Order(new VisitDate(date), new OrderMenu(orderBook)))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> createSuccessTestArguments() {
        return Stream.of(
                arguments(1, Map.of("양송이수프", 1)),
                arguments(31, Map.of("양송이수프", 20))
        );
    }

    @DisplayName("방문 날짜와 주문이 유효하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("createFailTestArguments")
    void createFailTest(int date, Map<String, Integer> orderBook, String exceptionMessage) {
        assertThatCode(() -> new Order(new VisitDate(date), new OrderMenu(orderBook)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(exceptionMessage);
    }

    static Stream<Arguments> createFailTestArguments() {
        return Stream.of(
                arguments(0, Map.of("양송이수프", 1), "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
                arguments(32, Map.of("양송이수프", 20), "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
                arguments(1, Map.of("양송이수프", 0), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
                arguments(1, Map.of("없는메뉴", 10), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
                arguments(31, Map.of("양송이수프", 21), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        );
    }

    @DisplayName("특정 이벤트 날짜에 해당하는지 반환한다.")
    @ParameterizedTest
    @MethodSource("isInEventPeriodTestArguments")
    void isInEventPeriodTest(Order order, EventDate eventDate, boolean expected) {
        boolean inEventPeriod = order.isInEventPeriod(eventDate);
        assertThat(inEventPeriod).isEqualTo(expected);
    }

    static Stream<Arguments> isInEventPeriodTestArguments() {
        return Stream.of(
                arguments(setOrder(1), EventDate.CHRISTMAS_D_DAY_EVENT, true),
                arguments(setOrder(2), EventDate.CHRISTMAS_D_DAY_EVENT, true),
                arguments(setOrder(25), EventDate.CHRISTMAS_D_DAY_EVENT, true),
                arguments(setOrder(26), EventDate.CHRISTMAS_D_DAY_EVENT, false),
                arguments(setOrder(1), EventDate.STAR_DAY_EVENT, false),
                arguments(setOrder(3), EventDate.STAR_DAY_EVENT, true),
                arguments(setOrder(10), EventDate.STAR_DAY_EVENT, true),
                arguments(setOrder(17), EventDate.STAR_DAY_EVENT, true),
                arguments(setOrder(24), EventDate.STAR_DAY_EVENT, true),
                arguments(setOrder(25), EventDate.STAR_DAY_EVENT, true),
                arguments(setOrder(30), EventDate.STAR_DAY_EVENT, false),
                arguments(setOrder(31), EventDate.STAR_DAY_EVENT, true)
        );
    }

    private static Order setOrder(int date) {
        return new Order(new VisitDate(date), new OrderMenu(Map.of("양송이수프", 1)));
    }

    @DisplayName("주말인지 반환한다.")
    @ParameterizedTest
    @MethodSource("isWeekendTestArguments")
    void isWeekendTest(Order order, boolean expected) {
        boolean isWeekend = order.isWeekend();
        assertThat(isWeekend).isEqualTo(expected);
    }

    static Stream<Arguments> isWeekendTestArguments() {
        return Stream.of(
                arguments(setOrder(1), true),
                arguments(setOrder(2), true),
                arguments(setOrder(3), false),
                arguments(setOrder(4), false),
                arguments(setOrder(5), false),
                arguments(setOrder(6), false),
                arguments(setOrder(7), false)
        );
    }

    @DisplayName("방문 날짜를 반환한다.")
    @ParameterizedTest
    @MethodSource("getDayOfMonthTestArguments")
    void getDayOfMonthTest(Order order, int expectedDate) {
        int dayOfMonth = order.getDayOfMonth();
        assertThat(dayOfMonth).isEqualTo(expectedDate);
    }

    static Stream<Arguments> getDayOfMonthTestArguments() {
        return Stream.of(
                arguments(setOrder(1), 1),
                arguments(setOrder(2), 2),
                arguments(setOrder(3), 3),
                arguments(setOrder(4), 4),
                arguments(setOrder(5), 5),
                arguments(setOrder(6), 6),
                arguments(setOrder(7), 7)
        );
    }

    @DisplayName("주문 총 금액을 반환한다.")
    @ParameterizedTest
    @MethodSource("getTotalPriceTestArguments")
    void getTotalPriceTest(Order order, int expectedTotalPrice) {
        int totalPrice = order.getTotalPrice();
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    static Stream<Arguments> getTotalPriceTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 3))), 165_000),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 15))), 120_000),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 12))), 180_000),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1))), 126_000),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1))), 140_000),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("제로콜라", 15, "바비큐립", 2))), 153_000),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))), 120_000),
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("티본스테이크", 1))), 55_000),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 11))), 88_000),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("초코케이크", 1))), 15_000),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("레드와인", 1, "양송이수프", 1))), 66_000),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("샴페인", 4, "초코케이크", 1))), 115_000),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("제로콜라", 14, "바비큐립", 1))), 96_000),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("해산물파스타", 1, "크리스마스파스타", 2))), 85_000)
        );
    }

    @DisplayName("할인 전 총 주문 금액이 10,000원 이상인지 반환한다.")
    @ParameterizedTest
    @MethodSource("isTotalPriceAcceptableDiscountTestArguments")
    void isTotalPriceAcceptableDiscountTest(Order order, boolean expected) {
        boolean totalPriceAcceptableDiscount = order.isTotalPriceAcceptableDiscount();
        assertThat(totalPriceAcceptableDiscount).isEqualTo(expected);
    }

    static Stream<Arguments> isTotalPriceAcceptableDiscountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("아이스크림", 1))), false),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 1))), false),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("양송이수프", 1))), false),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("양송이수프", 1))), false),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("시저샐러드", 1))), false),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("타파스", 1))), false),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("제로콜라", 1, "타파스", 1))), false),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("제로콜라", 1, "아이스크림", 1))), false),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 1))), true),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("초코케이크", 1))), true),
                arguments(new Order(new VisitDate(11), new OrderMenu(Map.of("샴페인", 1, "초코케이크", 1))), true),
                arguments(new Order(new VisitDate(12), new OrderMenu(Map.of("해산물파스타", 1, "크리스마스파스타", 1))), true),
                arguments(new Order(new VisitDate(13), new OrderMenu(Map.of("레드와인", 1, "양송이수프", 1))), true),
                arguments(new Order(new VisitDate(14), new OrderMenu(Map.of("해산물파스타", 1, "크리스마스파스타", 1))), true)
        );
    }

    @DisplayName("할인 전 총 주문 금액이 120,000원 이상인지 반환한다.")
    @ParameterizedTest
    @MethodSource("isTotalPriceAcceptableGiftDiscountTestArguments")
    void isTotalPriceAcceptableGiftDiscountTest(Order order, boolean expected) {
        boolean totalPriceAcceptableGiftDiscount = order.isTotalPriceAcceptableGiftDiscount();
        assertThat(totalPriceAcceptableGiftDiscount).isEqualTo(expected);
    }

    static Stream<Arguments> isTotalPriceAcceptableGiftDiscountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("아이스크림", 1))), false),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 1))), false),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("양송이수프", 1))), false),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("양송이수프", 1))), false),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("시저샐러드", 1))), false),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("타파스", 1))), false),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("제로콜라", 1, "타파스", 1))), false),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("제로콜라", 1, "아이스크림", 1))), false),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 2, "아이스크림", 2))), true),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("초코케이크", 10))), true),
                arguments(new Order(new VisitDate(11), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1))), true),
                arguments(new Order(new VisitDate(12), new OrderMenu(Map.of("해산물파스타", 3, "크리스마스파스타", 1))), true),
                arguments(new Order(new VisitDate(13), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1))), true),
                arguments(new Order(new VisitDate(14), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))), true)
        );
    }

    @DisplayName("주문한 메뉴에 포함된 디저트의 개수를 반환한다.")
    @ParameterizedTest
    @MethodSource("getDessertCountTestArguments")
    void getDessertCountTest(Order order, int count) {
        int dessertCount = order.getDessertCount();
        assertThat(dessertCount).isEqualTo(count);
    }

    static Stream<Arguments> getDessertCountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("아이스크림", 1))), 1),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 1))), 0),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("시저샐러드", 1))), 0),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("타파스", 1))), 0),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("제로콜라", 1, "타파스", 1))), 0),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("제로콜라", 1, "아이스크림", 1))), 1),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 2, "아이스크림", 2))), 2),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("초코케이크", 10))), 10),
                arguments(new Order(new VisitDate(11), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1))), 1),
                arguments(new Order(new VisitDate(12), new OrderMenu(Map.of("해산물파스타", 3, "크리스마스파스타", 1))), 0),
                arguments(new Order(new VisitDate(13), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(14), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))), 0)
        );
    }

    @DisplayName("주문한 메뉴에 포함된 메인코스의 개수를 반환한다.")
    @ParameterizedTest
    @MethodSource("getMainCourseCountTestArguments")
    void getMainCourseCountTest(Order order, int count) {
        int mainCourseCount = order.getMainCourseCount();
        assertThat(mainCourseCount).isEqualTo(count);
    }

    static Stream<Arguments> getMainCourseCountTestArguments() {
        return Stream.of(
                arguments(new Order(new VisitDate(1), new OrderMenu(Map.of("아이스크림", 1))), 0),
                arguments(new Order(new VisitDate(2), new OrderMenu(Map.of("시저샐러드", 1))), 0),
                arguments(new Order(new VisitDate(3), new OrderMenu(Map.of("양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(4), new OrderMenu(Map.of("양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(5), new OrderMenu(Map.of("시저샐러드", 1))), 0),
                arguments(new Order(new VisitDate(6), new OrderMenu(Map.of("타파스", 1))), 0),
                arguments(new Order(new VisitDate(7), new OrderMenu(Map.of("제로콜라", 1, "타파스", 1))), 0),
                arguments(new Order(new VisitDate(8), new OrderMenu(Map.of("제로콜라", 1, "아이스크림", 1))), 0),
                arguments(new Order(new VisitDate(9), new OrderMenu(Map.of("티본스테이크", 2, "아이스크림", 2))), 2),
                arguments(new Order(new VisitDate(10), new OrderMenu(Map.of("초코케이크", 10))), 0),
                arguments(new Order(new VisitDate(11), new OrderMenu(Map.of("샴페인", 5, "초코케이크", 1))), 0),
                arguments(new Order(new VisitDate(12), new OrderMenu(Map.of("해산물파스타", 3, "크리스마스파스타", 1))), 4),
                arguments(new Order(new VisitDate(13), new OrderMenu(Map.of("레드와인", 2, "양송이수프", 1))), 0),
                arguments(new Order(new VisitDate(14), new OrderMenu(Map.of("해산물파스타", 2, "크리스마스파스타", 2))), 4)
        );
    }
}
