/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import ultimatetictactoe.bot.Bot;
import ultimatetictactoe.bot.IBot;
import ultimatetictactoe.game.GameManager;
import ultimatetictactoe.game.GameState;
import ultimatetictactoe.game.IGameState;
import ultimatetictactoe.move.IMove;
import ultimatetictactoe.move.Move;

/**
 *
 * @author Brian
 */
public class MainController implements Initializable
{

    private GameManager gm;
    IGameState gameState;
    IBot bot;
    @FXML
    private AnchorPane AnchorPane;
    private List<IMove> removeShadowsList;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        gameState = new GameState();
        bot = new Bot();
        gm = new GameManager(gameState, bot);
        creatAllCells();
        
    }

    private void creatAllCells()
    {
        int btnWidth = 30;
        int btnHeight = 30;
        int boardOffsetEdge = 20;
        int macroBoardPadding = 8;

        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {
                String sX = x + "";
                String sY = y + "";
                UTTTButton btn = new UTTTButton();
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setMove(new Move(x, y));
                btn.setId(sX + sY);

                int paddingX = boardOffsetEdge + (x / 3) * macroBoardPadding;
                int paddingY = boardOffsetEdge + (y / 3) * macroBoardPadding;

                btn.setLayoutX(paddingX + btnWidth * x);
                btn.setLayoutY(paddingY + btnHeight * y);
                btn.setOnMouseClicked(event ->
                {
                    UTTTButton b = (UTTTButton) event.getSource();
                    boolean moveSucces = gm.updateGame(b.getMove());
                    if (moveSucces)
                    {
                        if (gameState.getMoveNumber() % 2 == 0)
                        {
                            b.setText("X");
                        } else
                        {
                            b.setText("O");
                        }
                        botMove();
                        removeShadows();
                        setShadowsOnButtons();
                    }
                });
                AnchorPane.getChildren().add(btn);
            }
        }

    }

    private void botMove()
    {
        gm.updateGame();

        String x = Integer.toString(gm.getBotMove().getX());
        String y = Integer.toString(gm.getBotMove().getY());

        String findBtn = "#" + x + y;

        UTTTButton foundBtn = (UTTTButton) AnchorPane.lookup(findBtn);

        if (gameState.getMoveNumber() % 2 == 0)
        {
            foundBtn.setText("X");
        } else
        {
            foundBtn.setText("O");
        }

    }

    private void setShadowsOnButtons()
    {
        DropShadow shadow = new DropShadow();
        List<IMove> buttonsToShow = gameState.getField().getAvailableMoves();
        removeShadowsList = buttonsToShow;

        for (int i = 0; i < buttonsToShow.size(); i++)
        {
            String x = Integer.toString(buttonsToShow.get(i).getX());
            String y = Integer.toString(buttonsToShow.get(i).getY());

            String findBtn = "#" + x + y;

            UTTTButton foundBtn = (UTTTButton) AnchorPane.lookup(findBtn);

            foundBtn.setEffect(shadow);
        }

    }

    private void removeShadows()
    {
        try
        {
            if (!removeShadowsList.isEmpty())
            {
                for (int i = 0; i < removeShadowsList.size(); i++)
                {
                    String x = Integer.toString(removeShadowsList.get(i).getX());
                    String y = Integer.toString(removeShadowsList.get(i).getY());

                    String findBtn = "#" + x + y;

                    UTTTButton foundBtn = (UTTTButton) AnchorPane.lookup(findBtn);

                    foundBtn.setEffect(null);
                }
            }
        } catch (Exception e)
        {
        }

    }

    @FXML
    private void clickCell(ActionEvent event)
    {

        //btn.setStyle("-fx-background-color: #0000FF; -fx-border-color: #000000");
    }

}
