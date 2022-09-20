package com.matejik.calculator.interpreter;

public class CosExpression implements Expression {
    Expression expression;

    public CosExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double interpret() {
        return Math.cos(Math.toRadians(expression.interpret()));
    }
}
