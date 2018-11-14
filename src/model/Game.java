package model;

import java.util.Random;

/**
 * Generates a new sudoku solution
 * user input
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class Game {
	static final int SIZE = 9;
	private int[][] solution; //pre-defined 2-D array solution
	private int[][] game; //current game; created after shuffling solution;
	private int[][] userInput;
	
	private boolean help;
	private int score;
	
	
	/**
	 * Constructor
	 */
	public Game(){
		solution = newGame(); // gets new solution board
		help = false;
		userInput = new int[SIZE][SIZE];
	}
	
	
	public int[][] newGame(){
		
		SolutionCreator aSolution = new SolutionCreator();
		return aSolution.getSolution();
		

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
