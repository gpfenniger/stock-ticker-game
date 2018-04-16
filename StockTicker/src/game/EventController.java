package game;

import game.board.Board;
import game.enums.Stock;
import game.player.Player;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/** 	Event controller for Stock Ticker
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class EventController {
    public static final String[] PLAYER_BOX_NAMES = {"Money", "Wheat", "Industry", "Gold", "Silver", "Bonds", "Oil"};
    public static int numOfPlayers = 0;

    public StockTickerComp game;

    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<VBox> playerBoxes = new ArrayList<VBox>();
    private ArrayList<Text[]> playerTexts = new ArrayList<Text[]>();
    private int playerTurn = 1;

    @FXML private ToggleGroup stockPicker;
    @FXML private ToggleGroup quantityPicker;
    @FXML private Text playerTurnText;
    @FXML private Text stockDiceResult;
    @FXML private Text actionDiceResult;
    @FXML private Text quantityDiceResult;
    @FXML private HBox playerArea;
    @FXML private Canvas board;

    public void initialize() {
        game = new StockTickerComp();
        Board.setContext(board.getGraphicsContext2D());
        Board.draw();
    }

    /** Handles a player clicking the buy button
     *  Buys the selected stock for the player at
     *  quantity specified in the radio buttons.
     * 
     *  @param ActionEvent
     */
    @FXML public void handleBuy(ActionEvent event) {
        players.get(playerTurn).buyStock(selectedStock(), selectedQuantity());
        update();
    }

    /** Handles a player clicking the sell button
     *  Sells the selected stock for the player at
     *  quantity specified in the radio buttons.
     * 
     *  @param ActionEvent
     */
    @FXML public void handleSell(ActionEvent event) {
        players.get(playerTurn).sellStock(selectedStock(), selectedQuantity());
        update();
    }

    /** Handles event when the end turn button is clicked
     *  Sets turn to next player and updates the game.
     * 
     *  @param ActionEvent
     */
    @FXML public void handleEndOfTurn(ActionEvent event) {
        if (++playerTurn >= numOfPlayers)
            playerTurn = 0;
        playerTurnText.setText(playerTurnText.getText().substring(0, 13) + " " + (playerTurn + 1) + "'s turn");
        rollDice();
        update();
    }

    /** Adds a new player to the game when button is clicked
     *  @param ActionEvent
     */
    @FXML public void handleAddPlayer(ActionEvent event) {
        if (numOfPlayers + 1 <= 8) {   //max number of players
            numOfPlayers++;
            playerArea.getChildren().addAll(addPlayer());
        }
    }

    /** Rolls the dice
     *  Generates three random dice rolls, lists what they are
     *  and updates stock values or player money.
     */
    private void rollDice() {
        String stockToChange = game.dice[0].rollDie();
        String action = game.dice[1].rollDie();
        String change = game.dice[2].rollDie();
        stockDiceResult.setText("Stock Result: " + stockToChange);
        actionDiceResult.setText("Action Result: " + action);
        quantityDiceResult.setText("Quantity Result: " + change);

        if (action == "up")
            Stock.toStock(stockToChange).add(Integer.parseInt(change));
        else if (action == "down")
            Stock.toStock(stockToChange).subtract(Integer.parseInt(change));
        else if (action == "div")
            for (Player p : players) 
                p.div(Stock.toStock(stockToChange), Integer.parseInt(change));
    }
    /** Returns a Stock object of the selected stock
     *  Gets the toggled button value for stocks and parses
     *  it into a stock object.
     *  @return selected Stock
     */
    private Stock selectedStock() {
        String stockName;
        stockName = stockPicker.getSelectedToggle().toString().substring(46, stockPicker.getSelectedToggle().toString().length() - 1);
        return Stock.toStock(stockName);
    }

    /** Returns an integer of the selected quantity
     *  Gets the toggled button value for quantity and parses
     *  it into a useable integer.
     *  @return toggled quantity
     */
    private int selectedQuantity() {
        return Integer.parseInt(quantityPicker.getSelectedToggle().toString().substring(46, quantityPicker.getSelectedToggle().toString().length() - 1));
    }

    /** Adds a new player to the game
     *  Adds new objects to arraylists so that player stats can
     *  be changed from there.
     * 
     *  @return VBox of the player's area
     */
    private VBox addPlayer() {
        players.add(new Player());

        VBox playerBox = new VBox();
        Text[] texts = new Text[8]; 
        texts[0] = new Text("Player " + numOfPlayers);

        for (int i = 1; i <= PLAYER_BOX_NAMES.length; i++) {
            texts[i] = new Text(PLAYER_BOX_NAMES[i - 1]);
        }
        playerTexts.add(texts);

        for (Text t : texts)
            playerBox.getChildren().addAll(t);
        
        playerBoxes.add(playerBox);
        return playerBoxes.get(numOfPlayers - 1);
    }

    /** Updates the game values
     *  Updates player values, draws the board and etc.
     */
    public void update() {
        playerTurnText.setText("Player " + (playerTurn + 1) + "'s turn");
        for (int i = 0; i < players.size(); i++) {
            for (int j = 1; j < playerTexts.get(i).length; j++) {
                if (j == 1) playerTexts.get(i)[j].setText("$" + players.get(i).getMoney());
                else playerTexts.get(i)[j].setText(PLAYER_BOX_NAMES[j - 1] + " : " + players.get(i).getStocks()[j - 2]);
            }
        }
        Board.draw();
    }
}