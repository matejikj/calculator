package com.matejik.calculator.utils;

import com.matejik.calculator.interpreter.AddExpression;
import com.matejik.calculator.interpreter.CosExpression;
import com.matejik.calculator.interpreter.DivideExpression;
import com.matejik.calculator.interpreter.Expression;
import com.matejik.calculator.interpreter.MinusExpression;
import com.matejik.calculator.interpreter.MultiplyExpression;
import com.matejik.calculator.interpreter.RootExpression;
import com.matejik.calculator.interpreter.SinExpression;
import com.matejik.calculator.interpreter.SquareExpression;
import com.matejik.calculator.interpreter.TanExpression;

public class Utils {
    public static boolean isDoubleOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        else
            return false;
    }
    public static boolean isSingleOperator(String s) {
        if (s.equals("sin") || s.equals("cos") || s.equals("tan") || s.equals("√") ||
                s.equals("²"))
            return true;
        else
            return false;
    }

    public static Expression getDoubleOperatorInstance(String s, Expression left,
                                                       Expression right) {
        switch (s) {
            case "+":
                return new AddExpression(left, right);
            case "-":
                return new MinusExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            case "/":
                return new DivideExpression(left, right);
        }
        return null;
    }

    public static Expression getSingleOperatorInstance(String s, Expression left) {
        switch (s) {
            case "sin":
                return new SinExpression(left);
            case "cos":
                return new CosExpression(left);
            case "tan":
                return new TanExpression(left);
            case "√":
                return new RootExpression(left);
            case "²":
                return new SquareExpression(left);
        }
        return null;
    }

}
