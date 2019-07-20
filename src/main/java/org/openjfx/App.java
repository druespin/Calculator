package org.openjfx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openjfx.database.DbApi;
import org.openjfx.fx.ButtonHandlers;
import org.openjfx.restapi.ServerSetup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static TextField display_result;
    public static TextArea history_window;
    public static List<String> digitList = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    public static List<String> operandList = List.of("+", "-", "*", "/");

    // DB
    public static DbApi db = new DbApi();

    @Override
    public void start(Stage stage) throws SQLException {

        /* Create table in database
         */
            db.createTable();

        // Task for rest service
            Task rest_task = new ServerSetup();
            Thread rest_thread = new Thread(rest_task);
            rest_thread.start();

        // Set layout
            HBox root = new HBox();
            root.setPadding(new Insets(10));

        // Right (history) area
            VBox vBoxRight = new VBox();
            vBoxRight.setSpacing(10);
            vBoxRight.setPadding(new Insets(10));
            vBoxRight.setAlignment(Pos.CENTER);

                Label history_label = new Label("History");
                history_window = new TextArea();
                history_window.setPrefRowCount(13);

                Button clearHistory = new Button("Clear History");

            vBoxRight.getChildren().addAll(history_label, history_window, clearHistory);

        /* Left (buttons) area
         */
            VBox vBoxLeft = new VBox();
            vBoxLeft.setSpacing(10);
            vBoxLeft.setAlignment(Pos.CENTER);
            vBoxLeft.setMinWidth(200);

            // Display field
                display_result = new TextField();
                display_result.setMaxWidth(150);
                display_result.setAlignment(Pos.CENTER_RIGHT);
                display_result.setEditable(false);

            // Create buttons
                List<Button> digitButtons = new ArrayList<>();      // buttons 1, 2, 3, ..
                List<Button> operandButtons = new ArrayList<>();    // buttons +, -, *, /
                List<Button> allButtonsList = new ArrayList<>();;

                Button resultButton = new Button("=");
                Button clearResult = new Button("C");

                digitList.forEach(each -> digitButtons.add(new Button(each)));
                operandList.forEach(each -> operandButtons.add(new Button(each)));

                allButtonsList.addAll(digitButtons);
                allButtonsList.addAll(operandButtons);
                allButtonsList.add(resultButton);
                allButtonsList.add(clearResult);

                ButtonHandlers buttonHandlers = new ButtonHandlers();
                    buttonHandlers.digitButtonHandler(digitButtons);
                    buttonHandlers.operandButtonHandler(operandButtons);
                    buttonHandlers.resultButtonHandler(resultButton);
                    buttonHandlers.clearButtonHandler(clearResult);
                    buttonHandlers.clearHistoryButtonHandler(clearHistory);

        // Buttons layout
            TilePane tiles = new TilePane();
            tiles.setAlignment(Pos.CENTER);
            tiles.setPrefTileHeight(30.0);
            tiles.setPrefTileWidth(30.0);
            tiles.setPadding(new Insets(10.0));
            tiles.setHgap(10.0);
            tiles.setVgap(10.0);
            tiles.getChildren().addAll(allButtonsList);

            vBoxLeft.getChildren().addAll(display_result, tiles);

        root.getChildren().addAll(vBoxLeft, vBoxRight);
        scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }


    public static void main(String[] args) throws IOException {
        launch();
    }

}