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
import javafx.scene.layout.AnchorPane;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.Move;

/**
 *
 * @author Brian
 */
public class MainController implements Initializable {

    private GameManager gm;
    IGameState gameState;
    @FXML
    private AnchorPane AnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameState = new GameState();
        gm = new GameManager(gameState);

        UTTTButton btn = new UTTTButton();
        btn.setPrefSize(30, 30);
        btn.setMove(new Move(0, 0));
        btn.setLayoutX(10);
        btn.setLayoutY(10);
        btn.setOnMouseClicked(event -> {
            UTTTButton b = (UTTTButton) event.getSource();
            boolean moveSucces = gm.updateGame(btn.getMove());
            if (moveSucces) {
                if (gameState.getMoveNumber() % 2 == 0) {
                    b.setText("X");
                } else {
                    b.setText("O");
                }
            }
        });
        AnchorPane.getChildren().add(btn);
    }

    @FXML
    private void clickCell(ActionEvent event) {

        //btn.setStyle("-fx-background-color: #0000FF; -fx-border-color: #000000");
    }

}
