package view;
/**
 * Creates entry window 
 * adds and sets up all the components in the main window
 * components such game, buttons, board, etc
 * 
 * @author Aleksandra, Ben, Jefferson
 */

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import controller.ButtonController;
import controller.SudokuController;
import model.Game;
public class Sudoku extends JFrame {
	
	//add everything such creating the window, board, buttons etc
	//add visability

	public static void main(String[] args) {
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
	    catch (Exception ex) { ex.printStackTrace(); }
	    new Sudoku();
		
		}
	
	public Sudoku() {
        super("SUDOKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        
        ButtonPad buttonPanel = new ButtonPad();//view
       
        
        add(buttonPanel, BorderLayout.NORTH);  
        

        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
		

}
	
	
