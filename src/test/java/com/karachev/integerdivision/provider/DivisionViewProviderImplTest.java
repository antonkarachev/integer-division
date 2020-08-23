package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DivisionViewProviderImplTest {

    private final DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

    @Test
    void ProvideViewShouldReturnCorrectStringIfItGetDividendDivisorAndDivisionStepList() {
        List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(78, 75, 3));
        steps.add(new DivisionStep(39, 25, 14));
        steps.add(new DivisionStep(144, 125, 19));
        steps.add(new DivisionStep(195, 175, 20));

        String expected =
                "_78945|25\n" +
                        " 75   |----\n" +
                        " --   |3157\n" +
                        " _39\n" +
                        "  25\n" +
                        "  --\n" +
                        " _144\n" +
                        "  125\n" +
                        "  ---\n" +
                        "  _195\n" +
                        "   175\n" +
                        "   ---\n" +
                        "    20";

        String actual = divisionViewProvider.provideView(78945, 25, steps);

        assertThat(expected, is(actual));
    }

    @Test
    void ProvideViewShouldReturnCorrectStringIfItGetDividendSmallerThanDivisorAndDivisionStepList() {
        List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(3, 0, 3));

        String expected =
                "_3|10\n" +
                        " 0|--\n" +
                        " -|0\n" +
                        " 3";

        String actual = divisionViewProvider.provideView(3, 10, steps);

        assertThat(expected, is(actual));
    }
}
