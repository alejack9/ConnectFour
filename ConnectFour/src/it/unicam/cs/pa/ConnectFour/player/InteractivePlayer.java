package it.unicam.cs.pa.ConnectFour.player;

import java.io.InputStream;
import java.io.PrintStream;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.exception.InternalException;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author Alessandra Boccuto
 *
 */
public class InteractivePlayer extends Player {

	private boolean printed;
	private ActionType selectedAction = ActionType.INSERT;

	public InteractivePlayer(String name, InputStream in, PrintStream out) {
		super(name, in, out);
		printed = false;
	}

	public InteractivePlayer(String name) {
		this(name, System.in, System.out);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction() throws InternalException {
		Utils.printField(field, super.getReferee());
		printed = true;
		this.print("Available Actions: "); // 0 to insert, 1 to pop etc

		super.getReferee().getAllowedActions().entrySet()
				.forEach(i -> out.println(i.getKey().ordinal() + " - " + i.getKey()));
		int x = Utils.doInput(in, out, parseRequest("Choose the action: "), this::isValidAction, Integer::parseInt);
		this.selectedAction = ActionType.values()[x];
		return this.selectedAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#getColumn()
	 */
	@Override
	public int getColumn() throws InternalException {
		if (!printed)
			Utils.printField(field, super.getReferee());
		int column = Utils.doInput(in, out,
				parseRequest(String.format("Choose a column from 1 to %d", field.getColumns())),
				(x) -> super.getReferee().isInBound(x, field.getColumns()) && super.getReferee().getAllowedActions()
						.get(selectedAction).test(field.getColumn(x), Utils.parsePlayer(getId())),
				(x) -> (Integer.parseInt(x) - 1));
		return column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#init(int,
	 * it.unicam.cs.pa.ConnectFour.core.MatchField,
	 * it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet)
	 */
	@Override
	public void init(int pid, MatchField field, RuleSet referee) throws IllegalIdValue {
		this.setID(pid);
		this.field = field;
		super.setReferee(referee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unicam.cs.pa.ConnectFour.player.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		print("Oh no you have made a mistake ... You have lost! " + e.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#startMatch()
	 */
	@Override
	public void startMatch() {
		this.print("My ID is " + this.ID);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unicam.cs.pa.ConnectFour.player.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("The other player has made an error! You have won!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#youLose()
	 */
	@Override
	public void youLose() {
		this.print("I lose!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#youWin()
	 */
	@Override
	public void youWin() {
		this.print("I win!");
	}

	private boolean isValidAction(int v) {
		try {
			if (super.getReferee().getAllowedActions().get(ActionType.values()[v]) != null) {
				for (int i = 0; i < field.getColumns(); i++) {
					if (super.getReferee().getAllowedActions().get(ActionType.values()[v]).test(field.getColumn(i),
							Utils.parsePlayer(getId())))
						return true;
				}
			}
		} catch (Throwable e) {
		}
		return false;
	}

	/**
	 * @param string What to write
	 */
	private void print(String string) {
		this.out.println(parseRequest(string));
	}

	private String parseRequest(String request) {
		return this.name + "> " + request;
	}

	/**
	 * @param pid
	 */
	private void setID(int pid) throws IllegalIdValue {
		if (pid >= 0) {
			this.ID = pid;
		} else
			throw new IllegalIdValue(pid);
	}
}