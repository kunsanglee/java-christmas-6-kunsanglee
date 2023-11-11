package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderMenuDto;
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

    public OrderMenuDto readOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return new OrderMenuDto(inputParser.parseOrderMenu(Console.readLine()));
    }
}
