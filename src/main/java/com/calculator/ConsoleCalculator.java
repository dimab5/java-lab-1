package com.calculator;

import java.util.Scanner;

public class ConsoleCalculator {
    private final Calculator calculator;
    private final Scanner scanner;

    public ConsoleCalculator() {
        calculator = new Calculator();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Calculator is running");
        System.out.println("Enter C to reset or S to stop");

        while (true) {
            if (!calculator.hasResult()) {
                if (!readFirstOperand()) {
                    break;
                }

                if (!calculator.hasResult()) {
                    continue;
                }
            }

            System.out.print("Enter operation (+, -, *, /), C to reset, or S to stop: ");
            String input = scanner.next();

            if (isStopCommand(input)) {
                break;
            }

            if (isResetCommand(input)) {
                resetCalculator();
                continue;
            }

            if (!readSecondOperandAndCalculate(input.charAt(0))) {
                break;
            }
        }

        System.out.println("Calculator stopped");
        scanner.close();
    }

    private boolean readFirstOperand() {
        System.out.print("Enter the first number: ");

        if (!scanner.hasNextDouble()) {
            return processCommandInsteadOfNumber(scanner.next());
        }

        calculator.setFirstOperand(scanner.nextDouble());
        System.out.println("Current result: " + calculator.getCurrentResult());
        return true;
    }

    private boolean readSecondOperandAndCalculate(char operation) {
        try {
            calculator.validateOperation(operation);
        } catch (CalculatorException exception) {
            System.out.println(exception.getMessage());
            return true;
        }

        System.out.print("Enter the second number: ");

        if (!scanner.hasNextDouble()) {
            return processCommandInsteadOfNumber(scanner.next());
        }

        double secondOperand = scanner.nextDouble();

        try {
            double result = calculator.calculate(operation, secondOperand);
            System.out.println("Result: " + result);
        } catch (CalculatorException exception) {
            System.out.println(exception.getMessage());
        }

        return true;
    }

    private boolean processCommandInsteadOfNumber(String input) {
        if (isStopCommand(input)) {
            return false;
        }

        if (isResetCommand(input)) {
            resetCalculator();
        } else {
            System.out.println("Error: enter a number");
        }

        return true;
    }

    private void resetCalculator() {
        calculator.reset();
        System.out.println("Result was reset");
    }

    private boolean isResetCommand(String input) {
        return input.equalsIgnoreCase("C");
    }

    private boolean isStopCommand(String input) {
        return input.equalsIgnoreCase("S");
    }
}
