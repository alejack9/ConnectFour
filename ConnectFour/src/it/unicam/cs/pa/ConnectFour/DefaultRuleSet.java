/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public class DefaultRuleSet extends AbstractRuleSet {
	
	private static final int[] DEFAULT_SIZE = { 6 , 7 };
	
	/**
	 * 
	 */
	public DefaultRuleSet() {
		// TODO Set default insertFun
		super( (x,y) -> new PieceLocation(0,0) , DEFAULT_SIZE );
	}
	
	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isValidInsert(int, it.unicam.cs.pa.ConnectFour.Piece)
	 */
	@Override
	public boolean isValidInsert( Cell[][] filed, int column ) throws IndexOutOfBoundsException {
		// TODO Set default ConnectFour rules.
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#isInBound(int)
	 */
	@Override
	public boolean isInBound( int column , int columns ) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see it.unicam.cs.pa.ConnectFour.AbstractRuleSet#getDefaultSize()
	 */
	// potrebbe sembrare una ripetizione: DEFAULT_SIZE e` pubblico! tuttavia e` necessario assicurarsi che tutti RuleSets abbiano un default size.
	@Override
	public int[] getDefaultSize() {
		return DEFAULT_SIZE;
	}

}
