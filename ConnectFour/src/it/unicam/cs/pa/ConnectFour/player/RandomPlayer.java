/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.MatchField;
import it.unicam.cs.pa.ConnectFour.exception.IllegalIdValue;
import it.unicam.cs.pa.ConnectFour.exception.InternalException;
import it.unicam.cs.pa.ConnectFour.factory.Factories;
import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSetType;

/**
 * @author giacchè
 *
 */
public class RandomPlayer extends Player {

	private Random random;
	private boolean echo;
	 
	public RandomPlayer(String name) {
		this(name, FactoriesProducer.getFactory(Factories.REFEREE).getReferee(RuleSetType.DEFAULT), true);
	}

	public RandomPlayer(String name, RuleSet ruleset, boolean echo) {
		this(name, ruleset, echo, System.in, System.out);
	}
	
	protected RandomPlayer(String name, RuleSet ruleset, boolean echo, InputStream in, PrintStream out) {
		super(name, ruleset, in, out);
		this.random = new Random();
		this.echo = echo;
	}

	@Override
	public ActionType chooseAction() throws InternalException {
		return super.getReferee().getAllowedActions().get(random.nextInt(super.getReferee().actionsNumber()));
	}

	@Override
	public int getColumn() throws InternalException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(int pid, MatchField field) throws IllegalIdValue {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loseForError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void winForError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youWin() {
		// TODO Auto-generated method stub
		
	}
	
}
