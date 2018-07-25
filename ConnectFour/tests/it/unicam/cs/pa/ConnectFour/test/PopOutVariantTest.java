/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.PieceFactory;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author Alessandro Giacche`
 *
 */
class PopOutVariantTest {

	RuleSet r = new PopOutRuleSet();

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#getAllowedActions()}.
	 */
	@Test
	void testGetAllowedActions() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#insertLocation(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
	@Test
	void testGetPieceLocation() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation, it.unicam.cs.pa.ConnectFour.core.Size)}.
	 */
	@Test
	void testIsInBoundCellLocationSize() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#isInBound(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testIsInBoundCellLocation() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#isInBound(int, int)}.
	 */
	@Test
	void testIsInBoundIntInt() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#isInBound(int)}.
	 */
	@Test
	void testIsInBoundInt() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#isValidInsert(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
	@Test
	void testIsValidInsert() {
		assert(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#pop(java.util.List)}.
	 */
	@Test
	void testPop() {
		List<Cell> p = new ArrayList<>();
		p.add(new Cell(0, 0));
		p.add(new Cell(1, 0));
		p.add(new Cell(2, 0));
		p.add(new Cell(3, 0));
		p.add(new Cell(4, 0));
		p.add(new Cell(5, 0));
		p.forEach(x -> x.setPiece(PieceFactory.getIstance().getPiece(CellStatus.P1)));
		p.forEach((x) -> System.out.println(x.getPiece().getId().get()));
		p = r.pop(p);
		p.forEach((x) -> System.out.println(x.getPiece().getId().orElse(-1)));
		
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.ruleSet.PopOutRuleSet#winner(it.unicam.cs.pa.ConnectFour.core.MatchField, it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testWinner() {
		assert(true);
	}

	@Test
	void testIsValidPop() {
		MatchField f = MatchField.getInstance();
		f.initMatchField(new Size(6,7));
		f.insert(new CellLocation(0, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
		f.insert(new CellLocation(1, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
//		f.insert(new CellLocation(2, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
//		f.insert(new CellLocation(3, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
//		f.insert(new CellLocation(4, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
//		f.insert(new CellLocation(5, 0), PieceFactory.getIstance().getPiece(CellStatus.P1));
		System.out.println(f.getColumn(0).size());
		System.out.println(r.isValidPop(0, f));
		System.out.println(f.getColumn(0).size());
	}
}
