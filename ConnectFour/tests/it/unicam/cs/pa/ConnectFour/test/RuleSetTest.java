/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.factory.AbstractFactory;
import it.unicam.cs.pa.ConnectFour.factory.Factories;
import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
class RuleSetTest {

	RuleSet rs = FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.DEFAULT);

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#actionsNumber()}.
	 */
	@Test
	void testActionsNumber() {
		assertTrue(rs.actionsNumber() == 1);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getAllowedActions()}.
	 */
	@Test
	void testGetAllowedActions() {
		assertTrue(rs.getAllowedActions().getOrDefault(ActionType.INSERT.ordinal(), ActionType.POP) == ActionType.INSERT);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#getPieceLocation(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
	@Test
	void testGetPieceLocation() {
		MatchField f = MatchField.getInstance();
		f.initMatchField("6x7");
		System.out.println(rs.getPieceLocation(0, f).getRow());
		assertTrue(rs.getPieceLocation(0, f).getRow() == 5);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testIsInBoundCellLocation() {
		assertTrue(rs.isInBound(new CellLocation(1,1)));
		assertFalse(rs.isInBound(new CellLocation(-1,1)));
		assertFalse(rs.isInBound(new CellLocation(1,-1)));
		assertFalse(rs.isInBound(new CellLocation(-1,-1)));
		assertFalse(rs.isInBound(new CellLocation(8,1)));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int, int)}.
	 */
	@Test
	void testIsInBoundIntInt() {
		assertTrue(rs.isInBound(1, 6));
		assertFalse(rs.isInBound(-1, 6));
		assertFalse(rs.isInBound(6, 6));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(int)}.
	 */
	@Test
	void testIsInBoundInt() {
		assertTrue(rs.isInBound(1));
		assertFalse(rs.isInBound(-1));
		assertFalse(rs.isInBound(8));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation, int[])}.
	 */
	@Test
	void testIsInBoundCellLocationIntArray() {
		assertTrue(rs.isInBound(new CellLocation(1, 1),new int[] { 6 , 7 }));
		assertFalse(rs.isInBound(new CellLocation(-1, 1),new int[] { 6 , 7 }));
		assertFalse(rs.isInBound(new CellLocation(1, -1),new int[] { 6 , 7 }));
		assertFalse(rs.isInBound(new CellLocation(-1, -1),new int[] { 6 , 7 }));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isValidAction(it.unicam.cs.pa.ConnectFour.core.ActionType)}.
	 */
	@Test
	void testIsValidAction() {
		assertTrue(rs.isValidAction(ActionType.INSERT));
		assertFalse(rs.isValidAction(ActionType.POP));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isValidInsert(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
	@Test
	void testIsValidInsert() {
		MatchField x = MatchField.getInstance();
		x.initMatchField("6x7");
		assertTrue(rs.isValidInsert(0, x));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#pop(java.util.List)}.
	 */
	@Test
	void testPop() {
		MatchField x = MatchField.getInstance();
		x.initMatchField("6x7");
		assertTrue(rs.pop(x.getColumn(0)).equals(x.getColumn(0)));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testNWZeroWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField("6x7");
		AbstractFactory pf = FactoriesProducer.getFactory(Factories.PIECES);
		x.insert(rs.getPieceLocation(0, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(5, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(3, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(3, x), pf.getPiece(CellStatus.P2));
		CellLocation lastloc = rs.getPieceLocation(3, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == CellStatus.P1);		
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testNWWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField("6x7");
		AbstractFactory pf = FactoriesProducer.getFactory(Factories.PIECES);
		x.insert(rs.getPieceLocation(0+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(1+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(1+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(2+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(2+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(3+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(2+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(5+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.getPieceLocation(3+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.getPieceLocation(3+2, x), pf.getPiece(CellStatus.P2));
		CellLocation lastloc = rs.getPieceLocation(3, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == CellStatus.P1);		
	}

}
