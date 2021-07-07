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

    public Symbol checkWinner() {
        Symbol winner = Symbol.NONE;

        if (getSymbol(0, 0) != Symbol.NONE && getSymbol(0, 0) == getSymbol(1, 0) && getSymbol(1, 0) == getSymbol(2, 0)) { //row1
            winner = getSymbol(0, 0);
        } else if (getSymbol(0, 1) != Symbol.NONE && getSymbol(0, 1) == getSymbol(1, 1) && getSymbol(1, 1) == getSymbol(2, 1)) { //row2
            winner = getSymbol(0, 1);
        } else if (getSymbol(0, 2) != Symbol.NONE && getSymbol(0, 2) == getSymbol(1, 2) && getSymbol(1, 2) == getSymbol(2, 2)) { //row3
            winner = getSymbol(0, 2);
        } else if (getSymbol(0, 0) != Symbol.NONE && getSymbol(0, 0) == getSymbol(0, 1) && getSymbol(0, 1) == getSymbol(0, 2)) { //column1
            winner = getSymbol(0, 0);
        } else if (getSymbol(1, 0) != Symbol.NONE && getSymbol(1, 0) == getSymbol(1, 1) && getSymbol(1, 1) == getSymbol(1, 2)) { //column2
            winner = getSymbol(1, 0);
        } else if (getSymbol(2, 0) != Symbol.NONE && getSymbol(2, 0) == getSymbol(2, 1) && getSymbol(2, 1) == getSymbol(2, 2)) { //column3
            winner = getSymbol(2, 0);
        } else if (getSymbol(0, 0) != Symbol.NONE && getSymbol(0, 0) == getSymbol(1, 1) && getSymbol(1, 1) == getSymbol(2, 2)) { //diagonal TopLeft to BottomRight
            winner = getSymbol(0, 0);
        } else if (getSymbol(0, 2) != Symbol.NONE && getSymbol(0, 2) == getSymbol(1, 1) && getSymbol(1, 1) == getSymbol(2, 0)) { //diagonal BottomLeft to TopRight
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
