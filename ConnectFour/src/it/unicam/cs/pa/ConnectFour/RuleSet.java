/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */
public interface RuleSet {
	
	public static final BiFunction<Cell[][], Integer, PieceLocation> insertFun;
	
	/**
	 * @param column
	 * @param piece
	 * @return
	 */
	public boolean isValidInsert(int column, Piece piece);

	/**
	 * @param field
	 * @param column
	 * @param piece
	 */
	public void insert(Cell[][] field, int column, Piece piece);

}
