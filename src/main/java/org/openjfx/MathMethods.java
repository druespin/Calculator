package org.openjfx;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;

import static org.openjfx.App.operandList;
import static org.openjfx.App.report;


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

            if (!numbers[0].isEmpty() && !numbers[1].isEmpty()) {
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
            else result = "";
        }
        return result;
    }
}