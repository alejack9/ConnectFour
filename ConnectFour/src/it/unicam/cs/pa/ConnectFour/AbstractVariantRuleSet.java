/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */
public abstract class AbstractVariantRuleSet extends AbstractRuleSet {

	protected AbstractRuleSet ruleSet;
	
	/**
	 * @param insertFun
	 */
	public AbstractVariantRuleSet(BiFunction<Cell[][], Integer, PieceLocation> insertFun , int[] DEFAULT_SIZE ) {
		super( insertFun , DEFAULT_SIZE );
	}
	
}
