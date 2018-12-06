package edu.sjsu.cs.cs151.view;

import java.awt.Color;

/**
 * Interface holds final constants,so they are seen across all classes
 * @author Aleksandra, Ben, Jefferson
 *
 */
public interface Constants {
	static final Color CLUE_COLOR = Color.GREEN;
	static final Color BASIC_COLOR = Color.WHITE;
	static final Color WRONG_COLOR = Color.RED;

	static final Color USER_COLOR = Color.BLUE;
	static final Color COMP_COLOR = Color.BLACK;

	static final String sudokuRules = "The goal of the game is to fill up the entire board with numbers 1-9." + "\n"
			+ "\n" + "To win the game, a user must input the correct numbers every one of the cells." + "\n" + "\n"
			+ "Within a 3x3 cell block, each cell corresponds to one number. " + "\n"
			+ "This means that numbers should not be repeated within a cell block." + "\n"
			+ "In other words, a cell block has values 1-9 corresponding to each cell. In one block there cannot be two cells with the same value"
			+ "\n" + "\n"
			+ "This rule also applies to rows and columns. This means that within a row or column of the sudoku board, the correct answer will not have a repeated number (1-9)."
			+ "\n" + "\n" + "Good luck with the game!";
	static final String sudokuButtonInfo = "User interface information:" + "\n"
			+ "To input a number into a cell, first select a number from the keypad." + "\n"
			+ "While a number from the keypad is selected, left click on a cell to input the selected number." + "\n"
			+ "To clear a cell, right click the desired cell and it will become a blank cell." + "\n" + "\n"
			+ "The 'New Game' button will generate and display a new game." + "\n"
			+ "Clicking this button will result in the game score, mistakes, and time elapsed to be reset/cleared."
			+ "\n" + "\n" + "The 'Solution' button will display the solution to the current board." + "\n"
			+ "Clicking the solution button will result in the score being set to zero, and the game will no longer be continued."
			+ "\n" + "\n" + "The 'Submit' button can be used to track your progress!" + "\n"
			+ "When pressed a window will pop up displayed time elapsed as well as mistakes on the board if there are incorrect cells."
			+ "\n"
			+ "If all cells are filled out correctly, the window will display the score, time elapsed, and mistakes made by the user for the specific game."
			+ "\n" + "\n" + "The 'Exit' button will close the game window." + "\n"
			+ "Another way to close the game window is by pressing the red X in the top left corner of the window frame."
			+ "\n" + "\n"
			+ "The 'Help' toggle button is used to help users find the cells that are wrong, and the cells that are correct."
			+ "\n" + "Correct cells are left as white and incorrect cells are marked as red." + "\n"
			+ "Green cells are automatically generated at the beginning of the game and cannot be changed.";
	static final String scoreDeets = "Scoring works in a very specific manner for this implementation of Sudoku." + "\n"
			+ "\n" + "The maximum score a user can have is 1000, while the minimum is 0." + "\n"
			+ "When using help, each help toggle results in the user losing 3 points from their final score." + "\n"
			+ "Meanwhile, using the submit button results in the user losing 1 point for every mistake made on the board.";

}
