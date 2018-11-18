package view;

import model.Game;
import model.SolutionCreator;

public class myTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		SolutionCreator solution = new SolutionCreator();
		System.out.println("");
		System.out.println("Solution for the game:");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				System.out.print(solution.getSolution()[i][j]);
			}
			System.out.println("");
		}*/
		
		
		Game myGame = new Game();
		System.out.println("Created game:");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				System.out.print(myGame.getGame()[i][j]);
			}
			System.out.println("");
		}
		

	}

}
