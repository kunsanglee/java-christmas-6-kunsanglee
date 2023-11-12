package christmas.service;

import christmas.domain.date.VisitDate;

public class DateService {

    public VisitDate createCusomterVisitDate(int date) {
        return new VisitDate(date);
    }
}
