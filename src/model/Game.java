package model;

/**
 * An abstract class of the Model.
 * Instance of this class is a current game situation
 * It has current 2D array with current game, 
 * reference to 2D solution array
 * keeps track of score
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */

import javax.swing.JOptionPane;

public abstract class Game {
	static final int SIZE = 9; 				// standard sudoku size
	static final int MIN_NUM_CLUES = 17; 	// minimal valid number of clues to set of on the board
	private int[][] solution; 				// 2D solved sudoku
	private int[][] game;					// currernt game array
	private int userInput; 					// number that user chose from the Keypad
	private boolean[][] helpArray; 			// boolean array holds corresponding values if the game elements are identical
											// to solution
	private boolean help; 					// boolean help flag on/off
	private int difficulty; 				// level of difficulty (0 or 1)

	// these fileds are used to calculate score
	long startTime;
	long endTime;
	int totalMistakes = 0;

	int score = 1000;

	/**
	 * Default constructor
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

		// first zero the game array
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				this.game[i][j] = 0;

		addClues(solution);

	}

	/**
	 * 
	 * @param aSolution 2D int array solution for the udoku
	 */
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

	/**
	 * Gets a number from the game array at given position
	 * 
	 * @param x column
	 * @param y row
	 * @return a number at given position
	 */
	public int getValue(int x, int y) {
		return game[y][x];
	}

	/**
	 * Sets up the value to a certain position of game array
	 * 
	 * @param aValue number to be set
	 * @param r      row position of value
	 * @param c      column position of value
	 */
	public void setValue(int aValue, int r, int c) {
		game[r][c] = aValue;
	}

	/**
	 * Sets the help flag true/false
	 * 
	 * @param help boolean value of the help to be set
	 */
	public void setHelp(boolean help) {
		this.help = help;

	}

	/**
	 * getter for help value
	 * 
	 * @return the boolean value of the help instance variable
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * getter for the solution array
	 * 
	 * @return the 2d array of the game's solution
	 */
	public int[][] getSolution() {
		return solution;
	}

	/**
	 * Sets a number that was chosen from a keypad.
	 * 
	 * @param keyPadNum Number that user selects from the button key pad
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
	 * Sets the booleaan HelpArray based on the values of the game array
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
	 * @return A boolean determining whether or not the cells on the board are
	 *         correct
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

	/**
	 * 
	 * @return difficulty level of the game (0 or 1)
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * 
	 * @param difficulty difficulty level of the game
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}