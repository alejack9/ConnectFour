/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.CellStatus;
import it.unicam.cs.pa.ConnectFour.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.MatchField;
import it.unicam.cs.pa.ConnectFour.Piece;
import it.unicam.cs.pa.ConnectFour.Utils;

/**
 * @author giacchè
 *
 */
class MatchFieldtTest {

	MatchField mf;
	int[] size = { 8 , 8 };
	
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#MatchField(int[], it.unicam.cs.pa.ConnectFour.AbstractRuleSet)}.
	 */
	@Test
	void testMatchFieldIntArrayAbstractRuleSet() {
		mf = new MatchField( new DefaultRuleSet(size) );
		Utils.printField(mf);
		assertNotNull(mf);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#MatchField(int[])}.
	 */
	@Test
	void testMatchFieldIntArray() {
		mf = new MatchField( size );
		Utils.printField(mf);
		assertNotNull(mf);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#MatchField(it.unicam.cs.pa.ConnectFour.AbstractRuleSet)}.
	 */
	@Test
	void testMatchFieldAbstractRuleSet() {
		mf = new MatchField( new DefaultRuleSet() );
		Utils.printField(mf);
		assertNotNull(mf);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#MatchField()}.
	 */
	@Test
	void testMatchField() {
		mf = new MatchField( );
		Utils.printField(mf);
		assertNotNull(mf);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#getStatus(int, int)}.
	 */
	@Test
	void testGetStatus() {
		testMatchFieldIntArray();
		for( int i = 0; i < size[0]; i++)
			for (int j = 0; j < size[1] ; j++)
				assertTrue(mf.getStatus(i, j) == CellStatus.EMPTY);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#insert(int, it.unicam.cs.pa.ConnectFour.Piece)}.
	 */
	@Test
	void testInsert() {
		testMatchFieldIntArray();
		assertTrue(mf.insert(2, new Piece(0, CellStatus.P1)));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#getView()}.
	 */
	@Test
	void testGetView() {
		testMatchFieldIntArray();
		assertNotNull(mf.getView());
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#isValidAt(int)}.
	 */
	@Test
	void testIsValidAt() {
		testMatchFieldIntArray();
		assertTrue(mf.isValidAt(1));
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#getRows()}.
	 */
	@Test
	void testGetRows() {
		testMatchFieldIntArray();
		assertTrue(mf.getRows() == 8);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.MatchField#getColums()}.
	 */
	@Test
	void testGetColums() {
		testMatchFieldIntArray();
		assertTrue(mf.getColums() == 8);
	}

}
