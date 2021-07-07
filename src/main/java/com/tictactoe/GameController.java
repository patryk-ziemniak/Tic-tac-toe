package com.tictactoe;

import java.util.Random;

public class GameController {

    private Game game = new Game();
    private final Random generator = new Random();
    private Symbol playerSymbol;
    private Symbol computerSymbol;
    private Symbol whoseMove;
    private GameStatus gameStatus = GameStatus.PROCESSING;

    public GameController() {
        if (generator.nextInt(2) == 0) {
            this.playerSymbol = Symbol.O;
            this.computerSymbol = Symbol.X;
        } else {
            this.playerSymbol = Symbol.X;
            this.computerSymbol = Symbol.O;
        }
        if (generator.nextInt(2) == 0) {
            this.whoseMove = Symbol.O;
        } else {
            this.whoseMove = Symbol.X;
        }
    }

    public GameController(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
        if (playerSymbol == Symbol.O) {
            this.computerSymbol = Symbol.X;
        } else {
            this.computerSymbol = Symbol.O;
        }
        if (generator.nextInt(2) == 0) {
            this.whoseMove = Symbol.O;
        } else {
            this.whoseMove = Symbol.X;
        }
    }

    public GameController(Symbol playerSymbol, Symbol whoseMove) {
        this.playerSymbol = playerSymbol;
        if (playerSymbol == Symbol.O) {
            this.computerSymbol = Symbol.X;
        } else {
            this.computerSymbol = Symbol.O;
        }
        this.whoseMove = whoseMove;
    }

    public GameStatus checkGameStatus() {
        GameStatus status = GameStatus.PROCESSING;
        Symbol winner = game.checkWinner();
        boolean emptyFields = game.checkLegalMovePossible();

        if (winner == playerSymbol) {
            status = GameStatus.WIN;
        } else if (winner == computerSymbol) {
            status = GameStatus.LOSE;
        } else if (winner == Symbol.NONE && emptyFields == false) {
            status = GameStatus.DRAW;
        }
        return status;
    }

    public void newGame() {
        game = new Game();
    }
}
