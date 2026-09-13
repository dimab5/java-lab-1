package com.calculator;

public class UnsupportedCalculatorOperationException extends CalculatorException {
    public UnsupportedCalculatorOperationException(char operation) {
        super("Error: unsupported operation " + operation + ".");
    }
}
