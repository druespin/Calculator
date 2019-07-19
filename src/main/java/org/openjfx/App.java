package org.openjfx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public static TextField display;
    public static TextArea report;
    public static List<String> digitList = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    public static List<String> operandList = List.of("+", "-", "*", "/");

    // DB
    public static DbApi db = new DbApi();

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        db.createTable();

        // Task for rest service
        Task rest_task = new ServerSetup();
        Thread rest_thread = new Thread(rest_task);
        rest_thread.start();

        // Setting layout
        HBox root = new HBox();

        // Results history area
        VBox vBoxRight = new VBox();
        vBoxRight.setPadding(new Insets(10.0));

        report = new TextArea();
        Button clearHistory = new Button("Clear History");
        vBoxRight.getChildren().addAll(report, clearHistory);

        // Button area
        VBox vBoxLeft = new VBox();
        vBoxLeft.setPadding(new Insets(10.0));
        vBoxLeft.setMinWidth(200.0);

        // Display field
        display = new TextField();
        display.setMinWidth(150);
        display.setAlignment(Pos.BASELINE_RIGHT);
        display.setEditable(false);

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

        // Buttons
        TilePane tiles = new TilePane();
        tiles.setPrefColumns(5);
        tiles.setPrefTileHeight(30.0);
        tiles.setPrefTileWidth(30.0);
        tiles.setAlignment(Pos.CENTER);
        tiles.setPadding(new Insets(10.0));
        tiles.setHgap(10.0);
        tiles.setVgap(10.0);
        tiles.getChildren().addAll(allButtonsList);

        vBoxLeft.getChildren().addAll(display, tiles);

        root.getChildren().addAll(vBoxLeft, vBoxRight);
        scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws IOException {
        launch();
    }

}