package it.unicam.cs.pa.ConnectFour.factory;

/**
 * @author giacchè
 *
 */
public enum Factories {
	PLAYERS,
	PIECES,
	REFEREE;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
//		String minName = super.name().toLowerCase();
//		return minName.replaceFirst(minName.charAt(0) + "", super.name().charAt(0) + "");
		switch(this) {
			case PIECES: return "Pieces";
			case REFEREE: return "Referee";
			default: return super.name();
		}
	}
}
