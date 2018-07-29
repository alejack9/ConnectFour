/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.PieceFactory;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.Winner;

/**
 * @author giacch�
 *
 */
class RuleSetTest {

	RuleSet rs = new DefaultRuleSet();

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
		assertTrue(rs.getAllowedActions().containsKey(ActionType.INSERT));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#insertLocation(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
//	@Test
	void testGetPieceLocation() {
		MatchField f = MatchField.getInstance();
		f.initMatchField(new Size(6,7));
		System.out.println(rs.insertLocation(0, f).getRow());
		assertTrue(rs.insertLocation(0, f).getRow() == 5);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testIsInBoundCellLocation() {
		assertTrue(rs.isInBound(new CellLocation(1,1),DefaultRuleSet.DEFAULT_SIZE));
		assertFalse(rs.isInBound(new CellLocation(-1,1),DefaultRuleSet.DEFAULT_SIZE));
		assertFalse(rs.isInBound(new CellLocation(1,-1),DefaultRuleSet.DEFAULT_SIZE));
		assertFalse(rs.isInBound(new CellLocation(-1,-1),DefaultRuleSet.DEFAULT_SIZE));
		assertFalse(rs.isInBound(new CellLocation(8,1),DefaultRuleSet.DEFAULT_SIZE));
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
		assertTrue(rs.isInBound(1,6));
		assertFalse(rs.isInBound(-1,6));
		assertFalse(rs.isInBound(8,6));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation, int[])}.
	 */
	@Test
	void testIsInBoundCellLocationIntArray() {
		assertTrue(rs.isInBound(new CellLocation(1, 1),new Size ( 6 , 7 )));
		assertFalse(rs.isInBound(new CellLocation(-1, 1),new Size ( 6 , 7 )));
		assertFalse(rs.isInBound(new CellLocation(1, -1),new Size ( 6 , 7 )));
		assertFalse(rs.isInBound(new CellLocation(-1, -1),new Size ( 6 , 7 )));
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
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#pop(java.util.List)}.
	 */
	@Test
	void testPop() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		assertTrue(rs.popColumn(0,x).equals(x.getColumn(0)));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testZeroNWWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(5, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		CellLocation lastloc = rs.insertLocation(3, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);		
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testTraslatedNWWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(0+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(1+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(2+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(3+2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3+2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(3+2, x), pf.getPiece(CellStatus.P2));
		CellLocation lastloc = rs.insertLocation(3+2, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testMiddleNWWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P1));
		
		
		CellLocation lastloc = rs.insertLocation(4, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testFinishNWWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(5, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(5, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(6, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(6, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(6, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(5, x), pf.getPiece(CellStatus.P1));
		
		CellLocation lastloc = rs.insertLocation(6, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testZeroNEWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(2, x), pf.getPiece(CellStatus.P1));
		
		CellLocation lastloc = rs.insertLocation(3, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
//	@Test
	void testTraslatedNEWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(0+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(1+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(2+1, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(0+1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(1+1, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(2+1, x), pf.getPiece(CellStatus.P1));
		
		CellLocation lastloc = rs.insertLocation(3+1, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testFinishNEWinner() {
		MatchField x = MatchField.getInstance();
		x.initMatchField(new Size(6,7));
		PieceFactory pf = PieceFactory.getIstance();
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(5, x), pf.getPiece(CellStatus.P2));
		x.insert(rs.insertLocation(3, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(4, x), pf.getPiece(CellStatus.P1));
		x.insert(rs.insertLocation(6, x), pf.getPiece(CellStatus.P1));
		
		CellLocation lastloc = rs.insertLocation(5, x);
		x.insert(lastloc, pf.getPiece(CellStatus.P1));
		Utils.printField(x, rs);
		
		System.out.println(rs.winner(x, lastloc).name().toString());
		assertTrue(rs.winner(x, lastloc) == Winner.P1);
	}	

}
