package org.openjfx.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.util.List;
import static org.openjfx.App.*;

/*
 * Class to process buttons clicking
 */

public class ButtonHandlers {

    private String operand = "";

    public static StringBuilder operation = new StringBuilder();

    // number
    private StringBuilder number = new StringBuilder();

    /* Digit buttons handler
     */
    public void digitButtonHandler(List<Button> digitButtons) {

        digitButtons.forEach(btn -> btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String digit = btn.getText();
                number.append(digit);
                operation.append(digit);
                display_result.setText(number.toString());
            }
        }));
    }

    /* Operand buttons handler
     */
    public void operandButtonHandler(List<Button> operandButtons) {

        operandButtons.forEach(btn -> btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                operand = btn.getText();

                if (noOperandYet()) {
                    operation.append(operand);
                    number.setLength(0);
                }
            }
        }));
    }

    boolean noOperandYet() {
        return !operation.toString().isEmpty() && !operation.toString().contains("+") &&
                !operation.toString().contains("-") && !operation.toString().contains("*")
                && !operation.toString().contains("/");
    }

    /* Result button handler
     */
    public void resultButtonHandler(Button resultButton) {

        resultButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String result = MathMethods.calculate(operation.toString(), operand);

                if (!result.isEmpty()) {
                    display_result.setText(result);
                    operation.append(" = " + result);
                    history_window.appendText(operation.toString() + "\n");
                    operation.setLength(0);
                    number.setLength(0);
                }

                else {
                    history_window.appendText("Invalid operation\n");
                    operation.setLength(0);
                    number.setLength(0);
                }
            }
        });
    }

    /* Clear result button handler
     */
    public void clearButtonHandler(Button clearButton) {

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display_result.setText("");
                operation.setLength(0);
                number.setLength(0);
            }
        });
    }

    /* Clear history button handler
     */
    public void clearHistoryButtonHandler(Button clearButton) {

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                history_window.clear();
            }
        });
    }
}