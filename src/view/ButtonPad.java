package view;

/**
 * Instance of the class is an object that includes 2 panels with
 * Main game buttons and Keypad buttons.
 *  
 * @author Aleksandra, Ben, Jefferson
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import messages.ExitGameMessage;
import messages.HelpMessage;
import messages.Message;
import messages.NewGameMessage;
import messages.SolutionMessage;
import messages.SubmitGameMessage;
import model.Game;

public class ButtonPad extends JPanel {
	private JButton solutionButton, newGameButton, exitButton, submitButton;
	private JCheckBox helpButton;
	private ButtonGroup keypad;
	private JToggleButton[] keypadNumbers;
	public BlockingQueue<Message> queue;

	/**
	 * Creates button pad

	 * @param queue takes messages that will result in updates in the game =======
	 * @param queue takes messages that will result in updates in the game
	 * 
	 */
	public ButtonPad(BlockingQueue<Message> queue) {
		super(new BorderLayout());
		this.queue = queue;
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS));
		add(aPanel, BorderLayout.NORTH);

		JPanel panelGameOptions = new JPanel();
		panelGameOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
		aPanel.add(panelGameOptions);

		// ------------------
		// MAIN BUTTONS OF THE GAME
		// ------------------
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(100, 30));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					queue.put(new NewGameMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		solutionButton = new JButton("Solution");
		solutionButton.setPreferredSize(new Dimension(100, 30));
		solutionButton.setToolTipText("See the solution!");
		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SolutionMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		});

		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(100, 30));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new ExitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}

		});

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(100, 30));
		submitButton.setToolTipText("Check if your input is correct!");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					queue.put(new SubmitGameMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}

		});

		panelGameOptions.add(newGameButton);
		panelGameOptions.add(solutionButton);
		panelGameOptions.add(submitButton);
		panelGameOptions.add(exitButton);

		helpButton = new JCheckBox("Help", false);
		helpButton.setBackground(Color.green);
		helpButton.setPreferredSize(new Dimension(75, 30));
		helpButton.setToolTipText("Toggle on to see which cells are correct/incorrect");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					queue.put(new HelpMessage());
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}

		});
		panelGameOptions.add(helpButton);

		// ------------------
		// KEY BUTTONS
		// ------------------
		JPanel panelNumbers = new JPanel(new GridLayout(3, 3));
		panelNumbers.setPreferredSize(new Dimension(200, 400));
		aPanel.add(panelNumbers);
		keypad = new ButtonGroup();
		keypadNumbers = new JToggleButton[9];
		for (int i = 0; i < 9; i++) {
			keypadNumbers[i] = new JToggleButton("" + (i + 1));
			keypadNumbers[i].setPreferredSize(new Dimension(50, 50));
			keypad.add(keypadNumbers[i]);
			panelNumbers.add(keypadNumbers[i]);
		}
	}

	/**
	 * Action listeners that will track actions performed and update model
	 * 
	 * @param game the current game being played 
	 * @param game the current game being played 
	 */
	public void addActionlisteners(Game game) {
		for (int i = 0; i < 9; i++)
			this.getKeypadNumbers()[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.setUserInput(Integer.parseInt(e.getActionCommand()));

				}
			});
	}

	/**
	 * Gets solution button
	 * 
	 * @return solution button
	 */
	public JButton getSolutionButton() {
		return solutionButton;
	}

	/**
	 * Gets new game button
	 * 
	 * @return new game button
	 */
	public JButton getNewGameButton() {
		return newGameButton;
	}

	/**
	 * Gets exit button
	 * 
	 * @return exit button
	 */
	public JButton getExitButton() {
		return exitButton;
	}

	/**
	 * Gets help check box button
	 * 
	 * @return help check box
	 */
	public JCheckBox getHelpButton() {
		return helpButton;
	}

	/**
	 * Gets key pad
	 * 
	 * @return key pad
	 */
	public ButtonGroup getKeypad() {
		return keypad;
	}

	/**
	 * Gets submit button
	 * 
	 * @return submit button
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}

	/**
	 * Gets keypad numbers
	 * 
	 * @return keypad numbers
	 */
	public JToggleButton[] getKeypadNumbers() {
		return keypadNumbers;
	}

}
