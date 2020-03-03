package ultimatetictactoe.game;

import ultimatetictactoe.bot.IBot;
import ultimatetictactoe.field.Field;
import ultimatetictactoe.field.IField;
import ultimatetictactoe.move.IMove;
import ultimatetictactoe.move.Move;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final String NON_AVAILABLE_MACRO_CELL = "-";
    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private IMove botMove;
    private String[] winner = new String[9];

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move) {
        //Verify the new move
        if (!verifyMoveLegality(move)) {
            return false;
        }

        //Update the currentState
        updateBoard(move);
        updateMacroboard(move);

        //Checks for a Winner in MacroBoard
        findMacroWinner();

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        currentState.setMoveNumber(currentState.getMoveNumber() + 1);

        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return updateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean verifyMoveLegality(IMove move) {

        boolean isInActiveMicroboard = currentState.getField().isInActiveMicroboard(move.getX(), move.getY());
        boolean isAvailable = currentState.getField().getPlayerId(move.getX(), move.getY()).equals(IField.EMPTY_FIELD);
        return isInActiveMicroboard && isAvailable;
    }

    private void updateBoard(IMove move) {
        currentState.getField().getBoard()[move.getX()][move.getY()] = "" + currentPlayer;
    }

    private void updateMacroboard(IMove move) {

        int macroX = move.getX() % 3;
        int macroY = move.getY() % 3;
        String[][] macroBoard = currentState.getField().getMacroboard();

        if (currentState.getField().getMacroboard()[macroX][macroY] != IField.AVAILABLE_FIELD
                && currentState.getField().getMacroboard()[macroX][macroY] != NON_AVAILABLE_MACRO_CELL) {
            for (int x = 0; x < macroBoard.length; x++) {
                for (int y = 0; y < macroBoard[x].length; y++) {
                    if (currentState.getField().getMacroboard()[x][y] == NON_AVAILABLE_MACRO_CELL) {
                        macroBoard[x][y] = IField.AVAILABLE_FIELD;
                    }
                }
            }
        }

        if (currentState.getField().getMacroboard()[macroX][macroY] == NON_AVAILABLE_MACRO_CELL
                || currentState.getField().getMacroboard()[macroX][macroY] == IField.AVAILABLE_FIELD) {
            for (int x = 0; x < macroBoard.length; x++) {
                for (int y = 0; y < macroBoard[x].length; y++) {
                    if (macroBoard[x][y] == IField.AVAILABLE_FIELD) {
                        macroBoard[x][y] = NON_AVAILABLE_MACRO_CELL;
                    }
                }
            }
            currentState.getField().getMacroboard()[macroX][macroY] = IField.AVAILABLE_FIELD;
        }

    }

    public IMove getBotMove() {
        return botMove;
    }

    private void findMacroWinner() {
        String[][] board = currentState.getField().getBoard();
        int b = 0;
        int n = 0;

        for (int y = 0; y < 3; y++) {
            for (int i = 0; i < 3; i++) {
                for (int x = 0; x < 3; x++) {
                    if ( //verital    
                            board[b + x][n + 0].equals(board[b + x][n + 1]) && board[b + x][n + 0].equals(board[b + x][n + 2])
                            && !board[b + x][n + 0].equals(".") && !board[b + x][n + 1].equals(".") && !board[b + x][n + 2].equals(".")
                            //horizontal
                            || board[n + 0][b + x].equals(board[n + 1][b + x]) && board[n + 0][b + x].equals(board[n + 2][b + x])
                            && !board[n + 0][b + x].equals(".") && !board[n + 1][b + x].equals(".") && !board[n + 2][b + x].equals(".")
                            //top left to buttom rigth
                            || board[b + 0][n + 0].equals(board[b + 1][n + 1]) && board[b + 0][n + 0].equals(board[b + 2][n + 2])
                            && !board[b + 0][n + 0].equals(".") && !board[b + 1][n + 1].equals(".") && !board[b + 2][n + 2].equals(".")
                            //top right to buttom left
                            || board[b + 2][n + 0].equals(board[b + 1][n + 1]) && board[b + 2][n + 0].equals(board[b + 0][n + 2])
                            && !board[b + 2][n + 0].equals(".") && !board[b + 1][n + 1].equals(".") && !board[b + 0][n + 2].equals(".")) {

                        //board 1
                        if (b == 0 && n == 0 && winner[0] == null) {

                            winner[0] = currentPlayer + "";

                            System.out.println("player " + winner[0] + " has won this board");
                        }
                        //board 2
                        if (b == 3 && n == 0 && winner[1] == null) {
                            winner[1] = currentPlayer + "";

                            System.out.println("player " + winner[1] + " has won this board");
                        }
                        //board 3
                        if (b == 6 && n == 0 && winner[2] == null) {
                            winner[2] = currentPlayer + "";

                            System.out.println("player " + winner[2] + " has won this board");
                        }
                        //board 4
                        if (b == 0 && n == 3 && winner[3] == null) {
                            winner[3] = currentPlayer + "";

                            System.out.println("player " + winner[3] + " has won this board");
                        }
                        //board 5
                        if (b == 3 && n == 3 && winner[4] == null) {
                            winner[4] = currentPlayer + "";

                            System.out.println("player " + winner[4] + " has won this board");
                        }
                        //board 6
                        if (b == 6 && n == 3 && winner[5] == null) {
                            winner[5] = currentPlayer + "";

                            System.out.println("player " + winner[5] + " has won this board");
                        }
                        //board 7
                        if (b == 0 && n == 6 && winner[6] == null) {
                            winner[6] = currentPlayer + "";

                            System.out.println("player " + winner[6] + " has won this board");
                        }
                        //board 8
                        if (b == 3 && n == 6 && winner[7] == null) {
                            winner[7] = currentPlayer + "";

                            System.out.println("player " + winner[7] + " has won this board");
                        }
                        //board 9
                        if (b == 6 && n == 6 && winner[8] == null) {
                            winner[8] = currentPlayer + "";

                            System.out.println("player " + winner[8] + " has won this board");
                        }
                    }
                }
                b = b + 3;
            }
            b = 0;
            n = n + 3;

        }

    }

    private void findBoardWinner() {

        int q = 0;

        for (int x = 0; x < 3; x++) {
            if (winner[0+q].equals(winner[1+q]) && winner[0+q].equals(winner[2+q]) && winner[0+q] != null) {
                System.out.println("We have a winner");
            }
            q = q + 3;
        }

    }

}
