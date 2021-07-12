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
    private final Image symbolX24 = new Image("file:src/main/resources/symbolX24.png");
    private final Image symbolO24 = new Image("file:src/main/resources/symbolO24.png");
    GameController gameController = new GameController();
    Symbol playerSymbol = gameController.getPlayerSymbol();
    Symbol computerSymbol = gameController.getComputerSymbol();

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

    public int convertPixelsToGridIndex(double pixels) {
        int result = 0;
        if (pixels < 115) {
            result = 0;
        } else if (pixels > 114 && pixels < 229) {
            result = 1;
        } else if (pixels > 228 && pixels < 343) {
            result = 2;
        }
        return result;
    }

    public void nextMove(GridPane gameBoard, Symbol whoseMove, GridPane menuPanel, ImageView turnOfImage, Label statusDetailLabel) {
        menuPanel.getChildren().removeAll(turnOfImage);
        if (whoseMove == Symbol.O) {
            turnOfImage = new ImageView(symbolO24);
        } else if (whoseMove == Symbol.X) {
            turnOfImage = new ImageView(symbolX24);
        }
        menuPanel.add(turnOfImage, 2, 2);

        if (gameController.getWhoseMove() == computerSymbol) {
            gameController.doComputerMove();
            gameController.setWhoseMove(playerSymbol);
        } else {
            gameBoard.setOnMouseClicked(field -> gameController.setSymbol(playerSymbol, convertPixelsToGridIndex(field.getX()),
                    convertPixelsToGridIndex(field.getY())));
            gameController.setWhoseMove(computerSymbol);
        }
        displayBoardFieldsSymbols(gameBoard, gameController);
        menuPanel.getChildren().remove(statusDetailLabel);
        if (gameController.checkGameStatus() == GameStatus.DRAW) {
            statusDetailLabel = new Label("DRAW!");
            menuPanel.add(statusDetailLabel, 2, 3);
            statusDetailLabel.setFont(new Font(24.0));
        } else if (gameController.checkGameStatus() == GameStatus.LOSE) {
            statusDetailLabel = new Label("YOU LOSE!");
            menuPanel.add(statusDetailLabel, 2, 3);
            statusDetailLabel.setFont(new Font(24.0));
        } else if (gameController.checkGameStatus() == GameStatus.WIN) {
            statusDetailLabel = new Label("YOU WIN!");
            menuPanel.add(statusDetailLabel, 2, 3);
            statusDetailLabel.setFont(new Font(24.0));
        } else {
            statusDetailLabel = new Label("In Game");
            menuPanel.add(statusDetailLabel, 2, 3);
            statusDetailLabel.setFont(new Font(24.0));
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
        gameBoard.setGridLinesVisible(true);                //lines showing columns and rows lines in application

        GridPane menuPanel = new GridPane();
        menuPanel.setPadding(new Insets(25, 25, 25, 0));
        ColumnConstraints col0MP = new ColumnConstraints(104);    // columns in total 283 px
        ColumnConstraints col1MP = new ColumnConstraints(35);
        ColumnConstraints col2MP = new ColumnConstraints(144);
        RowConstraints row0MP = new RowConstraints(30);       // rows in total 342 px
        RowConstraints row1MP = new RowConstraints(50);
        RowConstraints row2MP = new RowConstraints(50);
        RowConstraints row3MP = new RowConstraints(78);
        RowConstraints row4MP = new RowConstraints(78);
        RowConstraints row5MP = new RowConstraints(56);
        menuPanel.getColumnConstraints().addAll(col0MP, col1MP, col2MP);
        menuPanel.getRowConstraints().addAll(row0MP, row1MP, row2MP, row3MP, row4MP, row5MP);
        menuPanel.setGridLinesVisible(true);                //lines showing columns and rows lines in application

        Button newGame = new Button("New Game");
        menuPanel.add(newGame, 2, 0);
        GridPane.setHalignment(newGame, HPos.CENTER);
        GridPane.setValignment(newGame, VPos.TOP);
        newGame.setOnAction(click -> {
            gameController.newGame();
            clearBoardFields(gameBoard);
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

        Label computer = new Label("Computer");
        menuPanel.add(computer, 2, 1);
        GridPane.setHalignment(computer, HPos.RIGHT);
        computer.setFont(new Font(24.0));

        ImageView computerSymbolImage;
        if (computerSymbol == Symbol.O) {
            computerSymbolImage = new ImageView(symbolO24);
        } else {
            computerSymbolImage = new ImageView(symbolX24);
        }
        menuPanel.add(computerSymbolImage, 2, 1);
        GridPane.setHalignment(computerSymbolImage, HPos.LEFT);

        Label vs = new Label("vs");
        menuPanel.add(vs, 1, 1);
        vs.setFont(new Font(24.0));
        GridPane.setHalignment(vs, HPos.CENTER);

        Label turnOf = new Label("Turn of");
        menuPanel.add(turnOf, 0, 2, 1, 1);
        GridPane.setHalignment(turnOf, HPos.RIGHT);
        turnOf.setFont(new Font(24.0));

        ImageView turnOfImage = new ImageView();
        if (gameController.getWhoseMove() == Symbol.O) {
            turnOfImage = new ImageView(symbolO24);
        } else if (gameController.getWhoseMove() == Symbol.X) {
            turnOfImage = new ImageView(symbolX24);
        }
        menuPanel.add(turnOfImage, 2, 2);

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

        Button nextMove = new Button("Next Move");
        menuPanel.add(nextMove, 0, 0);
        GridPane.setHalignment(nextMove, HPos.CENTER);
        GridPane.setValignment(nextMove, VPos.TOP);
        ImageView finalTurnOfImage = turnOfImage;
        nextMove.setOnAction(click -> nextMove(gameBoard, gameController.getWhoseMove(), menuPanel, finalTurnOfImage, statusDetailLabel));

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
