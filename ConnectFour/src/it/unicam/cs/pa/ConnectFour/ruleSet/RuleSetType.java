package it.unicam.cs.pa.ConnectFour.ruleSet;

/**
 * @author giacchè
 *
 */
public enum RuleSetType {
	DEFAULT,
	POP,
	FIVEINROW;
	
	/**
	 * @param ruleset ruleSet name
	 * @return The corresponding ruleSetType
	 * @throws IllegalArgumentException unknown rule set
	 */
	public static RuleSetType parse (String ruleset) throws IllegalArgumentException {
		switch (ruleset.toLowerCase()) {
			case "default": return RuleSetType.DEFAULT;
			case "pop": return RuleSetType.POP;
			case "fiveinrow": return RuleSetType.FIVEINROW;
			default: throw new IllegalArgumentException("Ruleset '" + ruleset + "' is unkown.");
		}
	}
	
}
