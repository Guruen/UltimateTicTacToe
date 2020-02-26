/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ultimatetictactoe.field.Field;
import ultimatetictactoe.field.IField;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;

/**
 *
 * @author BBran
 */
public class Bot implements IBot {
    
    private IField field;
    
    public Bot()
    {
        field = new Field();
        
    }
    
    @Override
    public IMove doMove(IGameState state) {
        
        List<IMove> list = new ArrayList<>();
        list.addAll(field.getAvailableMoves());
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
    
}
