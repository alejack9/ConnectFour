package it.unicam.cs.pa.ConnectFour.core;

import java.util.Properties;

import it.unicam.cs.pa.ConnectFour.factory.AbstractFactory;
import it.unicam.cs.pa.ConnectFour.factory.Factories;
import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
// REPORT si sarebbe potuto mettere match observable e player observer per caricare init del player al cambiamento di stato di match. Tuttavia e` svantaggioso perche` dovrei passare a player il proprio id e il referee e dovrei creare un nuovo oggetto per inserire questi due parametri, in piu` id e` diverso per uno e per l'altro mentre la notifica viene inviata ad entrambi
public class Match {

	private static final Match INSTANCE = new Match();
	
	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;

	private Player[] players;
	private MatchField field;
	private MatchStatus status = MatchStatus.INIT;
	private int currentPlayer;
	private AbstractFactory piecesFactory;
	private RuleSet referee;
	private boolean initialized;
	
	private Match () {
		this.initialized = false;
	}
	
	public static Match getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param prop Properties field: must contains<ul><li>'size' (the match field size)</li><li>'firstPlayer' (the player who starts the match)</li><li>'ruleset' (the referee)</li>
	 * @throws NumberFormatException Unable to covert 'size' or 'firstPlayer' into Integer
	 * @throws IllegalArgumentException Some 'prop' value/s has/have not allowed values
	 */
	public boolean initMatch(Player p1 , Player p2 , Properties prop) {
		if(!initialized) {
			this.players = new Player[] { p1, p2 };
			this.field = new MatchField(prop.getProperty("size",RuleSetType.DEFAULT.defaultSize()) , prop.getProperty("ruleset","default"));
			this.currentPlayer = Integer.parseInt(prop.getProperty("firstPlayer","0"));
			if(currentPlayer < 0 || currentPlayer > 1) throw new IllegalArgumentException("firstPlayer must be 0 or 1, '" + currentPlayer + "' is not allowed");
			this.piecesFactory = FactoriesProducer.getFactory(Factories.PIECES);
			this.referee = this.field.getReferee();
		}
		return false;
	}
	
	public MatchStatus getStatus() {
		if(!initialized) return null;
		return this.status;
	}

	/**
	 * Initializes the players and starts the game
	 */
	public void play() throws IllegalStateException {
		if(!initialized) throw new IllegalStateException("Match must be initialized");
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
	 * @return false if the game ended
	 */
	private boolean doAction(ActionType action) {
		try {
			if (this.referee.isValidAction(action)) {
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
	 * @param player Player' id
	 * @return true if all's OK, false otherwise
	 */
	private boolean init(int player) {
		try {
			this.players[player].init(player, field );
			return true;
		} catch (Throwable e) {
			this.winForError(otherPlayer(player), e);
			return false;
		}
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
	 * @param player player' id
	 * @return the other player' id
	 */
	private int otherPlayer(int player) {
		return (player + 1) % 2;
	}

	/**
	 * Gets the column from field, pops the column according to referee's rules and sets the returned column in the field
	 * @param column The gotten column
	 */
	private void popAction( int column ) {
		field.setColumn(referee.pop(field.getColumn(column)),column);
	}

	/**
	 * @return the player's choice (if the allowed action are more than one) or the only choice
	 */
	private ActionType selectAction() {
		return referee.actionsNumber() > 1 ? players[currentPlayer].chooseAction() : referee.getAllowedActions()[0];
	}

	private void setStatus(MatchStatus status) {
		this.status = status;
	}

	/**
	 * @param winner Winner player' id
	 */
	private void win(int winner) {
		players[winner].youWin();
		players[otherPlayer(winner)].youLose();
	}

	/**
	 * @param player Winner player' id
	 * @param e The error
	 */
	private void winForError(int player, Throwable e) {
		this.players[player].winForError(e);
		this.players[otherPlayer(player)].loseForError(e);
	}
}
