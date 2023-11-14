package christmas;

import christmas.controller.RestaurantController;
import christmas.domain.discount.policy.ChristmasDiscount;
import christmas.domain.discount.policy.GiftDiscount;
import christmas.domain.discount.policy.StarDayDiscount;
import christmas.domain.discount.policy.WeekDayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import christmas.domain.date.service.DateService;
import christmas.domain.discount.service.DiscountService;
import christmas.domain.order.service.OrderMenuService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputParser;

public class Application {
    public static void main(String[] args) {
        RestaurantController restaurantController = getRestaurantController();
        restaurantController.run();
    }

    private static RestaurantController getRestaurantController() {
        return new RestaurantController(getInputView(),
                getOutputView(), getRestaurantService());
    }

    private static InputView getInputView() {
        return new InputView(new InputParser());
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }

    private static RestaurantService getRestaurantService() {
        return new RestaurantService(new DateService(), new OrderMenuService(), getDiscountService());
    }

    private static DiscountService getDiscountService() {
        return new DiscountService(new ChristmasDiscount(), new WeekDayDiscount(), new WeekendDiscount(),
                new StarDayDiscount(), new GiftDiscount());
    }
}
