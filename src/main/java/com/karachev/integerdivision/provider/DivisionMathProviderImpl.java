package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    @Override
    public List<DivisionStep> provideMathCalculation(int dividend, int divisor) {
        int[] dividendInDigits = convertDigitInSigns(dividend);

        return stepsMaker(dividendInDigits, dividend, divisor);
    }

    private int[] convertDigitInSigns(int dividend) {
        int lengthOfDividend = getCountOfDigits(dividend);
        int[] digitInSigns = new int[lengthOfDividend];

        for (int i = lengthOfDividend - 1; i >= 0; i--) {
            digitInSigns[i] = dividend % 10;
            dividend = dividend / 10;
        }

        return digitInSigns;
    }

    public static int getCountOfDigits(int number) {
        int count = 0;

        while (number != 0) {
            count++;
            number /= 10;
        }

        return count;
    }

    private List<DivisionStep> stepsMaker(int[] dividendInDigits, int dividend, int divisor) {
        List<DivisionStep> steps = new ArrayList<>();

        if (dividend < divisor) {
            steps.add(new DivisionStep(dividend, 0, dividend));
            return steps;
        }


        int remainder = 0;

        for (int digit : dividendInDigits) {
            int subQuotient = (remainder * 10) + digit;
            int closestMultiplier = subQuotient - (subQuotient % divisor);

            if (digit < divisor) {
                remainder = subQuotient;
            }

            if (subQuotient >= divisor) {
                remainder = subQuotient - closestMultiplier;
                steps.add(new DivisionStep(subQuotient, closestMultiplier, remainder));
            }
        }

        return steps;
    }
}
