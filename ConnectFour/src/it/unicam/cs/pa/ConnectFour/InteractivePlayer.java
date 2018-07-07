/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

import it.unicam.cs.pa.battleship.InternalException;

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
	private MatchField field;
	private BufferedReader in;
	private PrintStream out;
	
	public InteractivePlayer ( String name , InputStream in , PrintStream out ) {
		this.name = name;
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = out;		
	}
	
	public InteractivePlayer( String name) {
		this( name, System.in, System.out);
	};

	@Override
	public PieceLocation insert(Piece piece) {
		while(true) {
			Utils.printField(this.field);
			print("Place a checker");
			int column = doInput("Choose a column: ", this::isValidIndex, Integer::parseUnsignedInt);
			
			
			
			
			
		}
		return null;
	}

	private <T> T doInput(String string, Predicate<String> condition, Function<String, T> readFun) {
		// TODO to finish
		while (true) {
			System.out.println(string);
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

	private void print(String string) {
		System.out.println(this.name+ "> " + string);
	}

	@Override
	public void init(int pid, AbstractRuleSet ruleSet) {
		this.ID = pid;
		this.field = new MatchField(ruleSet);
	}

	@Override
	public void winForError(Throwable e) {
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
	public void youWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
