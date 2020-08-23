package com.karachev.integerdivision.provider;

import com.karachev.integerdivision.domain.DivisionStep;

import java.util.List;

import static com.karachev.integerdivision.provider.DivisionMathProviderImpl.getCountOfDigits;

public class DivisionViewProviderImpl implements DivisionViewProvider {
    private static final String SPACE_DELIMITER = " ";
    private static final String HORIZONTAL_LINE = "-";
    private static final String MINUS = "_";
    private static final String VERTICAL_LINE = "|";
    private static final String NEW_LINE = "\n";

    @Override
    public String provideView(int dividend, int divisor, List<DivisionStep> steps) {
        StringBuilder viewBuffer = new StringBuilder();
        DivisionStep startedDivisionStep = steps.get(0);

        if (dividend < divisor) {
            return viewBuffer.append(onlyOneStepMaker(dividend, divisor, startedDivisionStep)).toString();
        }

        return viewBuffer.append(firstStepMaker(dividend, divisor, startedDivisionStep))
                .append(remainingStepsMaker(steps)).toString();

    }

    private String onlyOneStepMaker(int dividend, int divisor, DivisionStep firstDivisionStep) {
        StringBuilder onlyOneStep = new StringBuilder();

        return onlyOneStep.append(MINUS)
                .append(dividend)
                .append(VERTICAL_LINE)
                .append(divisor)
                .append(NEW_LINE)
                .append(SPACE_DELIMITER)
                .append(firstDivisionStep.getClosestMultiplier())
                .append(signInserter(SPACE_DELIMITER, getCountOfDigits(dividend) - 1))
                .append(VERTICAL_LINE)
                .append(signInserter(HORIZONTAL_LINE, getCountOfDigits(divisor)))
                .append(NEW_LINE)
                .append(SPACE_DELIMITER)
                .append(HORIZONTAL_LINE)
                .append(signInserter(SPACE_DELIMITER, getCountOfDigits(dividend) - 1))
                .append(VERTICAL_LINE)
                .append(firstDivisionStep.getClosestMultiplier())
                .append(NEW_LINE)
                .append(SPACE_DELIMITER)
                .append(dividend)
                .toString();
    }

    private String firstStepMaker(int dividend, int divisor, DivisionStep firstDivisionStep) {

        StringBuilder firstStep = new StringBuilder();

        int result = dividend / divisor;
        int dividendLength = getCountOfDigits(dividend);
        int resultLength = getCountOfDigits(result);
        int closestMultiplierLength = getCountOfDigits(firstDivisionStep.getClosestMultiplier());
        int spaceNeeded = dividendLength - closestMultiplierLength;

        return firstStep.append(MINUS)
                .append(dividend)
                .append(VERTICAL_LINE)
                .append(divisor)
                .append(NEW_LINE)
                .append(SPACE_DELIMITER)
                .append(firstDivisionStep.getClosestMultiplier())
                .append(signInserter(SPACE_DELIMITER, spaceNeeded))
                .append(VERTICAL_LINE)
                .append(signInserter(HORIZONTAL_LINE, resultLength))
                .append(NEW_LINE)
                .append(SPACE_DELIMITER)
                .append(signInserter(HORIZONTAL_LINE, closestMultiplierLength))
                .append(signInserter(SPACE_DELIMITER, spaceNeeded))
                .append(VERTICAL_LINE)
                .append(result)
                .append(NEW_LINE)
                .toString();
    }

    private String remainingStepsMaker(List<DivisionStep> steps) {

        StringBuilder remainingSteps = new StringBuilder();

        int firstStepReminderLength = getCountOfDigits(steps.get(0).getRemainder());
        int firstStepClosestMultiplierLength = getCountOfDigits(steps.get(0).getClosestMultiplier());
        int indent = firstStepClosestMultiplierLength - firstStepReminderLength + 1;
        DivisionStep lastDivisionStep = steps.get(steps.size() - 1);

        for (int i = 1; i < steps.size(); i++) {

            DivisionStep divisionStep = steps.get(i);

            int closestMultiplierLength = getCountOfDigits(divisionStep.getClosestMultiplier());

            remainingSteps.append(signInserter(SPACE_DELIMITER, indent - 1))
                    .append(MINUS)
                    .append(divisionStep.getSubQuotient())
                    .append(NEW_LINE)
                    .append(signInserter(SPACE_DELIMITER, indent))
                    .append(divisionStep.getClosestMultiplier())
                    .append(NEW_LINE)
                    .append(signInserter(SPACE_DELIMITER, indent))
                    .append(signInserter(HORIZONTAL_LINE, closestMultiplierLength))
                    .append(NEW_LINE);

            indent = (signInserter(SPACE_DELIMITER, indent) +
                    signInserter(HORIZONTAL_LINE, closestMultiplierLength)).length()
                    - getCountOfDigits(divisionStep.getRemainder());
        }

        return remainingSteps.append(signInserter(SPACE_DELIMITER, indent))
                .append(lastDivisionStep.getRemainder()).toString();
    }

    private String signInserter(String whatInsert, int quantity) {
        StringBuilder insertion = new StringBuilder();

        for (int i = 0; i < quantity; i++) {
            insertion.append(whatInsert);
        }

        return insertion.toString();
    }

}
