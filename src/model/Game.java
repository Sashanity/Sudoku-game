package model;

import java.util.Observable;
import java.util.Random;

/**
 * Generates a new sudoku solution user input
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */

public class Game extends Observable {
	static final int SIZE = 9;
	static final int MIN_NUM_CLUES = 17;
	private int[][] solution;
	private int[][] game; // current game; created after shuffling solution;
	// private int[][] userInput;
	private boolean[][] checkGame;

	private boolean help;
	private int score;

	/**
	 * Constructor
	 */
	public Game() {
		game = new int[SIZE][SIZE];
		game = newGame();

		help = false;
		// userInput = new int[SIZE][SIZE];
	}

	public int[][] newGame() {
		SolutionCreator aSolution = new SolutionCreator();
		this.solution = aSolution.getSolution();
		/*
		 * System.out.println("Solution for the game:"); for (int i = 0; i < 9; i++) {
		 * for (int j = 0; j < 9; j++) {
		 * 
		 * System.out.print(solution[i][j]); } System.out.println(""); }
		 */
		int randCol, randRow, value;

		// first zero the game
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				this.game[i][j] = 0;

		// add clues
		for (int i = 0; i < MIN_NUM_CLUES; i++) {
			randRow = new Random().nextInt(SIZE);
			randCol = new Random().nextInt(SIZE);

			value = solution[randRow][randCol];
			// System.out.println("Chosen number "+ i +": " + "solution["+
			// randRow+"]["+randCol+"] = " +value);
			setValue(value, randRow, randCol);
		}

		return getGame();

	}

	public int[][] getGame() {
		return this.game;
	}

	/**
	 * checks the user input with the correct solution
	 */
	public void checkCurrentGame() {

	}

	/**
	 * Sets up the value of the cell from user
	 * 
	 * @param aValue is the number to set to a selected position
	 * @param x      X position of cell
	 * @param y      Y position of the cell
	 */
	public void setValue(int aValue, int r, int c) {
		game[r][c] = aValue;

	}

	/**
	 * Change the help flag true/false
	 */
	public void setHelp(boolean help) {
		this.help = help;
	}

	/**
	 * Checks if user asked for help
	 * 
	 * @return the value of help
	 */
	public boolean isHelp() {
		return help;
	}

	private void gameCheck() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				checkGame[i][j] = game[i][j] == solution[i][j];
	}

	public void runGameCheck() {
		gameCheck(); // creates boolean array, values are result of comparing of current game to
						// solution

		// notify the observer here to show in colors
	}

}
