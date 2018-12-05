package model;

import java.util.Observable;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Generates a new sudoku solution user input
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public abstract  class Game  {
	static final int SIZE = 9;
	static final int MIN_NUM_CLUES = 17;
	private int[][] solution;
	private int[][] game; // current game; created after shuffling solution;
	private int userInput;
	private boolean[][] helpArray;
	private int difficulty;
	long startTime;
	long endTime;
	int totalMistakes = 0;

	private boolean help;
	int score = 1000;

	/**
	 * Constructor starts help boolean as false
	 * 
	 */
	public Game() {
		game = new int[SIZE][SIZE];
		newGame();
		help = false;
		helpArray = new boolean[SIZE][SIZE];
		
		}

	/** 
	 * Method that creates a new game. Generates new values for sudoku board.
	 * 
	 */
	public void newGame() {
		this.solution = new SolutionCreator().getSolution();
		int randCol, randRow, value;
		// first zero the game
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				this.game[i][j] = 0;
		// add clues
		addClues(solution);
		/*
		for (int i = 0; i < MIN_NUM_CLUES; i++) {
			randRow = new Random().nextInt(SIZE);
			randCol = new Random().nextInt(SIZE);
			value = solution[randRow][randCol];
			if (game[randRow][randCol] == 0)
				setValue(value, randRow, randCol);
			else {
				i--;
			}
		}
		System.out.println();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(this.getGameArray()[i][j]);
			}
			System.out.println("");
		}*/
	}
	
	abstract public void addClues(int[][] aSolution);

	/**
	 * 
	 * @return the 2-d array of the current game
	 */
	public int[][] getGameArray() {
		return this.game;
	}

	/**
	 * Sets the 2d array of game to the current game array that is to be played
	 * 
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

	/**
	 * Method to set a cell based on the users input
	 * 
	 * @param keyPadNum Value that user selects from the button key pad
	 */
	public void setUserInput(int keyPadNum) {
		this.userInput = keyPadNum;

	}
	
	/**
	 * Gets the input of the user
	 * 
	 * @return The input of the user
	 */
	public int getUserInput() {
		return userInput;
	}

	/**
	 * Array used to tell whether or not a cell is to be marked when help is applied
	 * 
	 * @return The help array
	 */
	public boolean[][] getHelpArray() {
		return helpArray;
	}

	/**
	 * Sets the start time of the application/game
	 * 
	 * @param n The starting time of the application/game
	 */
	public void setStartTime(long n) {
		startTime = n;
	}

	/**
	 * Checks if user asked for help, creates the boolean help array
	 * 
	 * @return the value of help
	 */
	public void gameCheck() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) {
				if (game[i][j] == 0)
					helpArray[i][j] = true;
				else
					helpArray[i][j] = (game[i][j] == solution[i][j]);
			}

	}

	/**
	 * Calculates the number of mistakes on a particular board upon call
	 * 
	 * @return The number of mistakes on a particular board when method is called
	 */
	public int calcMistakes() {
		int mistakes = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (game[i][j] != 0 && game[i][j] != solution[i][j]) {
					mistakes++;
				}
			}
		}
		return mistakes;
	}

	/**
	 * Checks if all cells in board are correct
	 * 
	 * @return A boolean determining whether or not the cells on the board are correct
	 */
	public boolean checkBoard() {
		boolean gameFinished = true;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (game[i][j] != solution[i][j]) {
					gameFinished = false;
				}
			}
		}
		return gameFinished;
	}

	/**
	 * Removes 3 times from the score. Only use if "help" button is used.
	 */
	public void subtractPoints() {
		score = score - 3;
	}

	/**
	 * resets score to be used for new game;
	 */
	public void resetScore() {
		score = 0;
	}

	/**
	 * When submit button is clicked, calculates the score of the game and displays
	 * it. Takes into consideration the number of times "help" was used and number
	 * of total mistakes. Also displays the time elapsed.
	 */
	public void score() {
		endTime = System.currentTimeMillis();
		// Tracks time taken
		long timeTaken = endTime - startTime;
		int seconds = (int) (timeTaken / 1000) % 60;
		int minutes = (int) ((timeTaken / (1000 * 60)) % 60);
		int hours = (int) ((timeTaken / (1000 * 3600)) % 60);
		// Tracks the total number of mistakes
		totalMistakes = totalMistakes + calcMistakes();

		// Calculates the number of mistakes
		int boardMistakes = calcMistakes();
		// Message that displays when board isn't solved
		String scoreDetails = "Sorry! Unfortunately this is not the solution!" + "\n"
				+ "The Sudoku board is not fully filled out or has incorrect cells!" + "\n" + "\n"
				+ "Mistakes on board: " + boardMistakes + "\n" + "\n" + "Time Elapsed(hours/mins/secs): "
				+ String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":"
				+ String.format("%02d", seconds);
		// Message that displays when board is solved
		String scoreDetailsCompleted = "Congratulations on completing the Sudoku Board!" + "\n"
				+ "Number of mistakes made: " + totalMistakes + "\n" + "Score: " + score + "\n" + "\n"
				+ "Time Elapsed(hours/mins/secs): " + String.format("%02d", hours) + ":"
				+ String.format("%02d", minutes) + ":" + String.format("%02d", seconds);

		// Creates the alert window, upon button press
		if (checkBoard() == true) {
			JOptionPane.showMessageDialog(null, scoreDetailsCompleted, "Score", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, scoreDetails, "Score", JOptionPane.PLAIN_MESSAGE);
			score = score - totalMistakes;
		}
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}