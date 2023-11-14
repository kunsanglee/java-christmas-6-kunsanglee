package christmas.view.message;

public enum OutputMessage {
    RESTAURANT_HELLO("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    RESTAURANT_DISCOUNT_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_TITLE("<주문 메뉴>"),
    ORDER_MENU_ITEM("%s %d개"),
    TOTAL_AMOUNT_TITLE("<할인 전 총주문 금액>"),
    TOTAL_AMOUNT("%,d원"),
    GIFT_MENU_TITLE("<증정 메뉴>"),
    GIFT_MENU("샴페인 %d개"),
    DISCOUNT_DETAIL_TITLE("<혜택 내역>"),
    DISCOUNT_DETAIL("%s: -%,d원"),
    TOTAL_DISCOUNT_AMOUNT_TITLE("<총혜택 금액>"),
    TOTAL_DISCOUNT_AMOUNT("-%,d원"),
    DISCOUNTED_TOTAL_AMOUNT_TITLE("<할인 후 예상 결제 금액>"),
    DISCOUNTED_TOTAL_AMOUNT("%,d원"),
    EVENT_BADGE_TITLE("<%d월 이벤트 배지>"),
    NO_DISCOUNT("없음"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
