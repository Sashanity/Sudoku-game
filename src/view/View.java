package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import javax.imageio.ImageIO;
import javax.swing.*;

import messages.Message;

/**
 * Class used for UI of the Game. Main frame class
 * 
 * @author Aleksandra, Ben, Jefferson
 *
 */
public class View extends JFrame {
	private LeftPanel buttonPanel; // LeftPanel will hold all the buttons
	private SudokuPanel sudokuBoard; // SudokuPanel will display the game

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

		buttonPanel = new LeftPanel(queue);
		sudokuBoard = new SudokuPanel();
		sudokuBoard.setQueue(queue);

		// Creating menu bar with how to play options
		JMenuBar aBar = createMenu();

		add(buttonPanel, BorderLayout.WEST);
		add(sudokuBoard, BorderLayout.EAST);
		add(aBar, BorderLayout.NORTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JMenuBar createMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Help");
		JMenuItem rules = new JMenuItem("How to Play");
		JMenuItem buttonInfo = new JMenuItem("User Interface Info");
		JMenuItem scoreHelp = new JMenuItem("Scoring Details");

		menu.add(rules);
		menu.add(buttonInfo);
		menu.add(scoreHelp);
		bar.add(menu);

		// Action listeners for if user clicks on menu item, displays pop up window
		rules.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.sudokuRules, "How to Play Sudoku",
					JOptionPane.INFORMATION_MESSAGE);
		});
		buttonInfo.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.sudokuButtonInfo, "User Interface Information",
					JOptionPane.INFORMATION_MESSAGE);
		});
		scoreHelp.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null, Constants.scoreDeets, "How Scoring Works",
					JOptionPane.INFORMATION_MESSAGE);
		});
		return bar;
	}

	/**
	 * Gets button pad
	 * 
	 * @return button pad
	 */
	public LeftPanel getButtonPad() {
		return buttonPanel;
	}

	/**
	 * Gets board
	 * 
	 * @return board
	 */
	public SudokuPanel getBoard() {
		return sudokuBoard;
	}
}