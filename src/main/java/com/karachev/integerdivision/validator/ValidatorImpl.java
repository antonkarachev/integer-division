package com.karachev.integerdivision.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(int dividend, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor is zero");
        }
        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor must be above zero");
        }
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend must be above zero");
        }
    }
}
