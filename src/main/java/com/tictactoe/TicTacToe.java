package com.tictactoe;

public class TicTacToe {

    public static void main(String[] args) {
        GameController gameController = new GameController();
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
        }
    }
}
