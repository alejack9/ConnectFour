package it.unicam.cs.pa.ConnectFour.ruleSet;

import java.util.List;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.PieceLocation;
/**
 * @author giacchè
 *
 */
public class DefaultRuleSet implements RuleSet {

	private static final ActionType[] allowedActions = { ActionType.INSERT };

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#actionsNumber()
	 */
	@Override
	public int actionsNumber() {
		return allowedActions.length;
	}

	public ActionType[] getAllowedActions() {
		return allowedActions;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#insert(int, it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public PieceLocation insert(int column, Cell[][] field) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#isValidInsert(int)
	 */
	@Override
	public boolean isValidInsert(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#pop(java.util.List)
	 */
	@Override
	public List<Cell> pop(List<Cell> column) {
		return column;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.RuleSet#winner(it.unicam.cs.pa.ConnectFour.Cell[][])
	 */
	@Override
	public CellStatus winner(Cell[][] field) {
		// TODO Auto-generated method stub
		return null;
	}

}
