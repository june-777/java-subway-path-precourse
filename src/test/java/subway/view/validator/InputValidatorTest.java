package subway.view.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("[Exception] 특수 문자 존재 시 예외")
    @ParameterizedTest
    @ValueSource(strings = {"1.", "1!", "1 .", ".1"})
    void specialCharacter(String input) {
        assertThatThrownBy(() -> InputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("[Exception] 공백 시 예외")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\n", "\t", "\r"})
    void blank(String input) {
        assertThatThrownBy(() -> InputValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("[Success]")
    @ParameterizedTest
    @ValueSource(strings = {" 1", " 1", "1  ", "2", "D", "B", "ㄱ", "ㅎ", "핳", "붉", "뷁", "뛟", "rl", "김준기"})
    void correct(String input) {
        assertThatCode(() -> InputValidator.validate(input))
                .doesNotThrowAnyException();
    }
}