/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bot;

import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;

/**
 *
 * @author BBran
 */
public class Bot implements IBot {
    
  
    
    @Override
    public IMove doMove(IGameState state) {
        
        System.out.println("Bee Bop!");
        
        IMove move = state.getField().getAvailableMoves().get(0);

        System.out.println(move);
        
        return move;

    }
    
}
