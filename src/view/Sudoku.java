package view;
/**
 * Creates entry window 
 * adds and sets up all the components in the main window
 * components such game, buttons, board, etc
 * 
 * @author Aleksandra, Ben, Jefferson
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;
import javax.swing.text.View;

import controller.ButtonController;
import controller.SudokuController;
import model.Game;

public class Sudoku extends JFrame {

	// add everything such creating the window, board, buttons etc
	// add visibility
	/**private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private static Sudoku sudoku;
	private static Game game;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		sudoku = new Sudoku();
		game = new Game();
		ButtonController controller = new ButtonController(sudoku, game, queue);
		game.newGame();
		sudoku.dispose();
		queue.clear();

	}
	 * @param queue 
	*/

	private SudokuBoard sudokuBoard;
	public Sudoku(BlockingQueue<Message> queue) {

		super("SUDOKU");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		//Game game = new Game();

		ButtonPad buttonPanel = new ButtonPad(queue);
		sudokuBoard = new SudokuBoard();
		//ButtonController buttonController = new ButtonController(buttonPanel, game, sudokuBoard);

		//buttonController.update();
		buttonPanel.getNewGameButton().doClick();

		//SudokuController sudokuController = new SudokuController(sudokuBoard, game, buttonPanel);
		//sudokuController.update();

		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);

		//game.addObserver(buttonPanel);
		//game.addObserver(sudokuBoard);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	public SudokuBoard getBoard()
	{
		return sudokuBoard;
	}

}