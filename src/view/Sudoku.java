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

public class Sudoku extends JFrame{

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
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sudokuBoard.getCells()[i][j].setValue(game.getGame()[i][j], false);
				sudokuBoard.getSolution()[i][j].setValue(game.getSolution()[i][j], false);
				if(game.getGame()[i][j] != 0)
				{
					sudokuBoard.getCells()[i][j].accessible = false;
					sudokuBoard.getCells()[i][j].setBackground(Color.ORANGE);
				}
			}
		}
		
		ButtonController buttonController = new ButtonController(buttonPanel, game, sudokuBoard, this);
		buttonController.update();
		
	
	
		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);

		game.addObserver(buttonPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
