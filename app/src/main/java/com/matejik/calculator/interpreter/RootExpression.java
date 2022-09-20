package com.matejik.calculator.interpreter;

public class RootExpression implements Expression {
    Expression expression;

    public RootExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double interpret() {
        return Math.sqrt(expression.interpret());
    }
}
