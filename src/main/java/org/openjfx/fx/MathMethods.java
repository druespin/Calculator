package org.openjfx.fx;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MathMethods {

    public static String calculate(String operation, String operand) {

        String result = "";
        String[] numbers;

        if (!operand.isEmpty()) {
            if ("+".equals(operand)) {
                numbers = operation.split("\\+");
            }
            else if ("*".equals(operand)) {
                numbers = operation.split("\\*");
            }
            else numbers = operation.split(operand);

            if (numbers.length > 1 && !numbers[0].isEmpty() && !numbers[1].isEmpty()) {
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);

                if ("+".equals(operand)) {
                    result = String.valueOf(num1 + num2);
                }
                if ("-".equals(operand)) {
                    result = String.valueOf(num1 - num2);
                }
                if ("*".equals(operand)) {
                    result = String.valueOf(num1 * num2);
                }
                if ("/".equals(operand)) {
                    result = String.valueOf((double) num1 / num2);
                }
            }
        }
        return result;
    }
}