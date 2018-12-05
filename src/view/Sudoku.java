package view;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import controller.ButtonController;
import model.EasyGame;
import model.Game;
import model.HardGame;

/**
 * Class used for Sudoku game, combines views and controllers, uses queue to receive messages and update game
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
		String[] options = {"easy", "hard"};
		int choice = JOptionPane.showOptionDialog(null, "Difficulty level",
                "Choose Level of Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (choice==0)
			game = new EasyGame();
		else
			game = new HardGame();
		ButtonController controller = new ButtonController(sudoku, game, queue);
		queue.clear();
	}
}
