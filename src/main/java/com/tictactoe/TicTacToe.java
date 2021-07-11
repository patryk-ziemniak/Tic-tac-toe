package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final Image background = new Image("file:src/main/resources/background.png");
    private final Image symbolO = new Image("file:src/main/resources/symbolO.png");
    private final Image symbolX = new Image("file:src/main/resources/symbolX.png");

    public void displayBoardFieldsSymbols(GridPane gameField, GameController gameController) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameController.getSymbol(x, y) == Symbol.O) {
                    gameField.add(new ImageView(symbolO), x, y);
                } else if (gameController.getSymbol(x, y) == Symbol.X) {
                    gameField.add(new ImageView(symbolX), x, y);
                }
            }
        }
    }

    public void clearBoardFields(GridPane gameBoard) {
        gameBoard.getChildren().removeIf(node -> node.getClass() == ImageView.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(25, 25, 25, 25));
        ColumnConstraints col1GF = new ColumnConstraints(114);
        ColumnConstraints col2GF = new ColumnConstraints(114);
        ColumnConstraints col3GF = new ColumnConstraints(114);
        RowConstraints row1GF = new RowConstraints(114);
        RowConstraints row2GF = new RowConstraints(114);
        RowConstraints row3GF = new RowConstraints(114);
        gameBoard.getColumnConstraints().addAll(col1GF, col2GF, col3GF);
        gameBoard.getRowConstraints().addAll(row1GF, row2GF, row3GF);
        gameBoard.setGridLinesVisible(true);                //lines showing columns and rows lines in application

        GridPane menuPanel = new GridPane();
        menuPanel.setPadding(new Insets(25, 25, 25, 0));
        ColumnConstraints col0MP = new ColumnConstraints(110);    // columns in total 283 px
        ColumnConstraints col1MP = new ColumnConstraints(23);
        ColumnConstraints col2MP = new ColumnConstraints(150);
        RowConstraints row0MP = new RowConstraints(30);       // rows in total 342 px
        RowConstraints row1MP = new RowConstraints(50);
        RowConstraints row2MP = new RowConstraints(50);
        RowConstraints row3MP = new RowConstraints(78);
        RowConstraints row4MP = new RowConstraints(78);
        RowConstraints row5MP = new RowConstraints(56);
        menuPanel.getColumnConstraints().addAll(col0MP, col1MP, col2MP);
        menuPanel.getRowConstraints().addAll(row0MP, row1MP, row2MP, row3MP, row4MP, row5MP);
//        menuPanel.setGridLinesVisible(true);                //lines showing columns and rows lines in application

        Button newGame = new Button("New Game");
        menuPanel.add(newGame, 2, 0);
        GridPane.setHalignment(newGame, HPos.CENTER);
        GridPane.setValignment(newGame, VPos.TOP);

        Label player = new Label("Player");
        menuPanel.add(player, 0, 1);
        player.setFont(new Font(24.0));

        Label computer = new Label("Computer");
        menuPanel.add(computer, 2, 1);
        GridPane.setHalignment(computer, HPos.RIGHT);
        computer.setFont(new Font(24.0));

        Label vs = new Label("vs");
        menuPanel.add(vs, 1, 1);
        vs.setFont(new Font(24.0));

        Label turnOf = new Label("Turn of");
        menuPanel.add(turnOf, 0, 2, 1, 1);
        GridPane.setHalignment(turnOf, HPos.RIGHT);
        turnOf.setFont(new Font(24.0));

        Label statusLabel = new Label("Status:");
        menuPanel.add(statusLabel, 0, 3);
        GridPane.setHalignment(statusLabel, HPos.RIGHT);
        statusLabel.setFont(new Font(24.0));

        Label statusDetailLabel = new Label("In Game");
        menuPanel.add(statusDetailLabel, 2, 3);
        GridPane.setHalignment(statusDetailLabel, HPos.LEFT);
        statusDetailLabel.setFont(new Font(24.0));

        Label communicates = new Label(""); //You can't place your\nsymbol on enemy's field!\nPlease choose empty field. & communicates.setUnderline(true);
        menuPanel.add(communicates, 0, 4, 3, 2);
        GridPane.setHalignment(communicates, HPos.CENTER);
        GridPane.setValignment(communicates, VPos.CENTER);
        communicates.setFont(new Font(24.0));

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);

        HBox root = new HBox();
        root.setBackground(background);
        root.getChildren().addAll(gameBoard, menuPanel);

        Scene scene = new Scene(root, 700, 392, Color.BLACK);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*GameController gameController = new GameController();
        Symbol playerSymbol = gameController.getPlayerSymbol();
        Symbol computerSymbol = gameController.getComputerSymbol();
        //gameController.newGame(); Loop 'for' for displaying status of every field; <-0 Do that when player clicks on button "New Game"

        while (gameController.checkGameStatus() == GameStatus.PROCESSING) {
            //Display who's move
            if(gameController.getWhoseMove() == computerSymbol) {
                gameController.doComputerMove();
                gameController.setWhoseMove(playerSymbol);
            } else {
                gameController.setSymbol(playerSymbol, 0,0); //Do that on player click on specific field(X,Y)
                gameController.setWhoseMove(computerSymbol);
            }
            //Loop 'for' for displaying status of every field (X/O/Empty)
        }
        if(gameController.checkGameStatus() == GameStatus.DRAW) {
            //Show text "DRAW" on game board
        } else if (gameController.checkGameStatus() == GameStatus.LOSE) {
            //Show text "YOU LOSE" on game board
        } else if (gameController.checkGameStatus() == GameStatus.WIN) {
            //Show text "YOU WIN" on game board
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
