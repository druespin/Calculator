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
import org.openjfx.fx.ButtonHandlers;
import org.openjfx.restapi.ServerSetup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.openjfx.fx.Constants.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static TextField display_result;
    public static TextArea history_window;

    @Override
    public void start(Stage stage) throws SQLException {

        /* Create table in database (if not exists)
         */
            db.createTable();

        // Task for rest service
            Task rest_task = new ServerSetup();
            Thread rest_thread = new Thread(rest_task);
            rest_thread.start();

        // Set layout
            HBox root = new HBox();
            root.setPadding(new Insets(10));

        /* Left (digit buttons) area
         */
        VBox vBoxLeft = new VBox();
        vBoxLeft.setPadding(new Insets(40, 0, 0 , 0));
        vBoxLeft.setSpacing(10);
        vBoxLeft.setAlignment(Pos.TOP_CENTER);
        vBoxLeft.setMinWidth(200);

            // Display field
            display_result = new TextField();
            display_result.setMaxWidth(150);
            display_result.setAlignment(Pos.CENTER_RIGHT);
            display_result.setEditable(false);

            // Create buttons
            List<Button> digitButtons = new ArrayList<>();      // buttons 1, 2, 3, ..
            DIGITS.forEach(each -> digitButtons.add(new Button(each)));
            List<Button> operandButtons = new ArrayList<>();    // buttons +, -, *, /
            OPERANDS.forEach(each -> operandButtons.add(new Button(each)));

            Button resultBtn = new Button(RESULT_SYMBOL);
            Button clearResultBtn = new Button(CLEAR_DISPLAY);

            List<Button> allButtonsList = new ArrayList<>();

            allButtonsList.addAll(digitButtons);
            allButtonsList.addAll(operandButtons);
            allButtonsList.add(resultBtn);
            allButtonsList.add(clearResultBtn);


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

        /* Center (history) area
         */
        VBox vBoxCenter = new VBox();
        vBoxCenter.setMinWidth(150);
        vBoxCenter.setPadding(new Insets(10));
        vBoxCenter.setSpacing(10);
        vBoxCenter.setAlignment(Pos.TOP_CENTER);

            Label history_label = new Label(HISTORY);
            history_window = new TextArea();
            history_window.setMinHeight(200);

        vBoxCenter.getChildren().addAll(history_label, history_window);

        /*  Right area
         */
        VBox vBoxRight = new VBox();
        vBoxRight.setMinWidth(150);
        vBoxRight.setPadding(new Insets(40, 0, 0, 0));
        vBoxRight.setSpacing(10);
        vBoxRight.setAlignment(Pos.TOP_CENTER);

            Button clearHistoryBtn = new Button(CLEAR_HISTORY);
            Button uploadBtn = new Button(SAVE_HISTORY_TO_DATABASE);
            Button downloadBtn = new Button(DOWNLOAD_10_LATEST);

        vBoxRight.getChildren().addAll(clearHistoryBtn, uploadBtn, downloadBtn);

        // All buttons initialization
        ButtonHandlers buttonHandlers = new ButtonHandlers();
            buttonHandlers.digitButtonHandler(digitButtons);
            buttonHandlers.operandButtonHandler(operandButtons);
            buttonHandlers.resultButtonHandler(resultBtn);
            buttonHandlers.clearResultButtonHandler(clearResultBtn);
            buttonHandlers.clearHistoryButtonHandler(clearHistoryBtn);
            buttonHandlers.downloadButtonHandler(downloadBtn);
            buttonHandlers.uploadButtonHandler(uploadBtn);

        root.getChildren().addAll(vBoxLeft, vBoxCenter, vBoxRight);
        scene = new Scene(root, 550, 300);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}