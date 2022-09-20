package com.matejik.calculator.interpreter;

public class SquareExpression implements Expression {
    Expression expression;

    public SquareExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double interpret() {
        return expression.interpret() * expression.interpret();
    }
}
