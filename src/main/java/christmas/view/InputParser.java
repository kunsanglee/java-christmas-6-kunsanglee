package christmas.view;


import static christmas.exception.ExceptionMessage.INVALID_VISIT_DATE;

public class InputParser {
    public int parseVisitDay(String inputDay) {
        try {
            return Integer.parseInt(inputDay);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }
}
