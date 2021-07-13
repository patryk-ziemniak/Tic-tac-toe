package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final Image background = new Image("file:src/main/resources/background.png");
    private final Image symbolO = new Image("file:src/main/resources/symbolO.png");
    private final Image symbolX = new Image("file:src/main/resources/symbolX.png");
    private final Image symbolX24 = new Image("file:src/main/resources/symbolX24.png");
    private final Image symbolO24 = new Image("file:src/main/resources/symbolO24.png");
    GameController gameController = new GameController();
    Symbol playerSymbol = gameController.getPlayerSymbol();
    Symbol computerSymbol = gameController.getComputerSymbol();

    public void displayBoardFieldsSymbols(GridPane gameBoard) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameController.getSymbol(x, y) == Symbol.O) {
                    gameBoard.add(new ImageView(symbolO), x, y);
                } else if (gameController.getSymbol(x, y) == Symbol.X) {
                    gameBoard.add(new ImageView(symbolX), x, y);
                }
            }
        }
    }

    public void clearBoardFields(GridPane gameBoard) {
        gameBoard.getChildren().removeIf(node -> node.getClass() == ImageView.class);
    }

    public int convertPixelsToGridIndex(double pixels) {
        int result = -1;
        if (pixels > 25 && pixels < 140) {
            result = 0;
        } else if (pixels > 139 && pixels < 254) {
            result = 1;
        } else if (pixels > 253 && pixels < 368) {
            result = 2;
        }
        return result;
    }

    private void popupSetting(GridPane menuPanel, Popup popup, Label popupText) {
        popupText.setFont(new Font(50));
        popupText.setAlignment(Pos.CENTER);
        popup.getContent().clear();
        popup.getContent().add(popupText);
    }

    public boolean checkGameStatus(GridPane menuPanel, Popup popup) {
        boolean result = false;
        if (gameController.checkGameStatus() == GameStatus.DRAW) {
            Label popupText = new Label("DRAW!");
            popupText.setTextFill(Color.BLACK);
            popupSetting(menuPanel, popup, popupText);
            popup.show(menuPanel, 1070, 540);
            result = true;
        } else if (gameController.checkGameStatus() == GameStatus.LOSE) {
            Label popupText = new Label("YOU LOSE!");
            popupText.setTextFill(Color.RED);
            popupSetting(menuPanel, popup, popupText);
            popup.show(menuPanel, 1025, 540);
            result = true;
        } else if (gameController.checkGameStatus() == GameStatus.WIN) {
            Label popupText = new Label("YOU WIN!");
            popupText.setTextFill(Color.web("08FF00"));
            popupSetting(menuPanel, popup, popupText);
            popup.show(menuPanel, 1025, 540);
            result = true;
        }
        return result;
    }

    private void computerStartsMove(GridPane gameBoard) {
        if (gameController.getWhoStarts() == computerSymbol) {
            gameController.doComputerMove();
            displayBoardFieldsSymbols(gameBoard);
        }
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
//        gameBoard.setGridLinesVisible(true);                //lines showing columns and rows in application for debugging

        GridPane menuPanel = new GridPane();
        menuPanel.setPadding(new Insets(25, 25, 25, 0));
        ColumnConstraints col0MP = new ColumnConstraints(104);    // columns width in total 283 px
        ColumnConstraints col1MP = new ColumnConstraints(35);
        ColumnConstraints col2MP = new ColumnConstraints(144);
        RowConstraints row0MP = new RowConstraints(30);       // rows height in total 342 px
        RowConstraints row1MP = new RowConstraints(50);
        RowConstraints row2MP = new RowConstraints(50);
        RowConstraints row3MP = new RowConstraints(78);
        RowConstraints row4MP = new RowConstraints(78);
        RowConstraints row5MP = new RowConstraints(56);
        menuPanel.getColumnConstraints().addAll(col0MP, col1MP, col2MP);
        menuPanel.getRowConstraints().addAll(row0MP, row1MP, row2MP, row3MP, row4MP, row5MP);
//        menuPanel.setGridLinesVisible(true);                //lines showing columns and rows in application for debugging

        Popup popup = new Popup(); //used to show WIN, LOSE, DRAW and "can't place symbol" communicates

        Button newGame = new Button("New Game");
        menuPanel.add(newGame, 2, 0);
        GridPane.setHalignment(newGame, HPos.CENTER);
        GridPane.setValignment(newGame, VPos.TOP);
        newGame.setOnAction(click -> {
            gameController.newGame();
            clearBoardFields(gameBoard);
            popup.hide();
            computerStartsMove(gameBoard);
        });

        Label player = new Label("Player");
        menuPanel.add(player, 0, 1);
        player.setFont(new Font(24.0));

        ImageView playerSymbolImage;
        if (playerSymbol == Symbol.O) {
            playerSymbolImage = new ImageView(symbolO24);
        } else {
            playerSymbolImage = new ImageView(symbolX24);
        }
        menuPanel.add(playerSymbolImage, 0, 1);
        GridPane.setHalignment(playerSymbolImage, HPos.RIGHT);

        Label vs = new Label("vs");
        menuPanel.add(vs, 1, 1);
        vs.setFont(new Font(24.0));
        GridPane.setHalignment(vs, HPos.CENTER);

        ImageView computerSymbolImage;
        if (computerSymbol == Symbol.O) {
            computerSymbolImage = new ImageView(symbolO24);
        } else {
            computerSymbolImage = new ImageView(symbolX24);
        }
        menuPanel.add(computerSymbolImage, 2, 1);
        GridPane.setHalignment(computerSymbolImage, HPos.LEFT);

        Label computer = new Label("Computer");
        menuPanel.add(computer, 2, 1);
        GridPane.setHalignment(computer, HPos.RIGHT);
        computer.setFont(new Font(24.0));

        computerStartsMove(gameBoard);
        gameBoard.setOnMouseClicked(click -> {
            popup.hide();
            if (!gameController.setSymbol(playerSymbol, convertPixelsToGridIndex(click.getX()), convertPixelsToGridIndex(click.getY()))) {
                popup.getContent().clear();
                Label popupText = new Label("You can't place your symbol\non field with symbol!\nPlease choose empty field");
                popupText.setFont(new Font(24));
                popupText.setTextFill(Color.RED);
                popupText.setAlignment(Pos.CENTER);
                popup.getContent().add(popupText);
                popup.show(menuPanel, 1000, 510);
                return;
            }
            displayBoardFieldsSymbols(gameBoard);
            if (checkGameStatus(menuPanel, popup)) {
                return;
            }
            gameController.doComputerMove();
            displayBoardFieldsSymbols(gameBoard);
            checkGameStatus(menuPanel, popup);
        });

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
