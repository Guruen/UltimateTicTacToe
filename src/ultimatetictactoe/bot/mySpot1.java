/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bot;

import java.util.List;
import java.util.Random;
import ultimatetictactoe.field.IField;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;
import ultimatetictactoe.move.Move;

/**
 *
 * @author BBran
 */
public class mySpot1 implements IBot {

    private static final String BOTNAME = "Compiling []]]]]......} 42%";
    private String[][] localBoard = new String[9][9];
    private int indexPlus;
    private int indexMinus;
    private String playerID;

    @Override
    public IMove doMove(IGameState state) {

        if (state.getMoveNumber() % 2 == 0) {
            playerID = "" + 0;
        } else {
            playerID = "" + 1;
        }

        this.localBoard = state.getField().getBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (state.getField().isInActiveMicroboard(i, j)) {
                    if (j == 2 || j == 5 || j == 8) {
                        indexPlus = 0;
                    } else {
                        indexPlus = 1;
                    }
                    if (j == 0 || j == 3 || j == 6) {
                        indexMinus = 0;
                    } else {
                        indexMinus = -1;
                    }
                    // checks cols for top space available
                    if (localBoard[i][j].equals(playerID) && localBoard[i][j + indexPlus].equals(playerID) && localBoard[i][j+indexMinus].equals(IField.EMPTY_FIELD)) {
                        int indexMove = j + indexMinus;

                        System.out.println("Play this: " + i + "," + indexMove);

                        IMove m = new Move(i, indexMove);
                        if (localBoard[i][indexMove].equals(IField.EMPTY_FIELD)) {
                            System.out.println("Valid Move!");
                            return m;
                        }
                        break;
                    }
                    // checks cols for bottom space available
                    if (localBoard[i][j+indexMinus].equals(playerID) && localBoard[i][j].equals(playerID) && localBoard[i][j+indexPlus].equals(IField.EMPTY_FIELD)) {
                        int indexMove = j + indexPlus;

                        System.out.println("Play this: " + i + "," + indexMove);

                        IMove m = new Move(i, indexMove);
                        if (localBoard[i][indexMove].equals(IField.EMPTY_FIELD)) {
                            System.out.println("Valid Move!");
                            return m;
                        }
                        break;
                    }            

                    if (i == 2 || i == 5 || i == 8) {
                        indexPlus = 0;
                    } else {
                        indexPlus = 1;
                    }
                    if (i == 0 || i == 3 || i == 6) {
                        indexMinus = 0;
                    } else {
                        indexMinus = -1;
                    }
                    //checks rows for first space available
                    if (localBoard[i][j].equals(playerID) && localBoard[i + indexPlus][j].equals(playerID) && localBoard[i + indexMinus][j].equals(IField.EMPTY_FIELD)) {
                        int indexMove = i + indexMinus;

                        System.out.println("Play this: " + indexMove + "," + j);

                        IMove m = new Move(indexMove, j);

                        if (localBoard[indexMove][j].equals(IField.EMPTY_FIELD)) {
                            System.out.println("Valid Move!");
                            return m;
                        }
                        break;
                    }
                    //checks row for last space available
//                    if (localBoard[i][j].equals(playerID) && localBoard[i+indexMinus][j].equals(playerID) && localBoard[i][j].equals(IField.EMPTY_FIELD)) {
//                        int indexMove = i + indexPlus;
//
//                        System.out.println("check tile: " + i + "," + j);
//                        System.out.println("Play this: " + indexMove + "," + j);
//
//                        IMove m = new Move(indexMove, j);
//
//                        if (localBoard[indexMove][j].equals(IField.EMPTY_FIELD)) {
//                            System.out.println("Valid Move!");
//                        }
//                        break;
//                    }

                    System.out.println("cordinate is: " + i + "," + j + " contains: " + localBoard[i][j]);

                }

            }
        }
        System.out.println("playerid is: " + playerID);
        Random rand = new Random();
        List<IMove> moves = state.getField().getAvailableMoves();
        
        IMove move = moves.get(rand.nextInt(moves.size()));
        return move;
    }

    @Override
    public String getBotName() {
        return BOTNAME;
    }

}
