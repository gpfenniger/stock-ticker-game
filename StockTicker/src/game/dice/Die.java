package game.dice;

/** 	A dice object which rolls from a string array
	@author Griffin Pfenniger < griffin (dot) pfenniger (at) gmail >
	@version 0.0, 2018-01-13
*/
public class Die {
	private String[] values;
	
	public Die(String[] values) {
		this.values = values;
	}
	
	public String rollDie() {
		return values[rndm()];
	}
	
	private int rndm() {
		return (int) ((Math.random() * 7) - 1);
	}
}