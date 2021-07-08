package com.tictactoe;

import java.util.Random;

public class GameController {

    private Game game = new Game();
    private final Random generator = new Random();
    private Symbol playerSymbol;
    private Symbol computerSymbol;
    private Symbol whoseMove;

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

    public Symbol getSymbol(int x, int y) {
        return game.getSymbol(x, y);
    }

    public boolean setSymbol(Symbol symbol, int x, int y) {
        return game.setSymbol(symbol, x, y);
    }

    public void doComputerMove() {
        boolean condition;
        do {
            condition = setSymbol(this.computerSymbol, generator.nextInt(3), generator.nextInt(3));
        } while (!condition);
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

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public Symbol getComputerSymbol() {
        return computerSymbol;
    }

    public Symbol getWhoseMove() {
        return whoseMove;
    }

    public void setWhoseMove(Symbol whoseMove) {
        this.whoseMove = whoseMove;
    }
}
