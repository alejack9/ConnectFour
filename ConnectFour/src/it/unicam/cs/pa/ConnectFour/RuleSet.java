/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public enum RuleSet {
	DEFAULT,
	POP,
	FIVEINROW;
	
	public static RuleSet parse (String ruleset) {
		switch (ruleset.toLowerCase()) {
			case "default": return RuleSet.DEFAULT;
			case "pop": return RuleSet.POP;
			case "5inrow": return RuleSet.FIVEINROW;
			default: throw new IllegalArgumentException("Ruleset '" + ruleset + "' is unkown.");
		}
	}
	
}
