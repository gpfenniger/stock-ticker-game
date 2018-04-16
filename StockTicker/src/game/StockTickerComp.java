package game;

import game.board.Board;
import game.dice.Die;

/** 	Composition class of Stock Ticker
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class StockTickerComp {
    public Die[] dice = new Die[3];

    public StockTickerComp() {
        dice[0] = new Die(new String[] {"Wheat", "Industry", "Gold", "Silver", "Bonds", "Oil"});
        dice[1] = new Die(new String[] {"up", "down", "div", "up", "down", "div"});
        dice[2] = new Die(new String[] {"5", "10", "20", "5", "10", "20"});
    }
}