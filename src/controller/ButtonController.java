package controller;

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
import view.SudokuBoard;

public class ButtonController {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;

	public ButtonController(ButtonPad buttonPad, Game game, SudokuBoard sudokuBoard) {
		this.buttonPad = buttonPad;
		this.game = game;
		this.sudokuBoard = sudokuBoard;
	}

	public void update() {
		
		buttonPad.getNewGameButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				game.newGame();
				game.setStartTime(System.currentTimeMillis());
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
				game.resetScore();
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
				game.subtractPoints();
			}

		});

		buttonPad.getSubmitButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.score();
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

