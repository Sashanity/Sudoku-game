package view;

/**

 * Class represents a cell of the 9x9 sudoku board
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Cell extends JLabel {
	private int x;
	private int y;

	public Cell(int x, int y) {
		super("", CENTER);
		this.x = x;
		this.y = y;

		setPreferredSize(new Dimension(50, 50));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		setBackground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		setOpaque(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {//makes background of cell gray to signify that it is selected
			public void mouseClicked(MouseEvent event) {
				setBackground(Color.GRAY);
			}
		});
	}

	public void setValue(int num, boolean userInput) {
		setForeground(userInput ? Color.BLUE : Color.BLACK);
		setText(num > 0 ? num + "" : "");
	}

	public int getCellX() {
		return x;
	}

	public int getCellY() {
		return y;
	}

}
