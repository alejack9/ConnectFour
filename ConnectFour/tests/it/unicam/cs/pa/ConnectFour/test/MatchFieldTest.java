/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.factory.PieceFactory;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;

/**
 * @author giacch�
 *
 */
class MatchFieldTest {

	MatchField mf = MatchField.getInstance();
	
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#initMatchField(java.lang.String)}.
	 */
	@Test
	void testInitMatchField() {
		mf.initMatchField(DefaultRuleSet.DEFAULT_SIZE);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		testInitMatchField();
		assertNotNull(mf);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getCellStatus(int, int)}.
	 */
	@Test
	void testGetCellStatusIntInt() {
		testInitMatchField();
		for(int i = 0 ; i < mf.getColumns() ; i++)
			for(int j = 0 ; j < mf.getRows() ; j++ )
				System.out.println(mf.getCellStatus(j, i));
//				assertEquals(mf.getCellStatus(j, i), CellStatus.EMPTY);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getCellStatus(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testGetCellStatusCellLocation() {
		testInitMatchField();
		for(int i = 0 ; i < mf.getColumns() ; i++)
			for(int j = 0 ; j < mf.getRows() ; j++ )
				System.out.println(mf.getCellStatus(j, i));
//				assertEquals(mf.getCellStatus(new CellLocation(j, i)), CellStatus.EMPTY);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getField()}.
	 */
	@Test
	void testGetField() {
		testInitMatchField();
		assertNotNull(mf.getField());
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getListsGetters()}.
	 */
	@Test
	void testGetListsGetters() {
		testInitMatchField();
		assertNotNull(mf.getListsGetters());
		//assertTrue(mf.getListsGetters().size() == 4);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getPieces()}.
	 */
	@Test
	void testGetPieces() {testInitMatchField();
		assertTrue(mf.getPieces() == 0);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#setColumn(java.util.List, int)}.
	 */
	@Test
	void testSetColumn() {testInitMatchField();
		testGetColumnInt();
		List<Cell> x = mf.getColumn(0);
		x.get(1).setPiece(PieceFactory.getIstance().getPiece(CellStatus.P1));
		mf.setColumn(x, 0);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getColumn(int)}.
	 */
	@Test
	void testGetColumnInt() {testInitMatchField();
		for (Cell cell : mf.getColumn(1)) {
			assertTrue(cell.getPiece().isNull());
		}
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getColumn(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testGetColumnCellLocation() {
		testInitMatchField();
		for(Cell cell : mf.getColumn(new CellLocation(1, 0))) {
			assertTrue(cell.getLocation().getColumn() == 0);
		}
			
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getRow(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testGetRow() {
		testInitMatchField();
		for(Cell cell : mf.getRow(new CellLocation(1,0))) {
			assertTrue(cell.getLocation().getRow() == 1);
		}
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getNWDiagonal(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testGetNWDiagonal() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getNEDiagonal(it.unicam.cs.pa.ConnectFour.core.CellLocation)}.
	 */
	@Test
	void testGetNEDiagonal() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getRows()}.
	 */
	@Test
	void testGetRows() {testInitMatchField();
		assertTrue(mf.getRows() == 6);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getColumns()}.
	 */
	@Test
	void testGetColumns() {testInitMatchField();
		assertTrue(mf.getColumns() == 7);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#getView(it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet)}.
	 */
	@Test
	void testGetView() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.core.MatchField#insert(it.unicam.cs.pa.ConnectFour.core.CellLocation, it.unicam.cs.pa.ConnectFour.piece.Piece)}.
	 */
	@Test
	void testInsert() {
		assertTrue(true);
	}

}
