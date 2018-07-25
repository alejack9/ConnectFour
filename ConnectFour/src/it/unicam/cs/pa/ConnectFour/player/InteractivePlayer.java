package it.unicam.cs.pa.ConnectFour.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Predicate;

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
	private ActionType selectedAction;

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
	 * @see it.unicam.cs.pa.ConnectFour.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction() throws InternalException {
		Utils.printField(field, super.getReferee());
		printed = true;
		this.print("Available Actions: "); // 0 to insert, 1 to pop etc

		super.getReferee().getAllowedActions().entrySet()
				.forEach(i -> System.out.println(i.getKey() + " - " + i.getValue()));

		int x = doInput("Choose the action: ", this::isValidAction, Integer::parseUnsignedInt);
		this.selectedAction = ActionType.values()[x]; 
		return this.selectedAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#getColumn()
	 */
	@Override
	public int getColumn() throws InternalException {
		if (!printed)
			Utils.printField(field, super.getReferee());
		int column = doInput(String.format("Choose a column from 1 to %d", field.getColumns()),
				(x) -> super.getReferee().getAllowedActions().get(selectedAction.ordinal()).test(field.getColumn(x), Utils.parsePlayer(getId())),
				(x) -> (Integer.parseUnsignedInt(x) - 1));
		return column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#init(int)
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
	 * @see it.unicam.cs.pa.ConnectFour.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		print("Oh no you have made a mistake ... You have lost! " + e.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#startMatch()
	 */
	@Override
	public void startMatch() {
		this.print("My ID is " + this.ID);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("The other player has made an error! You have won!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#youLose()
	 */
	@Override
	public void youLose() {
		this.print("You have lost!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.unicam.cs.pa.ConnectFour.Player#youWin()
	 */
	@Override
	public void youWin() {
		this.print("You win!");
	}

	/**
	 * @param string    What to ask
	 * @param condition Input condition/s
	 * @param readFun   Parser from String to required type
	 * @return the inserted value
	 * @throws IOException
	 */
	private <T> T doInput(String request, Predicate<T> condition, Function<String, T> readFun)
			throws InternalException {
		while (true) {
			this.print(request);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new InternalException(e);
			}
			T x = readFun.apply(line);
			if (!condition.test(x)) {
				System.out.println("Input Error!");
			} else {
				return x;
			}
		}
	}

	private boolean isValidAction(int v) {
		try {
			try {
				if(super.getReferee().getAllowedActions().get(v) != null) {
					for(int i = 0; i < field.getColumns(); i++) {
						if(super.getReferee().getAllowedActions().get(v)
								.test(field.getColumn(i), Utils.parsePlayer(getId())))
							return true;
					}
				}
			} finally { }
		} finally { }
		return false;
	}

//	private boolean isValidInsert(String txt) {
//		try {
//			int v = Integer.parseUnsignedInt(txt) - 1;
//			return (super.getReferee().isInBound(v, field.getColumns())
//					&& super.getReferee().isValidInsert(v, field, Utils.parsePlayer(getId())));
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}

	/**
	 * @param string What to write
	 */
	private void print(String string) {
		this.out.println(this.name + "> " + string);
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