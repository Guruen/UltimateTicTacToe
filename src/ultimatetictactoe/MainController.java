/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ultimatetictactoe.bot.Bot;
import ultimatetictactoe.bot.IBot;
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
    IBot bot;
    @FXML
    private AnchorPane AnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gameState = new GameState();
        bot = new Bot();
        gm = new GameManager(gameState, bot);
        creatAllCells();

    }

    private void creatAllCells() {
        int btnWidth = 30;
        int btnHeight = 30;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                UTTTButton btn = new UTTTButton();
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setMove(new Move(x, y));
                btn.setLayoutX(10 + btnWidth * x);
                btn.setLayoutY(10 + btnHeight * y);
                btn.setOnMouseClicked(event -> {
                    UTTTButton b = (UTTTButton) event.getSource();
                    boolean moveSucces = gm.updateGame(b.getMove());
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

        }

    }

    @FXML
    private void clickCell(ActionEvent event) {

        //btn.setStyle("-fx-background-color: #0000FF; -fx-border-color: #000000");
    }

}
