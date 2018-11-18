/*package view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Game;
import model.SolutionCreator;
import java.util.ArrayList;

public class smallBoard extends JPanel {

		private JTextField[] inputfields;
		ArrayList<ArrayList> values = new ArrayList<>();
		Game myGame = new Game();
		
		public smallBoard() {
			//Keeps track of the position index
			for (int i = 0; i < 9; i++) {
				//Will hold the numbers for each sudoku block
				ArrayList<Integer> sudokuNums = new ArrayList<>();
				for (int j = 0; j < 9; j++) {
					for (int k = 0; k < 9; k++) {
						//values.add(myGame.getGame()[j][k]);
					}
				}
			}
			
			setLayout(new GridLayout(3, 3, 2, 2));
			inputfields = new JTextField[3 * 3];
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					int index = (row * 3) + col;
					JTextField field = new JTextField(4);
					field.setText("A");
					field.setText("B");
					super.add(field);
				}
			}
		}
	}*/