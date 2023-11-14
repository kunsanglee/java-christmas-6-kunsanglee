package christmas.controller;

import christmas.domain.discount.dto.DiscountDetailsDto;
import christmas.domain.order.dto.OrderMenuDto;
import christmas.domain.date.dto.VisitDateDto;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class RestaurantController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RestaurantService restaurantService;

    public RestaurantController(InputView inputView, OutputView outputView, RestaurantService restaurantService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantService = restaurantService;
    }

    public void run() {
        printHello();
        VisitDateDto visitDateDto = getVisitDateDto();
        OrderMenuDto orderMenuDto = getOrderMenuDto();
        printVisitDateAndOrderMenu(visitDateDto, orderMenuDto);
        calculateAndPrintOrderDetails();
    }

    private void printHello() {
        outputView.printHello();
    }

    private VisitDateDto getVisitDateDto() {
        return repeat(() -> {
            VisitDateDto visitDateDto = inputView.readDate();
            restaurantService.saveVisitDate(visitDateDto);
            return visitDateDto;
        });
    }

    private OrderMenuDto getOrderMenuDto() {
        return repeat(() -> {
            OrderMenuDto orderMenuDto = inputView.readOrderMenu();
            restaurantService.saveOrderMenu(orderMenuDto);
            return orderMenuDto;
        });
    }

    private void printVisitDateAndOrderMenu(VisitDateDto visitDateDto, OrderMenuDto orderMenuDto) {
        outputView.printEventPreview(visitDateDto);
        outputView.printMenu(orderMenuDto);
    }

    private void calculateAndPrintOrderDetails() {
        int orderTotalAmount = restaurantService.getOrderTotalAmount();
        DiscountDetailsDto discountDetailsDto = restaurantService.calculateDiscountDetails();
        outputView.printOrderDetails(orderTotalAmount, discountDetailsDto);
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
