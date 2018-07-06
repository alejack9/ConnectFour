/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

import java.io.PrintStream;
import java.util.function.BiFunction;

/**
 * @author giacchè
 *
 */
public class Utils {

	// TODO Printer methods.
/*	public static void printField(MatchField field ) {
		printField( System.out , field.getView() , field.getRows() , field.getColums() );
	}
	//random comment
	public static void printField(PrintStream writer ,  MatchField field ) {
		printField( writer , field.getView() , field.getRows() , field.getColums() );
	}

	public static void printField(BiFunction<Integer, Integer, CellStatus> view, int rows , int columns ) {
		printField(System.out , view , rows , columns );
	}

	public static void printField(PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int rows , int columns ) {
		for( int i=0; i<size ; i++ ) {
			printRowDelimiter(writer,size);
			printRow(writer,view,i,size);
		}
		printRowDelimiter(writer,size);
	}
	
	private static void printRowDelimiter( PrintStream writer , int rows , int columns ) {
		writer.print("    ");
		for( int i=0 ; i<size ; i++ ) {
			writer.print("+---");
		}
		writer.println("+");
	}
	
	private static void printRow(PrintStream writer , BiFunction<Integer, Integer, CellStatus> view, int rows , int columns ) {
		writer.print(String.format("%4d", rows ));
		for( int i=0 ; i<size ; i++ ) {
			writer.print("| "+view.apply(rows, i)+" ");
		}
		writer.println("|");
	}
*/
}
