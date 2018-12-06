package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import javax.imageio.ImageIO;
import javax.swing.*;
import controller.Constants;
import messages.Message;

/**
 * Class used for UI of the Game
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class View extends JFrame {
	private ButtonPad buttonPanel;
	private SudokuBoard sudokuBoard;

	/**
	 * Construct UI of game, adding different view components together
	 * 
	 * @param queue used to hold messages that will be used to change stuff in the
	 *              game
	 */
	public View(BlockingQueue<Message> queue) {
		super("SUDOKU");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		String icon = "icon.png";
		try {
			setIconImage(ImageIO.read(View.class.getResource(icon)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Adding menu bar with how to play option
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Help");
		JMenuItem rules = new JMenuItem("How to Play");
		JMenuItem buttonInfo = new JMenuItem("User Interface Info");
		JMenuItem scoreHelp = new JMenuItem("Scoring Details");

		menu.add(rules);
		menu.add(buttonInfo);
		menu.add(scoreHelp);
		bar.add(menu);
		add(bar, BorderLayout.NORTH);

		//Action listeners for if user clicks on menu item, displays pop up window
		rules.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.sudokuRules, "How to Play Sudoku", JOptionPane.INFORMATION_MESSAGE);
		});
		buttonInfo.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.sudokuButtonInfo, "User Interface Information",
					JOptionPane.INFORMATION_MESSAGE);
		});
		scoreHelp.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.scoreDeets, "How Scoring Works", JOptionPane.INFORMATION_MESSAGE);
		});

		buttonPanel = new ButtonPad(queue);
		sudokuBoard = new SudokuBoard();
		sudokuBoard.setQueue(queue);

		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Gets button pad
	 * 
	 * @return button pad
	 */
	public ButtonPad getButtonPad() {
		return buttonPanel;
	}

	/**
	 * Gets board
	 * 
	 * @return board
	 */
	public SudokuBoard getBoard() {
		return sudokuBoard;
	}
}