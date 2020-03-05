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
public class mySpot implements IBot
{

    private static final String BOTNAME = "Gimme That Spot!";
    private String[][] localBoard = new String[9][9];
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;
    private String playerID;

    @Override
    public IMove doMove(IGameState state)
    {
        
        if (state.getMoveNumber() % 2 == 0)
        {
            playerID = "" + 0;
        } else
        {
            playerID = "" + 1;
        }

        localBoard = state.getField().getBoard();

        for (int x = 0; x < localBoard.length; x++)
        {
            for (int y = 0; y < localBoard.length; y++)
            {
                if (state.getField().isInActiveMicroboard(x, y))
                {

                    if (x >= 6)
                    {
                        x1 = 6;
                        x2 = 7;
                        x3 = 8;
                    } else if (x >= 3)
                    {
                        x1 = 3;
                        x2 = 4;
                        x3 = 5;
                    } else
                    {
                        x1 = 0;
                        x2 = 1;
                        x3 = 2;
                    }

                    if (y >= 6)
                    {
                        y1 = 6;
                        y2 = 7;
                        y3 = 8;
                    } else if (x >= 3)
                    {
                        y1 = 3;
                        y2 = 4;
                        y3 = 5;
                    } else
                    {
                        y1 = 0;
                        y2 = 1;
                        y3 = 2;
                    }
                    //Check Rows for valid spot
                    if (localBoard[x1][y].equals(playerID) && localBoard[x2][y].equals(playerID) && localBoard[x3][y].equals(IField.EMPTY_FIELD))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x1 + "," + y + " & " + x2 + "," + y);
                        if (localBoard[x3][y].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x3 + "," + y);
                            IMove m = new Move(x3, y);
                            return m;
                        }
                    }

                    if (localBoard[x1][y].equals(IField.EMPTY_FIELD) && localBoard[x2][y].equals(playerID) && localBoard[x3][y].equals(playerID))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x2 + "," + y + " & " + x3 + "," + y);
                        if (localBoard[x1][y].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x1 + "," + y);
                            IMove m = new Move(x1, y);
                            return m;
                        }
                    }

                    if (localBoard[x1][y].equals(playerID) && localBoard[x2][y].equals(IField.EMPTY_FIELD) && localBoard[x3][y].equals(playerID))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x1 + "," + y + " & " + x3 + "," + y);
                        if (localBoard[x2][y].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x2 + "," + y);
                            IMove m = new Move(x2, y);
                            return m;
                        }
                    }


                    // checks cols for valid move
                    if (localBoard[x][y1].equals(playerID) && localBoard[x][y2].equals(playerID) && localBoard[x][y3].equals(IField.EMPTY_FIELD))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x + "," + y1 + " & " + x + "," + y2);
                        if (localBoard[x][y3].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x + "," + y3);
                            IMove m = new Move(x, y3);
                            return m;
                        }
                    }
                    if (localBoard[x][y1].equals(IField.EMPTY_FIELD) && localBoard[x][y2].equals(playerID) && localBoard[x][y3].equals(playerID))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x + "," + y2 + " & " + x + "," + y3);
                        if (localBoard[x][y1].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x + "," + y1);
                            IMove m = new Move(x, y1);
                            return m;
                        }
                    }

                    if (localBoard[x][y1].equals(playerID) && localBoard[x][y2].equals(IField.EMPTY_FIELD) && localBoard[x][y3].equals(playerID))
                    {
                        System.out.println("Found playerID: " + playerID + " in coords: " + x + "," + y1 + " & " + x + "," + y3);
                        if (localBoard[x][y2].equals(IField.EMPTY_FIELD))
                        {
                            System.out.println("Play Valid move: " + x + "," + y2);
                            IMove m = new Move(x, y2);
                            return m;
                        }
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
    public String getBotName()
    {
        return BOTNAME;
    }

}
