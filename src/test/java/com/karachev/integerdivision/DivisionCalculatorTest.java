package com.karachev.integerdivision;

import com.karachev.integerdivision.domain.DivisionStep;
import com.karachev.integerdivision.provider.DivisionMathProvider;
import com.karachev.integerdivision.provider.DivisionViewProvider;
import com.karachev.integerdivision.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DivisionCalculatorTest {

    @Mock
    private Validator mockedValidator;

    @Mock
    private DivisionMathProvider mockedDivisionMathProvider;

    @Mock
    private DivisionViewProvider mockedDivisionViewProvider;

    @InjectMocks
    private DivisionCalculator divisionCalculator;

    @Test
    void CalculateShouldReturnCorrectStringWhenGetValidatorDividendAndDivisor() {

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

        doNothing().when(mockedValidator).validate(anyInt(), anyInt());
        when(mockedDivisionMathProvider.provideMathCalculation(anyInt(), anyInt())).thenReturn(steps);
        when(mockedDivisionViewProvider.provideView(anyInt(), anyInt(), anyList())).thenReturn(expected);
        String actual = divisionCalculator.calculate(78945, 25);

        assertThat(expected, is(actual));

        verify(mockedValidator).validate(anyInt(), anyInt());
        verify(mockedDivisionMathProvider).provideMathCalculation(anyInt(), anyInt());
        verify(mockedDivisionViewProvider).provideView(anyInt(), anyInt(), anyList());
    }

    @Test
    void CalculateShouldNotRunsWhenValidatorThrowsException() {
        doThrow(new IllegalArgumentException()).when(mockedValidator).validate(anyInt(), anyInt());
        assertThrows(IllegalArgumentException.class, () -> divisionCalculator.calculate(10, 0));
        verifyNoMoreInteractions(mockedDivisionMathProvider, mockedDivisionViewProvider);
    }
}
