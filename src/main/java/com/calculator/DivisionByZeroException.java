package com.calculator;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException() {
        super("Error: division by zero.");
    }
}
