/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bot;

import java.util.List;
import java.util.Random;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;

/**
 *
 * @author BBran
 */
public class RandoBot implements IBot
{

    private static final String BOTNAME = "Randy Random";
    private Random rand = new Random();

    @Override
    public IMove doMove(IGameState state)
    {
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
