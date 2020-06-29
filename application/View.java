package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * View class the modifies the GUI with buttons and text areas
 * @author Micah Weiberg
 * @version 12/11/18
 * CS Marble Solitaire Project
 */
public class View extends GridPane
{
	/** Pointer to the Model object */
	private Model m;

	/** Button that restarts the game */
	private Button restartB;

	/** Text area that displays the key to whether the game has been won */
	TextArea endGame;
	
	/** Text area that displays basic instructions to the game */
	TextArea instruction;

	/** The constructor with a Model object that sets the buttons and text areas
	 * @param inputModel Model object
	 */
	public View(Model inputModel)
	{
		// Initialize model
		m = inputModel;
		endGame = new TextArea();
		instruction = new TextArea();
		restartB = new Button("Restart Game");
		GridPane mainPanel = new GridPane();
		
		// Editing the end game text area
		endGame.setMaxHeight(75);
		endGame.setMaxWidth(150);
		endGame.setText("Michigan = You won!\nNotre Dame = Game over, you lost");
		endGame.setEditable(false);
		endGame.setWrapText(true);
		
		// Editing the instruction text area
		instruction.setMaxHeight(200);
		instruction.setMaxWidth(150);
		instruction.setText("Blue = full space\nBlack = empty space\nRed = selected space\n\n"
				+ "Move a marble (full space) over another marble to an empty space to complete a valid move");
		instruction.setEditable(false);
		instruction.setWrapText(true);

		// Adding components to panels
		GridPane endGameP = new GridPane();
		endGameP.add(endGame, 0, 0);
		GridPane instructionP = new GridPane();
		instructionP.add(instruction, 0, 0);
		GridPane restartButtonP = new GridPane();
		restartButtonP.add(restartB, 0, 0);
		
		// Adding panels to main panel
		mainPanel.add(new Label("Result: "), 0, 0);
		mainPanel.add(endGameP, 1, 0);
		mainPanel.add(restartButtonP, 1, 1);
		mainPanel.add(new Label("Instructions: "), 0, 2);
		mainPanel.add(instructionP, 1, 2);
		
		add(mainPanel, 0, 0);
	}

	/**
	 * Return pointer to the restart button
	 * @return A pointer to the restart button
	 */
	public Button getRestartButton()
	{
		return restartB;
	}

	/** 
	 * Adding action listeners to buttons
	 * @param parent The class that contains the action performed
	 */
	public void activateButtons(EventHandler parent)
	{
		restartB.setOnAction(parent);
	}
}