/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.Properties;
import java.util.Random;

import it.unicam.cs.pa.ConnectFour.factories.AbstractFactory;
import it.unicam.cs.pa.ConnectFour.factories.Factories;

/**
 * @author giacchè
 *
 */
public class Match {

	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;

	private final Player[] players;
	private final MatchField field;
	private MatchStatus status = MatchStatus.INIT;
	private RuleSetType ruleSet;
	private int currentPlayer;
	private AbstractFactory piecesFactory;

	/*
	 * public Match ( Player p1 , Player p2 , int firstPlayer , AbstractRuleSet
	 * ruleSet ) { if ( firstPlayer < 0 || firstPlayer > 1 ) throw new
	 * IllegalArgumentException(String.
	 * format("firstPlayer must be 0 or 1, \"%d\" is not allowed.", firstPlayer));
	 * this.players = new Player[] { p1 , p2 }; this.field = new MatchField( ruleSet
	 * ); this.currentPlayer = firstPlayer; }
	 */

	public Match(Player p1, Player p2, Properties prop) {
		this.players = new Player[] { p1, p2 };
		this.field = new MatchField(prop.getProperty("size"));
		this.currentPlayer = Integer.parseInt(prop.getProperty("firstPlayer"));
		this.ruleSet = RuleSetType.parse(prop.getProperty("ruleset"));
		this.piecesFactory = FactoriesProducer.getFactory(Factories.PIECES);
	}

	/*
	 * public Match ( Player p1 , Player p2 , int firstPlayer ) { this ( p1 , p2 ,
	 * firstPlayer , new DefaultRuleSet() ); } public Match ( Player p1 , Player p2
	 * ) { this ( p1 , p2 , new DefaultRuleSet() ); }
	 */

	public MatchStatus getStatus() {
		return this.status;
	}

	private void setStatus(MatchStatus status) {
		this.status = status;
	}

	public void play() {
		if (!init(PLAYER1)) {
			return;
		}
		if (!init(PLAYER2)) {
			return;
		}
		this.setStatus(MatchStatus.ARRANGE);
		_play();
	}

	private void _play() {
		this.players[PLAYER1].startMatch();
		this.players[PLAYER2].startMatch();
		while (doAction());
	}

	/**
	 * @return
	 */
	private boolean doAction() {
		try {
			Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
			PieceLocation loc = players[currentPlayer].insert(piece);
			this.field.insert(loc, piece);

			if (this.field.winner() != null) {
				win(this.currentPlayer);
				return false;
			}
		} catch (Throwable e) {
			winForError(otherPlayer(this.currentPlayer), e);
			return false;
		}
		this.currentPlayer = otherPlayer(this.currentPlayer);
		return true;
	}

	private boolean init(int player) {
		try {
			this.players[player].init(player, field.getRuleSet());
			return true;
		} catch (Throwable e) {
			this.winForError(otherPlayer(player), e);
			return false;
		}
	}

	/**
	 * @param otherPlayer
	 * @param e
	 */
	private void winForError(int player, Throwable e) {
		this.players[player].winForError(e);
		this.players[otherPlayer(player)].loseForError(e);

	}

	/**
	 * @param player
	 * @return
	 */
	private int otherPlayer(int player) {
		return (player + 1) % 2;
	}
}
