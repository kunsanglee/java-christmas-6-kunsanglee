package christmas.view;

import static christmas.domain.discount.enums.DiscountValue.DEFAULT_GIFT_COUNT;
import static christmas.domain.discount.enums.DiscountValue.NO_DISCOUNT_AMOUNT;
import static christmas.domain.discount.enums.DiscountValue.RECOVER_GIFT_DISCOUNT_AMOUNT;
import static christmas.view.message.OutputMessage.DISCOUNTED_TOTAL_AMOUNT;
import static christmas.view.message.OutputMessage.DISCOUNTED_TOTAL_AMOUNT_TITLE;
import static christmas.view.message.OutputMessage.DISCOUNT_DETAIL;
import static christmas.view.message.OutputMessage.DISCOUNT_DETAIL_TITLE;
import static christmas.view.message.OutputMessage.EVENT_BADGE_TITLE;
import static christmas.view.message.OutputMessage.GIFT_MENU;
import static christmas.view.message.OutputMessage.GIFT_MENU_TITLE;
import static christmas.view.message.OutputMessage.NO_DISCOUNT;
import static christmas.view.message.OutputMessage.ORDER_MENU_ITEM;
import static christmas.view.message.OutputMessage.ORDER_MENU_TITLE;
import static christmas.view.message.OutputMessage.RESTAURANT_DISCOUNT_PREVIEW;
import static christmas.view.message.OutputMessage.RESTAURANT_HELLO;
import static christmas.view.message.OutputMessage.TOTAL_AMOUNT;
import static christmas.view.message.OutputMessage.TOTAL_AMOUNT_TITLE;
import static christmas.view.message.OutputMessage.TOTAL_DISCOUNT_AMOUNT;
import static christmas.view.message.OutputMessage.TOTAL_DISCOUNT_AMOUNT_TITLE;

import christmas.EventConfig;
import christmas.domain.discount.enums.EventBadge;
import christmas.domain.discount.dto.DiscountDetailsDto;
import christmas.domain.order.dto.OrderMenuDto;
import christmas.domain.date.dto.VisitDateDto;
import java.util.Map;

public class OutputView {

    public void printHello() {
        System.out.println(String.format(RESTAURANT_HELLO.getMessage(), EventConfig.MONTH.getNumber()));
    }

    public void printOrderDetails(int orderTotalAmount, DiscountDetailsDto discountDetailsDto) {
        printTotalAmount(orderTotalAmount);
        printGiftMenu(discountDetailsDto);
        printDiscountDetails(discountDetailsDto);
        printTotalDiscountAmount(discountDetailsDto);
        printDiscountedTotalAmount(orderTotalAmount, discountDetailsDto);
        printEventBadge(discountDetailsDto);
    }

    public void printEventPreview(VisitDateDto visitDateDto) {
        printWithNewLine(String.format(RESTAURANT_DISCOUNT_PREVIEW.getMessage(), EventConfig.MONTH.getNumber(),
                visitDateDto.date()));
    }

    public void printMenu(OrderMenuDto orderMenuDto) {
        printWithNewLine(ORDER_MENU_TITLE.getMessage());
        orderMenuDto.orderMenu()
                .forEach((key, value) -> System.out.println(String.format(ORDER_MENU_ITEM.getMessage(), key, value)));
    }

    private void printTotalAmount(int orderTotalAmount) {
        printWithNewLine(TOTAL_AMOUNT_TITLE.getMessage());
        System.out.println(String.format(TOTAL_AMOUNT.getMessage(), orderTotalAmount));
    }

    private void printGiftMenu(DiscountDetailsDto discountDetailsDto) {
        printWithNewLine(GIFT_MENU_TITLE.getMessage());
        if (discountDetailsDto.isContainGift()) {
            System.out.println(String.format(GIFT_MENU.getMessage(), DEFAULT_GIFT_COUNT.getValue()));
            return;
        }
        System.out.println(NO_DISCOUNT.getMessage());
    }

    private void printDiscountDetails(DiscountDetailsDto discountDetailsDto) {
        printWithNewLine(DISCOUNT_DETAIL_TITLE.getMessage());
        Map<String, Integer> detail = discountDetailsDto.detail();
        if (detail.isEmpty()) {
            System.out.println(NO_DISCOUNT.getMessage());
            return;
        }
        detail.forEach((discountName, discountAmount) -> {
            if (discountAmount != NO_DISCOUNT_AMOUNT.getValue()) {
                System.out.println(String.format(DISCOUNT_DETAIL.getMessage(), discountName, discountAmount));
            }
        });
    }

    private void printTotalDiscountAmount(DiscountDetailsDto discountDetailsDto) {
        printWithNewLine(TOTAL_DISCOUNT_AMOUNT_TITLE.getMessage());
        int totalDiscountAmount = discountDetailsDto.getTotalDiscountAmount();
        if (totalDiscountAmount < NO_DISCOUNT_AMOUNT.getValue()) {
            System.out.println(String.format(TOTAL_DISCOUNT_AMOUNT.getMessage(), totalDiscountAmount));
            return;
        }
        System.out.println(NO_DISCOUNT.getMessage());
    }

    private void printDiscountedTotalAmount(int orderTotalAmount, DiscountDetailsDto discountDetailsDto) {
        printWithNewLine(DISCOUNTED_TOTAL_AMOUNT_TITLE.getMessage());
        int totalDiscountAmount = discountDetailsDto.getTotalDiscountAmount();
        if (discountDetailsDto.isContainGift()) {
            totalDiscountAmount -= RECOVER_GIFT_DISCOUNT_AMOUNT.getValue();
        }
        System.out.println(String.format(DISCOUNTED_TOTAL_AMOUNT.getMessage(), orderTotalAmount - totalDiscountAmount));
    }

    private void printEventBadge(DiscountDetailsDto discountDetailsDto) {
        printWithNewLine(String.format(EVENT_BADGE_TITLE.getMessage(), EventConfig.MONTH.getNumber()));
        int totalDiscountAmount = discountDetailsDto.getTotalDiscountAmount();
        String badge = EventBadge.getBadge(totalDiscountAmount);
        System.out.println(badge);
    }

    private static void printWithNewLine(String message) {
        System.out.println();
        System.out.println(message);
    }

    public void printErrorMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
