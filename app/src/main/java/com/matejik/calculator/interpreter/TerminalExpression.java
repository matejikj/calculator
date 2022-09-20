package com.matejik.calculator.interpreter;

public class TerminalExpression implements Expression {
    double number;

    public TerminalExpression(double i) {
        number = i;
    }

    public TerminalExpression(String s) {
        number = Double.parseDouble(s);
    }

    @Override
    public double interpret() {
        return number;
    }
}
