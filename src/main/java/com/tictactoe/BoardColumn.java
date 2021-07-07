package com.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class BoardColumn {

    private final List<Symbol> column = new ArrayList<>();

    public BoardColumn() {
        column.add(Symbol.NONE);
        column.add(Symbol.NONE);
        column.add(Symbol.NONE);
    }

    public List<Symbol> getColumn() {
        return column;
    }
}
