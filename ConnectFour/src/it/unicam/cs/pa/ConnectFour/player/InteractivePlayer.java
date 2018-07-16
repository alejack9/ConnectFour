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
import it.unicam.cs.pa.ConnectFour.factory.Factories;
import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author Alessandra Boccuto
 *
 */
public class InteractivePlayer extends Player {

	private boolean printed;
	
	public InteractivePlayer( String name , RuleSet referee , InputStream in , PrintStream out ) {
		super( name , referee , in , out );
		printed = false;
	}
	public InteractivePlayer( String name , RuleSet referee ) {
		this( name , referee , System.in , System.out );
	}
	public InteractivePlayer( String name ) {
		this( name , FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.DEFAULT) );
	}
	
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#init(int)
	 */
	@Override
	public void init(int pid , MatchField field ) throws IllegalIdValue {
		this.setID(pid);
		this.field = field;
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
		this.print("My ID is " + this.ID);
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction( ) throws InternalException {
		Utils.printField(field, super.getReferee());
		printed = true;
		this.print("Available Actions: "); //0 to insert, 1 to pop etc
		
		super.getReferee().getAllowedActions().entrySet().forEach(i -> System.out.println(i.getKey() + " - " + i.getValue()));
		
		int x = doInput("Choose the action: ", this::isExistingAction, Integer::parseUnsignedInt);
		return super.getReferee().getAllowedActions().get(x);
	}

	private boolean isExistingAction(String txt) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return(super.getReferee().getAllowedActions().get(v) == null ? false : true);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isValidInsert(String txt) {
		try {
			int v = Integer.parseUnsignedInt(txt);
			return(super.getReferee().isInBound(v) && super.getReferee().isValidInsert(v,field));
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#getColumn()
	 */
	@Override
	public int getColumn() throws InternalException {
		if(!printed) Utils.printField(field, super.getReferee());
		int column = doInput(String.format("Choose a column from 0 to %d", field.getColumns()), this::isValidInsert , Integer::parseUnsignedInt);
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
	private <T> T doInput(String request, Predicate<String> condition, Function<String, T> readFun) throws InternalException {
		while (true) {
			this.out.println(request);
			String line;
			try {
				line = this.in.readLine();
			} catch (IOException e) {
				throw new InternalException(e);
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