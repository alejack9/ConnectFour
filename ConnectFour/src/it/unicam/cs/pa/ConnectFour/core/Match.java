package it.unicam.cs.pa.ConnectFour.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
//import it.unicam.cs.pa.ConnectFour.factory.AbstractFactory;
//import it.unicam.cs.pa.ConnectFour.factory.Factories;
//import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.factory.PieceFactory;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacche`
 *
 */
// REPORT si sarebbe potuto mettere match observable e player observer per caricare init del player al cambiamento di stato di match. Tuttavia e` svantaggioso perche` dovrei passare a player il proprio id e il referee e dovrei creare un nuovo oggetto per inserire questi due parametri, in piu` id e` diverso per uno e per l'altro mentre la notifica viene inviata ad entrambi
public final class Match {

	// REPORT is singleton
	private static final Match INSTANCE = new Match();
	private boolean initialized;

	private static final int PLAYER1 = 0;
	private static final int PLAYER2 = 1;
	private int currentPlayer;

	private Player[] players;
	private MatchField field;
	private RuleSet referee;

	private MatchStatus status = MatchStatus.INIT;

	private PieceFactory piecesFactory;

	private static Map<ActionType, Function<Integer, CellLocation>> actions;

	private Match() {
		actions = new HashMap<>();
		actions.put(ActionType.INSERT, column -> {
			Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
			CellLocation location = referee.getPieceLocation(column, field);
			return field.insert(location, piece) ? location : null;
		});
		actions.put(ActionType.POP, column -> {
			field.setColumn(referee.pop(field.getColumn(column)), column);
			return field.getColumn(column).iterator().next().getLocation();
		});

		this.initialized = false;
	}

	/**
	 * @param p1   player 1
	 * @param p2   player 2
	 * @param prop Properties field: must contains
	 *             <ul>
	 *             <li>'size' (the match field size, use {@link it.unicam.cs.pa.ConnectFour.core.Utils#sizeToString(int[]) Utils.sizeToString(int[])} to convert the size in string)</li>
	 *             <li>'firstPlayer' (the player who starts the match)</li>
	 *             <li>'ruleset' (the referee)</li>
	 * @throws NumberFormatException    Unable to covert 'size' or 'firstPlayer'
	 *                                  into Integer
	 * @throws IllegalArgumentException Some 'prop' value/s has/have not allowed
	 *                                  values
	 */
	public boolean initMatch(Player p1, Player p2, Properties prop)
			throws NumberFormatException, IllegalArgumentException {
		if (!initialized) {
			this.players = new Player[] { p1, p2 };
			this.field = MatchField.getInstance();
			this.field.initMatchField(prop.getProperty("size", DefaultRuleSet.DEFAULT_SIZE.toString()));
			this.currentPlayer = Integer.parseInt(prop.getProperty("firstPlayer", "0"));
			if (currentPlayer < 0 || currentPlayer > 1)
				throw new IllegalArgumentException(
						"firstPlayer must be 0 or 1, '" + currentPlayer + "' is not allowed");
			this.piecesFactory = PieceFactory.getIstance();
			this.referee = Utils.getReferee(prop.getProperty("ruleset", DefaultRuleSet.NAME));
			this.referee = new DefaultRuleSet();
//			this.referee = FactoriesProducer.getFactory(Factories.REFEREE)
//					.getReferee(RuleSetType.parse(prop.getProperty("ruleset", RuleSetType.DEFAULT.name())));
			this.initialized = true;
			return true;
		}
		return false;
	}

	public static Match getInstance() {
		return INSTANCE;
	}

	/**
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public MatchStatus getStatus() {
		if (!initialized)
			throw new UnitializedSingleton("Match");
		return this.status;
	}

	/**
	 * Initializes the players and starts the game
	 * 
	 * @throws UnitializedSingleton Match is not initialized
	 */
	public void play() throws IllegalStateException {
		if (!initialized)
			throw new UnitializedSingleton("Match");
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
		while (doAction(selectAction()))
			;
	}

	/**
	 * @return false if the game ended
	 */
	private boolean doAction(ActionType action) {
		try {
			if (this.referee.isValidAction(action)) {
				int column = players[this.currentPlayer].getColumn();
				CellLocation loc = actions.get(action).apply(column);
				// REPORT removed if (action == ActionType.INSERT) insertAction(column);
				// REPORT removed if (action == ActionType.POP) popAction(column);
				if (isEnd(loc))
					return false;
			} else
				return true;
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
			this.players[player].init(player, field);
			return true;
		} catch (Throwable e) {
			this.winForError(otherPlayer(player), e);
			return false;
		}
	}

	// /**
	// * Makes a piece, gets the PieceLocation from referee and requires to field to
	// insert the piece
	// * @param column The gotten column
	// */
	// private void insertAction( int column ) throws IllegalPieceLocation{
	// Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
	// PieceLocation location = referee.insert(column, field);
	// field.insert(location, piece);
	// }

	/**
	 * @return true if the game ended, false otherwise
	 */
	private boolean isEnd(CellLocation lastCell) {
		CellStatus winner = referee.winner(field, lastCell);
		if(field.getPieces() != field.getColumns()*field.getRows() && winner == CellStatus.EMPTY) return false;

		Utils.printField(field, referee);
		if(field.getPieces() == field.getColumns() * field.getRows()) {
			tie();
		}
		else if(winner != CellStatus.EMPTY) {
			win(winner.ordinal());
		}
		return true;
	}

	/**
		 * 
		 */
	private void tie() {
		players[currentPlayer].youLose();
		players[otherPlayer(currentPlayer)].youLose();
	}

//	/**
//	 * Makes a piece, gets the PieceLocation from referee and requires to field to insert the piece
//	 * @param column The gotten column
//	 */
//	private void insertAction( int column ) throws IllegalPieceLocation{
//		Piece piece = piecesFactory.getPiece(CellStatus.parse(currentPlayer));
//		PieceLocation location = referee.insert(column, field);
//		field.insert(location, piece);
//	}

	/**
	 * @param player player' id
	 * @return the other player' id
	 */
	private int otherPlayer(int player) {
		return (player + 1) % 2;
	}

//	/**
//	 * Gets the column from field, pops the column according to referee's rules and sets the returned column in the field
//	 * @param column The gotten column
//	 */
//	private void popAction( int column ) {
//		field.setColumn(referee.pop(field.getColumn(column)),column);
//	}

	/**
	 * @return the player's choice (if the allowed action are more than one) or the
	 *         only choice
	 */
	private ActionType selectAction() {
		return referee.actionsNumber() > 1 ? players[currentPlayer].chooseAction()
				: referee.getAllowedActions().values().iterator().next();
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
	 * @param e      The error
	 */
	private void winForError(int player, Throwable e) {
		this.players[player].winForError(e);
		this.players[otherPlayer(player)].loseForError(e);
	}
}
