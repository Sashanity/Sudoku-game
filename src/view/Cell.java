package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Class represents a cell of the 9x9 sudoku board
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class Cell extends JLabel {
	private int x;
	private int y;
	private int value;
	boolean isSelected;
	boolean accessible = true;

	/**
	 * Constructor of a cell, settings of a cell
	 * 
	 * @param x
	 *            X coordinate of cell location
	 * @param y
	 *            Y coordinate of cell location
	 */
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
	}

	/**
	 * Sets the value of a cell based on parameters
	 * 
	 * @param num
	 *            Number that the cell will be set to
	 * @param userInput
	 *            Determines whether or not cell should be changed
	 */
	public void setValue(int num, boolean userInput) {
		setForeground(userInput ? Color.BLUE : Color.BLACK);
		setText(num > 0 ? num + "" : "");
		value = num;
	}

	/**
	 * Gets the X coordinate of a cell
	 * 
	 * @return The X coordinate of a cell
	 */
	public int getCellX() {
		return x;
	}

	/**
	 * Gets the value of a cell
	 * 
	 * @return The value of a particular cell
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the Y coordinate of a cell
	 * 
	 * @return The Y coordinate of a cell
	 */
	public int getCellY() {
		return y;
	}
}
