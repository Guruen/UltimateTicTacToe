/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;

/**
 *
 * @author BBran
 */
public class RobertRando implements IBot
{

    private static final String BOTNAME = "Rando-Robert";
    private Random rand = new Random();

    @Override
    public IMove doMove(IGameState state)
    {
        List <IMove> moves = state.getField().getAvailableMoves();

        List <IMove> randomove = new ArrayList<>();
        
        randomove.add(moves.get(0));
        randomove.add(moves.get(moves.size()-1));
        
        IMove move = randomove.get(rand.nextInt(randomove.size()));
        
        return move;

    }

    @Override
    public String getBotName()
    {
        return BOTNAME;
    }
}
