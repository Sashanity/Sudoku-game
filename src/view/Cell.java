package view;

/**

 * Class represents a cell of the 9x9 sudoku board
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Cell extends JLabel {

	private int x;
	private int y;

	 public Cell(int x, int y) {
	        super("", CENTER);
	        this.x = x;
	        this.y = y;
	                                                                                 
	        setPreferredSize(new Dimension(50,50));
	        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	        setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	        setOpaque(true);
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
