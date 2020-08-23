package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;

import java.util.List;

public interface DivisionMathProvider {
    List<DivisionStep> provideMathCalculation(int dividend, int divisor);
}
