package org.openjfx;

import javafx.application.Application;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.openjfx.ButtonHandlers.operation;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static TextField display;
    public static TextArea report;

    public static List<String> digitList = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    public static List<String> operandList = List.of("+", "-", "*", "/");
    public List<Button> digitButtons = new ArrayList<>();
    public List<Button> operandButtons = new ArrayList<>();
    public List<Button> allButtonsList = new ArrayList<>();;


    @Override
    public void start(Stage stage) throws IOException {

        HBox root = new HBox();
        root.setPadding(new Insets(10.0));

        VBox vBox = new VBox();
        vBox.setMinWidth(200.0);
        vBox.setFillWidth(true);
        vBox.setPadding(new Insets(10.0));

        display = new TextField();
        display.setAlignment(Pos.BASELINE_RIGHT);
        display.setEditable(false);

        // Create buttons
        digitList.forEach(each -> digitButtons.add(new Button(each)));
        operandList.forEach(each -> operandButtons.add(new Button(each)));
        Button resultButton = new Button("=");
        Button clearButton = new Button("C");

            allButtonsList.addAll(digitButtons);
            allButtonsList.addAll(operandButtons);
            allButtonsList.add(resultButton);
            allButtonsList.add(clearButton);

            ButtonHandlers buttonHandlers = new ButtonHandlers();
            buttonHandlers.digitButtonHandler(digitButtons);
            buttonHandlers.operandButtonHandler(operandButtons);
            buttonHandlers.resultButtonHandler(resultButton);
            buttonHandlers.clearButtonHandler(clearButton);

        // Button area
        TilePane tiles = new TilePane();
        tiles.setPrefColumns(5);
        tiles.setPrefTileHeight(30.0);
        tiles.setPrefTileWidth(30.0);
        tiles.setAlignment(Pos.CENTER);
        tiles.setPadding(new Insets(10.0));
        tiles.setHgap(10.0);
        tiles.setVgap(10.0);
        tiles.getChildren().addAll(allButtonsList);

        vBox.getChildren().addAll(display, tiles);

        report = new TextArea();

        root.getChildren().addAll(vBox, report);
        scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

/*
    public static void main(String[] args) {
        launch();
    }
*/



}