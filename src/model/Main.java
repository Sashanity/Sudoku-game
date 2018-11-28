package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.UIManager;

import controller.ButtonController;
import view.Message;
import view.Sudoku;

public class Main {
	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private static Sudoku sudoku;
	private static Game game;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		sudoku = new Sudoku(queue);
		game = new Game();
		ButtonController controller = new ButtonController(sudoku, game, queue);
		queue.clear();

	}
}
