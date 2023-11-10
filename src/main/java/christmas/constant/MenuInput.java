package christmas.constant;

public enum MenuInput {
    COMMA(","),
    REGEX("^([가-힣]+-\\d+)$"),
    DASH("-"),
    ;

    private final String value;

    MenuInput(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

