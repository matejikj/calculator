package com.matejik.calculator.interpreter;

public class AddExpression implements Expression {
    Expression leftExpression;
    Expression rightExpresion;

    public AddExpression(Expression leftExpression,
                              Expression rightExpresion) {
        this.leftExpression = leftExpression;
        this.rightExpresion = rightExpresion;
    }

    @Override
    public double interpret() {
        return leftExpression.interpret() + rightExpresion.interpret();
    }
}
