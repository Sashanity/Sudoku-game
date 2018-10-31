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
	
	/**
	 * Constructor
	 * creates a single cell object
	 * sets up the initial graphic values
	 * @param x X-position of cell
	 * @param y Y-position of cell
	 */
	 public Cell(int x, int y) {
		 super("", CENTER);
		 this.x = x;
         this.y = y;
         
         setPreferredSize(new Dimension(50,50));
         setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
         setOpaque(true);
         setFont(new Font(Font.DIALOG, Font.BOLD, 20));


	    }
	 
	 
	 /**
	  * sets the int value to the cell
	  * and set up the color depending on id help is turned on
	  * @param num a number from user to check
	  * @param userInput check if number is a i=user input or not
	  */
	 public void setValue (int num, boolean userInput){
		 setText(num > 0 ? num + "" : "");
		 setForeground(userInput ? Color.GRAY : Color.BLACK);	 
	 }
	 
	 /**
	  * getter method
	  * @return a positions x of the cell
	  */
	 public int getX(){
		 return x;
	 }
	 
	 /**
	  * getter method
	  * @return a positions y of the cell
	  */
	 public int getY(){
		 return y;
	 }
	 
	 
	 
	 
		      
		         

}
