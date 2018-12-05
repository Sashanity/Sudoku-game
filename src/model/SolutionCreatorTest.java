package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionCreatorTest {

	@Test
	void getSolutionTest() {
		SolutionCreator sc = new SolutionCreator();
		sc.getSolution();
		assertNotNull(sc);
	}
	
}
