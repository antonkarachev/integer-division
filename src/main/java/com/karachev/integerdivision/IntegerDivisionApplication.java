package com.karachev.integerdivision;

import com.karachev.integerdivision.provider.DivisionMathProvider;
import com.karachev.integerdivision.provider.DivisionMathProviderImpl;
import com.karachev.integerdivision.provider.DivisionViewProvider;
import com.karachev.integerdivision.provider.DivisionViewProviderImpl;
import com.karachev.integerdivision.validator.ValidatorImpl;

import java.util.Scanner;

public class IntegerDivisionApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi! This is console application to show process of long division");

        System.out.print("Enter dividend: ");
        int dividend = scanner.nextInt();

        System.out.print("Enter divisor: ");
        int divisor = scanner.nextInt();

        ValidatorImpl validatorImpl = new ValidatorImpl();
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

        DivisionCalculator divisionCalculator = new DivisionCalculator(validatorImpl, divisionMathProvider,
                divisionViewProvider);

        System.out.println(divisionCalculator.calculate(dividend, divisor));
    }
}
