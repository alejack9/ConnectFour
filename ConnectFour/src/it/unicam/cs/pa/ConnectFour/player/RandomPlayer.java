/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.core.Utils;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.exception.InternalException;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacche`
 *
 */
public class RandomPlayer extends Player {

	private Random random;
	private boolean echo;

	private boolean printed;

	private ActionType selectedAction = ActionType.INSERT;

	/**
	 * @param name The player's name
	 * @param echo {@code true} if the actions have to be written, {@code false}
	 *             otherwise
	 * @param in   The input {@link InputStream}
	 * @param out  The output {@link PrintStream}
	 */
	public RandomPlayer(String name, boolean echo, InputStream in, PrintStream out) {
		super(name, in, out);
		this.random = new Random();
		this.echo = echo;
	}

	/**
	 * @param name The player's name
	 * @param echo {@code true} if the actions have to be written, {@code false}
	 *             otherwise
	 */
	public RandomPlayer(String name, boolean echo) {
		this(name, echo, System.in, System.out);
	}

	/**
	 * @param name The player's name
	 */
	public RandomPlayer(String name) {
		this(name, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction() throws InternalException {
		if (echo) {
			Utils.printField(field, super.getReferee());
			printed = true;
		}
		do {
			this.selectedAction = new ArrayList<ActionType>(super.getReferee().getAllowedActions().keySet())
					.get(random.nextInt(super.getReferee().getAllowedActions().size()));
		} while (!isValid(this.selectedAction));

		return this.selectedAction;
	}

	/**
	 * @param action The {@link ActionType}
	 * @return {@code true} if it is valid, {@code false} otherwise
	 */
	private boolean isValid(ActionType action) {
		try {
			try {
				if (super.getReferee().getAllowedActions().get(action) != null) {
					for (int i = 0; i < field.getColumns(); i++) {
						if (super.getReferee().getAllowedActions().get(action).test(field.getColumn(i),
								Utils.parsePlayer(getId())))
							return true;
					}
				}
			} finally {
			}
		} finally {
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#getColumn()
	 */
	@Override
	public int getColumn() throws InternalException {
		if (echo && !printed) {
			Utils.printField(field, super.getReferee());
		}
		int selcol;
		while (!super.getReferee().getAllowedActions().get(selectedAction)
				.test(field.getColumn(selcol = random.nextInt(field.getColumns())), Utils.parsePlayer(getId())))
			;
		if (echo)
			print("I choose " + selectedAction.toString() + " in the column " + (selcol + 1));
		return selcol;
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
		this.ID = pid;
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
		print("I've lost because of '" + e.getMessage() + "'");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#startMatch()
	 */
	@Override
	public void startMatch() {
		print("My ID is " + ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.unicam.cs.pa.ConnectFour.player.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("I've won because of '" + e.getMessage() + "'");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#youLose()
	 */
	@Override
	public void youLose() {
		out.println(this.name + "> " + "I have lost!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.player.Player#youWin()
	 */
	@Override
	public void youWin() {
		out.println(this.name + "> " + "I have win!");
	}

	/**
	 * @param string The {@link String} to be printed
	 */
	private void print(String string) {
		if (echo) {
			out.println(this.name + "> " + string);
		}
	}

}
