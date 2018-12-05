package view;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import controller.Handler;
import model.Game;

/**
 * Class used to create SudokuBoard
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class SudokuBoard extends JPanel {
	public static final int SIZE = 3;
	private Cell[][] cells;
	private Cell[][] solution;
	private JPanel[][] subBoards;

	/**
	 * Creates layout of sudoku board
	 */
	public SudokuBoard() {
		super(new GridLayout(3, 3));
		subBoards = new JPanel[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				subBoards[row][col] = new JPanel(new GridLayout(3, 3));
				if (row + col == 1 || row + col == 3)
					subBoards[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				else
					subBoards[row][col].setBorder(BorderFactory.createLineBorder(Color.gray));
				add(subBoards[row][col]);
			}
		}
		solution = new Cell[9][9];
		cells = new Cell[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				solution[row][col] = new Cell(col, row);
				cells[row][col] = new Cell(col, row);
				subBoards[row / 3][col / 3].add(cells[row][col]);
			}
		}
	}

	/**
	 * Gets the cells for a board
	 * 
	 * @return the cells of a board
	 */
	public Cell[][] getCells() {
		return cells;
	}

	/**
	 * Gets the solution for a board
	 * 
	 * @return the solution of a board
	 */
	public Cell[][] getSolution() {
		return solution;
	}

	/**
	 * Method used for setting help in the board, enables help function
	 * 
	 * @param game
	 *            current game being played
	 */
	public void setHelp(Game game) {
		if (game.isHelp() == true) {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++) {
					if (game.getHelpArray()[i][j] == false)
						cells[i][j].setBackground(Color.red);
					else if (!cells[i][j].getBackground().equals(Color.GREEN))
						cells[i][j].setBackground(Color.white);
				}
		} else {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (cells[i][j].getBackground().equals(Color.red))
						cells[i][j].setBackground(Color.white);
		}
	}

	/**
	 * Adds clues to the board
	 * 
	 * @param game
	 *            current game being played
	 */
	public void setClues(Game game) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cells[i][j].setBackground(Color.white);
				cells[i][j].setValue(game.getGameArray()[i][j], false);
				if (game.getGameArray()[i][j] != 0) {
					cells[i][j].setBackground(Color.green);
				}
			}
		}
	}

	/**
	 * Sets the board to display the solution
	 * 
	 * @param game
	 *            current game being played
	 */
	public void setSolution(Game game) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!cells[i][j].getBackground().equals(Color.green)) {
					cells[i][j].setBackground(Color.white);
					game.setGameArray(game.getSolution());
					cells[i][j].setValue(game.getGameArray()[i][j], false);
				}
			}
		}
	}

	/**
	 * Adds mouse listeners that will listen to user action events
	 * 
	 * @param game
	 *            current game being played
	 */
	public void addMouselisteners(Game game) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++)
				getCells()[y][x].addMouseListener(new Handler(game));
		}
	}
}
