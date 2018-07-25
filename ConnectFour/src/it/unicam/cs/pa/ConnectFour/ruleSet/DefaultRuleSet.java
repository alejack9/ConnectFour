package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.exception.IllegalColumnException;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;

/**
 * @author giacche`
 *
 */
public class DefaultRuleSet implements RuleSet {

	private static final HashMap<ActionType, BiPredicate<List<Cell>, CellStatus>> allowedActions = new HashMap<>();

	public static final Size DEFAULT_SIZE = new Size(6, 7);

	public static final String NAME = "DefaultRuleSet";

	private final Function<List<Cell>, Optional<Cell>> destinationCell = (column) -> column.stream()
			.filter(Cell::isEmpty).reduce((prev, last) -> last);

	private final BiPredicate<List<Cell>, CellStatus> checkIns = (column, cell) -> destinationCell.apply(column)
			.isPresent();

	public DefaultRuleSet() {
		allowedActions.put(ActionType.INSERT, checkIns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getAllowedActions()
	 */
	@Override
	public HashMap<ActionType, BiPredicate<List<Cell>, CellStatus>> getAllowedActions() {
		return allowedActions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#insertLocation(int,
	 * it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public CellLocation insertLocation(int column, MatchField field)
			throws IllegalColumnException, IllegalPieceLocation {
		if (!isInBound(column, field.getColumns()))
			throw new IllegalColumnException(column, field);

		return destinationCell.apply(field.getColumn(column)).orElseThrow(() -> new IllegalPieceLocation(column, field))
				.getLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.
	 * ConnectFour.core.CellLocation, it.unicam.cs.pa.ConnectFour.core.Size)
	 */
	@Override
	public boolean isInBound(CellLocation loc, Size customSize) {
		return loc.getRow() >= 0 && loc.getRow() < customSize.getRows() && loc.getColumn() >= 0
				&& loc.getColumn() < customSize.getColumns();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int, int)
	 */
	@Override
	public boolean isInBound(int column, int customColumnSize) {
		return isInBound(new CellLocation(0, column), new Size(1, customColumnSize));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#popColumn(int,
	 * it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public List<Cell> popColumn(int column, MatchField field) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.
	 * ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)
	 */
	@Override
	public Winner winner(MatchField field, CellLocation cellLocation) {
		if (field.getPieces() == field.getColumns() * field.getRows())
			return Winner.TIE;

		for (Cell cell : field.getColumn(cellLocation)) {
			if (cell.isEmpty())
				continue;
			for (Entry<Function<CellLocation, List<Cell>>, Function<Cell, Integer>> functions : field.getGettersMap()
					.entrySet()) {
				boolean win = collapseIndexes(
						functions.getKey().apply(cell.getLocation()).stream().filter((c) -> !c.isEmpty())
								.filter((c) -> c.getStatus() == field.getCellStatus(cell.getLocation()))
								.map(c -> functions.getValue().apply(c))
								.collect(Collectors.toCollection(ArrayList<Integer>::new))).stream().map(l -> l.size())
										.filter(l -> l >= 4).count() > 0;

				if (win)
					return Winner.convert(field.getCellStatus(cell.getLocation()));
			}
		}
		return Winner.NONE;
	}

	/**
	 * @param indexes - The indexes list
	 * @return the list of consecutive indexes sequences
	 */
	private List<List<Integer>> collapseIndexes(List<Integer> indexes) {
		List<Integer> candidate = new ArrayList<>();
		List<List<Integer>> toReturn = new ArrayList<>();

		candidate.add(indexes.get(0));
		for (int i = 1; i < indexes.size(); i++) {
			if (indexes.get(i) == candidate.get(candidate.size() - 1) + 1)
				candidate.add(indexes.get(i));
			else {
				toReturn.add(candidate);
				candidate = new ArrayList<>();
				candidate.add(i);
			}
		}
		if (!toReturn.contains(candidate))
			toReturn.add(candidate);
		return toReturn;
	}
}
