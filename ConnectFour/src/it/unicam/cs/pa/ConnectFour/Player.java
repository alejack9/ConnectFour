/**
 * 
 */
package it.unicam.cs.pa.ConnectFour;

/**
 * @author giacchè
 *
 */
public interface Player {
	
	PieceLocation arrange(Piece piece);
	
	void init(int pid, int size);

	void winForError(Throwable e);

	void loseForError(Throwable e);

	void startMatch();

	void youWin();

	void youLose();
	
}
