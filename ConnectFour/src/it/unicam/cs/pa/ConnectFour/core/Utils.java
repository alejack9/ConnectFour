/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.io.PrintStream;
import java.util.function.BiFunction;

import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacche`
 *
 */
public class Utils {

	public static void clearScreen() {
		clearScreen(System.out);
	}

	public static void clearScreen(PrintStream writer) {
		for (int i = 0; i < 100; i++)
			writer.println();
	}

	/**
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
