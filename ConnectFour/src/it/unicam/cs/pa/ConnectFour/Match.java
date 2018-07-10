/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.util.Properties;
import java.util.Random;

import it.unicam.cs.pa.ConnectFour.factories.AbstractFactory;

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
	private RuleSet referee;
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
		this.piecesFactory = FactoriesProducer.getFactory(Factories.PIECES);
		this.referee = FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.parse(prop.getProperty("ruleset")));
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
		while (doAction(selectAction()));
	}

	/**
	 * @return
	 */
	private ActionType selectAction() {
		return referee.actionsNumber() > 1 ? players[currentPlayer].chooseAction() : referee.getAllowedActions()[0];
	}

	/**
	 * @return
	 */
	private boolean doAction(ActionType action) {
		try {
			if (referee.isValidAction(action)) {
				int column = players[this.currentPlayer].getColumn();
				if (action == ActionType.INSERT) insertAction(column);
				if (action == ActionType.POP) popAction(column);
				
				if(isEnd()) return false;
			}
			else return true;
		} catch (Throwable e) {
			winForError(otherPlayer(this.currentPlayer), e);
			return false;
		}
		this.currentPlayer = otherPlayer(this.currentPlayer);
		return true;
	}

	/**
	 * @return
	 */
	private boolean isEnd() {
		CellStatus winner = referee.winner(field.getCells()); 
		if(winner != null) {
			win(winner.ordinal());
			return true;
		}
		return false;
	}

	/**
	 * @param winner
	 */
	private void win(int winner) {
		players[winner].youWin();
		players[otherPlayer(winner)].youLose();
	}

	/**
	 * 
	 */
	private void insertAction( int column ) {
		Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
		PieceLocation loc = referee.insert(column, field.getCells());
		field.insert(loc, piece);
	}
	private void popAction( int column ) {
		field.setColumn(referee.pop(field.getColumn(column)),column);
	}

	private boolean init(int player) {
		try {
			this.players[player].init(player, referee);
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
