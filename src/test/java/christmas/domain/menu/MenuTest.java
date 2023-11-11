package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.order.menu.Menu;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라",
            "레드와인", "샴페인"})
    void findMenuItemByNameSuccessTest(String menuName) {
        assertThatCode(() -> Menu.findMenuItemByName(menuName))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이soup", "tapas", "샐러드싫어", "스네이크", "aasd", " ", "", "cake", "아이스 크림", "제로 콜라",
            "12312321", "APPETIZER"})
    void findMenuItemByNameFailTest(String menuName) {
        assertThatCode(() -> Menu.findMenuItemByName(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
