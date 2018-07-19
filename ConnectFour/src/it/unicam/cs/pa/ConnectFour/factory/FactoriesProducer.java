package it.unicam.cs.pa.ConnectFour.factory;

import it.unicam.cs.pa.ConnectFour.exception.UnknownEnumValue;

/**
 * @author giacchè
 *
 */
public class FactoriesProducer {
	/**
	 * @throws UnknownEnumValue if the factory is unknown
	 */
	public static AbstractFactory getFactory(Factories factory) throws UnknownEnumValue {
		switch (factory) {
		case PIECES:	return PieceFactory.getIstance();
		case REFEREE:	return new RefereeFactory();
		default:		throw new UnknownEnumValue(factory);
		}
	}
}
