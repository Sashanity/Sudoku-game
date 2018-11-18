package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SudokuBoard extends JPanel {
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;

	private smallBoard[] subBoards;

	public SudokuBoard() {
		subBoards = new smallBoard[3 * 3];
		setBorder(new EmptyBorder(4, 4, 4, 4));
		setLayout(new GridLayout(3, 3, 2, 2));
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int i = (row * 3) + col;
				smallBoard board = new smallBoard();
				board.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 4), new EmptyBorder(4, 4, 4, 4)));
				subBoards[i] = board;
				add(board);
			}
		}
	}
}
