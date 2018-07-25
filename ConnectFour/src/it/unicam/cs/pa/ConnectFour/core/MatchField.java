package it.unicam.cs.pa.ConnectFour.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacche`
 *
 */

public final class MatchField {
	// REPORT singleton
	private static final MatchField INSTANCE = new MatchField();
	private boolean initialized;
	private static final UnitializedSingleton e = new UnitializedSingleton("MatchField");
	/**
	 * first List: columns list<br />
	 * second List: rows list
	 */
	private final List<List<Cell>> field;
	/**
	 * [0] = rows<br />
	 * [1] = columns
	 */
	private Size size;
	private int pieces;

	private Map<Function<CellLocation, List<Cell>>,Function<Cell,Integer>> gettersMap = new HashMap<>();
//	private List<Function<CellLocation, List<Cell>>> listsGetters = new ArrayList<>();

	/**
	 * 
	 */
	private MatchField() {
		this.field = new ArrayList<>();
		this.initialized = false;

		gettersMap.put(c -> this.getRow(c), c -> c.getLocation().getColumn());
		gettersMap.put(c -> this.getColumn(c), c -> c.getLocation().getRow());
		gettersMap.put(c -> this.getNEDiagonal(c), c -> c.getLocation().getColumn());
		gettersMap.put(c -> this.getNWDiagonal(c), c -> c.getLocation().getColumn());
//		listsGetters.add((c) -> this.getRow(c));
//		listsGetters.add((c) -> this.getColumn(c));
//		listsGetters.add((c) -> this.getNEDiagonal(c));
//		listsGetters.add((c) -> this.getNWDiagonal(c));
	}

	/**
	 * @param size the field size
	 * @throws IllegalArgumentException 'size' is not valid
	 */
	public boolean initMatchField(Size size) throws IllegalArgumentException {
		if (!initialized) {
			/**
			 * ArrayList has better 'get' and 'set' than LinkedList, worst 'add' but we don't care
			 */
			this.size = size;
			this.initialized = true;
			fill();
			return true;
		}
		return false;
	}

	public static MatchField getInstance() {
		return INSTANCE;
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public CellStatus getCellStatus(int row, int column) throws UnitializedSingleton {
		checkInit();
		return this.field.get(column).get(row).getStatus();
	}

	/**
	 * @param cell
	 * @return
	 */
	public CellStatus getCellStatus(CellLocation cell) {
		return getCellStatus(cell.getRow(), cell.getColumn());
	}

//	/**
//	 * @return the listsGetters
//	 */
//	public List<Function<CellLocation, List<Cell>>> getListsGetters() {
//		return Collections.unmodifiableList(listsGetters);
//	}

	/**
	 * @return the gettersMap
	 */
	public Map<Function<CellLocation, List<Cell>>, Function<Cell, Integer>> getGettersMap() {
		return gettersMap;
	}

	public int getPieces() {
		return this.pieces;
	}

	/**
	 * Replace a column with another column
	 * 
	 * @throws UnitializedSingleton Match is not initialized
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
	 * @param column
	 * @return
	 */
	public List<Cell> getColumn(int column) throws UnitializedSingleton {
		checkInit();
		return field.get(column);
	}

	/**
	 * @return The column as Cell list
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public List<Cell> getColumn(CellLocation cell) throws UnitializedSingleton {
		return getColumn(cell.getColumn());
	}

	public List<Cell> getRow(CellLocation cell) throws UnitializedSingleton {
		checkInit();
		List<Cell> toReturn = new ArrayList<>();
		for (List<Cell> column : this.field) {
			toReturn.add(column.get(cell.getRow()));
		}
		return toReturn;
	}

	public List<Cell> getNWDiagonal(CellLocation cell) {
		return getDiagonal(cell.getRow(), cell.getColumn(), Direction.NW);
	}

	public List<Cell> getNEDiagonal(CellLocation cell) {
		return getDiagonal(cell.getRow(), cell.getColumn(), Direction.NE);
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getRows() throws UnitializedSingleton {
		checkInit();
		return this.size.getRows();
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public int getColumns() throws UnitializedSingleton {
		checkInit();
		return this.size.getColumns();
	}

	/**
	 * @return The viewer BiFunction
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public BiFunction<Integer, Integer, CellStatus> getView(RuleSet referee) throws UnitializedSingleton {
		checkInit();
		return (row, column) -> {
			if (!referee.isInBound(new CellLocation(row,column), this.size)) {
				return null;
			}
			return getCellStatus(row, column);
		};
	}

	/**
	 * Inserts a piece in the field
	 * 
	 * @return true if all's OK, false otherwise
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public boolean insert(CellLocation location, Piece piece) throws UnitializedSingleton {
		checkInit();
		if (pieces < getColumns() * getRows()) {
			if (this.field.get(location.getColumn()).get(location.getRow()).setPiece(piece)) {
				pieces++;
				return true;
			}
		}
		return false;
	}

	private void checkInit() throws UnitializedSingleton {
		if (!initialized)
			throw e;
	}

	/**
	 * Makes rows * columns Cells in the field
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

	private Cell findLastCell(int row, int col, Direction dir) throws UnknownEnumValue {
		while (dir.limit(this).test(row, col)) {
			row = dir.rowStep(row);
			col = dir.colStep(col);
		}
		return this.field.get(col).get(row);
	}

	private List<Cell> getDiagonal(int row, int col, Direction dir) {

		Cell firstCell = findLastCell(row, col, dir);
		Cell lastCell = findLastCell(row, col, dir.opposite());

		// , (x) -> field.get(x.getColumn() + 1).get(x.getRow() + 1))
		
		List<Cell> toReturn = Stream
				.iterate(firstCell
				, (x) -> !x.equals(lastCell)
				, (x) -> field.get(dir.opposite().colStep(x.getLocation().getColumn())).get(dir.opposite().rowStep(x.getLocation().getRow())))
				.collect(Collectors.toCollection(ArrayList<Cell>::new));
		toReturn.add(lastCell);

		return toReturn;
	}

	/**
	 * 
	 */
	public void clear() {
		for (List<Cell> list : this.field) {
			for (Cell cell : list) {
				cell.pop();
			}
		}
		this.pieces = 0;
	}
}
