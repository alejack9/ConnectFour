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
	private final int[] SIZE;
	private final BiFunction<Cell[][], Integer, PieceLocation> insertFun;

	public AbstractRuleSet ( BiFunction<Cell[][], Integer, PieceLocation> insertFun , int[] SIZE ) {
		this.SIZE = SIZE;
		this.insertFun = insertFun;
	}
	
	/**
	 * @param column
	 * @return
	 */
	public abstract boolean isValidInsert ( Cell[][] field, int column ) throws IndexOutOfBoundsException;

	public abstract boolean isInBound ( int column );
		
	public int[] getSize() {
		return SIZE;
	}

	public abstract int[] getDefaultSize();
	
	public BiFunction<Cell[][], Integer, PieceLocation> getInsertFun () {
		return insertFun;
	}

	/**
	 * @return
	 */
	public int getRows() {
		return SIZE[0];
	}

	/**
	 * @return
	 */
	public int getColumns() {
		return SIZE[1];
	}

}
