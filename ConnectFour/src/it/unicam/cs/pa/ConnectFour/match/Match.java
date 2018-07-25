package it.unicam.cs.pa.ConnectFour.match;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.CellLocation;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.PieceFactory;
import it.unicam.cs.pa.ConnectFour.core.Size;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.exception.UnitializedSingleton;
import it.unicam.cs.pa.ConnectFour.piece.Piece;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.DefaultRuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.Winner;

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
	private int firstPlayer;
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
			Piece piece = piecesFactory.getPiece(Utils.parsePlayer(currentPlayer));
			CellLocation location = referee.insertLocation(column, field);
			return field.insert(location, piece) ? location : null;
		});
		actions.put(ActionType.POP, column -> {
			field.setColumn(referee.popColumn(column, field), column);
			return field.getColumn(column).iterator().next().getLocation();
		});

		this.initialized = false;
	}

	public static Match getInstance() {
		return INSTANCE;
	}

	/**
	 * @param p1 First player
	 * @param p2 Second player
	 * @param prop Properties HashMap: it contains<ul>
	 * <li>size ({@link Size} class)</li>
	 * <li>RuleSet ({@link RuleSet} class)</li>
	 * <li>firstPlayer ({@link Integer} class)</li>
	 * @return true if the initialization have been done, false otherwise
	 * @throws IllegalArgumentException one or more entries in the HashMap are not valid
	 */
	public boolean initMatch(Player p1, Player p2, HashMap<String, Object> prop) throws IllegalArgumentException {
		if (!initialized) {
			this.players = new Player[] { p1, p2 };
			this.field = MatchField.getInstance();
			this.field.initMatchField(getObject(prop.getOrDefault("size", DefaultRuleSet.DEFAULT_SIZE), Size.class));
			
			this.currentPlayer = getObject(prop.getOrDefault("firstPlayer", 0), Integer.class);
			this.firstPlayer = currentPlayer;
			if (currentPlayer < 0 || currentPlayer > 1)
				throw new IllegalArgumentException("firstPlayer must be 0 or 1, '" + currentPlayer + "' is not allowed");
	
			this.referee = getObject(prop.getOrDefault("ruleset", new DefaultRuleSet()), RuleSet.class);
			this.piecesFactory = PieceFactory.getIstance();
			this.initialized = true;
			return true;
		}
		return false;
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
	 * @param player player' id
	 * @return the other player' id
	 */
	public static int otherPlayer(int player) {
		return (player + 1) % 2;
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

	public void restart() {
		this.status = MatchStatus.ARRANGE;
		this.firstPlayer = otherPlayer(firstPlayer);
		this.currentPlayer = this.firstPlayer;
		this.field.clear();
		this.piecesFactory.restart();
		play();
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
				CellLocation loc = actions.get(action).apply(column);
				if (isEnd(loc)) return false;
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
			this.players[player].init(player, field, referee);
			return true;
		} catch (Throwable e) {
			this.winForError(otherPlayer(player), e);
			return false;
		}
	}

	/**
	 * @return true if the game ended, false otherwise
	 */
	private boolean isEnd(CellLocation lastCell) {
		Winner winner = referee.winner(field, lastCell);
		if (field.getPieces() != field.getColumns() * field.getRows() && winner == Winner.NONE)
			return false;

		Utils.printField(field, referee);
		switch(winner) {
			case TIE: tie();break;
			case BOTH: win(currentPlayer);break;
			case NONE: break;
			default: win(winner.ordinal());
		}
		this.status = MatchStatus.END;
		return true;
	}

	private void tie() {
		players[currentPlayer].youLose();
		players[otherPlayer(currentPlayer)].youLose();
	}

	/**
	 * @return the player's choice (if the allowed action are more than one) or the
	 *         only choice
	 */
	private ActionType selectAction() {
		return referee.actionsNumber() > 1 ? players[currentPlayer].chooseAction()
				: referee.getAllowedActions().keySet().iterator().next();
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

	/**
	 * @param toConvert the object to be converted
	 * @param targetClass the target class
	 * @return the object casted
	 * @throws IllegalArgumentException if the passed object is not castable to the target class
	 */
	private <T> T getObject(Object toConvert, Class<? extends T> targetClass) throws IllegalArgumentException {
		if(!targetClass.isAssignableFrom(toConvert.getClass())) throw new IllegalArgumentException("HasMap must contain a " + targetClass.getSimpleName() + " class, not a " + toConvert.getClass().getSimpleName() + " class");
		return targetClass.cast(toConvert);
	}
}
