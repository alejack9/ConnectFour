package it.unicam.cs.pa.ConnectFour.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Predicate;

import it.unicam.cs.pa.ConnectFour.core.ActionType;
import it.unicam.cs.pa.ConnectFour.core.Cell;
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
	private Cell[][] field;
	private BufferedReader in;
	private PrintStream out;
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
	public void init(int pid , RuleSet referee ) throws IllegalIdValue {
		this.setID(pid);
		this.referee = referee;
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#chooseAction()
	 */
	@Override
	public ActionType chooseAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#getColumn()
	 */
	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#youWin()
	 */
	@Override
	public void youWin() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#winForError(java.lang.Throwable)
	 */
	@Override
	public void winForError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#youLose()
	 */
	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.Player#loseForError(java.lang.Throwable)
	 */
	@Override
	public void loseForError(Throwable e) {
		// TODO Auto-generated method stub
		
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
