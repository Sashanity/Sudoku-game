package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JPanel;
import model.Game;

public class SudokuBoard extends JPanel implements Observer {
	public static final int SIZE = 3;
	private Cell[][] cells;
	private Cell[][] solution;
	private JPanel[][] subBoards;

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
				// cells[row][col].setAccessible(true);
				subBoards[row / 3][col / 3].add(cells[row][col]);
			}
		}

	}

	public Cell[][] getCells() {
		return cells;
	}

	public Cell[][] getSolution() {
		return solution;
	}
	/*
	 * public Cell getSelected() { Cell selected = null; for (int i = 0; i < 9; i++)
	 * { for (int j = 0; j < 0; j++) { if (cells[i][j].isSelected) selected =
	 * cells[i][j]; } } return selected; }
	 */

	public void setHelp(Game game) {
		System.out.println("Setting help to board");
		if (game.isHelp() == true) {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++) {
					if (game.getHelpArray()[i][j] == false)
						cells[i][j].setBackground(Color.red);
					else if (!cells[i][j].getBackground().equals(Color.GREEN))
						cells[i][j].setBackground(Color.white);
				}

		}

		else {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if (cells[i][j].getBackground().equals(Color.red))
						cells[i][j].setBackground(Color.white);

		}

		/*
		 * for (int i = 0; i < 9; i++) for (int j = 0; j < 9; j++) { if
		 * (game.getHelpArray()[i][j] == false) { cells[i][j].setBackground(Color.red);
		 * } else if (!cells[i][j].getBackground().equals(Color.GREEN))
		 * cells[i][j].setBackground(Color.white);
		 * 
		 * } if (game.isHelp() == false) { for (int i = 0; i < 9; i++) for (int j = 0; j
		 * < 9; j++) if (!cells[i][j].getBackground().equals(Color.GREEN)) {
		 * cells[i][j].setBackground(Color.white); } }
		 */
	}

	public void setClues(Game game) {
		// this block adds clues to the board

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

	public void setSolution(Game game) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!cells[i][j].getBackground().equals(Color.green)) {
					cells[i][j].setBackground(Color.white);
					// to make the board unmodifiable we need to put solution in the game.
					// thts how it works now

					game.setGameArray(game.getSolution());
					cells[i][j].setValue(game.getGameArray()[i][j], false);

					// cells[i][j].setAccessible(false);
					// cells[i][j].isSelected = false;

				}

			}
		}
	}

	@Override

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}