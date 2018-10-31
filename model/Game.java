package model;

import java.util.Random;

/**
 * Generates a new sudoku solution
 * user input
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class Game {
	
	private int[][] solution; //pre-defined 2-D array solution
	private int[][] game; //current game; created after shuffling solution;
	
	private boolean help;
	private int score;
	
	
	/**
	 * Constructor
	 */
	public Game(){
		newGame();
		help = false;
	}
	
	/**
	 * creates a new solvable board solution by shuffling the predefined one
	 */
	/**
	 *  Creating the solved sudoku: 
	 *  shift the row above to the left by 3 unless
	 *  its vertical index is equally divisible by 3 (starting with index 0) 
	 *  in which case shift the row above by 4.
	 *  
	 *  then sends this boards to shuffle
	 */
	public void newGame(){

	}
	
	
	public int[][] shuffleGame(int[][] aGame){
		Random random = new Random();
		//int x = random.nextInt(3)
		return null;
	}
	
	
	/**
	 * checks the user input with the correct solution
	 */
	public void checkCurrentGame(){
		
	}
	
	/**
	 * Sets up the value of the cell from user
	 * @param aValue is the number to set to a selected position 
	 * @param x X position of cell
	 * @param y Y position of the cell
	 */
	public void setValue(int aValue, int x, int y){
		
	}
	

	/**
	 * Change the help flag true/false
	 */
	public void setHelp(){
		
	}
	
	/**
	 * Checks if user asked for help
	 * @return the value of help
	 */
	public boolean isHelp(){
		return help;
	}
	
	/**
	 * Clones the predefined solution to the tmp 2D array
	 * @param solution is predefined 2D array
	 * @return returns copy of the predefined solution
	 */
	public int[][] cloneSolution(int[][] solution){
		int[][] newGame = null;
		for (int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				newGame[i][j] = solution[i][j];
				
		return newGame;
		
	}
	


}
