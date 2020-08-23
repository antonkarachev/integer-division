package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class DivisionMathProviderImplTest {

    private final DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void ProvideMathCalculationShouldMakeListOfStepsWhenGetDividend78945AndDivisor4() {

        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(7, 4, 3));
        expected.add(new DivisionStep(38, 36, 2));
        expected.add(new DivisionStep(29, 28, 1));
        expected.add(new DivisionStep(14, 12, 2));
        expected.add(new DivisionStep(25, 24, 1));

        List<DivisionStep> actual =
                divisionMathProvider.provideMathCalculation(78945, 4);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void ProvideMathCalculationShouldMakeListOfStepsWhenGetDividend270AndDivisor9() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(27, 27, 0));

        List<DivisionStep> actual =
                divisionMathProvider.provideMathCalculation(270, 9);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void ProvideMathCalculationShouldMakeListOfStepsWhenGetDividend10005AndDivisor7() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(10, 7, 3));
        expected.add(new DivisionStep(30, 28, 2));
        expected.add(new DivisionStep(20, 14, 6));
        expected.add(new DivisionStep(65, 63, 2));

        List<DivisionStep> actual =
                divisionMathProvider.provideMathCalculation(10005, 7);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void ProvideMathCalculationShouldMakeListOfStepsWhenGetDividendEqualsZero() {

        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(0, 0, 0));

        List<DivisionStep> actual =
                divisionMathProvider.provideMathCalculation(0, 10);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void ProvideMathCalculationShouldMakeListOfStepsWhenGetDividendSmallerThanDivisor() {

        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(3, 0, 3));

        List<DivisionStep> actual =
                divisionMathProvider.provideMathCalculation(3, 10);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }
}
