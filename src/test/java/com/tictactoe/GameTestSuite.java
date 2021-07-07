package com.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTestSuite {

    @Test
    void testCheckLegalMovePossibleOnStart() {
        //Given
        Game game = new Game();

        //When
        boolean result = game.checkLegalMovePossible();

        //Then
        assertTrue(result);
    }

    @Test
    void testCheckLegalMovePossibleWhenNoEmptyField() {
        //Given
        Game game = new Game();
        game.setSymbol(Symbol.O, 0, 0);
        game.setSymbol(Symbol.O, 1, 0);
        game.setSymbol(Symbol.O, 2, 0);
        game.setSymbol(Symbol.O, 0, 1);
        game.setSymbol(Symbol.O, 1, 1);
        game.setSymbol(Symbol.O, 2, 1);
        game.setSymbol(Symbol.O, 0, 2);
        game.setSymbol(Symbol.O, 1, 2);
        game.setSymbol(Symbol.O, 2, 2);

        //When
        boolean result = game.checkLegalMovePossible();

        //Then
        assertFalse(result);
    }
}
