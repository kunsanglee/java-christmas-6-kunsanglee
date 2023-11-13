package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.discount.event.EventBadge;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventBadgeTest {

    @ParameterizedTest
    @MethodSource("getBadgeTestArguments")
    void getBadgeTest(int totalDiscountAmount, String expectedEventBadge) {
        String badge = EventBadge.getBadge(totalDiscountAmount);
        assertThat(badge).isEqualTo(expectedEventBadge);
    }

    static Stream<Arguments> getBadgeTestArguments() {
        return Stream.of(
                arguments(0, "없음"),
                arguments(5_000, "별"),
                arguments(10_000, "트리"),
                arguments(20_000, "산타"),
                arguments(100_000, "산타")
        );
    }
}
