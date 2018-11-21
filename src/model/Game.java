package model;

import java.util.Observable;
import java.util.Random;

import view.Sudoku;
import view.SudokuBoard;

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
	private int userInput;
	private boolean[][] helpArray;

	private boolean help;
	private int score;

	/**
	 * Constructor
	 */

	public Game() {
		game = new int[SIZE][SIZE];
		newGame();
		help = false;
		helpArray = new boolean[SIZE][SIZE];

	}

	public void newGame() {

		this.solution = new SolutionCreator().getSolution();

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
			// System.out.println("Chosen number " + i + ": " + "solution[" + randRow + "]["
			// + randCol + "] = " + value);
			if (game[randRow][randCol] == 0)
				setValue(value, randRow, randCol);
			else {
				// System.out.println("Number solution[" + randRow + "][" + randCol + "] = " +
				// value + " was already chosen");
				i--;
			}

		}
		System.out.println();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(this.getGameArray()[i][j]);
			}
			System.out.println("");
		}

	}

	public int[][] getGameArray() {
		return this.game;
	}

	public void setGameArray(int[][] array) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				game[i][j] = array[i][j];

			}

		}
	}

	public int getValue(int x, int y) {
		return game[y][x];
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
		setChanged();
		System.out.println();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(this.getGameArray()[i][j]);
			}
			System.out.println("");
		}

	}

	/**
	 * Change the help flag true/false
	 */
	public void setHelp(boolean help) {
		this.help = help;

	}

	public boolean isHelp() {
		return help;
	}

	public int[][] getSolution() {
		return solution;
	}

	public void setUserInput(int keyPadNum) {
		this.userInput = keyPadNum;

	}

	public int getUserInput() {
		return userInput;
	}

	public boolean[][] getHelpArray() {
		return helpArray;
	}

	/**
	 * Checks if user asked for help
	 * 
	 * @return the value of help
	 */

	
	//creates boolean helparray
	public void gameCheck() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) {
				if (game[i][j] == 0)
					helpArray[i][j] = true;
				else
					helpArray[i][j] = (game[i][j] == solution[i][j]);
			}

	}



}