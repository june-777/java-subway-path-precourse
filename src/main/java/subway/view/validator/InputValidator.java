package subway.view.validator;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern pattern = Pattern.compile(".*[^\\w\\sㄱ-힣].*");

    public static void validate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백 입력은 안됩니다.");
        }
        if (pattern.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 특수문자가 존재합니다.");
        }
    }
}
