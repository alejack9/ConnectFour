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
	
	private ActionType selectedAction;

	protected RandomPlayer(String name, boolean echo, InputStream in, PrintStream out) {
		super(name, in, out);
		this.random = new Random();
		this.echo = echo;
	}

	public RandomPlayer(String name, boolean echo) {
		this(name, echo, System.in, System.out);
	}
	
	public RandomPlayer(String name) {
		this(name, true);
	}

	@Override
	public ActionType chooseAction() throws InternalException {
		if(echo) { Utils.printField(field, super.getReferee()); printed = true; }
		do {
			this.selectedAction = ActionType.values()[new ArrayList<Integer>(super.getReferee().getAllowedActions().keySet()).get(random.nextInt(super.getReferee().getAllowedActions().size()))];
		} while(!isValid(this.selectedAction));
		
		return this.selectedAction;
	}
	

	private boolean isValid(ActionType action) {
		try {
			try {
				if(super.getReferee().getAllowedActions().get(action.ordinal()) != null) {
					for(int i = 0; i < field.getColumns(); i++) {
						if(super.getReferee().getAllowedActions().get(action.ordinal())
								.test(field.getColumn(i), Utils.parsePlayer(getId())))
							return true;
					}
				}
			} finally { }
		} finally { }
		return false;
	}


	@Override
	public int getColumn() throws InternalException {
		if(echo && !printed) { Utils.printField(field, super.getReferee()); }
		int selcol;
		while(!super.getReferee().getAllowedActions().get(selectedAction.ordinal()).test(field.getColumn(selcol = random.nextInt(field.getColumns())), Utils.parsePlayer(getId())));
//		while(!super.getReferee().isValidInsert(selcol = random.nextInt(field.getColumns()), field,));
		if(echo) print("I choose " + selectedAction.toString() + " in the column " + (selcol + 1));
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
		print("I've lost because of '" + e.toString() + "'");
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
