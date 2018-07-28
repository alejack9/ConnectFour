/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.player;

/**
 * @author Alessandra Boccuto
 *
 */
public enum PlayerType {
	INTERACTIVEPLAYER, RANDOMPLAYER;

	public String toString() {
		switch (this) {
		case INTERACTIVEPLAYER:
			return "Interactive Player";
		case RANDOMPLAYER:
			return "Random Player";
		}
		return super.name();
	}

	/**
	 * @param playername
	 * @return Player object
	 */
	public Player getPlayer(String playername) {
		switch (this) {
		case INTERACTIVEPLAYER:
			return new InteractivePlayer(playername);
		case RANDOMPLAYER:
			return new RandomPlayer(playername);
		}
		;
		return null;
	}
}