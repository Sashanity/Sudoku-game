package edu.sjsu.cs.cs151.model;
/**
 * This is a Model class
 * One of the implementations of the Game class
 * Number of set up to the board is 27
 */

import java.util.Random;

/**
 * Second implementation of the Gaame class
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class EasyGame extends Game {

	/**
	 * Constructor
	 */
	public EasyGame() {
		super();
		setDifficulty(0);

	}

	@Override
	/**
	 * @see Game.java
	 */
	public void addClues(int[][] aSolution) {

		int randRow, randCol, value;
		for (int i = 0; i < MIN_NUM_CLUES + 10; i++) {
			randRow = new Random().nextInt(SIZE);
			randCol = new Random().nextInt(SIZE);
			value = aSolution[randRow][randCol];
			if (getGameArray()[randRow][randCol] == 0)
				setValue(value, randRow, randCol);
			else {
				i--;
			}
		}

	}

}
