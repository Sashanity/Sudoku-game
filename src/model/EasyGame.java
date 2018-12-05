package model;

import java.util.Random;

public class EasyGame extends Game {

	public EasyGame() {
		super();
		setDifficulty(0);

	}

	public void addClues(int[][] aSolution) {

		int randRow, randCol, value;
		for (int i = 0; i < MIN_NUM_CLUES + 5; i++) {
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
