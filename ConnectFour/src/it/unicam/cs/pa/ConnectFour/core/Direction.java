/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;

/**
 * Represents the Directions in the MatchField
 * 
 * @author giacche`
 *
 */
public enum Direction {
	NORTH, EAST, NW, NE, SW, SE, WEST, SOUTH;

	/**
	 * Returns a {@code BiPredicate} that will {@code tests} the bounds of passed
	 * row and column (ROW , COLUMN)
	 * 
	 * @param mf The {@link MatchField}
	 * @return A {@code BiPredicate} that will {@code tests} the bounds of passed
	 *         row and column (ROW , COLUMN)
	 * @throws UnknownEnumValue if the initialized enum value is unknown
	 */
	public BiPredicate<Integer, Integer> limit(MatchField mf) throws UnknownEnumValue {
		switch (this) {
		case NE:
		case NORTH:
			return (x, y) -> NORTH.getPredicate(mf).test(x) && EAST.getPredicate(mf).test(y);
		case SE:
		case EAST:
		case SOUTH:
			return (x, y) -> SOUTH.getPredicate(mf).test(x) && EAST.getPredicate(mf).test(y);
		case SW:
		case WEST:
			return (x, y) -> SOUTH.getPredicate(mf).test(x) && WEST.getPredicate(mf).test(y);

		case NW:
			return (x, y) -> NORTH.getPredicate(mf).test(x) && WEST.getPredicate(mf).test(y);
		default:
			throw new UnknownEnumValue(this);
		}
	}

	/**
	 * Returns the opposite Direction based on the initialized one
	 * 
	 * @return The opposite Direction based on the initialized one (e.g. NORTH -&gt;
	 *         SOUTH, etc...)
	 */
	public Direction opposite() {
		return Direction.values()[Direction.values().length - 1 - this.ordinal()];

		// switch(this) {
		// case NORTH: return SOUTH;
		// case SOUTH: return NORTH;
		// case NE: return SW;
		// case SW: return NE;
		// case EAST: return WEST;
		// case WEST: return EAST;
		// case NW: return SE;
		// case SE: return NW;
		// default: return null;
		// }
	}

	/**
	 * Returns the new column index gotten by making a step in the initialized
	 * direction
	 * 
	 * @param col The column
	 * @return The new column index gotten by making a step in the initialized
	 *         direction
	 */
	public int colStep(int col) {
		return col + getStep()[1];
	}

	/**
	 * Returns the new row index gotten by making a step in the initialized
	 * direction
	 * 
	 * @param row The column
	 * @return The new row index gotten by making a step in the initialized
	 *         direction
	 */
	public int rowStep(int row) {
		return row + getStep()[0];
	}

	/**
	 * Returns a {@code Predicate} that will {@code tests} the bounds of passed row
	 * or column
	 * 
	 * @param mf The {@link MatchField}
	 * @return A {@code Predicate} that will {@code tests} the bounds of passed row
	 *         or column
	 */
	private Predicate<Integer> getPredicate(MatchField mf) {
		switch (this) {
		case NORTH:
		case WEST:
			return (x) -> x > 0;
		case SOUTH:
			return (x) -> x < mf.getRows() - 1;
		case EAST:
			return (x) -> x < mf.getColumns() - 1;
		default:
			return null;
		}
	}

	/**
	 * Returns the value to sum to make a step in the initialized direction
	 * 
	 * @return The value to sum to make a step in the initialized direction
	 */
	private int[] getStep() {
		switch (this) {
		case NORTH:
			return new int[] { -1, 0 };
		case NE:
			return new int[] { -1, +1 };
		case EAST:
			return new int[] { 0, +1 };
		case SE:
			return new int[] { +1, +1 };
		case SOUTH:
			return new int[] { +1, 0 };
		case SW:
			return new int[] { +1, -1 };
		case WEST:
			return new int[] { 0, -1 };
		case NW:
			return new int[] { -1, -1 };
		default:
			return new int[] { 0, 0 };
		}
	}
}
