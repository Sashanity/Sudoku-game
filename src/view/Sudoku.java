package view;
/**
 * Creates entry window 
 * adds and sets up all the components in the main window
 * components such game, buttons, board, etc
 * 
 * @author Aleksandra, Ben, Jefferson
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.ButtonController;
import controller.SudokuController;
import model.Game;

public class Sudoku extends JFrame {

	// add everything such creating the window, board, buttons etc
	// add visability

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Sudoku();

	}

	public Sudoku() {

		super("SUDOKU");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		Game game = new Game();

		ButtonPad buttonPanel = new ButtonPad();
		SudokuBoard sudokuBoard = new SudokuBoard();

		ButtonController buttonController = new ButtonController(buttonPanel, game, sudokuBoard);

		buttonController.update();
		buttonPanel.getNewGameButton().doClick();

		SudokuController sudokuController = new SudokuController(sudokuBoard, game, buttonPanel);
		sudokuController.update();

		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);

		game.addObserver(buttonPanel);
		game.addObserver(sudokuBoard);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

}