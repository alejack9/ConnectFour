/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

/**
 * @author Alessandra Boccuto
 *
 */
public enum RuleSetType {
	DEFAULT, POPOUT;

	public String toString() {
		switch (this) {
		case DEFAULT:
			return "DefaultRuleset";
		case POPOUT:
			return "PopOutRuleset";
		}
		return super.name();
	}

	/**
	 * @return Ruleset object
	 */
	public RuleSet getRuleSet() {
		switch (this) {
		case DEFAULT:
			return new DefaultRuleSet();
		case POPOUT:
			return new PopOutRuleSet();
		}
		return null;
	}
}
