package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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

		cells = new Cell[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				cells[row][col] = new Cell(col, row);
				subBoards[row / 3][col / 3].add(cells[row][col]);
			}
		}

	}

	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}
