package model;

import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

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
	long startTime;
	long endTime;
	int totalMistakes = 0;

	private boolean help;
	private int score;

	/**
	 * Constructor
	 * starts help boolean as false
	 * 
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
	/**
	 * 
	 * @return the 2-d array of the current game
	 */
	public int[][] getGameArray() {
		return this.game;
	}

	/**
	 * Sets the 2d array of game to the current game array that is to be played
	 * @param array the new 2d game array
	 */
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
	/**
	 * 
	 * @return the boolean value of the help instance variable
	 */
	public boolean isHelp() {
		return help;
	}
	/**
	 * 
	 * @return the 2d array of the game's solution
	 */
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
	public void setStartTime(long n) {
		startTime = n;
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
	
	//Calculates the number of mistakes on a particular board upon call
	public int calcMistakes() { 
		int mistakes  = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(game[i][j] != 0 && game[i][j] != solution[i][j]) {
					mistakes++;
				}
			}
		}
		return mistakes;
	}

	//Checks if all cells in board are correct
	public boolean checkBoard() {
		boolean gameFinished = true;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(game[i][j] != solution[i][j]) {
					gameFinished = false;
				}
			}
		}
		return gameFinished;
	}
	public void score() {
		endTime = System.currentTimeMillis();
		//Tracks time taken
		long timeTaken = endTime - startTime;
		int seconds = (int) (timeTaken / 1000) % 60 ;
	    int minutes = (int) ((timeTaken / (1000*60)) % 60);
	    int hours = (int) ((timeTaken / (1000*3600)) % 60);
		//Tracks the total number of mistakes
		totalMistakes = totalMistakes + calcMistakes();
		
		//Calculates the number of mistakes
		int boardMistakes  = calcMistakes();
		//Message that displays when board isn't solved
		String scoreDetails = "Sorry! Unfortunately this is not the solution!" + "\n" + 
				"The Sudoku board is not fully filled out or has incorrect cells!" + "\n" + "\n"
				+ "Mistakes on board: " + boardMistakes + "\n" + "\n" + "Time Elapsed(hours/mins/secs): " +
				String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);  
		//Message that displays when board is solved
		String scoreDetailsCompleted = "Congratulations on completing the Sudoku Board!" + "\n"
				+ "Number of mistakes made: " + totalMistakes + "\n" + "Score: " + score +
				"\n" + "\n" + "Time Elapsed(hours/mins/secs): " +
				String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
		
		//Creates the alert window, upon button press
		if(checkBoard() == true) {
			JOptionPane.showMessageDialog(null, scoreDetailsCompleted, "Score", JOptionPane.PLAIN_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, scoreDetails, "Score", JOptionPane.PLAIN_MESSAGE);
			score = score - totalMistakes;
		}
	}

}