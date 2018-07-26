package it.unicam.cs.pa.ConnectFour.match;

/**
 * Represents the statuses that a {@link Match} may get
 * 
 * @author giacche`
 *
 */
public enum MatchStatus {
	/**
	 * Initializing Parameters
	 */
	INIT,
	/**
	 * Playing the game
	 */
	ARRANGE,
	/**
	 * Match ended
	 */
	END;
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch (this) {
		case INIT:
			return "Initializing";
		case ARRANGE:
			return "Playing";
		case END:
			return "Game over";
		}
		return super.name();
	}
}
