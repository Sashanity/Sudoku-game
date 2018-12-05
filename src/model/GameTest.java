package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void test() {
		HardGame newGameTest = new HardGame();
		assertNotNull(newGameTest);
	}

	@Test
	void compareGames() {
		HardGame newGameTest = new HardGame();
		HardGame newGameTest2 = new HardGame();
		assertNotSame(newGameTest, newGameTest2);
	}

	@Test
	void testCalcScore() {
		HardGame newGameTest = new HardGame();
		// One score point is subtracted for each total mistake
		// So for this case, 1000-8=992
		newGameTest.totalMistakes = 8;
		// Opens window to update score, close window to complete test case
		newGameTest.score();
		assertEquals(992, newGameTest.score);
	}

	@Test
	void testScore() {
		HardGame newGameTest = new HardGame();
		newGameTest.resetScore();
		newGameTest.subtractPoints();
		assertEquals(-3, newGameTest.score);
	}

}
