package it.unicam.cs.pa.ConnectFour.player;

/**
 * @author giacchè
 *
 */
public enum PlayerType {
	RANDOM,
	INTERACTIVE;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch (this) {
			case RANDOM: return "Random";
			case INTERACTIVE: return "Interactive";
		}
		return super.name();
	}
}
