package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;

import java.util.List;

public interface DivisionViewProvider {
    public String provideView (int dividend, int divisor, List<DivisionStep> steps);
}
