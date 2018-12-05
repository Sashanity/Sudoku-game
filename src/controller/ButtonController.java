package controller;

/**
 * A controller class
 * Changes the View(SudokuBoard,java) and Model(Game.java) of the game
 */
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.*;
import view.*;

public class ButtonController {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;
	private LinkedList<Valve> valves = new LinkedList<Valve>();
	private BlockingQueue<Message> queue;

	/**
	 * 
	 * @param sudoku main JFrame of the game sudoku
	 * @param game   current game
	 * @param queue
	 * @throws Exception
	 */
	public ButtonController(View sudoku, Game game, BlockingQueue<Message> queue) throws Exception {
		this.game = game;
		this.queue = queue;
		sudokuBoard = sudoku.getBoard();
		sudokuBoard.setClues(game); 
		buttonPad = sudoku.getButtonPad();
		sudokuBoard.addMouselisteners(game);
		buttonPad.addActionlisteners(game);
		mainLoop();
	}

	/**
	 * 
	 * @return a BlockingQueue object 
	 */
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public void mainLoop() throws Exception {
		valves.add(new DoNewGameValve());
		valves.add(new ExitGameValve());
		valves.add(new GetSolutionValve());
		valves.add(new GetHelpValve());
		valves.add(new SubmitGameValve());
		Valve.ValveResponse response = Valve.ValveResponse.EXECUTED;
		Message message = null;
		while (response != Valve.ValveResponse.FINISH) {
			try {
				message = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Valve valve : valves) {
				response = valve.execute(message);
				if (response != Valve.ValveResponse.MISS)
					break;
			}
		}
	}

	// VALVES

	/**
	 * Creates new game
	 * 
	 */
	private class DoNewGameValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			System.out.println("NEW GAME");
			String[] options = {"easy", "hard"};
			int choice = JOptionPane.showOptionDialog(null, "Difficulty level",
	                "Choose Level of Difficulty",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			
			if (choice==0 && game.getDifficulty() == 0)
				game.newGame();
			else 
				if (choice==0 && game.getDifficulty() == 1)
					game = new EasyGame();
			else 
				if (choice==1 && game.getDifficulty() == 0)
					game = new HardGame();
			else
				if (choice==1 && game.getDifficulty() == 1)
					game.newGame();
				


				
			game.newGame();// creates a new game
			game.setStartTime(System.currentTimeMillis());// resets the game start time to
			sudokuBoard.setClues(game);// sets the view to display the clues on sudokoboard
			System.out.println("DoNewGameValve Executed");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 * Exits the game
	 * 
	 */
	private class ExitGameValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != ExitGameMessage.class) {
				return ValveResponse.MISS;
			}
			System.out.println("EXIT");
			System.exit(0);// exits the game
			System.out.println("ExitGameValve Executed");
			return ValveResponse.FINISH;// mainloop now finished
		}
	}


	/**
	 * Shows the games solution on the board
	 * 
	 */
	private class GetSolutionValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != SolutionMessage.class) {
				return ValveResponse.MISS;
			}
			sudokuBoard.setSolution(game);
			game.resetScore();
			System.out.println("Show Solution");
			System.out.println("GetSolutionValve Executed");
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 * Toggles help on or off Removes 3 points from score every time help is used
	 *
	 */
	private class GetHelpValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != HelpMessage.class) {
				return ValveResponse.MISS;
			}

			if (!game.isHelp()) {
				game.setHelp(true);
				System.out.println("Help on");
				game.gameCheck();
				sudokuBoard.setHelp(game);
				game.subtractPoints();
			}

			else {
				game.setHelp(false);
				sudokuBoard.setHelp(game);

			}

			System.out.println("GetHelpValve Executed");
			return ValveResponse.EXECUTED;
		}

	}

	/**
	 * 
	 * 
	 *
	 */
	private class SubmitGameValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != SubmitGameMessage.class) {
				return ValveResponse.MISS;
			}
			game.score();
			System.out.println("SubmitGameValve Executed");
			return ValveResponse.EXECUTED;
		}
	}
	

	
}
