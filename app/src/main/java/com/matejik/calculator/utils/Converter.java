package com.matejik.calculator.utils;

import static com.matejik.calculator.utils.Utils.isSingleOperator;

import java.util.LinkedList;
import java.util.Stack;

public class Converter {
    private static boolean isOperator(String s)
    {
        return (s.equals("+") ||
                (s.equals("-")) ||
                (s.equals("*")) ||
                (s.equals("²")) ||
                (s.equals("√")) ||
                (s.equals("sin")) ||
                (s.equals("cos")) ||
                (s.equals("tan")) ||
                (s.equals("/"))
                );
    }

    private static int getPriority(String s)
    {
        if (s.equals("-") || s.equals("+"))
            return 1;
        if (s.equals("*") || s.equals("/"))
            return 2;
        if (s.equals("√") || s.equals("²") || s.equals("sin") || s.equals("cos") || s.equals("tan"))
            return 3;
        return 0;
    }
    public static String infixToPrefix(LinkedList<String> list)
    {
        Stack<String> operators = new Stack<String>();
        Stack<String> operands = new Stack<String>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("²")){
                String s = list.remove(i);
                list.add(i-1, s);
            }
        }
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals("("))
            {
                operators.push(list.get(i));
            }
            else if (list.get(i).equals(")"))
            {
                while (!operators.empty() &&
                        (!operators.peek().equals("(")))
                {
                    String op1 = operands.peek();
                    operands.pop();
                    String op2 = operands.peek();
                    operands.pop();
                    String op = operators.peek();
                    operators.pop();
                    String tmp = op + " " + op2 + " " + op1;
                    operands.push(tmp);
                }
                operators.pop();
                if ( isSingleOperator(operators.peek())) {
                    String op1 = operands.peek();
                    operands.pop();
                    String op = operators.peek();
                    operators.pop();
                    String tmp = op + " " + op1;
                    operands.push(tmp);
                }
            }
            else if (!isOperator(list.get(i)))
            {
                operands.push(list.get(i));
            }
            else
            {
                while (!operators.empty() &&
                        getPriority(list.get(i)) <=
                                getPriority(operators.peek()))
                {
                    if (isSingleOperator(operators.peek())) {
                        String op1 = operands.peek();
                        operands.pop();
                        String op = operators.peek();
                        operators.pop();
                        String tmp = op + " " + op1;
                        operands.push(tmp);
                    } else {
                        String op1 = operands.peek();
                        operands.pop();
                        String op2 = operands.peek();
                        operands.pop();
                        String op = operators.peek();
                        operators.pop();
                        String tmp = op + " " + op2 + " " + op1;
                        operands.push(tmp);
                    }
                }
                operators.push(list.get(i));
            }
        }
        while (!operators.empty())
        {
            if (isSingleOperator(operators.peek())) {
                String op1 = operands.peek();
                operands.pop();
                String op = operators.peek();
                operators.pop();
                String tmp = op + " " + op1;
                operands.push(tmp);
            } else {
                String op1 = operands.peek();
                operands.pop();
                String op2 = operands.peek();
                operands.pop();
                String op = operators.peek();
                operators.pop();
                String tmp = op + " " + op2 + " " + op1;
                operands.push(tmp);
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).equals("²")){
                String s = list.remove(i);
                list.add(i+1, s);
            }
        }
        return operands.peek();
    }
}
