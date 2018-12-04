package view;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import controller.ButtonController;
import model.Game;

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
		game = new Game();
		ButtonController controller = new ButtonController(sudoku, game, queue);
		queue.clear();
	}
}
