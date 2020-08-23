package com.karachev.integerdivision;

import com.karachev.integerdivision.domain.DivisionStep;
import com.karachev.integerdivision.provider.DivisionMathProvider;
import com.karachev.integerdivision.provider.DivisionViewProvider;
import com.karachev.integerdivision.validator.Validator;

import java.util.List;

public class DivisionCalculator {

    private final Validator validator;
    private final DivisionMathProvider mathProvider;
    private final DivisionViewProvider divisionViewProvider;

    public DivisionCalculator(Validator validator,
                              DivisionMathProvider mathProvider, DivisionViewProvider divisionViewProvider) {
        this.validator = validator;
        this.mathProvider = mathProvider;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculate(int dividend, int divisor) {

        validator.validate(dividend, divisor);
        List<DivisionStep> steps = mathProvider.provideMathCalculation(dividend, divisor);

        return divisionViewProvider.provideView(dividend, divisor, steps);
    }
}
