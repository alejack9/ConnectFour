package it.unicam.cs.pa.ConnectFour;

import java.util.Properties;
import java.util.Random;

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

	/**
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param prop Properties field: contains 'size' (the match field size), 'firstPlayer' (the player who starts the match) and 'ruleset' (the referee)
	 * @throws NumberFormatException Unable to covert 'size' or 'firstPlayer' into Integer
	 * @throws IllegalArgumentException Some 'prop' value/s has/have not allowed values
	 */
	public Match(Player p1, Player p2, Properties prop) throws NumberFormatException , IllegalArgumentException {
		this.players = new Player[] { p1, p2 };
		this.field = new MatchField(prop.getProperty("size"));
		this.currentPlayer = Integer.parseInt(prop.getProperty("firstPlayer"));
		if(currentPlayer < 0 || currentPlayer > 1) throw new IllegalArgumentException("firstPlayer must be 0 or 1, '" + currentPlayer + "' is not allowed");
		this.piecesFactory = FactoriesProducer.getFactory(Factories.PIECES);
		this.referee = FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.parse(prop.getProperty("ruleset")));
	}
	
	public MatchStatus getStatus() {
		return this.status;
	}

	/**
	 * Initializes the players and starts the game
	 */
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

	private void setStatus(MatchStatus status) {
		this.status = status;
	}

	/**
	 * @return the player's choice (if the allowed action are more than one) or the only choice
	 */
	private ActionType selectAction() {
		return referee.actionsNumber() > 1 ? players[currentPlayer].chooseAction() : referee.getAllowedActions()[0];
	}

	/**
	 * @return false if the game ended
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
	 * @return true if the game ended, false otherwise
	 */
	private boolean isEnd() {
		CellStatus winner = referee.winner(field.getCells()); 
		if(winner != CellStatus.EMPTY) {
			win(winner.ordinal());
			return true;
		}
		return false;
	}

	/**
	 * @param winner Winner player' id
	 */
	private void win(int winner) {
		players[winner].youWin();
		players[otherPlayer(winner)].youLose();
	}

	/**
	 * Makes a piece, gets the PieceLocation from referee and requires to field to insert the piece
	 * @param column The gotten column
	 */
	private void insertAction( int column ) {
		Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
		PieceLocation loc = referee.insert(column, field.getCells());
		field.insert(loc, piece);
	}
	/**
	 * Gets the column from filed, pops the column according to referee's rules and sets the returned column in the field
	 * @param column The gotten column
	 */
	private void popAction( int column ) {
		field.setColumn(referee.pop(field.getColumn(column)),column);
	}

	/**
	 * @param player Player' id
	 * @return true if all's OK, false otherwise
	 */
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
	 * @param player Winner player' id
	 * @param e The error
	 */
	private void winForError(int player, Throwable e) {
		this.players[player].winForError(e);
		this.players[otherPlayer(player)].loseForError(e);
	}

	/**
	 * @param player player' id
	 * @return the other player' id
	 */
	private int otherPlayer(int player) {
		return (player + 1) % 2;
	}
}
