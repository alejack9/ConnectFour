/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public enum RuleSetType {
	DEFAULT,
	POP,
	FIVEINROW;
	
	public static RuleSetType parse (String ruleset) {
		switch (ruleset.toLowerCase()) {
			case "default": return RuleSetType.DEFAULT;
			case "pop": return RuleSetType.POP;
			case "5inrow": return RuleSetType.FIVEINROW;
			default: throw new IllegalArgumentException("Ruleset '" + ruleset + "' is unkown.");
		}
	}
	
}
