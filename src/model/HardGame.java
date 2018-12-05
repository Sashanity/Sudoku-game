package model;

import java.util.Random;

public class HardGame extends Game {

	public HardGame() {
		super();
		setDifficulty(1);
	}

	@Override
	public void addClues(int[][] aSolution) {
		int randRow, randCol, value;
		for (int i = 0; i < MIN_NUM_CLUES; i++) {
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
