/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */
public abstract class AbstractRuleSet {
	
	protected final BiFunction<Cell[][], Integer, PieceLocation> insertFun;
	/**
	 * rows,columns
	 */ 
	protected final int[] DEFAULT_SIZE;

	public AbstractRuleSet ( BiFunction<Cell[][], Integer, PieceLocation> insertFun , int[] DEFAULT_SIZE ) {
		this.DEFAULT_SIZE = DEFAULT_SIZE;
		this.insertFun = insertFun;
	}
	
	/**
	 * @param column
	 * @return
	 */
	public abstract boolean isValidInsert( Cell[][] field, int column );

	public abstract boolean isInBound ( int column , int columns );
	
	public abstract int[] getDefaultSize();
	
	public BiFunction<Cell[][], Integer, PieceLocation> getInsertFun () {
		return insertFun;
	}

}
