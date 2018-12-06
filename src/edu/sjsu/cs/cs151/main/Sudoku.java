package edu.sjsu.cs.cs151.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import edu.sjsu.cs.cs151.controller.Controller;
import edu.sjsu.cs.cs151.model.EasyGame;
import edu.sjsu.cs.cs151.model.Game;
import edu.sjsu.cs.cs151.model.HardGame;
import edu.sjsu.cs.cs151.view.View;
import edu.sjsu.cs.cs151.view.messages.Message;

/**
 * Class used for Sudoku game, combines views and controllers, uses queue to
 * receive messages and update game
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class Sudoku {

	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private static View sudoku; // view
	private static Game game; // model

	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		sudoku = new View(queue);
		String[] options = { "Easy", "Hard" };
		int choice = JOptionPane.showOptionDialog(null, "Difficulty level", "Choose Level of Difficulty",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (choice == 0)
			game = new EasyGame();
		else
			game = new HardGame();
		game.setStartTime(System.currentTimeMillis());
		Controller controller = new Controller(sudoku, game, queue);
		queue.clear();
	}
}
