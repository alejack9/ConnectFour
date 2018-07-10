/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.io.PrintStream;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author giacchè
 *
 */
public class Utils {

	public static void printField ( MatchField field ) {
		printField( System.out , field.getView() , field.getRows() , field.getColumns() );
	}
	
	public static void printField ( PrintStream writer ,  MatchField field ) {
		printField( writer , field.getView() , field.getRows() , field.getColumns() );
	}

	public static void printField ( BiFunction<Integer, Integer, CellStatus> view, int rows , int columns ) {
		printField(System.out , view , rows , columns );
	}

	public static void printField ( PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int rows , int columns ) {
		printColumnIndexes( writer , columns );
		for( int row = 0; row < rows ; row++ ) {
			printRow ( writer , view , row , columns );
			printRowDelimiter ( writer , columns );
		}
	}

	public static void clearScreen() {
		clearScreen(System.out);
    }
	public static void clearScreen( PrintStream writer ) {
		for(int i = 0 ; i < 100 ; i++)
			writer.println(); 
    }
	
	private static void printColumnIndexes( PrintStream writer , int columns) {
		writer.print("    ");
		for (int i = 0 ; i < columns ; i++) {
			writer.print("  " + (i + 1) + " ");
		}
		writer.println();
	}

	private static void printRowDelimiter ( PrintStream writer , int columns ) {
		writer.print("    ");
		for( int i = 0 ; i < columns ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	private static void printRow ( PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int row , int columns ) {
		//writer.print(String.format("%4d", row ));
		writer.print("    ");
		for( int column = 0 ; column < columns ; column++ ) {
			writer.print("| " + view.apply( row , column ) + " ");
		}
		writer.println("|");
	}
	
	public static int[] sizeParse (String size) {
		try {
			int[] toReturn = Stream.of(size.split("x")).mapToInt(Integer::parseInt).takeWhile(c -> c > 1).toArray();

			if(toReturn.length == 2)
				return toReturn;
			throw new IllegalArgumentException();
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}
	
	public static RuleSetType ruleSetParse (String ruleset) {
		return RuleSetType.parse(ruleset);
	}

}
