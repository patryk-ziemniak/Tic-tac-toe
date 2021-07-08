package com.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<BoardColumn> boardColumns = new ArrayList<>();

    public Game() {
        boardColumns.add(new BoardColumn());
        boardColumns.add(new BoardColumn());
        boardColumns.add(new BoardColumn());
    }

    public Symbol getSymbol(int x, int y) {
        return boardColumns.get(x).getColumn().get(y);
    }

    public boolean setSymbol(Symbol symbol, int x, int y) {
        if (getSymbol(x, y) == Symbol.NONE) {
            boardColumns.get(x).getColumn().set(y, symbol);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLineOfThreeSymbols(int x1, int y1, int x2, int y2, int x3, int y3) {
        return getSymbol(x1, y1) != Symbol.NONE && getSymbol(x1, y1) == getSymbol(x2, y2) && getSymbol(x2, y2) == getSymbol(x3, y3);
    }

    public Symbol checkWinner() {
        Symbol winner = Symbol.NONE;

        if (checkLineOfThreeSymbols(0,0,1,0,2,0)) { //row1
            winner = getSymbol(0, 0);
        } else if (checkLineOfThreeSymbols(0,1,1,1,2,1)) { //row2
            winner = getSymbol(0, 1);
        } else if (checkLineOfThreeSymbols(0,2,1,2,2,2)) { //row3
            winner = getSymbol(0, 2);
        } else if (checkLineOfThreeSymbols(0,0,0,1,0,2)) { //column1
            winner = getSymbol(0, 0);
        } else if (checkLineOfThreeSymbols(1,0,1,1,1,2)) { //column2
            winner = getSymbol(1, 0);
        } else if (checkLineOfThreeSymbols(2,0,2,1,2,2)) { //column3
            winner = getSymbol(2, 0);
        } else if (checkLineOfThreeSymbols(0,0,1,1,2,2)) { //diagonal TopLeft to BottomRight
            winner = getSymbol(0, 0);
        } else if (checkLineOfThreeSymbols(0,2,1,1,2,0)) { //diagonal BottomLeft to TopRight
            winner = getSymbol(0, 2);
        }
        return winner;
    }

    public boolean checkLegalMovePossible() {
        return boardColumns.stream()
                .flatMap(boardColumn -> boardColumn.getColumn().stream())
                .anyMatch(symbol -> symbol == Symbol.NONE);
    }
}
