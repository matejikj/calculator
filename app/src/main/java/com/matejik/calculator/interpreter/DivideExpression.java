package com.matejik.calculator.interpreter;

public class DivideExpression implements Expression {
    Expression leftExpression;
    Expression rightExpresion;

    public DivideExpression(Expression leftExpression,
                              Expression rightExpresion) {
        this.leftExpression = leftExpression;
        this.rightExpresion = rightExpresion;
    }

    @Override
    public double interpret() {
        double a = leftExpression.interpret();
        double b = rightExpresion.interpret();
        double i = a / b;
        return i;
    }
}
