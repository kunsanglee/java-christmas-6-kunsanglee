package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.VisitDateDto;

public class InputView {
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public VisitDateDto readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return new VisitDateDto(inputParser.parseVisitDay(Console.readLine()));
    }
}
