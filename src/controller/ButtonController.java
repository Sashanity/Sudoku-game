package controller;

/**

 * Controller class for the Button class
 * event handler
 * @author Aleksandra, Ben, Jefferson
 */
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import model.*;
import view.*;

public class ButtonController {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;
	private View sudoku;
	private LinkedList<Valve> valves = new LinkedList<Valve>();
	private BlockingQueue<Message> queue;

	public ButtonController(View sudoku, Game game, BlockingQueue<Message> queue) throws Exception {
		this.sudoku = sudoku;
		this.game = game;
		this.queue = queue;
		sudokuBoard = sudoku.getBoard();
		sudokuBoard.setClues(game); // sets the clues on the board
		buttonPad = sudoku.getButtonPad();
		sudokuBoard.addMouselisteners(game);
		buttonPad.addActionlisteners(game);
		mainLoop();
	}

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
	 *
	 */
	private class DoNewGameValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
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

	/*
	 * Shows the games solution on the board
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

	/*
	 * 
	 * Toggles help on or off Removes 3 points from score everytime help is used
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
