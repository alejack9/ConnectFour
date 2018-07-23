///**
// * 
// */
//package it.unicam.cs.pa.ConnectFour.test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//import it.unicam.cs.pa.ConnectFour.factory.AbstractFactory;
//import it.unicam.cs.pa.ConnectFour.factory.Factories;
//import it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer;
//
///**
// * @author giacchè
// *
// */
//class FactoriesProducerTest {
//	
//	/**
//	 * Test method for {@link it.unicam.cs.pa.ConnectFour.factory.FactoriesProducer#getFactory(it.unicam.cs.pa.ConnectFour.factory.Factories)}.
//	 */
//	@Test
//	void testGetFactory() {
//		AbstractFactory pieceFactory = FactoriesProducer.getFactory(Factories.PIECES);
//		AbstractFactory playerFactory  = FactoriesProducer.getFactory(Factories.REFEREE);
//
//		assertNotNull(pieceFactory);
//		assertNotNull(playerFactory);
//	}
//
//}
