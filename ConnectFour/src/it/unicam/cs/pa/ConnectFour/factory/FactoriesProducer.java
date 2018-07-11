package it.unicam.cs.pa.ConnectFour.factory;

/**
 * @author giacchè
 *
 */
public class FactoriesProducer {
	/**
	 * @throws IllegalArgumentException if the factory is unknown
	 */
	public static AbstractFactory getFactory(Factories factory) throws IllegalArgumentException {
		switch (factory) {
		case PIECES:	return new PieceFactory();
		case PLAYERS:	return new PlayerFactory();
		case REFEREE:	return new RefereeFactory();
		default:		throw new IllegalArgumentException("factory '" + factory + "' is unknown.");
		}
	}
}
