package controller;

import controller.SudokuController;
import java.awt.Color;
import java.awt.Dimension;
/**

 * Controller class for the Button class
 * event handler
 * @author Aleksandra, Ben, Jefferson
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import model.Game;
import view.ButtonPad;
import view.Sudoku;
import view.SudokuBoard;

public class ButtonController {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;
	private Sudoku sudoku;
	int score = 1000;
	int totalMistakes = 0;
	long startTime;
	long endTime;
	public ButtonController(ButtonPad buttonPad, Game game, SudokuBoard sudokuBoard) {
		this.buttonPad = buttonPad;
		this.game = game;
		this.sudokuBoard = sudokuBoard;
		// this.sudoku = sudoku;
	}

	public void update() {

		buttonPad.getNewGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				game.newGame();
				startTime = System.currentTimeMillis();
				sudokuBoard.setClues(game);
				System.out.println("NEW GAME");

			}
		});

		buttonPad.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("EXIT");
				System.exit(0);
			}
		});
		buttonPad.getSolutionButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sudokuBoard.setSolution(game);
				score = 0;
				System.out.println("Show Solution");
			}
		});
		
		// help button shows cells in red that are not correct
		buttonPad.getHelpButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!game.isHelp()) {
					game.setHelp(true);
					System.out.println("Help on");
					game.gameCheck();
					sudokuBoard.setHelp(game);

				} else {
					game.setHelp(false);
					sudokuBoard.setHelp(game);
				}
				//Removes 3 points from score everytime help is used
				score = score - 3;
			}

		});

		buttonPad.getSubmitButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				endTime = System.currentTimeMillis();
				//Tracks time taken
				long timeTaken = endTime - startTime;
				int seconds = (int) (timeTaken / 1000) % 60 ;
			    int minutes = (int) ((timeTaken / (1000*60)) % 60);
			    int hours = (int) ((timeTaken / (1000*3600)) % 60);
				//Tracks the total number of mistakes
				totalMistakes = totalMistakes + game.calcMistakes();
				
				//Calculates the number of mistakes
				int boardMistakes  = game.calcMistakes();
				//Message that displays when board isn't solved
				String scoreDetails = "Sorry! Unfortunately this is not the solution!" + "\n" + 
						"The Sudoku board is not fully filled out or has incorrect cells!" + "\n" + "\n"
						+ "Mistakes on board: " + boardMistakes + "\n" + "\n" + "Time Elapsed(hours/mins/secs): " +
						String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);  
				//Message that displays when board is solved
				String scoreDetailsCompleted = "Congratulations on completing the Sudoku Board!" + "\n"
						+ "Number of mistakes made: " + totalMistakes + "\n" + "Score: " + score +
						"\n" + "\n" + "Time Elapsed(hours/mins/secs): " +
						String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
				
				//Creates the alert window, upon button press
				if(game.checkBoard() == true) {
					JOptionPane.showMessageDialog(null, scoreDetailsCompleted, "Score", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, scoreDetails, "Score", JOptionPane.PLAIN_MESSAGE);
					score = score - totalMistakes;
				}
			}
		});

		// adds listeners to the number buttons
		for (int i = 0; i < 9; i++)
			buttonPad.getKeypadNumbers()[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					game.setUserInput(Integer.parseInt(e.getActionCommand()));

				}

			});

	}

}
