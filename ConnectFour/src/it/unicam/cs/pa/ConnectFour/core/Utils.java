/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import it.unicam.cs.pa.ConnectFour.exception.InternalException;
import it.unicam.cs.pa.ConnectFour.player.Player;
import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * Static Utils class
 * 
 * @author giacche`
 *
 */
public class Utils {

	private Utils() {
	}

	/**
	 * Wrap several times
	 */
	public static void clearScreen() {
		clearScreen(System.out);
	}

	/**
	 * Wrap several times in a specific PrintStream
	 *
	 * @param writer Target PrintStream
	 */
	public static void clearScreen(PrintStream writer) {
		for (int i = 0; i < 50; i++)
			writer.println();
	}

	/**
	 * Converts a player id into a CellStatus, if possible
	 * 
	 * @param player - The {@link Player}'s id, must be 0 or 1
	 * @return The player is {@link CellStatus} form
	 * @throws IllegalArgumentException if {@code player} is not valid
	 */
	public static CellStatus parsePlayer(int player) throws IllegalArgumentException {
		switch (player) {
		case 0:
			return CellStatus.P1;
		case 1:
			return CellStatus.P2;
		default:
			throw new IllegalArgumentException("'player' must be 0 or 1, '" + player + "' is not allowed.");
		}
	}

	public static void printField(MatchField field, RuleSet referee) {
		printField(System.out, field.getView(referee), field.getRows(), field.getColumns());
	}

	public static void printField(PrintStream writer, MatchField field, RuleSet referee) {
		printField(writer, field.getView(referee), field.getRows(), field.getColumns());
	}

	public static void printField(BiFunction<Integer, Integer, CellStatus> view, int rows, int columns) {
		printField(System.out, view, rows, columns);
	}

	public static void printField(PrintStream writer, BiFunction<Integer, Integer, CellStatus> view, int rows,
			int columns) {
		printColumnIndexes(writer, columns);
		for (int row = 0; row < rows; row++) {
			printRow(writer, view, row, columns);
			printRowDelimiter(writer, columns);
		}
	}

	/**
	 * @param in		the stream reader (designed as BufferedReader)
	 * @param out		the output stream
	 * @param string    What to ask
	 * @param condition Input condition/s
	 * @param readFun   Parser from String to required type
	 * @return the inserted value
	 * @throws IOException
	 */
	public static <T> T doInput(BufferedReader in, PrintStream out, String request, Predicate<T> condition, Function<String, T> readFun) {
		while (true) {
			out.println(request);
			String line;
			try {
				line = in.readLine();
			} catch (IOException e) {
				throw new InternalException(e);
			}
			T x = null;
			try {
				x = readFun.apply(line);				
			} catch (Throwable e) {
				out.println("Input Error!");
				continue;
			}
			if (!condition.test(x)) {
				out.println("Input Error!");
			} else {
				return x;
			}
		}
	}

//	/**
//	 */
//	private static <T> T doInput(String request, Predicate<T> condition, Function<String, T> readFun)
//			throws InternalException {
//		return doInput(new BufferedReader(new InputStreamReader(System.in)), System.out, request,condition,readFun);
//		while (true) {
//			this.print(request);
//			String line;
//			try {
//				line = this.in.readLine();
//			} catch (IOException e) {
//				throw new InternalException(e);
//			}
//			T x = null;
//			try {
//				x = readFun.apply(line);				
//			} catch (Throwable e) {
//				out.println("Input Error!");
//				continue;
//			}
//			if (!condition.test(x)) {
//				out.println("Input Error!");
//			} else {
//				return x;
//			}
//		}
//	}

	private static void printColumnIndexes(PrintStream writer, int columns) {
		writer.print("    ");
		for (int i = 0; i < columns; i++) {
			writer.print("  " + (i + 1) + " ");
		}
		writer.println();
	}

	private static void printRowDelimiter(PrintStream writer, int columns) {
		writer.print("    ");
		for (int i = 0; i < columns; i++) {
			writer.print("+---");
		}
		writer.println("+");
	}

	private static void printRow(PrintStream writer, BiFunction<Integer, Integer, CellStatus> view, int row,
			int columns) {
		writer.print("    ");
		for (int column = 0; column < columns; column++) {
			writer.print("| " + view.apply(row, column) + " ");
		}
		writer.println("|");
	}
}
