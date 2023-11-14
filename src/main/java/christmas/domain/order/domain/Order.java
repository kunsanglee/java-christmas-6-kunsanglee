package christmas.domain.order.domain;


import static christmas.domain.order.enums.OrderValue.MIN_ORDER_AMOUNT_FOR_DISCOUNT;
import static christmas.domain.order.enums.OrderValue.MIN_ORDER_AMOUNT_FOR_GIFT_DISCOUNT;

import christmas.domain.date.domain.VisitDate;
import christmas.domain.discount.enums.EventDate;

public class Order {

    private final VisitDate visitDate;
    private final OrderMenu orderMenu;

    public Order(VisitDate visitDate, OrderMenu orderMenu) {
        this.visitDate = visitDate;
        this.orderMenu = orderMenu;
    }

    public boolean isInEventPeriod(EventDate eventDate) {
        return this.visitDate.isInEventPeriod(eventDate);
    }

    public boolean isWeekend() {
        return this.visitDate.isWeekEnd();
    }

    public int getDayOfMonth() {
        return this.visitDate.getDayOfMonth();
    }

    public int getTotalPrice() {
        return this.orderMenu.getTotalPrice();
    }

    public boolean isTotalPriceAcceptableDiscount() {
        return MIN_ORDER_AMOUNT_FOR_DISCOUNT.getValue() <= this.getTotalPrice();
    }

    public boolean isTotalPriceAcceptableGiftDiscount() {
        return MIN_ORDER_AMOUNT_FOR_GIFT_DISCOUNT.getValue() <= this.getTotalPrice();
    }

    public int getDessertCount() {
        return this.orderMenu.getDessertCount();
    }

    public int getMainCourseCount() {
        return this.orderMenu.getMainCourseCount();
    }
}
