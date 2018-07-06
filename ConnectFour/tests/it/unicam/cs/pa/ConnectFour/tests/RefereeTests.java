/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.AbstractRuleSet;
import it.unicam.cs.pa.ConnectFour.DefaultRuleSet;

/**
 * @author giacchè
 *
 */
class RefereeTests {
	AbstractRuleSet rs = new DefaultRuleSet();
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.AbstractRuleSet#AbstractRuleSet(java.util.function.BiFunction, int[])}.
	 */
	@Test
	void testAbstractRuleSet() {
		assertNotNull(rs);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isValidInsert(it.unicam.cs.pa.ConnectFour.Cell[][], int)}.
	 */
	@Test
	void testIsValidInsert() {
		assert(rs.isValidInsert(field, column))
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isInBound(int, int)}.
	 */
	@Test
	void testIsInBound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.AbstractRuleSet#getDefaultSize()}.
	 */
	@Test
	void testGetDefaultSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.AbstractRuleSet#getInsertFun()}.
	 */
	@Test
	void testGetInsertFun() {
		fail("Not yet implemented");
	}

}
