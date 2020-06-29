package application;

/**
 * Interface for the Model class
 * @author Micah Weiberg
 * @version 12/11/18
 * CS2 Marble Solitaire Project
 */
public interface ModelInterface
{	
	public void move(int x1, int x2, int y1, int y2);
	
	public void moveDirection(int x1, int x2, int y1, int y2);
	
	public boolean gameOver();
	
	public boolean isWin();
}