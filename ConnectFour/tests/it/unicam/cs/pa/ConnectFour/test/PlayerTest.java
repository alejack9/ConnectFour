/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.player.InteractivePlayer;
import it.unicam.cs.pa.ConnectFour.player.Player;

/**
 * @author giacch�
 *
 */
class PlayerTest {

	Player p = new InteractivePlayer("Buru");
	
	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#Player(java.lang.String, it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet, java.io.InputStream, java.io.PrintStream)}.
	 */
	@Test
	void testPlayer() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#chooseAction()}.
	 */
	@Test
	void testChooseAction() {
		testInit();
		assertTrue(p.chooseAction() == ActionType.INSERT);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#getColumn()}.
	 */
	@Test
	void testGetColumn() {
		testInit();
		assertTrue(p.getColumn() == 0);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#getReferee()}.
	 */
	@Test
	void testGetReferee() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#init(int, it.unicam.cs.pa.ConnectFour.core.MatchField)}.
	 */
	@Test
	void testInit() {
		MatchField mf = MatchField.getInstance();
		mf.initMatchField(new Size(6,7));
		p.init(0, mf);
		assertTrue(p.getId() == 0);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#loseForError(java.lang.Throwable)}.
	 */
	@Test
	void testLoseForError() {
		p.loseForError(new Throwable("sfigato."));
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#startMatch()}.
	 */
	@Test
	void testStartMatch() {
		p.startMatch();
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#winForError(java.lang.Throwable)}.
	 */
	@Test
	void testWinForError() {
		p.winForError(new Throwable("che culo che c'hai"));
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#youLose()}.
	 */
	@Test
	void testYouLose() {
		p.youLose();
		assertTrue(true);
	}

	/**
	 * Test method for {@link it.unicam.cs.pa.ConnectFour.player.Player#youWin()}.
	 */
	@Test
	void testYouWin() {
		p.youWin();
		assertTrue(true);
	}

}
