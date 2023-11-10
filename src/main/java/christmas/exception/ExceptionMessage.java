package christmas.exception;

public enum ExceptionMessage {
    PREFIX("[ERROR] "),
    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
