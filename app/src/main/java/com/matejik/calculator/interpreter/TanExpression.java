package com.matejik.calculator.interpreter;

public class TanExpression implements Expression {
    Expression expression;

    public TanExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double interpret() {
        return Math.tan(Math.toRadians(expression.interpret()));
    }
}
