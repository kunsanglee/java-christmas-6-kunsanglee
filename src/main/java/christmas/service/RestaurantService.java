package christmas.service;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.DiscountDetails;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import christmas.dto.DiscountDetailsDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.VisitDateDto;

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
