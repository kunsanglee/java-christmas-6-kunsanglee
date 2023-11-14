package christmas.service;

import christmas.domain.date.service.DateService;
import christmas.domain.date.domain.VisitDate;
import christmas.domain.discount.domain.DiscountDetails;
import christmas.domain.discount.service.DiscountService;
import christmas.domain.order.domain.Order;
import christmas.domain.order.domain.OrderMenu;
import christmas.domain.order.service.OrderMenuService;
import christmas.domain.discount.dto.DiscountDetailsDto;
import christmas.domain.order.dto.OrderMenuDto;
import christmas.domain.date.dto.VisitDateDto;

public class RestaurantService {

    private final DateService dateService;
    private final OrderMenuService orderService;
    private final DiscountService discountService;
    private VisitDate visitDate;
    private OrderMenu orderMenu;

    public RestaurantService(DateService dateService, OrderMenuService orderService, DiscountService discountService) {
        this.dateService = dateService;
        this.orderService = orderService;
        this.discountService = discountService;
    }

    public void saveVisitDate(VisitDateDto visitDateDto) {
        this.visitDate = dateService.createCusomterVisitDate(visitDateDto.date());
    }

    public void saveOrderMenu(OrderMenuDto orderMenuDto) {
        this.orderMenu = orderService.createOrderMenu(orderMenuDto.orderMenu());
    }

    public int getOrderTotalAmount() {
        return this.orderMenu.getTotalPrice();
    }

    public DiscountDetailsDto calculateDiscountDetails() {
        Order order = new Order(this.visitDate, this.orderMenu);
        DiscountDetails discountDetails = discountService.calculateDiscountDetails(order);
        return new DiscountDetailsDto(discountDetails.getDiscountDetails());
    }
}
