package game.player;

import game.enums.Stock;

/** 	Constructs a Player object.
	@author Griffin Pfenniger < pfenniger (dot) griffin (at) gmail >
	@version 0.0, 2018-01-13
*/
public class Player {
	private static int playerCount = 0;
	private int playerNum;
	private int money = 5000;
	private int[] stocks = {0, 0, 0, 0, 0, 0};	//parallel data structure

	public Player() {
		this.playerNum = ++playerCount; 
	}

	public void buyStock(Stock stock, int quantity) {
		if (this.money >= (stock.getValue() / 100) * quantity) {
			this.stocks[stock.getPos()] += quantity;
			this.money -= (stock.getValue() / 100) * quantity;
		}
	}

	public void sellStock(Stock stock, int quantity) {
		if (this.stocks[stock.getPos()] >= quantity) {
			this.money += (stock.getValue() / 100) * quantity;
			this.stocks[stock.getPos()] -= quantity;
		}
	}

	public void div(Stock stock, int change) {
		this.money += stocks[stock.getPos()] / change;
	}

	public int[] getStocks() { return stocks; }
	public int getMoney() { return money; }

	@Override public String toString() {
		return "Player " + playerNum;
	}
}