package subway.view.exception;

// 시간 이슈로 활용 X
public enum InputException {
    BLANK("공백 입력은 불가 합니다."),
    NOT_NUMERIC("숫자만 입력 가능합니다."),
    OUT_OF_RANGE("정수형 범위를 넘어섰습니다."),

    START_WITH_SEPARATOR("구분자는 맨 처음에 있으면 안됩니다."),
    END_WITH_SEPARATOR("구분자는 맨 끝에 있으면 안됩니다."),
    CONTINUOUS_SEPARATOR("구분자는 연속되선 안됩니다."),

    EXCEED_LENGTH("제한된 입력 길이를 초과했습니다.");
    private static final String PREFIX = "[ERROR] ";
    private final String message;

    InputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public void make() {
        throw new IllegalArgumentException(getMessage());
    }

    public void make(Throwable throwable) {
        throw new IllegalArgumentException(getMessage(), throwable);
    }
}