package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
/**

 * Controller class for the Button class
 * event handler
 * @author Aleksandra, Ben, Jefferson
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import model.*;
import view.*;

public class ButtonController {
	private SudokuBoard sudokuBoard;
	private ButtonPad buttonPad;
	private Game game;
	private Sudoku sudoku;
	private LinkedList<Valve> valves = new LinkedList<Valve>();
	private BlockingQueue<Message> queue;

	public ButtonController(Sudoku sudoku, Game game, BlockingQueue<Message> queue) throws Exception {
		this.sudoku = sudoku;
		this.game = game;
		this.queue = queue;
		sudokuBoard = sudoku.getBoard();
		sudokuBoard.setClues(game); //sets the clues on the board
		buttonPad = sudoku.buttonPanel;
		this.update();
		mainLoop();
	}
	public BlockingQueue<Message> getQueue(){
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

	private interface Valve {
		enum ValveResponse {
			MISS, EXECUTED, FINISH;
		}

		public ValveResponse execute(Message message);

	}

	/**
	 * Creates new game
	 * @author Benjamin
	 *
	 */
	private class DoNewGameValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			game.newGame();//creates a new game
			game.setStartTime(System.currentTimeMillis());//resets the game start time to
			sudokuBoard.setClues(game);//sets the view to display the clues on sudokoboard
			System.out.println("DoNewGameValve Executed");
			return ValveResponse.EXECUTED;
		}
	}
	/**
	 * Exits the game
	 * @author Benjamin
	 *
	 */
	private class ExitGameValve implements Valve {
		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != ExitGameMessage.class) {
				return ValveResponse.MISS;
			}
			System.out.println("EXIT");
			System.exit(0);//exits the game
			System.out.println("ExitGameValve Executed");
			return ValveResponse.FINISH;//mainloop now finished
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
	 * Toggles help on or off
	 * Removes 3 points from score everytime help is used 
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
	/*
	 * gives mouse listeners to the sudoku board and actionlisteners to the buttonpad
	 * May need to refactor these into the view class somehow
	 */
	public void update() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++)
				sudokuBoard.getCells()[y][x].addMouseListener(new Handler(game));
		}
		// adds listeners to the number buttons
		for (int i = 0; i < 9; i++)
			buttonPad.getKeypadNumbers()[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					game.setUserInput(Integer.parseInt(e.getActionCommand()));

				}

			});
	}

	class Handler implements MouseListener {
		private Game game;

		public Handler(Game game) {
			this.game = game;
			// System.out.println("MouseListener attached");
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			JLabel aLabel = (JLabel) e.getSource();
			Component component = aLabel.getComponentAt(e.getPoint());
			if (component instanceof Cell) {
				Cell aCell = (Cell) component;
				int r = aCell.getCellX();
				int c = aCell.getCellY();
				if (game.getValue(r, c) == 0 || aCell.getForeground().equals(Color.BLUE)) {
					System.out.println("Cell " + r + " " + c + " can be modified");

					System.out.println(game.getUserInput());

					game.setValue(game.getUserInput(), c, r);// sets values to the game array
					aCell.setValue(game.getUserInput(), true);
				} else {

					System.out.println("Cell " + r + " " + c + " cannot be modified");
					System.out.println("Value: " + game.getValue(r, c));
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
