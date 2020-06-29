package application;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Model class that contains the data for the application
 * @author Micah Weiberg
 * @version 12/11/18
 * CS2 Marble Solitaire Project
 */
public class Model
{
	/** Sets the game board for peg solitaire. Sets states as values in the 7x7 board. */
	ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<ArrayList<Integer>>();

	/** Stores the value of the number of pegs still on the board */
	private int numberOfMarbles = 32;

	/** Sets final value of an empty slot on the board to zero */
	public final static int empty = 0;

	/** Sets final value of a full slot on the board to one */
	public final static int full = 1;

	/** Sets final value of an invalid slot on the board to two */
	public final static int invalid = 2;

	/**
	 * Constructor that sets the game board with initial values
	 */
	public Model()
	{
		// This for loop sets the whole game board 
		for (int i = 0; i < 7; i++)
		{
			gameBoard.add(new ArrayList<Integer>());
			for (int j = 0; j < 7; j++)
			{
				gameBoard.get(i).add(full);
			}
		}
		
		// This for loop sets the top left 2x2 section to invalid
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				gameBoard.get(i).set(j, invalid);
			}
		}
		
		// This for loop sets the bottom left 2x2 section to invalid
		for (int i = 5; i < 7; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				gameBoard.get(i).set(j, invalid);
			}
		}
		
		// This for loop sets the top right 2x2 section to invalid
		for (int i = 0; i < 2; i++)
		{
			for (int j = 5; j < 7; j++)
			{
				gameBoard.get(i).set(j, invalid);
			}
		}
		
		// This for loop sets the bottom right 2x2 section to invalid
		for (int i = 5; i < 7; i++)
		{
			for (int j = 5; j < 7; j++)
			{
				gameBoard.get(i).set(j, invalid);
			}
		}
		
		// This for loop sets the middle (3,3) spot to empty
		for (int i = 3; i < 4; i++)
		{
			for (int j = 3; j < 4; j++)
			{
				gameBoard.get(i).set(j, empty);
			}
		}
	}

	/**
	 * Method that moves the marble according to the coordinates and prints the corresponding game board
	 * @param x1 The x-coordinate of the starting spot for the marble being moved
	 * @param x2 The x-coordinate of the ending spot for the marble being moved
	 * @param y1 The y-coordinate of the starting spot for the marble being moved
	 * @param y2 The y-coordinate of the ending spot for the marble being moved
	 * @throws Exception for if the move is not made 2 spaces apart
	 */
	public void move(int x1, int x2, int y1, int y2) throws Exception
	{
		// If statement for a move up
		if (y2 == y1 && x2 == x1 - 2)
		{
			moveDirection(x1, x2, y1, y2);
			numberOfMarbles--;
		}
		
		// If statement for a move down
		else if (y2 == y1 && x2 == x1 + 2)
		{
			moveDirection(x1, x2, y1, y2);
			numberOfMarbles--;
		}
		
		// If statement for a move left
		else if (y2 == y1 - 2 && x2 == x1)
		{
			moveDirection(x1, x2, y1, y2);
			numberOfMarbles--;
		}
		
		// If statement for a move right
		else if (y2 == y1 + 2 && x2 == x1)
		{
			moveDirection(x1, x2, y1, y2);
			numberOfMarbles--;
		}
		
		else
			throw new Exception ("Invalid move: Move must be up, down, left, or right 2 spaces\n");
		return;
	}

	/**
	 * Method that attempts make a valid with a peg up on the game board to a spot 2 spaces above it
	 * @param x1 The x-coordinate of the starting spot for the marble being moved
	 * @param x2 The x-coordinate of the ending spot for the marble being moved
	 * @param y1 The y-coordinate of the starting spot for the marble being moved
	 * @param y2 The y-coordinate of the ending spot for the marble being moved
	 * @throws Exception for if the space moving to is not empty or the space moving from is not full
	 */
	public void moveDirection(int x1, int x2, int y1, int y2) throws Exception
	{				
		// This for loop sets the new (x2, y2) location = full
		for (int i = x2; i <= x2; i++)
		{
			for (int j = y2; j <= y2; j++)
			{
				if (gameBoard.get(i).get(j) == full || gameBoard.get(i).get(j) == invalid)
					throw new Exception("Invalid move: The space you move to must be empty\n");
				gameBoard.get(i).set(j, full);
			}
		}
		
		// This for loop sets the original (x1, y1) location = empty
		for (int i = x1; i <= x1; i++)
		{
			for (int j = y1; j <= y1; j++)
			{
				if (gameBoard.get(i).get(j) == empty || gameBoard.get(i).get(j) == invalid)
					throw new Exception("Invalid move: The space you move from must be full\n");
				gameBoard.get(i).set(j, empty);
			}
		}
		
		// This for loop sets the space between moves to empty, provided the move was valid
		for (int i = ((x1+x2)/2); i <= ((x1+x2)/2); i++)
		{
			for (int j = ((y1+y2)/2); j <= ((y1+y2)/2); j++)
			{
				gameBoard.get(i).set(j, empty);
			}
		}
		return;
	}

	/**
	 * Method that determines if the game is over
	 * @return Whether the game is over
	 */
	public boolean gameOver()
	{
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				if (((j < 5) && gameBoard.get(i).get(j) == full && gameBoard.get(i).get(j+1) == full 
						&& gameBoard.get(i).get(j+2) == empty))
				{
					return false;
				}
				if (((j > 1) && gameBoard.get(i).get(j) == full && gameBoard.get(i).get(j-1) == full 
						&& gameBoard.get(i).get(j-2) == empty))
				{
					return false;
				}
				if (((i < 5) && gameBoard.get(i).get(j) == full && gameBoard.get(i+1).get(j) == full 
						&& gameBoard.get(i+2).get(j) == empty))
				{
					return false;
				}
				if (((i > 1) && gameBoard.get(i).get(j) == full && gameBoard.get(i-1).get(j) == full 
						&& gameBoard.get(i-2).get(j) == empty))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method that determines if the game has been won
	 * @return Whether the player has won the game
	 */
	public boolean isWin()
	{
		if (numberOfMarbles == 1)
			return true;
		else
			return false;
	}
}