package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Controller class that makes changes to Model method and contributes to View method
 * @author Micah Weiberg
 * @version 12/11/18
 * CS2 Marble Solitaire Project
 */
public class Main extends Application implements EventHandler<ActionEvent>
{
	/** Pointer to Model object */
	private Model m;

	/** Pointer to View object */
	private View view;

	/** ArrayList of ArrayList of Buttons */
	private ArrayList<ArrayList<Button>> buttons;

	/** Counter variable that tracks which part of the move is being made */
	private int counter;

	/** Array that holds the initial coordinates of the selected marble being moved */
	private int [] firstSelect;

	/**
	 * Method that initializes the GUI
	 */
	public void start(Stage primaryStage)
	{
		try {
			buttons = new ArrayList<ArrayList<Button>>();			
			counter = 0;

			populateButtonArrayList();

			GridPane root = new GridPane();

			// Adding buttons to root
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					root.add(buttons.get(i).get(j), i, j);
				}
			}

			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Marble Solitaire");

			// Adding buttons and text areas from View class
			view = new View(m);
			view.activateButtons(this);
			root.add(view, 8, 8);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that populates the ArrayList of ArrayList of Buttons according the correct game board
	 */
	private void populateButtonArrayList()
	{
		for (int i = 0; i < 7; i++)
		{
			buttons.add(new ArrayList<Button>());
			for (int j = 0; j < 7; j++)
			{
				Button b = new Button();
				b.setShape(new Circle(1.5));
				b.setMinSize(50, 50);				

				m = new Model();
				firstSelect = new int[2];

				if (m.gameBoard.get(i).get(j) == m.full)
					b.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				if (m.gameBoard.get(i).get(j) == m.invalid)
					b.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				if (m.gameBoard.get(i).get(j) == m.empty)
					b.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

				b.setOnAction(this);
				buttons.get(i).add(b);
			}
		}
	}

	/**
	 * Method that updates the Buttons after each clicked Button according to the state of the game
	 */
	private void updateUI() 
	{
		// If the game is not over, the board will be set according to previous moves
		if (m.gameOver() == false)
		{
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					buttons.get(i).get(j).setGraphic(null);
					if (m.gameBoard.get(i).get(j) == m.full)
						buttons.get(i).get(j).setBackground(new Background(new BackgroundFill
								(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
					if (m.gameBoard.get(i).get(j) == m.invalid)
						buttons.get(i).get(j).setBackground(new Background(new BackgroundFill(
								Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
					if (m.gameBoard.get(i).get(j) == m.empty)
						buttons.get(i).get(j).setBackground(new Background(new BackgroundFill(
								Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
		}
		
		// If the game is over and has been won, the board will be set with the according image
		if (m.isWin() == true)
		{
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					buttons.get(i).get(j).setGraphic(new ImageView(new Image("BlockM.jpg")));
					buttons.get(i).get(j).setBackground(new Background(new BackgroundFill(
							Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
		}
		
		// If the game is over and has been lost, the board will be set with the according image
		if (m.isWin() == false && m.gameOver() == true)
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					buttons.get(i).get(j).setGraphic(new ImageView(new Image("ND.jpg")));
					buttons.get(i).get(j).setBackground(new Background(new BackgroundFill(
							Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
				}
			}
		return;
	}

	/**
	 * Main method that inputs to the console and launches the GUI
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);

		// COMMENTED OUT CODE FROM CONSOLE VERSION

		//		try {
		//			Model m = new Model();
		//
		//			for (int i = 0; i < 7; i++)
		//			{
		//				System.out.println(m.gameBoard.get(i));
		//			}
		//
		//			// Loop to print this code while the game is still active
		//			while (m.gameOver() != true)
		//			{
		//				Scanner input = new Scanner(System.in);
		//				System.out.println("Game board: 0 = empty space, 1 = full space, 2 = invalid space");
		//				System.out.print("Enter x and y coordinates you are moving from: ");
		//				int x1 = input.nextInt();
		//				int y1 = input.nextInt();
		//				System.out.print("Enter x and y coordinates you are moving to: ");
		//				int x2 = input.nextInt();
		//				int y2 = input.nextInt();
		//
		//				try {
		//					m.move(x1, x2, y1, y2);
		//				} catch (Exception e) {
		//					System.out.print(e.getMessage());
		//				}
		//			}
		//			
		//			if (m.isWin() == true)
		//				System.out.println("You won!");
		//			else
		//				System.out.println("Game over");
		//
		//		} catch (Exception e) {
		//			System.out.print(e.getMessage());
		//		}
	}

	/**
	 * Method that handles what happens when Buttons are clicked
	 */
	public void handle(ActionEvent event)
	{
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				if (event.getSource() == buttons.get(i).get(j))
				{
					if (m.gameOver() == true)
						return;
					if (counter % 2 == 0) // Coordinate that the player is moving from
					{
						buttons.get(i).get(j).setBackground(new Background(new BackgroundFill
								(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
						x1 = i;
						y1 = j;
						firstSelect[0] = i;
						firstSelect[1] = j;
						counter++;
					}
					else // Coordinate that the player is moving to
					{
						buttons.get(i).get(j).setBackground(new Background(new BackgroundFill
								(Color.CRIMSON, CornerRadii.EMPTY, Insets.EMPTY)));
						x2 = i;
						y2 = j;
						x1 = firstSelect[0];
						y1 = firstSelect[1];
						counter++;

						try {
							m.move(x1, x2, y1, y2);
							firstSelect = new int[2];
							updateUI();
						} catch (Exception e) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Error");
							alert.setContentText(e.getMessage());
							alert.showAndWait();
							counter = 0;
							updateUI();
						}
					}
					return;
				}
			}
		}

		// If the restart button is clicked
		if (event.getSource() == view.getRestartButton())
		{
			firstSelect = new int[2];
			m = new Model();
			updateUI();
			counter = 0;
		}
	}
}