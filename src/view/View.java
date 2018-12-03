package view;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;

public class View extends JFrame {

	// add everything such creating the window, board, buttons etc
	// add visibility
	private ButtonPad buttonPanel;
	private SudokuBoard sudokuBoard;

	public View(BlockingQueue<Message> queue) {

		super("SUDOKU");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		buttonPanel = new ButtonPad(queue);
		sudokuBoard = new SudokuBoard();

		buttonPanel.getNewGameButton().doClick();

		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public ButtonPad getButtonPad() {
		return buttonPanel;
	}

	public SudokuBoard getBoard() {
		return sudokuBoard;
	}

}