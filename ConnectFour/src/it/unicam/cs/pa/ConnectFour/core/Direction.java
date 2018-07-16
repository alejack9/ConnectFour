/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;

/**
 * @author giacchè
 *
 */
public enum Direction {
	NORTH, EAST, NW, NE, SW, SE, WEST, SOUTH;
	
	/**
	 * @return [ ROW , COLUMN ]
	 */
	private int[] getStep() {
		switch(this) {
			case NORTH:	return new int[] {	-1	,	0	};
			case NE:	return new int[] {	-1	,	+1	};
			case EAST:	return new int[] {	0	,	+1	};
			case SE:	return new int[] {	+1	,	+1	};
			case SOUTH:	return new int[] {	+1	,	0	};
			case SW:	return new int[] {	+1	,	-1	};
			case WEST:	return new int[] {	0	,	-1	};
			case NW:	return new int[] {	-1	,	-1	};
			default:	return new int[] {	0	,	0	};
		}
	}

	/**
	 * @return
	 */
	public Direction opposite() {
		// TODO TO TEST
		return Direction.values()[Direction.values().length - 1 - this.ordinal()];
		
//		switch(this) {
//			case NORTH:	return SOUTH;
//			case SOUTH:	return NORTH;
//			case NE:	return SW;
//			case SW:	return NE;
//			case EAST:	return WEST;
//			case WEST:	return EAST;
//			case NW:	return SE;
//			case SE:	return NW;
//			default:	return null;
//		}
	}

	/**
	 * @param col
	 * @return
	 */
	public int colStep(int col) {
		return col + getStep()[1];
	}

	/**
	 * @param row
	 * @return
	 */
	public int rowStep(int row) {
		return row + getStep()[0];
	}

	/**
	 * @param matchField
	 * @return [ ROW , COLUMN ] BiPredicate: row,column -> is in limit
	 */
	public BiPredicate<Integer,Integer> limit(MatchField mf) throws UnknownEnumValue {
		switch(this) {
			case NE:
				case NORTH:		return (x,y) -> NORTH.getPredicate(mf).test(x) && EAST.getPredicate(mf).test(y);
			case SE:
				case EAST:
					case SOUTH:	return (x,y) -> SOUTH.getPredicate(mf).test(x) && EAST.getPredicate(mf).test(x);
			case SW:
				case WEST:		return (x,y) -> SOUTH.getPredicate(mf).test(x) && WEST.getPredicate(mf).test(x);
				
			case NW:			return (x,y) -> NORTH.getPredicate(mf).test(x) && WEST.getPredicate(mf).test(x);
			default:	throw new UnknownEnumValue(this);
		}
	}
	
	private Predicate<Integer> getPredicate(MatchField mf) {
		switch(this) {
			case NORTH: case WEST: return (x) -> x > 0;
			case SOUTH: return (x) -> x < mf.getRows();
			case EAST:	return (x) -> x < mf.getColumns();
			default: 	return null;
		}
	}
}
