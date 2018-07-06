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
	
	/**
	 * rows,columns
	 */
	private final int[] DEFAULT_SIZE;
	private final BiFunction<Cell[][], Integer, PieceLocation> insertFun;

	public AbstractRuleSet ( BiFunction<Cell[][], Integer, PieceLocation> insertFun , int[] DEFAULT_SIZE ) {
		this.DEFAULT_SIZE = DEFAULT_SIZE;
		this.insertFun = insertFun;
	}
	
	/**
	 * @param column
	 * @return
	 */
	public abstract boolean isValidInsert ( Cell[][] field, int column ) throws IndexOutOfBoundsException;

	public abstract boolean isInBound ( int column , int columns );
		
	public int[] getDefaultSize() {
		return DEFAULT_SIZE;
	}
	
	public BiFunction<Cell[][], Integer, PieceLocation> getInsertFun () {
		return insertFun;
	}

}
