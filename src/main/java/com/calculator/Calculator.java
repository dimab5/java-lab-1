package com.calculator;

public class Calculator {
    private double currentResult;
    private boolean hasResult;

    public double getCurrentResult() {
        return currentResult;
    }

    public boolean hasResult() {
        return hasResult;
    }

    public void setFirstOperand(double firstOperand) {
        currentResult = firstOperand;
        hasResult = true;
    }

    public void reset() {
        currentResult = 0;
        hasResult = false;
    }

    public double calculate(char operation, double secondOperand) throws CalculatorException {
        validateOperation(operation);

        currentResult = switch (operation) {
            case '+' -> currentResult + secondOperand;
            case '-' -> currentResult - secondOperand;
            case '*' -> currentResult * secondOperand;
            case '/' -> divide(secondOperand);
            default -> currentResult;
        };

        hasResult = true;
        return currentResult;
    }

    public void validateOperation(char operation) throws UnsupportedCalculatorOperationException {
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
            throw new UnsupportedCalculatorOperationException(operation);
        }
    }

    private double divide(double secondOperand) throws DivisionByZeroException {
        if (secondOperand == 0) {
            throw new DivisionByZeroException();
        }

        return currentResult / secondOperand;
    }
}
