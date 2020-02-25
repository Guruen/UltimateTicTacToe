/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;
import ultimatetictactoe.game.IGameState;

/**
 *
 * @author Brian
 */
public class MainController implements Initializable
{
    private GameManager gm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        IGameState gameState = new GameState();
        gm = new GameManager(gameState);
    }    

    @FXML
    private void clickCell(ActionEvent event) {
        
        JFXButton btn = (JFXButton) event.getSource();
        btn.setText("X");
        //btn.setStyle("-fx-background-color: #0000FF; -fx-border-color: #000000");
    }
    
}
