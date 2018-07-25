/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.player;

import java.io.InputStream;
import java.io.PrintStream;
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

	protected RandomPlayer(String name, boolean echo, InputStream in, PrintStream out) {
		super(name, in, out);
		this.random = new Random();
		this.echo = echo;
	}

	public RandomPlayer(String name, boolean echo) {
		this(name, echo, System.in, System.out);
	}
	
	public RandomPlayer(String name) {
//		this(name, FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.DEFAULT), true);
		this(name, true);
	}

	@Override
	public ActionType chooseAction() throws InternalException {
		if(echo) { Utils.printField(field, super.getReferee()); printed = true; }
		return super.getReferee().getAllowedActions().keySet().
	}

	@Override
	public int getColumn() throws InternalException {
		if(echo && !printed) { Utils.printField(field, super.getReferee()); }
		int selcol;
		while(!super.getReferee().isValidInsert(selcol = random.nextInt(field.getColumns()), field,Utils.parsePlayer(getId())));
		if(echo) print("I've choose the column " + selcol);
		return selcol;
	}

	@Override
	public void init(int pid, MatchField field, RuleSet referee) throws IllegalIdValue {
		this.ID = pid;
		this.field = field;
		super.setReferee(referee);
	}

	@Override
	public void loseForError(Throwable e) {
		print("I've lost because of '" + e.getMessage() + "'");
	}

	@Override
	public void startMatch() {
		print("My ID is " + ID);
	}

	@Override
	public void winForError(Throwable e) {
		print("I've won because of '" + e.getMessage() + "'");
	}

	@Override
	public void youLose() {
//		print("I have lost!");
		out.println(this.name+"> "+"I have lost!");
	}

	@Override
	public void youWin() {
//		print("I have win!");
		out.println(this.name+"> "+"I have win!");
	}
	
	private void print(String string) {
		if (echo) {
			out.println(this.name+"> "+string);
		}
	}
	
}
