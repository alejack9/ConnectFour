/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.CellStatus;
import it.unicam.cs.pa.ConnectFour.factory.AbstractFactory;
import it.unicam.cs.pa.ConnectFour.factory.Factories;
import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
class AbstractFactoryTest {

	AbstractFactory pieceFactory = FactoriesProducer.getFactory(Factories.PIECES);
	AbstractFactory refereeFactory  = FactoriesProducer.getFactory(Factories.REFEREE);
	
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.factory.AbstractFactory#getPiece(it.unicam.cs.pa.ConnectFour.core.CellStatus)}.
	 */
	@Test
	void testGetPiece() {
		assertNull(refereeFactory.getPiece(CellStatus.P1));
		assertNull(refereeFactory.getPiece(CellStatus.P2));
		assertNull(refereeFactory.getPiece(CellStatus.EMPTY));

		Piece p1 = pieceFactory.getPiece(CellStatus.P1);
		assertTrue(p1.getColor() == CellStatus.P1 && p1.getId().get() == 0);
		
		Piece p2 = pieceFactory.getPiece(CellStatus.P2);
		assertTrue(p2.getColor() == CellStatus.P2 && p2.getId().get() == 1);
		
		assertThrows(IllegalArgumentException.class, () -> pieceFactory.getPiece(CellStatus.EMPTY));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.factory.AbstractFactory#getReferee(it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType)}.
	 */
	@Test
	void testGetReferee() {
		assertNotNull(refereeFactory.getReferee(RuleSetType.DEFAULT));
		assertNull(refereeFactory.getReferee(RuleSetType.FIVEINROW));
		assertNull(refereeFactory.getReferee(RuleSetType.POP));

		assertNull(pieceFactory.getReferee(RuleSetType.DEFAULT));
		assertNull(pieceFactory.getReferee(RuleSetType.FIVEINROW));
		assertNull(pieceFactory.getReferee(RuleSetType.POP));
	}

}
