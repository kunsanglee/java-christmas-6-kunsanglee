package christmas.view;

import static christmas.view.message.InputMessage.READ_DATE_MESSAGE;
import static christmas.view.message.InputMessage.READ_ORDER_MENU_MESSAGE;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderMenuDto;
import christmas.dto.VisitDateDto;
import christmas.view.util.InputParser;

public class InputView {

    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public VisitDateDto readDate() {
        System.out.println();
        System.out.println(READ_DATE_MESSAGE.getMessage());
        return new VisitDateDto(inputParser.parseVisitDay(Console.readLine()));
    }

    public OrderMenuDto readOrderMenu() {
        System.out.println();
        System.out.println(READ_ORDER_MENU_MESSAGE.getMessage());
        return new OrderMenuDto(inputParser.parseOrderMenu(Console.readLine()));
    }
}
