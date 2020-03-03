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
public class mySpot implements IBot {

    private static final String BOTNAME = "Gimme That Spot!";
    private String[][] localBoard = new String[9][9];
    private int plusIndex;
    private int minusIndex;
    private String playerID;

    @Override
    public IMove doMove(IGameState state) {

        if (state.getMoveNumber() % 2 == 0) {
            playerID = "" + 0;
        } else {
            playerID = "" + 1;
        }

        localBoard = state.getField().getBoard();

        for (int x = 0; x < localBoard.length; x++) {
            for (int y = 0; y < localBoard.length; y++) {
                if (state.getField().isInActiveMicroboard(x, y)) {

                    if (x == 2 || x == 5 || x == 8)
                    {
                        plusIndex = 0;
                    }
                    else
                    {
                        plusIndex = 1;
                    }
                    
                    //Check Rows for playerid
                    if (localBoard[x][y].equals(playerID))
                    {
                        int mN = x+plusIndex;
                        System.out.println("Found playerID: " + playerID + " in coords: " + x + "," + y + " and " + mN + "," + y);
                    }
                    
                    
                    System.out.println("cordinate is: " + x + "," + y + " contains: " + localBoard[x][y]);

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
