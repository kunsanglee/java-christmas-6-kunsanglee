package christmas;

public enum EventConfig {
    YEAR(2023),
    MONTH(12),
    ;

    private final int number;

    EventConfig(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
