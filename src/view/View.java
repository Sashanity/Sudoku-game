package view;

import java.awt.*;
import java.awt.event.ActionEvent;
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
		
		//Adding menu bar with how to play option
		JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Help");
        JMenuItem rules = new JMenuItem("How to Play");
        JMenuItem buttonInfo = new JMenuItem("Game Rules");
        JMenuItem scoreHelp = new JMenuItem("Scoring Details");
        menu.add(rules);
        menu.add(buttonInfo);
        menu.add(scoreHelp);
        bar.add(menu);
        add(bar, BorderLayout.NORTH);
        
        //Action listener for menu bar that displays message 
        String sudokuRules = "The goal of the game is to fill up the entire board with numbers 1-9." + "\n" + "\n" 
        + "To win the game, a user must input the correct numbers every one of the cells." + "\n" + "\n"
        + "Within a 3x3 cell block, each cell corresponds to one number. "
        + "\n" + "This means that numbers should not be repeated within a cell block." + "\n"
        + "In other words, a cell block has values 1-9 corresponding to each cell. In one block there cannot be two cells with the same value" + 
        "\n" + "\n" + "This rule also applies to rows and columns. This means that within a row or column of the sudoku board, the correct answer will not have a repeated number (1-9)."
        + "\n" + "\n" + "Good luck with the game!";
        String sudokuButtonInfo = "User inteface information:" + "\n" +
        "The 'New Game' button will generate and display a new game." + "\n" +
        "Clicking this button will result in the game score, mistakes, and time elapsed to be reset/cleared." +
        "\n" + "\n" + "The 'Solution' button will display the solution to the current board." + "\n" +
        "Clicking the solution button will result in the score being set to zero, and the game will no longer be continued." +
        "\n" + "\n" + "The 'Submit' button can be used to track your progress!" + "\n"
        + "When pressed a window will pop up displayed time elapsed as well as mistakes on the board if there are incorrect cells." + "\n" +
        "If all cells are filled out correctly, the window will display the score, time elapsed, and mistakes made by the user for the specific game."
        + "\n" + "\n" + "The 'Exit' button will close the game window." + "\n" + 
        "Another way to close the game window is by pressing the red X in the top left corner of the window frame." + "\n" + "\n" +
        "The 'Help' toggle button is used to help users find the cells that are wrong, and the cells that are correct." + "\n" +
        "Correct cells are left as white and incorrect cells are marked as red." + "\n" +
        "Green cells are automatically generated at the beginning of the game and cannot be changed.";
        String scoreDeets = "Scoring works in a very specific manner for this implementation of Sudoku." + "\n" +
        "\n" + "The maximum score a user can have is 1000, while the minimum is 0." + "\n" +
        "When using help, each help toggle results in the user losing 3 points from their final score." + "\n" +
        "Meanwhile, using the submit button results in the user losing 1 point for every mistake made on the board.";
        rules.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, sudokuRules, "How to Play Sudoku", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonInfo.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, sudokuButtonInfo, "User Interface Information", JOptionPane.INFORMATION_MESSAGE);
        });
        scoreHelp.addActionListener((ActionEvent e) -> {
        	JOptionPane.showMessageDialog(null, scoreDeets, "How Scoring Works", JOptionPane.INFORMATION_MESSAGE);
        });
        
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