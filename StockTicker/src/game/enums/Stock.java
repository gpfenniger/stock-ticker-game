package game.enums;

import javafx.scene.paint.Color;

/** 	Stock object holds a single stocks properties
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class Stock {
	public static Stock[] stocks = {
		new Stock("Wheat", 0),
		new Stock("Industry", 1),
		new Stock("Gold", 2),
		new Stock("Silver", 3),
		new Stock("Oil", 4),
		new Stock("Bonds", 5)
	};

	private String name;
	private int value = 100;
	private int pos;
	private Color colour;

	public Stock(String name, int pos) {
		this.name = name;
		this.pos = pos;
		this.colour = stocksColour();
	}
	
	private Color stocksColour() {
		switch(name) {
			case "Wheat" : return Color.YELLOW;
			case "Industry" : return Color.PINK;
			case "Gold" : return Color.GOLD;
			case "Silver" : return Color.SILVER;
			case "Oil" : return Color.BLUE;
			case "Bonds" : return Color.GREEN;
			default: return Color.WHITE;
		}
	}

	public String getName() { return name; }
	public int getValue() { return value; }
	public Color getColour() { return colour; }
	public int getPos() { return pos; }
	public void add(int add) { 
		if (this.value + add < 200)
			this.value += add; 
		else this.value = 100;
	}
	public void subtract(int subtract) { 
		if (this.value - subtract > 0)
			this.value -= subtract;
		else this.value = 100; 
	}
	public void reset() { this.value = 100; }

	public static Stock toStock(String s) {
		switch(s) {
			case "Wheat": return Stock.stocks[0];
			case "Industry" : return Stock.stocks[1];
			case "Gold" : return Stock.stocks[2];
			case "Silver" : return Stock.stocks[3];
			case "Bonds" : return Stock.stocks[4];
			case "Oil" : return Stock.stocks[5];
			default: return Stock.stocks[0];
		}
	}
}