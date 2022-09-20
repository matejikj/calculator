package com.matejik.calculator.interpreter;

public class MinusExpression implements Expression {
    Expression leftExpression;
    Expression rightExpresion;

    public MinusExpression(Expression leftExpression,
                            Expression rightExpresion) {
        this.leftExpression = leftExpression;
        this.rightExpresion = rightExpresion;
    }

    @Override
    public double interpret() {
        return leftExpression.interpret() - rightExpresion.interpret();
    }
}
