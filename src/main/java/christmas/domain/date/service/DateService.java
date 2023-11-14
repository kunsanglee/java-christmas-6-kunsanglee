package christmas.domain.date.service;

import christmas.domain.date.domain.VisitDate;

public class DateService {

    public VisitDate createCusomterVisitDate(int date) {
        return new VisitDate(date);
    }
}
