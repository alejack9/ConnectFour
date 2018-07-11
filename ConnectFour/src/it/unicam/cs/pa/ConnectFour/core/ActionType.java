package it.unicam.cs.pa.ConnectFour.core;

/**
 * @author giacchè
 *
 */
public enum ActionType {
	INSERT,
	POP;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
//		String minName = super.name().toLowerCase();
//		return minName.replaceFirst(minName.charAt(0) + "", super.name().charAt(0) + "");
		switch(this) {
			case INSERT: return "Insert";
			case POP: return "Pop";
		}
		return super.name();
	}	
}
