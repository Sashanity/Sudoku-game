package controller;

import java.awt.Dimension;
/**

 * Controller class for the Button class
 * event handler
 * @author Aleksandra, Ben, Jefferson
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				System.out.println("NEW GAME");
				/*
				 * System.out.println("Created game:"); for (int i = 0; i < 9; i++) { for (int j
				 * = 0; j < 9; j++) {
				 * 
				 * System.out.print(game.getGame()[i][j]); } System.out.println(""); }
				 */
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
				System.out.println("Show Solution");
			}
		});
		buttonPad.getHelpButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!game.isHelp()) {
					game.setHelp(true);
					System.out.println("Help on");
				} else if (game.isHelp()) {
					game.setHelp(false);
					System.out.println("Help off");
				}

			}
		});
		buttonPad.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Input Submitted");
			}
		});
		for (int i = 0; i < 9; i++) {//changes displayed number for selected cell
			Integer number = Integer.parseInt(buttonPad.getKeypadNumbers()[i].getText());
			buttonPad.getKeypadNumbers()[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < 9; j++)
						for (int k = 0; k < 9; k++) {
							if (sudokuBoard.getCells()[j][k].isSelected() && sudokuBoard.getCells()[j][k].isAccessible())

							{
								//need to figure out how to use this with setValue in Cell class
								sudokuBoard.getCells()[j][k].setValue(number, true);
							}
						}
				}
			});
		}

	}

}
