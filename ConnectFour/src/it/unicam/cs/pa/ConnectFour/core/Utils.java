/**
 * 
 */
package it.unicam.cs.pa.ConnectFour.core;

import java.io.PrintStream;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import it.unicam.cs.pa.ConnectFour.ruleSet.RuleSet;

/**
 * @author giacche`
 *
 */
public class Utils {

	public static void clearScreen() {
		clearScreen(System.out);
	}

	public static void clearScreen( PrintStream writer ) {
		for(int i = 0 ; i < 100 ; i++)
			writer.println(); 
	}

	public static void printField ( MatchField field, RuleSet referee ) {
		printField( System.out , field.getView(referee) , field.getRows() , field.getColumns() );
	}
	
	public static void printField ( PrintStream writer ,  MatchField field, RuleSet referee ) {
		printField( writer , field.getView(referee) , field.getRows() , field.getColumns() );
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

	public static int[] stringToSize (String size) throws IllegalArgumentException {
		try {
			List.of(size.split(",")).forEach(Integer::parseInt);
			int[] toReturn = new int[] { Integer.parseInt(size.split(",")[0]) , Integer.parseInt(size.split(",")[1]) };
			// REPORT stream is not useful in this case: there're only 2 elements
//			int[] toReturn = Stream.of(size.split(",")).mapToInt(Integer::parseInt).takeWhile(c -> c > 1).toArray();
	
			if(toReturn.length == 2)
				return toReturn;
			throw new IllegalArgumentException();
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}
	
	/**
	 * convert a couple of {@link Integer} to a string 
	 * @param size an array of 2 values: [ ROWS , COLUMNS ]
	 * @return a string in ROWS,COLUMNS form
	 */
	// REPORT we could use a new class "Size" and implement the "toString" method
	public static String sizeToString (int[] size) {
		return size[0] + "," + size[1];
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
}
