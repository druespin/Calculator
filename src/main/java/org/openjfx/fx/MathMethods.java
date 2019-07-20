package org.openjfx.fx;

/*
    Calculations class
 */
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

                    result = (num2 == 0) ? "big bang!" :
                            String.valueOf((double) num1 / (double) num2);

                    if (result.length() > 10)
                        result = result.substring(0, 9);
                }
            }
        }
        return result;
    }
}