package com.karachev.integerdivision.validator;

import com.karachev.integerdivision.validator.Validator;
import com.karachev.integerdivision.validator.ValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ValidatorImplTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfDivisorEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(10, 0),
                "Divisor is zero");
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfDivisorBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(10, -10),
                "Divisor must be above zero");
    }

    @Test
    void validatorShouldThrowIllegalArgumentExceptionIfDividendBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(-10, 10),
                "Dividend must be above zero");
    }

    @Test
    void validatorShouldNotThrowExceptionIfDividendBelowZero() {
        assertDoesNotThrow(()->(validator).validate(10,10));
    }

}
