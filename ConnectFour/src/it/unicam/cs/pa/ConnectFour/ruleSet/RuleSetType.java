package it.unicam.cs.pa.ConnectFour.ruleSet;

/**
 * @author giacche`
 *
 */
public enum RuleSetType {
	/**
	 * Default rule set: four in row to win, gravity rules
	 */
	DEFAULT,
	/**
	 * Default rule set but players can also \"pop\" a column: remove the last Piece to make the column scroll
	 */
	POP,
	/**
	 * Default rule set but five in row to win
	 */
	FIVEINROW;
	 
	/*public Size defaultSize() {
		switch(this) {
			case DEFAULT: return new Size(new Integer[] { 6 , 7 });
			case POP: return new Size(new Integer[] { 6 , 7 });
			case FIVEINROW: return new Size(new Integer[] { 8 , 8 });
		}
		return new Size(new Integer[] { 0 , 0 });
	}*/

	/**
	 * @param ruleset ruleSet name
	 * @return The corresponding ruleSetType
	 * @throws IllegalArgumentException unknown rule set
	 */
	public static RuleSetType parse (String rulesetName) throws IllegalArgumentException {
		for (RuleSetType rs : RuleSetType.values()) {
			if(rs.name().equals(rulesetName)) {
				return rs;
			}
		}
		throw new IllegalArgumentException("RuleSet Type '" + rulesetName + "' is unkown.");
//		switch (rulesetName.toLowerCase()) {
//			case "default": return RuleSetType.DEFAULT;
//			case "pop": return RuleSetType.POP;
//			case "fiveinrow": return RuleSetType.FIVEINROW;
//			default: throw new IllegalArgumentException("RuleSet Type '" + rulesetName + "' is unkown.");
//		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch(this) {
			case DEFAULT: return "Default";
			case POP: return "Pop";
			case FIVEINROW: return "Five in Row";
		}
		return super.name();
	}
	
	
}
