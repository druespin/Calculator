package org.openjfx.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openjfx.App.display_result;
import static org.openjfx.App.history_window;
import static org.openjfx.fx.Constants.*;

/**
 * Processing all buttons click events
 */

public class ButtonHandlers {

    private String operand = "";

    public static StringBuilder operation = new StringBuilder();

    // Number typed
    private StringBuilder number = new StringBuilder();

    /*
        All digit buttons handler
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

    /*
        Operand buttons handler
     */
    public void operandButtonHandler(List<Button> operandButtons) {

        operandButtons.forEach(btn -> btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                operand = btn.getText();

                /* Add operand to operation if there is no one yet
                     Only one math action is allowed
                 */
                if (operandNotPresent()) {
                    operation.append(operand);
                    number.setLength(0);
                }
            }
        }));
    }

    /*
        Ensure operation is not empty and has no operand yet
     */
    boolean operandNotPresent() {
        return !operation.toString().contains("+") &&
                !operation.toString().contains("-") && !operation.toString().contains("*")
                && !operation.toString().contains("/");
    }

    /*
       Result button (=) handler
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

    /*
        Clear result button handler
     */
    public void clearResultButtonHandler(Button clearResultButton) {

        clearResultButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display_result.setText("");
                operation.setLength(0);
                number.setLength(0);
            }
        });
    }

    /*
        Clear history button handler
     */
    public void clearHistoryButtonHandler(Button clearHistoryButton) {

        clearHistoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                history_window.clear();
            }
        });
    }

    /*
        'Save History to DB' button handler
     */
    public void uploadButtonHandler(Button uploadButton) {

        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (!history_window.getText().isEmpty())
                {
                    List<String> list = Arrays.asList(history_window.getText().split("\n"));

                    list.forEach(entry -> {
                        try
                        {
                            db.insertToDB(entry);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                }
            }
        });
    }

    /*
        'Download 10 latest' button handler
     */
    public void downloadButtonHandler(Button downloadButton) {

        downloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                List<String> list = new ArrayList<>();
                try {
                    list = db.get10LatestEntries();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                history_window.clear();
                list.forEach(entry -> {
                    history_window.appendText(entry + "\n");
                });
            }
        });
    }
}