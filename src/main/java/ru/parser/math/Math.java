package ru.parser.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lollipop on 03.07.2017.
 */
public class Math {

    public String doOperation(String firstParam, String sign, String secondParam) {
        switch (sign) {
            case "+":
                return String.valueOf(Double.parseDouble(firstParam) + Double.parseDouble(secondParam));
            case "-":
                return String.valueOf(Double.parseDouble(firstParam) - Double.parseDouble(secondParam));
            case "*":
                return String.valueOf(Double.parseDouble(firstParam) * Double.parseDouble(secondParam));
            case "/":
                return String.valueOf(Double.parseDouble(firstParam) / Double.parseDouble(secondParam));
            case "^":
                return String.valueOf(java.lang.Math.pow(Double.parseDouble(firstParam), Double.parseDouble(secondParam)));
            default:
                return "";
        }
    }

    public Double calculate(String expression) {
        if (!(expression.contains("(") && expression.contains(")")))
            return parseInBracketExpression(expression);
        else {
            String currentExpression = expression;
            char[] chars = expression.toCharArray();
            int counter = -1;
            int startIndex = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '(' && counter == -1){
                    counter = 1;
                    startIndex = i;
                }
                else if (chars[i] == '(') counter++;
                else if (chars[i] == ')') counter--;
                if (counter == 0) {
                    counter = -1;
                    currentExpression = currentExpression.substring(0, startIndex) + calculate(currentExpression.substring(startIndex + 1, i))
                            + currentExpression.substring(i+1, currentExpression.length());
                    i = 0;
                    chars = currentExpression.toCharArray();
                }
            }
            return parseInBracketExpression(currentExpression);
        }
    }

    public Double parseInBracketExpression(String expression) {
        String finalExpression = expression.replaceAll("\\+", "_+_")
                .replaceAll("\\-", "_-_")
                .replaceAll("\\*", "_*_")
                .replaceAll("\\/", "_/_")
                .replaceAll("\\^", "_^_");
        System.out.println(finalExpression);
        String[] splittedExpression = finalExpression.split("_");
        ArrayList<String> expressionList = new ArrayList<>();
        expressionList.addAll(Arrays.asList(splittedExpression));
        splittedExpression = null;
        int size = expressionList.size();
        for (int i = 2; i < size; ) {
            if (i + 1 < size) {
                if (signFactory(expressionList.get(i - 1)) >= signFactory(expressionList.get(i + 1))) {
                    System.out.println(expressionList.get(i - 2) + expressionList.get(i - 1) + expressionList.get(i));
                    expressionList.set(i - 2, doOperation(expressionList.get(i - 2),
                            expressionList.get(i - 1), expressionList.get(i)));

                    expressionList.remove(i - 1);
                    expressionList.remove(i - 1);
                    i = 2;
                } else i += 2;
            } else {
                expressionList.set(i - 2, doOperation(expressionList.get(i - 2),
                        expressionList.get(i - 1), expressionList.get(i)));

                expressionList.remove(i - 1);
                expressionList.remove(i - 1);
                i = 2;
            }
            size = expressionList.size();
        }
        Double result = Double.parseDouble(expressionList.get(0));
        ;
        expressionList = null;
        finalExpression = null;
        return result;
    }

    public int signFactory(String sign) {
        switch (sign) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
