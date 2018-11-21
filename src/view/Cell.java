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
	private int value;
	boolean isSelected;
	boolean accessible = true;

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

		/*
		 * addMouseListener(new MouseAdapter() {// makes background of cell gray to
		 * signify that it is selected public void mouseClicked(MouseEvent event) { if
		 * (getBackground().equals(Color.WHITE)) { setBackground(Color.gray); isSelected
		 * = true; System.out.println(isSelected); } else if (this.equals(Color.GRAY)) {
		 * setBackground(Color.WHITE); isSelected = false;
		 * System.out.println(isSelected); } } });
		 */
	}

	public void setValue(int num, boolean userInput) {
		setForeground(userInput ? Color.BLUE : Color.BLACK);
		setText(num > 0 ? num + "" : "");
		value = num;
	}

	public int getCellX() {
		return x;
	}

	public int getValue() {
		return value;
	}

	public int getCellY() {
		return y;
	}

	/*
	 * public void setSelected(boolean selected) { isSelected = selected; } public
	 * void setAccessible(boolean accessible) { this.accessible = accessible;
	 * 
	 * }
	 * 
	 * public boolean isSelected() {//need to figure out how to make it possible for
	 * only one Cell to be selected at a time
	 * 
	 * return isSelected; } public boolean isAccessible() { return accessible; }
	 */
}
