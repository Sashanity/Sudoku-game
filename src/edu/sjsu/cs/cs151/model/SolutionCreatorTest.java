package edu.sjsu.cs.cs151.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.sjsu.cs.cs151.view.SudokuPanel;

class SolutionCreatorTest {

	@Test
	void getSolutionTest() {
		SolutionCreator sc = new SolutionCreator();
		sc.getSolution();
		assertNotNull(sc);
	}

	@Test
	void setSolutionTest() throws Exception {
		SudokuPanel sudokuBoard = new SudokuPanel();
		HardGame game = new HardGame();
		sudokuBoard.setSolution(game);
		assertArrayEquals(game.getGameArray(), game.getSolution());
	}
}
