/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;

/**
 * @author giacchè
 *
 */
class CellTest {
	Cell c = new Cell();
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Cell#Cell()}.
	 */
	@Test
	void testCell() {
		Cell c = new Cell();
		assertNotNull(c);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Cell#setPiece(it.unicam.cs.pa.ConnectFour.Piece)}.
	 */
	@Test
	void testSetPiece() {
		testCell();
		assertTrue(c.setPiece(new Piece(0,CellStatus.P1)));
		assertTrue(c.getStatus() == CellStatus.P1);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Cell#getStatus()}.
	 */
	@Test
	void testGetStatus() {
		testCell();
		assertTrue(c.getStatus() == CellStatus.EMPTY);
		assertTrue(c.setPiece(new Piece(0,CellStatus.P1)));
		assertTrue(c.getStatus() == CellStatus.P1);
		assertFalse(c.setPiece(new Piece(1,CellStatus.P2)));
		assertTrue(c.getStatus() == CellStatus.P1);
		
		Piece p = c.pop();
		assertNotNull(p);
		assertTrue(p.getId() == 0);
		assertTrue(p.getColor() == CellStatus.P1);
		assertTrue(c.setPiece(new Piece(1,CellStatus.P2)));
		assertTrue(c.getStatus() == CellStatus.P2);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Cell#getPiece()}.
	 */
	@Test
	void testGetPiece() {
		testCell();
		assertNull(c.getPiece());
		c.setPiece(new Piece(0,CellStatus.P1));
		assertNotNull(c.getPiece());
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Cell#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		testCell();
		assertTrue(c.isEmpty());
	}

}
