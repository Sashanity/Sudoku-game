package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Game;

public class SudokuBoard extends JPanel {
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;

	//private smallBoard[] subBoards;

	public SudokuBoard() {
		Game myGame = new Game();
		setLayout(new GridLayout(9, 9, 10, 10));
		//setBorder(new EmptyBorder(4, 4, 4, 4));
		JTextField[][] boardText = new JTextField[9][9];
		Font fieldFont = new Font("Comic Sans MS", Font.BOLD, 20);
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				boardText[row][col] = new JTextField();
				String value = Integer.toString(myGame.getGame()[row][col]);
				if(myGame.getGame()[row][col] == 0) {
					boardText[row][col].setText("");
				}
				else {
					boardText[row][col].setText(value);
					boardText[row][col].setEditable(false);
					boardText[row][col].setBackground(Color.green);
				}
				boardText[row][col].setFont(fieldFont);

				//Adds the specific text from a row/col to the JTextField that correlates to it
				super.add(boardText[row][col]);
			}
		}
		
	}
}
