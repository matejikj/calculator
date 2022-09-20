package com.matejik.calculator;

import static com.matejik.calculator.utils.Utils.getDoubleOperatorInstance;
import static com.matejik.calculator.utils.Utils.getSingleOperatorInstance;
import static com.matejik.calculator.utils.Utils.isDoubleOperator;
import static com.matejik.calculator.utils.Utils.isSingleOperator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.matejik.calculator.interpreter.Expression;
import com.matejik.calculator.interpreter.TerminalExpression;
import com.matejik.calculator.utils.Converter;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    LinkedList<String> inputList;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose, buttonBack;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDot, buttonSquared,buttonRoot, buttonSin,buttonCos, buttonTan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputList = new LinkedList<>();
        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.input);

        assignId(buttonBack,R.id.button_back);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonC,R.id.button_c);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonSin,R.id.button_sin);
        assignId(buttonCos,R.id.button_cos);
        assignId(buttonTan,R.id.button_tan);
        assignId(buttonRoot,R.id.button_root);
        assignId(buttonSquared,R.id.button_squared);
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("=")){
            resultTv.setText(getResult(inputList));
        } else if (buttonText.equals("C")){
            inputList = new LinkedList<>();
        } else if (buttonText.equals("◄")){
            inputList.removeLast();
        } else if (buttonText.equals("(") || buttonText.equals(")")) {
            inputList.add(buttonText);
        } else if (isDoubleOperator(buttonText) || isSingleOperator(buttonText)) {
            if (isSingleOperator(buttonText)) {
                inputList.add(buttonText);
                if (!buttonText.equals("²"))
                    inputList.add("(");
            } else
                inputList.add(buttonText);
        } else {
            if (!inputList.isEmpty()) {
                if (isDoubleOperator(inputList.getLast()) || isSingleOperator(inputList.getLast()) || inputList.getLast().equals("(") || inputList.getLast().equals(")")) {
                    inputList.add(buttonText);
                } else {
                    String item = inputList.getLast();
                    inputList.removeLast();
                    inputList.add(item + buttonText);
                }
            } else {
                inputList.add(buttonText);
            }
        }
        solutionTv.setText(toText(inputList));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String toText(List<String> input) {
        StringBuilder res = new StringBuilder();
        input.forEach(x -> res.append(x));
        return res.toString();
    }

    public String getResult(LinkedList<String> input) {
        try {
            String result = Converter.infixToPrefix(input);
            System.out.println("Prefix: " + result);

            Stack<Expression> stack = new Stack<Expression>();

            String[] tokenList = result.split(" ");
            for (int i = tokenList.length - 1; i >= 0; i-- ) {
                if (isDoubleOperator(tokenList[i])) {
                    Expression leftExpression = stack.pop();
                    Expression rightExpression = stack.pop();
                    Expression operator = getDoubleOperatorInstance(tokenList[i], leftExpression,
                            rightExpression);
                    double res = operator.interpret();
                    stack.push(new TerminalExpression(res));
                } else {
                    if (isSingleOperator(tokenList[i])) {
                        Expression expression = stack.pop();
                        Expression operator = getSingleOperatorInstance(tokenList[i], expression);
                        double res = operator.interpret();
                        stack.push(new TerminalExpression(res));
                    } else {
                        Expression j = new TerminalExpression(tokenList[i]);
                        stack.push(j);
                    }
                }
            }
            double res = stack.pop().interpret();
            res = (double)Math.round(res * 100000000d) / 100000000d;
            return String.valueOf(res);
        } catch (Exception e) {
            return "Err";
        }
    }
}