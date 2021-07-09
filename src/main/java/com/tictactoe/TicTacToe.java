package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private Image background = new Image("file:src/main/resources/background.jpg");
    private Image SymbolO = new Image("file:src/main/resources/Symbol-O.jpg");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gridPane = new GridPane();
        gridPane.setBackground(background);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        ColumnConstraints col1 = new ColumnConstraints(114);
        ColumnConstraints col2 = new ColumnConstraints(114);
        ColumnConstraints col3 = new ColumnConstraints(114);
        ColumnConstraints col4 = new ColumnConstraints(308);
        RowConstraints row1 = new RowConstraints(114);
        RowConstraints row2 = new RowConstraints(114);
        RowConstraints row3 = new RowConstraints(114);
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        gridPane.getRowConstraints().addAll(row1,row2,row3);
        gridPane.setGridLinesVisible(true);                //linie oddzielajace kolumny i rzedy

        ImageView Symbol11 = new ImageView(SymbolO);
        gridPane.add(Symbol11, 1, 1);
        GridPane.setHalignment(Symbol11, HPos.LEFT);
        GridPane.setValignment(Symbol11, VPos.TOP);


        Scene scene = new Scene(gridPane, 700, 392, Color.BLACK);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
