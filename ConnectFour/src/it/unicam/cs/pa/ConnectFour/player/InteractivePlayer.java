package it.unicam.cs.pa.ConnectFour.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author Alessandra Boccuto
 *
 */
public class InteractivePlayer implements Player {
	
	private String name;
	private int ID;
	/**
	 * must be the same for both players
	 */
	private List<List<Cell>> field;
	private BufferedReader in;
	private PrintStream out;
	private MatchField field1;
	private RuleSet referee;
	
	public InteractivePlayer( String name , InputStream in , PrintStream out ) {
		this.name = name;
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#init(int)
	 */
	@Override
	public void init(int pid , MatchField field ) throws IllegalIdValue {
		this.setID(pid);
		this.field1 = field;
		this.referee = field.getReferee();
	}

	/**
	 * @param pid
	 */
	private void setID(int pid) throws IllegalIdValue {
		if(pid >= 0) {
			this.ID = pid;
		}
		else throw new IllegalIdValue(pid);
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#startMatch()
	 */
	@Override
	public void startMatch() {
		this.print(this.name + "has ID " + this.ID);
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction() {
		// TODO use i.orinal() instead of index
		while(true) {
			Integer index = 0;
			this.print("Actions avaible: " ); //0 to insert, 1 to pop etc
			for ( ActionType i: field1.getReferee().getAllowedActions()) {
				this.print(index.toString()+ "to " + referee.getAllowedActions().toString()+ "\n");
				index++;
			}
			try {
				int x = doInput(("Choose the action: "), this::isExistingAction, Integer::parseUnsignedInt);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return referee.getAllowedActions()[index];
		}
	}

	private boolean isExistingAction(String txt) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return(v < referee.getAllowedActions().length);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isInBound(String txt) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return(referee.isInBound(v));
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#getColumn()
	 */
	@Override
	public int getColumn() {
		//FIXME getColumns()
		int column = doInput(String.format("Choose a column from 0 to %d", field1.getColumns()), this::isInBound , Integer::parseUnsignedInt);
		return column;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#youWin()
	 */
	@Override
	public void youWin() {
		this.print("You win!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		print("The other player has made an error! You have won!");
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#youLose()
	 */
	@Override
	public void youLose() {
		this.print("You have lost!");		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		print("Oh no you have made a mistake ... You have lost! "+e.getMessage());		
	}

	/**
	 * @param string What to ask
	 * @param condition Input condition/s
	 * @param readFun Parser from String to required type
	 * @return the inserted value
	 * @throws IOException
	 */
	private <T> T doInput(String string, Predicate<String> condition, Function<String, T> readFun) throws IOException {
		// TODO to finish
		while (true) {
			this.out.println(string);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new IOException(e);
			}
			if (!condition.test(line)) {
				System.out.println("Input Error!");
			} else {
				return readFun.apply(line);
			}
		}
	}

	/**
	 * @param string What to write
	 */
	private void print(String string) {
		this.out.println(this.name+ "> " + string);
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
}
