package controller;

import java.awt.Color;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import javax.swing.JOptionPane;

import messages.*;
import model.*;
import view.*;

/**
 * A controller class Changes the View(SudokuBoard,java) and Model(Game.java) of
 * the game
 * 
 * @author Aleksandra, Ben, Jefferson
 */
public class Controller {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;
	private LinkedList<Valve> valves = new LinkedList<Valve>();
	private BlockingQueue<Message> queue;

	/**
	 * Constructor
	 * 
	 * @param sudoku main JFrame of the game sudoku
	 * @param game   current game
	 * @param queue  the queue to hold messages
	 * @throws Exception
	 */
	public Controller(View sudoku, Game game, BlockingQueue<Message> queue) throws Exception {
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
	 * getter of the queue
	 * 
	 * @return a BlockingQueue object
	 */
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	/**
	 * This loop will run while game is running in order to catch messages and send
	 * them through the appropriate valve. This loop will end when the game is
	 * exited.
	 * 
	 * @throws Exception
	 */
	public void mainLoop() throws Exception {
		valves.add(new DoNewGameValve());
		valves.add(new ExitGameValve());
		valves.add(new GetSolutionValve());
		valves.add(new GetHelpValve());
		valves.add(new SubmitGameValve());
		valves.add(new UserInputValve());
		Valve.ValveResponse response = Valve.ValveResponse.EXECUTED;
		Message message = null;
		while (response != Valve.ValveResponse.FINISH) {
			try {
				message = queue.take();// take a message from the queue (there will only be one at a time).
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Valve valve : valves) {// iterates through all valves to find which one the Message will trigger code
										// execution in
				response = valve.execute(message);
				if (response != Valve.ValveResponse.MISS)// if code is executed, go back to the while loop
					break;
			}
		}
	}

	// -----------------------
	// VALVES
	// -----------------------
	/**
	 * Creates new game valve
	 * 
	 */
	private class DoNewGameValve implements Valve {

		/**
		 * (non-Javadoc)
		 * 
		 * @see controller.Valve#execute(messages.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS; // code will not execute
			}
			
			String[] options = { "Easy", "Hard" };
			int choice = JOptionPane.showOptionDialog(null, "Difficulty level", "Choose Level of Difficulty",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if(choice == game.getDifficulty()) {
				game.newGame();
				sudokuBoard.setClues(game);
			}
			else {
				if(choice == 0)
					game = new EasyGame();
				else
					game = new HardGame();
			}
			game.setStartTime(System.currentTimeMillis());// resets the game start time to 0
			buttonPad.addActionlisteners(game);
			sudokuBoard.setClues(game);// sets the view to display the clues on sudokoboard
			return ValveResponse.EXECUTED;
		}
	}
	/**
	 * Exits the game and closes the window.
	 * 
	 */
	private class ExitGameValve implements Valve {

		/**
		 * @param message the message received from queue (non-Javadoc)
		 * 
		 * @see controller.Valve#execute(messages.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != ExitGameMessage.class) {
				return ValveResponse.MISS; // code will not execute
			}
			System.exit(0);
			return ValveResponse.FINISH;// mainloop now finished
		}
	}

	/**
	 * Shows the game's solution on the sudokuboard.
	 * 
	 */
	private class GetSolutionValve implements Valve {
		/**
		 * @param message the message received from queue (non-Javadoc)
		 * 
		 * @see controller.Valve#execute(messages.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != SolutionMessage.class) {
				return ValveResponse.MISS;// code will not execute
			}
			sudokuBoard.setSolution(game);
			game.resetScore();// resets the score to 0 because show solution takes away all possible points
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 * Toggles help on or off. Removes 3 points from score every time help is used.
	 *
	 */
	private class GetHelpValve implements Valve {
		@Override
		/**
		 * @param message the message received from queue (non-Javadoc)
		 * 
		 * @see controller.Valve#execute(view.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != HelpMessage.class) {
				return ValveResponse.MISS;// code will not execute
			}

			if (!game.isHelp()) {// toggle on
				game.setHelp(true);
				//System.out.println("Help on");
				game.gameCheck();
				sudokuBoard.setHelp(game);
				game.subtractPoints();// remove 3 points
			}

			else {// toggle off
				//System.out.println("Help off");
				game.setHelp(false);
				sudokuBoard.setHelp(game);

			}
			return ValveResponse.EXECUTED;
		}

	}

	/**
	 * Submits the game and calls score on the game.
	 * 
	 *
	 */
	private class SubmitGameValve implements Valve {

		/**
		 * @param message the message received from queue (non-Javadoc)
		 * 
		 * @see controller.Valve#execute(messages.Message)
		 */

		public ValveResponse execute(Message message) {
			if (message.getClass() != SubmitGameMessage.class) {
				return ValveResponse.MISS;// code will not execute
			}
			game.score();
			return ValveResponse.EXECUTED;
		}
	}
	private class UserInputValve implements Valve{

		public ValveResponse execute(Message message) {
			if(message.getClass() != UserInputMessage.class) {
				return ValveResponse.MISS; //code will not execute
			}
			UserInputMessage msg = (UserInputMessage) message;
			System.out.print(msg.getCell().getValue()); //print value of givencell
			System.out.print(",  " + game.getValue(msg.getY(), msg.getX())); //print value in the game int[][]
			System.out.print(", " + sudokuBoard.getCells()[msg.getX()][msg.getY()].getValue()); //get value at sudokuBoard Cell[][]
			System.out.println(msg.getCell().getForeground().toString()); //print color of this cell
			if (sudokuBoard.getCells()[msg.getX()][msg.getY()].getValue() == 0 || msg.getCell().getForeground().equals(Color.BLUE)) {
				game.setValue(game.getUserInput(), msg.getX(), msg.getY()); //for some reason had to switch x and y coords between getvalue and setvalue
				//sudokuBoard.getCells()[msg.getX()][msg.getY()].setValue(game.getUserInput(), true);
				msg.getCell().setValue(game.getUserInput(), true);
				System.out.println("changed");
			}
			
			/*if(game.getValue(msg.getY(), msg.getX()) == 0 ||msg.getCell().getForeground().equals(Color.BLUE)) {
				game.setValue(game.getUserInput(), msg.getY(), msg.getX());
				msg.getCell().setValue(game.getUserInput(), true);
				sudokuBoard.getCells()[msg.getX()][msg.getY()].setValue(game.getUserInput(), true);
			}
			*/
			return ValveResponse.EXECUTED;
		}
		
	}

}
