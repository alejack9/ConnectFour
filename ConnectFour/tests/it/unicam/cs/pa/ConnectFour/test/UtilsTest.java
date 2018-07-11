/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.piece.AbstractPiece;
import it.unicam.cs.pa.ConnectFour.core.Utils;

/**
 * @author giacchè
 *
 */
class UtilsTest {
	MatchField mf = new MatchField();

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.Utils#printField(it.unicam.cs.pa.ConnectFour.MatchField)}.
	 */
	@Test
	void testPrintFieldMatchField() {
		Utils.printField(mf);
		mf.insert(1, new Piece(0,CellStatus.P1));
		mf.insert(1, new Piece(1,CellStatus.P2));
		mf.insert(1, new Piece(2,CellStatus.P2));
		mf.insert(1, new Piece(3,CellStatus.P2));
		mf.insert(1, new Piece(4,CellStatus.P2));
		assertTrue(mf.isValidAt(1));
		mf.insert(1, new Piece(5,CellStatus.P2));
		assertFalse(mf.isValidAt(1));
//		mf.insert(1, new Piece(6,CellStatus.P2));
//		mf.insert(1, new Piece(7,CellStatus.P2));
		Utils.clearScreen();
		Utils.printField(mf);
	}
}
