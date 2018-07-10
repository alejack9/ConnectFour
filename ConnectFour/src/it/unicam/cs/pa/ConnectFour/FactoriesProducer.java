/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class FactoriesProducer {
	public static AbstractFactory getFactory(Factories factory) {
		switch (factory) {
		case PIECES:	return new PieceFactory();
		case PLAYERS:	return new PlayerFactory();
		case REFEREE:	return new RefereeFactory();
		default:		return null;
		}
	}
}
