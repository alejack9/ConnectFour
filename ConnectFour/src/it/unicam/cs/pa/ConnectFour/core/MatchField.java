package it.unicam.cs.pa.ConnectFour.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;
import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;
import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * Represents a match field
 * 
 * @author giacche`
 *
 */
public final class MatchField {
	// REPORT singleton
	private static final MatchField INSTANCE = new MatchField();
	private boolean initialized;
	private static final UnitializedSingleton e = new UnitializedSingleton("MatchField");
	/**
	 * <b>first List:</b> columns list<br />
	 * <b>second List:</b> rows list
	 */
	private final List<List<Cell>> field;

	private Size size;
	private int pieces;

	private Map<Function<CellLocation, List<Cell>>, Function<CellLocation, Integer>> gettersMap = new HashMap<>();

	private MatchField() {
		this.field = new ArrayList<>();
		this.initialized = false;

		gettersMap.put(c -> this.getRow(c.getRow()), c -> c.getColumn());
		gettersMap.put(c -> this.getColumn(c.getColumn()), c -> c.getRow());
		gettersMap.put(c -> this.getNEDiagonal(c), c -> c.getColumn());
		gettersMap.put(c -> this.getNWDiagonal(c), c -> c.getColumn());
	}

	/**
	 * Initializes MatchField
	 * 
	 * @param size The field size
	 * @return {@code True} if correctly initialized, {@code false} otherwise
	 */
	public boolean initMatchField(Size size) {
		if (!initialized) {
			// REPORT ArrayList has better 'get' and 'set' than LinkedList, worst 'add' but
			// we don't care
			this.size = size;
			this.initialized = true;
			fill();
			return true;
		}
		return false;
	}

	/**
	 * Provides the MatchField instance
	 * 
	 * @return The MatchField instance
	 */
	public static MatchField getInstance() {
		return INSTANCE;
	}

	/**
	 * Removes all the pieces in the field
	 */
	public void clear() {
		for (List<Cell> list : this.field) {
			for (Cell cell : list) {
				cell.pop();
			}
		}
		this.pieces = 0;
	}

	/**
	 * Returns the {@link CellStatus} of the {@link AbstractPiece piece} contained in the selected Cell
	 * 
	 * @param cellLocation The {@link Cell} coordinates
	 * @return The {@link CellStatus} of the {@link AbstractPiece piece} contained
	 *         in the selected Cell
	 */
	public CellStatus getCellStatus(CellLocation cellLocation) {
		return this.field.get(cellLocation.getColumn()).get(cellLocation.getRow()).getStatus();
	}

	/**
	 * Return a Map, see {@code return} for details
	 * 
	 * @return A {@link Map} that contains a {@link Set set of entries} that are a
	 *         couple of {@link Function functions}:
	 *         <ol>
	 *         <li>The first given function of each entry returns a list of
	 *         {@link Cell cells} basing on a passed {@link CellLocation} and an
	 *         arbitrary direction,</li>
	 *         <li>The second one converts a {@link CellLocation} in an
	 *         {@link Integer} returning the index that change in every {@link Cell}
	 *         in the list (e.g. column for the row array, row for the column's one,
	 *         etc...)</li>
	 *         </ol>
	 */
	public Map<Function<CellLocation, List<Cell>>, Function<CellLocation, Integer>> getGettersMap() {
		return gettersMap;
	}

	/**
	 * Returns the number of {@link AbstractPiece pieces}
	 *
	 * @return The number of {@link AbstractPiece pieces}
	 */
	public int getPieces() {
		return this.pieces;
	}

	/**
	 * Replaces a column with another column
	 * 
	 * @param newColumn The new column
	 * @param column The index of the column to modify
	 * @throws UnitializedSingleton if MatchField is not initialized
	 */
	public void setColumn(List<Cell> newColumn, int column) throws UnitializedSingleton {
		checkInit();
		long emptyCells = field.get(column).stream().filter(cell -> cell.isEmpty()).count();
		for (int i = 0; i < newColumn.size(); i++) {
			field.get(column).set(i, newColumn.get(i));
		}
		pieces += emptyCells - field.get(column).stream().filter(cell -> cell.isEmpty()).count();
	}

	/**
	 * Returns a column as a list of cells
	 * 
	 * @param column The index of the column to return
	 * @return The column
	 * @throws UnitializedSingleton if MatchField is not initialized
	 */
	public List<Cell> getColumn(int column) throws UnitializedSingleton {
		checkInit();
		return field.get(column);
	}

	/**
	 * Returns a row as a list of cells
	 * 
	 * @param row The index of the row to return
	 * @return The row
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<Cell> getRow(int row) throws UnitializedSingleton {
		checkInit();
		List<Cell> toReturn = new ArrayList<>();
		for (List<Cell> column : this.field) {
			toReturn.add(column.get(row));
		}
		return toReturn;
	}

	/**
	 * Returns a North-West diagonal as a list of cells
	 * 
	 * @param cellLocation The base {@link CellLocation}-
	 * @return The North-West diagonal passing throw the given {@link CellLocation}
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<Cell> getNWDiagonal(CellLocation cellLocation) throws UnitializedSingleton {
		checkInit();
		return getDiagonal(cellLocation, Direction.NW);
	}

	/**
	 * Returns a North-East diagonal as a list of cells
	 *
	 * @param cellLocation The base {@link CellLocation}-
	 * @return The North-East diagonal passing throw the given {@link CellLocation}
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<Cell> getNEDiagonal(CellLocation cellLocation) throws UnitializedSingleton {
		checkInit();
		return getDiagonal(cellLocation, Direction.NE);
	}

	/**
	 * Returns the number of rows
	 * 
	 * @return The number of rows
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getRows() throws UnitializedSingleton {
		checkInit();
		return this.size.getRows();
	}

	/**
	 * Returns the number of columns
	 * 
	 * @return The number of columns
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getColumns() throws UnitializedSingleton {
		checkInit();
		return this.size.getColumns();
	}

	/**
	 * Returns a function to view the state of a Cell
	 * 
	 * @param referee The reference {@link RuleSet referee}
	 * @return The {@link BiFunction} that given a couple (row,column) returns the
	 *         {@link CellStatus}
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public BiFunction<Integer, Integer, CellStatus> getView(RuleSet referee) throws UnitializedSingleton {
		checkInit();
		return (row, column) -> {
			CellLocation c = new CellLocation(row, column);
			if (!referee.isInBound(c, this.size)) {
				return null;
			}
			return getCellStatus(c);
		};
	}

	/**
	 * Inserts a piece in the field
	 * 
	 * @param location The {@link CellLocation location} where the {@link Piece}
	 *                 should be inserted
	 * @param piece    The {@link Piece}
	 * @return {@code True} if the {@link Piece} has been inserted, {@code false}
	 *         otherwise
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public boolean insert(CellLocation location, Piece piece) throws IllegalPieceLocation, UnitializedSingleton {
		checkInit();
		if (pieces < getColumns() * getRows()) {
			try {
				if (this.field.get(location.getColumn()).get(location.getRow()).setPiece(piece)) {
					pieces++;
					return true;
				}
			} catch (Throwable e) {
				throw new IllegalPieceLocation(location, this);
			}
		}
		return false;
	}

	private void checkInit() throws UnitializedSingleton {
		if (!initialized)
			throw e;
	}

	/**
	 * Fill the field {@link List} with {@code rows*columns} {@link Cell cells}
	 */
	private void fill() {
		for (int i = 0; i < getColumns(); i++) {
			List<Cell> toInsert = new ArrayList<>();
			for (int j = 0; j < getRows(); j++) {
				toInsert.add(new Cell(j, i));
			}
			field.add(toInsert);
		}
	}

	/**
	 * @param row The row
	 * @param col The column
	 * @param dir The {@link Direction}
	 * @return The last MatchField {@link Cell} following the given direction from
	 *         the passed {@code <row,column>} coordinate
	 * @throws UnknownEnumValue if the passed {@link Direction} is not valid
	 */
	private Cell findLastCell(int row, int col, Direction dir) throws UnknownEnumValue {
		while (dir.limit(this).test(row, col)) {
			row = dir.rowStep(row);
			col = dir.colStep(col);
		}
		return this.field.get(col).get(row);
	}

	/**
	 * @param cellLoc The based {@link CellLocation cell coordinates}
	 * @param dir     The {@link Direction}
	 * @return The diagonal that passes throw the given coordinate in the given
	 *         {@link Direction}
	 */
	private List<Cell> getDiagonal(CellLocation cellLoc, Direction dir) {
		Cell firstCell = findLastCell(cellLoc.getRow(), cellLoc.getColumn(), dir);
		Cell lastCell = findLastCell(cellLoc.getRow(), cellLoc.getColumn(), dir.opposite());

		List<Cell> toReturn = Stream
				.iterate(firstCell, (x) -> !x.equals(lastCell),
						(x) -> field.get(dir.opposite().colStep(x.getLocation().getColumn()))
								.get(dir.opposite().rowStep(x.getLocation().getRow())))
				.collect(Collectors.toCollection(ArrayList<Cell>::new));
		toReturn.add(lastCell);

		return toReturn;
	}
}
