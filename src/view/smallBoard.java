package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class smallBoard extends JPanel {

		private JTextField[] inputfields;

		public smallBoard() {
			setLayout(new GridLayout(3, 3, 2, 2));
			inputfields = new JTextField[3 * 3];
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					int index = (row * 3) + col;
					JTextField field = new JTextField(4);
					inputfields[index] = field;
					super.add(field);
				}
			}
		}
	}