package com.matejik.calculator.interpreter;

public class SinExpression implements Expression {
    Expression expression;

    public SinExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double interpret() {
        return Math.sin(Math.toRadians(expression.interpret()));
    }
}
