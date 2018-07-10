/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * @author giacchè
 *
 */

public class MatchField {

	private final List<List<Cell>> field;
	private int[] size;
	private int pieces;

	public MatchField (String size) {
		this.size = Utils.sizeParse(size);
		//field = new Cell[intSize[0]][intSize[1]];
	}

	/*public MatchField ( AbstractRuleSet ruleSet ) {
		this.ruleSet = ruleSet;
		this.field = new Cell[getRows()][getColums()];
		fill();
	}
	public MatchField(int[] size) {
		this( new DefaultRuleSet( size ) );
	}
	public MatchField () {
		this( new DefaultRuleSet() );
	}*/
	
	public CellStatus getStatus ( int row , int column ) {
		return this.field.get(column).get(row).getStatus();
	}
	
	/**
	 * @param column
	 * @return
	 */
	/*public boolean insert ( int column , Piece piece ) {
		if(!this.ruleSet.isInBound( column )) return false;
		if(this.ruleSet.isValidInsert( this.field, column ) && this.insert( ruleSet.getInsertFun().apply( field , column ) , piece )) {
			this.pieces++;
			return true;
		}
		return false;
	}*/
	
	public boolean insert ( PieceLocation location , Piece piece ) {
		return this.field.get(location.getColumn()).get(location.getRow()).setPiece(piece);
	}
	
/*	private void fill() {
		for( int i=0 ; i < this.getRows() ; i++ )
			for( int j=0 ; j < this.getColums() ; j++ )
				this.field[i][j] = new Cell();
	}*/
	/**
	 * @return
	 */
	public BiFunction<Integer, Integer, CellStatus> getView() {
		return ( row , column ) -> {
			if ( !this.ruleSet.isInBound( column ) ) {
				return null;
			}
			return getStatus( row , column );
		};
	}

/*	public boolean isValidAt ( int column ) {
		if(this.ruleSet.isInBound( column ))
			return this.ruleSet.isValidInsert( field , column );
		return false;
	}*/
	
	/**
	 * @return
	 */
	public int getRows() {
		return this.ruleSet.getRows();
	}
	/**
	 * @return
	 */
	public int getColumns() {
		return this.ruleSet.getColumns();
	}
	/**
	 * @return
	 */
/*	public AbstractRuleSet getRuleSet() {
		return this.ruleSet;
	}*/

	/**
	 * @return
	 */
	public Cell[][] getCells() {
		Cell[][] toReturn = new Cell[getRows()][getColumns()];
		for(int j = 0 ; j < getColumns() ; j++) {
			for(int i = 0 ; i < getRows() ; i++) {
				toReturn[i][j] = field.get(j).get(i);
			}
		}
		return toReturn;
	}

	/**
	 * @param column
	 * @return
	 */
	public List<Cell> getColumn(int column) {
		return field.get(column);
	}

	/**
	 * @param pop
	 */
	public void setColumn(List<Cell> newColumn, int column) {
		for(int i = 0 ; i < newColumn.size() ; i++) {
			field.get(column).set(i, newColumn.get(i));
		}
	}
	
}
