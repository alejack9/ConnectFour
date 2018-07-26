/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.exception.IllegalColumnException;
import it.unicam.cs.pa.ConnectFour.exception.IllegalPieceLocation;
import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;

/**
 * it uses {@link DefaultRuleSet} to avoid the copy of methods
 * 
 * @author Alessandro Giacche`
 *
 */
public class PopOutRuleSet extends DefaultRuleSet {

	private final HashMap<ActionType, BiPredicate<List<Cell>, CellStatus>> allowedActions;

	private final BiPredicate<List<Cell>, CellStatus> checkPop = (column, cell) -> {
		if (cell != column.get(column.size() - 1).getStatus())
			return false;
		return column.parallelStream().filter(x -> !x.isEmpty()).count() > 1;
	};

	public static final String NAME = "PopOutRuleSet";

	public PopOutRuleSet() {
		super();
		allowedActions = super.getAllowedActions();
		allowedActions.put(ActionType.POP, checkPop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet#getAllowedActions()
	 */
	@Override
	public HashMap<ActionType, BiPredicate<List<Cell>, CellStatus>> getAllowedActions() {
		return allowedActions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet#insertLocation(int,
	 * it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public CellLocation insertLocation(int column, MatchField field)
			throws IllegalColumnException, IllegalPieceLocation {
		return super.insertLocation(column, field);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet#popColumn(int,
	 * it.unicam.cs.pa.ConnectFour.core.MatchField)
	 */
	@Override
	public List<Cell> popColumn(int column, MatchField field) throws IllegalColumnException {
		if (!isInBound(column, field.getColumns()))
			throw new IllegalColumnException(column, field);
		List<Cell> toReturn = field.getColumn(column);
		AbstractPiece succ = toReturn.get(0).pop();
		for (int i = 1; i < toReturn.size(); i++) {
			AbstractPiece tmp = succ;
			succ = toReturn.get(i).pop();
			toReturn.get(i).setPiece(tmp);
		}
		// REPORT USED TO USE ROTATE BUT IT ROTATES ELEMENTS AND WE HAVE TO ROTATE
		// PIECES, NOT CELLS.
//		Collections.rotate(toReturn , 1);
//		toReturn.get(0).pop();
		return toReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet#winner(it.unicam.cs.pa.
	 * ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)
	 */
	@Override
	public Winner winner(MatchField field, CellLocation cellLocation) {
		if (field.getPieces() == field.getColumns() * field.getRows())
			return Winner.TIE;

		Map<CellStatus, List<Integer>> winnersSequences = new HashMap<CellStatus, List<Integer>>();

		for (Cell cell : field.getColumn(cellLocation.getColumn())) {
			if (!cell.isEmpty() && !winnersSequences.containsKey(cell.getStatus())) {
				List<List<Integer>> toInsert = getWinSeq(field, cell.getLocation());
				// REPORT we could use {@code putIfAbsent} but we had more useless loops
				if (!toInsert.isEmpty())
					winnersSequences.put(cell.getStatus(), toInsert.iterator().next());
			}
		}
		if (winnersSequences.size() == 0)
			return Winner.NONE;
		if (winnersSequences.size() == 1)
			return Winner.convert(winnersSequences.entrySet().iterator().next().getKey());
		return Winner.BOTH;
	}

	/**
	 * @param field        - The {@link MatchField}
	 * @param cellLocation - The {@link CellLocation}
	 * @return A list of sequences where there are more than 4 pieces with the same
	 *         status which is the {@code cellLocation}'s one
	 */
	private List<List<Integer>> getWinSeq(MatchField field, CellLocation cellLocation) {
		List<List<Integer>> toReturn = new ArrayList<>();

		for (Entry<Function<CellLocation, List<Cell>>, Function<CellLocation, Integer>> functions : field.getGettersMap()
				.entrySet()) {
			List<List<Integer>> winnersIndexes = collapseIndexes(functions.getKey().apply(cellLocation).stream()
					.filter((c) -> !c.isEmpty()).filter((c) -> c.getStatus() == field.getCellStatus(cellLocation))
					.map(c -> functions.getValue().apply(c.getLocation())).collect(Collectors.toCollection(ArrayList<Integer>::new)))
							.stream().filter(l -> l.size() >= 4).collect(Collectors.toList());
			toReturn.addAll(winnersIndexes);
		}
		return toReturn;
	}

	/**
	 * @param indexes - The indexes list
	 * @return The list of consecutive indexes sequences
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
